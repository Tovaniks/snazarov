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
    private final Queue<Runnable> tasks = new LinkedBlockingQueue<>();
    private volatile boolean init = false;
    private volatile boolean stop = false;
    private final Object lock = new Object();

    public SimpleThreadPool() {
        this(Runtime.getRuntime().availableProcessors());
    }

    public SimpleThreadPool(final int core) {
        for (int index = 0; index < core; index++) {
            threads.add(new Thread(() -> {
                while (!stop) {
                    while (!tasks.isEmpty()) {
                        tasks.poll().run();
                    }
                }
            }));
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
        if (!stop) {
            tasks.offer(new Thread(job));
        }

    }

    /**
     * Остановка тасок. Запущенные отработают, новые не будут добавлены
     */
    public void shutdown() {
        this.stop = true;
    }

}

