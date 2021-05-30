import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;
    private static final String RESUME_NOT_FOUND = "Resume with uuid=%s was not found";

    void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    void save(Resume r) {
        if (r != null) {
            if (getFromStorage(r.uuid) != null) {
                System.out.println("Resume already exists");
            } else if (size == storage.length) {
                System.out.println("Storage is full");
            } else {
                storage[size++] = r;
            }
        }
    }

    void update(Resume r) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(r.uuid)) {
                storage[i] = r;
                return;
            }
        }
        System.out.println(String.format(RESUME_NOT_FOUND, r.uuid));
    }

    Resume get(String uuid) {
        Resume resume = getFromStorage(uuid);
        if (resume == null) {
            System.out.println(String.format(RESUME_NOT_FOUND, uuid));
        }
        return resume;
    }

    void delete(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                if (size - 1 - i >= 0) System.arraycopy(storage, i + 1, storage, i, size - 1 - i);
                storage[size - 1] = null;
                size--;
                return;
            }
        }
        System.out.println(String.format(RESUME_NOT_FOUND, uuid));
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    int size() {
        return size;
    }

    private Resume getFromStorage(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }
}