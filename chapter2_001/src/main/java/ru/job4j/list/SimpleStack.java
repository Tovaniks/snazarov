package ru.job4j.list;

public class SimpleStack<T> {

    LinkedArray<T> array = new LinkedArray<>();

    public T poll() {
        return array.removeLast();
    }

    public void push(T value) {
        array.add(value);
    }
}
