package ru.igar15.webapp.storage;

import ru.igar15.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume(uuid, "");
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void doSave(Resume r) {
        int insertIndex = size;
        for (int i = 0; i < size; i++) {
            if (storage[i].compareTo(r) > 0) {
                insertIndex = i;
                break;
            }
        }
        if (insertIndex == size) {
            storage[insertIndex] = r;
        } else {
            Resume temp = r;
            for (int i = insertIndex; i < size + 1; i++) {
                Resume innerTemp = storage[i];
                storage[i] = temp;
                temp = innerTemp;
            }
        }
    }

    @Override
    protected void doDelete(int index) {
        for (int i = index; i < size - 1; i++) {
            storage[i] = storage[i + 1];
        }
    }
}