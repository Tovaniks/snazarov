package ru.job4j.monitor;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserStorageTest {


    private class Add extends Thread {

        private User user;
        private UserStorage storage;

        public Add(UserStorage storage, User user) {
            this.storage = storage;
            this.user = user;
        }

        @Override
        public void run() {
            this.storage.add(user);
        }
    }

    private class Update extends Thread {

        private User user;
        private UserStorage storage;

        public Update(UserStorage storage, User user) {
            this.storage = storage;
            this.user = user;
        }

        @Override
        public void run() {
            this.storage.update(user);
        }
    }

    private class Delete extends Thread {

        private User user;
        private UserStorage storage;

        public Delete(UserStorage storage, User user) {
            this.storage = storage;
            this.user = user;
        }

        @Override
        public void run() {
            this.storage.delete(user);
        }
    }

    private class Transfer extends Thread {

        private int from;
        private int to;
        private int amount;
        private UserStorage storage;

        public Transfer(UserStorage storage, int from, int to, int amount) {
            this.storage = storage;
            this.from = from;
            this.to = to;
            this.amount = amount;
        }

        @Override
        public void run() {
            this.storage.transfer(from, to, amount);
        }
    }


    @Test
    public void whenAddDuplicateThenNotAdded() throws InterruptedException {
        User first = new User(1, "Sergey", 1000);
        User second = new User(2, "Anton", 50);
        UserStorage storage = new UserStorage();
        Add t1 = new Add(storage, first);
        Add t2 = new Add(storage, second);
        Add t3 = new Add(storage, first);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        t3.start();
        t3.join();
        assertThat(storage.toString(),
                is(String.format("{%d=%s, %d=%s}", first.getID(), first.toString(), second.getID(), second.toString())));
    }

    @Test
    public void whenDeleteUserThenDelete() throws InterruptedException {
        User first = new User(1, "Sergey", 1000);
        User second = new User(2, "Anton", 50);
        UserStorage storage = new UserStorage();
        Add t1 = new Add(storage, first);
        Add t2 = new Add(storage, second);
        Delete t3 = new Delete(storage, first);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        t3.start();
        t3.join();
        assertThat(storage.toString(),
                is(String.format("{%d=%s}", second.getID(), second.toString())));
    }

    @Test
    public void whenUpdateThenChange() throws InterruptedException {
        User first = new User(1, "Sergey", 1000);
        User second = new User(2, "Petr", 1000);
        User third = new User(2, "Andrey", 50);
        UserStorage storage = new UserStorage();
        Add t1 = new Add(storage, first);
        Add t2 = new Add(storage, second);
        Update t3 = new Update(storage, third);
        t1.start();
        t2.start();
        t2.join();
        t1.join();
        t3.start();
        t3.join();
        assertThat(storage.toString(),
                is(String.format("{%d=%s, %d=%s}", first.getID(), first.toString(), second.getID(), third.toString())));
    }

    @Test
    public void whenTransferAndNoEnoughMoneyThenNotTransfer() throws InterruptedException {
        User first = new User(1, "Sergey", 100);
        User second = new User(2, "Denis", 0);
        User third = new User(3, "Andrey", 10);
        int before = second.getAmount() + third.getAmount();
        int firstBefore = first.getAmount();
        UserStorage storage = new UserStorage();
        Add t1 = new Add(storage, first);
        Add t2 = new Add(storage, second);
        Add t3 = new Add(storage, third);
        Transfer traid1 = new Transfer(storage, first.getID(), second.getID(), 90);
        Transfer traid2 = new Transfer(storage, first.getID(), third.getID(), 90);
        t1.start();
        t2.start();
        t3.start();
        t2.join();
        t1.join();
        t3.join();
        traid2.start();
        traid1.start();
        traid1.join();
        traid2.join();
        assertThat(firstBefore - first.getAmount(), is(second.getAmount() + third.getAmount() - before));
        assertThat(first.getAmount(), is(10));
    }

}