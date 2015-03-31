package fi.nano.pathfinding.dataStructures;

import java.util.Arrays;

/**
 *
 * @author Nanofus
 */
public class OArrayList<O> {

    private int size = 0;

    private final int initialCapacity = 12;
    private Object objects[];

    public OArrayList() {
        objects = new Object[initialCapacity];
    }

    public void add(O o) {
        if (size == objects.length) {
            enlargeList();
        }
        objects[size++] = o;
    }

    public Object get(int index) {
        checkIfOutOfBounds(index);

        if (objects[index] != null) {
            Object item = objects[index];
            return item;
        }
        return null;
    }

    public Object remove(int index) {
        checkIfOutOfBounds(index);

        Object o = get(index);
        if (index != --size) {
            System.arraycopy(o, index + 1, o, index, size - index);
        }
        return o;
    }

    public int size() {
        return size;
    }

    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (o == get(i)) {
                return i;
            }
        }
        return -1;
    }

    private void enlargeList() {
        objects = Arrays.copyOf(objects, objects.length * 2);
    }

    private void checkIfOutOfBounds(int index) {
        if (index < 0 || index > objects.length) {
            throw new IndexOutOfBoundsException();
        }
    }
}
