package ru.job4j.wait;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Class User.
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.06.06
 */
public class SimpleLock {

    long threadID;
    private int lockCount = 0;

    /**
     * Лочим работу
     *
     * @throws InterruptedException
     */
    public synchronized void lock() throws InterruptedException {
        if (lockCount == 0) {
            lockCount++;
            threadID = Thread.currentThread().getId();
        } else if (lockCount > 0 && threadID == Thread.currentThread().getId()) {
            lockCount++;
        } else {
            wait();
            lockCount++;
            threadID = Thread.currentThread().getId();
        }
    }

    /**
     * Разлочиваем работу
     *
     * @throws InterruptedException
     */
    public synchronized void unlock() throws InterruptedException {
        if (lockCount == 0) {
            throw new IllegalStateException();
        }
        lockCount--;
        if (lockCount == 0) {
            notify();
        }
    }
}
