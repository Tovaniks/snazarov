package ru.job4j.wait;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleLockTest {

    @Test
    public void when10ThreadAddThenReturn10() throws InterruptedException {
        Counter counter = new Counter();
        Thread thread1 = new Thread(new Do(counter));
        Thread thread2 = new Thread(new Do(counter));
        Thread thread3 = new Thread(new Do(counter));
        Thread thread4 = new Thread(new Do(counter));
        Thread thread5 = new Thread(new Do(counter));
        Thread thread6 = new Thread(new Do(counter));
        Thread thread7 = new Thread(new Do(counter));
        Thread thread8 = new Thread(new Do(counter));
        Thread thread9 = new Thread(new Do(counter));
        Thread thread10 = new Thread(new Do(counter));
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();
        thread7.start();
        thread8.start();
        thread9.start();
        thread10.start();
        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();
        thread5.join();
        thread6.join();
        thread7.join();
        thread8.join();
        thread9.join();
        thread10.join();

        assertThat(counter.getCount(), is(10));


    }

    class Do implements Runnable {

        private Counter counter;

        public Do(Counter counter) {
            this.counter = counter;
        }

        @Override
        public void run() {
            try {
                counter.add();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class Counter {
        private int count = 0;
        private SimpleLock lock = new SimpleLock();

        void add() throws InterruptedException {
            lock.lock();
            try {
                count++;
            } finally {
                lock.unlock();
            }
        }

        int getCount() {
            return this.count;
        }
    }
}