package ru.igar15.webapp.storage;

import java.lang.reflect.Array;

public class GenericArrayStorage<T> {
    private T[] storage;
    private int size = 0;

    @SuppressWarnings("unchecked")
    public GenericArrayStorage(Class<T> clazz) {
        this.storage = (T[]) Array.newInstance(clazz, 10000);
    }

    public void save(T element) {
        storage[size++] = element;
    }

    public T get(int index) {
        if (index < size) {
            return storage[index];
        }
        return null;
    }

    public static void main(String[] args) {
        GenericArrayStorage<String> genericArrayStorage = new GenericArrayStorage<>(String.class);
        genericArrayStorage.save("123123");
        genericArrayStorage.save("123124");
        genericArrayStorage.save("123125");
        System.out.println(genericArrayStorage.get(0));
        System.out.println(genericArrayStorage.get(1));
        System.out.println(genericArrayStorage.get(2));
    }
}
