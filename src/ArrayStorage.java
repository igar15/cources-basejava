import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    void save(Resume r) {
        if (r != null && get(r.uuid) == null) {
            storage[size++] = r;
        }
    }

    Resume get(String uuid) {
        return Arrays.stream(storage)
                .limit(size)
                .filter(resume -> resume.uuid.equals(uuid))
                .findFirst().orElse(null);
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
}
