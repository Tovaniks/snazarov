package ru.job4j.list;

public class SimpleQueue<T> {

    LinkedArray<T> array = new LinkedArray<>();


    public T poll() {
        return array.removeFirst();
    }

    public void push(T value) {
        array.add(value);
    }
}
