package ru.job4j.wait;

/**
 * Class User.
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.06.06
 */
public class SimpleLock {

    private long threadID;
    private int lockCount = 0;

    /**
     * Лочим работу
     *
     * @throws InterruptedException
     */
    public synchronized void lock() throws InterruptedException {
        if (lockCount != 0) {
            while (lockCount > 0 && threadID != Thread.currentThread().getId()) {
                this.wait();
            }
        }
        lockCount++;
        threadID = Thread.currentThread().getId();
    }

    /**
     * Разлочиваем работу
     */
    public synchronized void unlock() {
        if (lockCount == 0) {
            throw new IllegalStateException();
        }
        if (threadID == Thread.currentThread().getId()) {
            if (--lockCount == 0) {
                notifyAll();
            }
        }
    }
}
