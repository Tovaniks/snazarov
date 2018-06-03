package ru.job4j.wait;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleBlockingQueueTest {

    private Map<Integer, Boolean> map = new ConcurrentHashMap<>();

    @Test
    public void when2ThreadsWorkThenMapValueIsTrue() throws InterruptedException {
        SimpleBlockingQueue queue = new SimpleBlockingQueue();
        Consumer con = new Consumer(queue);
        con.start();
        for (int index = 0; index < 20; index++) {
            map.put(index, false);
        }
        for (Integer index : map.keySet()) {
            Producer prod = new Producer(queue, index);
            prod.start();
            prod.join();
        }
        for (Integer index : map.keySet()) {
            assertThat(map.get(index), is(true));
        }


    }

    private class Consumer extends Thread {
        private final SimpleBlockingQueue queue;

        public Consumer(SimpleBlockingQueue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    map.put((Integer) queue.poll(), true);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    private class Producer extends Thread {
        private final SimpleBlockingQueue queue;
        private final Integer index;

        public Producer(SimpleBlockingQueue queue, Integer index) {
            this.queue = queue;
            this.index = index;
        }

        @Override
        public void run() {
            try {
                queue.offer(index);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

    }

}