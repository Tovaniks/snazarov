package ru.job4j.wait;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

/**
 * SimpleBlockingQueue.
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.06.03
 */
@ThreadSafe
public class SimpleBlockingQueue<E> {

    @GuardedBy("this")
    private Queue<E> queue = new LinkedList<>();
    private int maxSize;

    /**
     * Конструктор, присваивает максимальное кол-во элементов в очереди. По умолчанию 2^31
     */
    public SimpleBlockingQueue() {
        this(Integer.MAX_VALUE);
    }

    /**
     * Максимальное кол-во элементов в очереди
     * @param max максимум
     */
    public SimpleBlockingQueue(Integer max) {
        if (max < 1) {
            throw new IllegalStateException();
        }
        this.maxSize = max;
    }

    /**
     * Добавляем элемент в очередь
     *
     * @param values элемент
     * @throws InterruptedException
     */
    public synchronized void offer(E values) throws InterruptedException {
        while (this.queue.size() == maxSize) {
            this.wait();
        }
        this.notifyAll();
        this.queue.add(values);
    }

    /**
     * Удаляем первый элемент из очереди и возращаем его в качестве результата
     *
     * @return первый элемент внутренней очереди
     * @throws InterruptedException
     */
    public synchronized E poll() throws InterruptedException {
        while (this.queue.isEmpty()) {
            this.wait();
        }
        this.notifyAll();
        return queue.poll();
    }

}
