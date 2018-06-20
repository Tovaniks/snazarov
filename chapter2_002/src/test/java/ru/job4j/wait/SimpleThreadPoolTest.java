package ru.job4j.wait;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SimpleThreadPoolTest {

    private Integer[] massive;

    @Test
    public void testWhenUseTest() throws InterruptedException {
        SimpleThreadPool s = new SimpleThreadPool();
        massive = new Integer[20];
        for (int index = 0; index < 20; index++) {
            s.work(new Go(index));
        }
        Thread.sleep(15000);
        for (int index = 0; index < massive.length; index++) {
            assertThat(index, is(massive[index]));
        }
    }


    @Test
    public void testWhenUseTest2() throws InterruptedException {
        SimpleThreadPool s = new SimpleThreadPool();
        massive = new Integer[20];
        for (int index = 0; index < 20; index++) {
            s.work(new Go(index));
        }
        Thread.sleep(5000);
        s.shutdown();
        boolean result = false;
        for (Integer aMassive : massive) {
            if (aMassive == null) {
                result = true;
                break;
            }
        }
        assertThat(result, is(true));
    }


    private class Go implements Runnable {

        private final int id;

        public Go(final int id) {
            this.id = id;

        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            massive[id] = id;
        }
    }

}