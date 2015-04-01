package fi.nano.pathfinding.dataStructures;

import java.util.Arrays;

/**
 * Oma ArrayList-toteutus.
 * @author Nanofus
 */
public class OwnArrayList<O> {

    private int size = 0;

    private final int initialCapacity = 12;
    private Object objects[];

    public OwnArrayList() {
        objects = new Object[initialCapacity];
    }

    /**
     * Lisää objekti
     * @param o Objekti
     */
    public void add(O o) {
        if (size == objects.length) {
            enlargeList();
        }
        objects[size++] = o;
    }

    /**
     * Hae objekti indeksistä
     * @param index Indeksi
     * @return Objekti
     */
    public Object get(int index) {
        checkIfOutOfBounds(index);

        if (objects[index] != null) {
            Object item = objects[index];
            return item;
        }
        return null;
    }

    /**
     * Poista objekti indeksistä
     * @param index Indeksi
     * @return Poistettu objekti
     */
    public Object remove(int index) {
        checkIfOutOfBounds(index);

        Object o = get(index);
        if (index != --size) {
            System.arraycopy(o, index + 1, o, index, size - index);
        }
        return o;
    }

    /**
     * Palauta listan koko
     * @return Koko
     */
    public int size() {
        return size;
    }

    /**
     * Onko listassa objekti
     * @param o Objekti
     * @return Onko objekti listassa
     */
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    /**
     * Onko lista tyhjä
     * @return 
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Objektin indeksi
     * @param o Objekti
     * @return Objektin indeksi
     */
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (o == get(i)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Kasvata listaa sen täyttyessä.
     */
    private void enlargeList() {
        objects = Arrays.copyOf(objects, objects.length * 2);
    }

    /**
     * Tuottaa virheilmoituksen jos yritetään käsitellä listaa virheellisellä indeksillä.
     * @param index 
     */
    private void checkIfOutOfBounds(int index) {
        if (index < 0 || index > objects.length) {
            throw new IndexOutOfBoundsException();
        }
    }
}
