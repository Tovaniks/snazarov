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
    private final Integer core;
    @GuardedBy("lock")
    private Integer count = 0;
    @GuardedBy("lock")
    private volatile boolean stop = false;
    private final Object lock = new Object();

    public SimpleThreadPool() {
        this(Runtime.getRuntime().availableProcessors());
    }

    public SimpleThreadPool(final int core) {
        this.core = core;
        init();
    }

    /**
     * Инициализация двух потоков.
     * Одновременно запускается два потока, один должен перекидывать таски из tasks в threads, а другой должен запускать таски из threads.
     * До тех пор, пока выполняется core-потоков, ни одна таска не может быть запущена.
     */
    private void init() {
        new Thread() {
            @Override
            public void run() {
                synchronized (lock) {
                    while (!stop) {
                        while (tasks.isEmpty() || count >= core) {
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        if (stop) {
                            break;
                        }
                        count++;
                        threads.add(new Thread(tasks.poll()));
                        lock.notifyAll();
                    }
                }

            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                synchronized (lock) {
                    while (!stop) {
                        while (threads.isEmpty()) {
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        if (stop) {
                            break;
                        }
                        exec(threads.remove(0));
                    }
                }
            }
        }.start();
    }

    /**
     * Запускается таска, ожидается ее окончание, почле чего уменьшается счетчик работающих тасок.
     *
     * @param thread таска
     */
    private void exec(final Thread thread) {
        new Thread() {
            @Override
            public void run() {
                thread.start();
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock) {
                    count--;
                    lock.notifyAll();
                }
            }
        }.start();

    }


    /**
     * Добавление новой таски
     *
     * @param job таска
     */
    public void work(Runnable job) {
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
        }
    }

}

