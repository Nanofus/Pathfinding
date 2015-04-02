package fi.nano.pathfinding.dataStructures;

/**
 * Oma ArrayList-toteutus.
 *
 * @author Nanofus
 * @param <O> Listattava oliotyyppi
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
     *
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
     *
     * @param index Indeksi
     * @return Objekti
     */
    public O get(int index) {
        checkIfOutOfBounds(index);

        if (objects[index] != null) {
            Object item = objects[index];
            return (O) item;
        }
        return null;
    }

    /**
     * Poista objekti indeksistä.
     *
     * @param index Indeksi
     * @return Poistettu objekti
     */
    public O remove(int index) {
        checkIfOutOfBounds(index);

        Object o = get(index);

        if (index != --size) {
            cleanArray(index);
        }

        return (O) o;
    }

    /**
     * Palauta listan koko.
     *
     * @return Koko
     */
    public int size() {
        return size;
    }

    /**
     * Onko listassa tietty objekti.
     *
     * @param o Objekti
     * @return Onko objekti listassa
     */
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    /**
     * Onko lista tyhjä.
     *
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Objektin indeksi.
     *
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
        Object[] copy = new Object[objects.length * 2];

        for (int i = 0; i < objects.length; i++) {
            copy[i] = objects[i];
        }

        objects = copy;
    }

    /**
     * Tuottaa virheilmoituksen jos yritetään käsitellä listaa virheellisellä
     * indeksillä.
     *
     * @param index
     */
    private void checkIfOutOfBounds(int index) {
        if (index < 0 || index > objects.length) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Kun poistetaan kohta taulukosta pudotetaan ylimääräinen väli pois.
     *
     * @param index Indeksi josta objekti poistettiin
     */
    private void cleanArray(int index) {
        Object[] copy = new Object[objects.length - 1];

        int objectIndex = 0;
        for (int i = 0; i < copy.length; i++) {
            if (i != index) {
                copy[i] = objects[objectIndex];
                objectIndex++;
            }
        }

        objects = copy;
    }
}
