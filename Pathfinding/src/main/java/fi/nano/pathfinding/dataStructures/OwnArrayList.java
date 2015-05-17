package fi.nano.pathfinding.dataStructures;

/**
 * Oma ArrayList-toteutus.
 *
 * @author Nanofus
 * @param <O> Listattava oliotyyppi
 */
public class OwnArrayList<O> {

    private int size = 0;
    private int capacity = 10;
    private Object array[];

    /**
     * Konstruktori
     */
    public OwnArrayList() {
        array = new Object[capacity];
    }

    /**
     * Lisää objekti
     *
     * @param object Objekti
     */
    public void add(O object) {
        if (size == array.length) {
            EnlargeArray();
        }
        array[size++] = object;
    }

    /**
     * Hae objekti indeksistä
     *
     * @param index Indeksi
     * @return Objekti
     */
    public O get(int index) {
        CheckIfOutOfBounds(index);

        if (array[index] != null) {
            Object item = array[index];
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
        CheckIfOutOfBounds(index);

        Object object = get(index);

        if (index != --size) {
            CleanArray(index);
        }

        return (O) object;
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
     * @param object Objekti
     * @return Onko objekti listassa
     */
    public boolean contains(Object object) {
        return indexOf(object) != -1;
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
     * @param object Objekti
     * @return Objektin indeksi
     */
    public int indexOf(Object object) {
        for (int i = 0; i < size; i++) {
            if (object == get(i)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Kasvata taulukkoa sen täyttyessä.
     */
    private void EnlargeArray() {
        Object[] copy = new Object[array.length * 2];

        for (int i = 0; i < array.length; i++) {
            copy[i] = array[i];
        }

        array = copy;
    }

    /**
     * Tuottaa virheilmoituksen jos yritetään käsitellä listaa virheellisellä
     * indeksillä.
     *
     * @param index
     */
    private void CheckIfOutOfBounds(int index) {
        if (index < 0 || index > array.length) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Kun poistetaan kohta taulukosta pudotetaan ylimääräinen väli pois.
     *
     * @param index Indeksi josta objekti poistettiin
     */
    private void CleanArray(int index) {
        Object[] copy = new Object[array.length - 1];
        
        int objectIndex = 0;
        for (int i = 0; i < copy.length; i++) {
            if (i != index) {
                copy[i] = array[objectIndex];
                objectIndex++;
            }
        }

        array = copy;
    }
}
