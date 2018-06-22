package ru.job4j.wait;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Class SimpleThreadPool.
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.06.20
 */
@ThreadSafe
public class SimpleThreadPool {


    @GuardedBy("lock")
    private final List<Thread> threads = new LinkedList<>();
    @GuardedBy("lock")
    private final Queue<Runnable> tasks = new LinkedBlockingQueue<>();
    @GuardedBy("lock")
    private volatile boolean init = false;
    @GuardedBy("lock")
    private volatile boolean stop = false;
    private final Object lock = new Object();

    public SimpleThreadPool() {
        this(Runtime.getRuntime().availableProcessors());
    }

    public SimpleThreadPool(final int core) {
        for (int index = 0; index < core; index++) {
            threads.add(new Thread() {
                @Override
                public void run() {
                    while (!stop) {
                        while (tasks.isEmpty() && !stop) {
                            synchronized (lock) {
                                try {
                                    lock.wait();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        if (stop) {
                            break;
                        }
                        Thread thread = new Thread(tasks.poll());
                        thread.start();
                        try {
                            thread.join();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }
            });
        }
    }

    /**
     * Запуск потоков в threads
     */
    private void init() {
        synchronized (lock) {
            this.init = true;
            for (Thread thread : threads) {
                thread.start();
            }
        }
    }


    /**
     * Добавление новой таски
     *
     * @param job таска
     */
    public void work(Runnable job) {
        if (!init) {
            init();
        }
        synchronized (lock) {
            if (!stop) {
                tasks.offer(new Thread(job));
                lock.notifyAll();
            }
        }
    }

    /**
     * Остановка тасок. Запущенные отработают, новые не будут добавлены
     */
    public void shutdown() {
        synchronized (lock) {
            this.stop = true;
            lock.notifyAll();
        }
    }

}

