package fi.nano.pathfinding.dataStructures;

import fi.nano.pathfinding.structure.Node;

/**
 * Binäärikeko. Käyttää solmuobjekteja sillä tallentaa tiedon solmun indeksistä solmuun itseensä.
 * @author Nanofus
 */
public class OwnBinaryHeap {

    private Node[] array = null;
    private int size = 0;
    private int capacity = 11;
    private NodeComparator comparator;

    /**
     * Konstruktori
     *
     * @param comparator Solmukomparaattori
     */
    public OwnBinaryHeap(NodeComparator comparator) {
        this.comparator = comparator;
        this.array = new Node[capacity];
    }

    /**
     * Lisää solmun kekoon
     *
     * @param node Lisättävä solmu
     */
    public void add(Node node) {
        if (size >= array.length) {
            enlargeHeap();
        }

        size++;
        int i = size - 1;
        while (i > 0 && comparator.compare(get(parent(i)), node) >= 0) {
            changeIndex(i, parent(i));
            i = parent(i);
        }

        array[i] = node;
        updateHeapIndex(i);
    }

    /**
     * Ottaa keon päällimmäisen solmun.
     *
     * @return Keon päällimmäinen solmu
     */
    public Node poll() {
        if (isEmpty()) {
            return null;
        }

        Node node = get(0);
        node.binaryHeapIndex = -1;

        size--;

        if (isEmpty()) {
            array[0] = null;
        } else {
            changeIndex(0, size);
            sift(0);
        }

        return node;
    }

    /**
     * Päivittää solmun sijainnin keossa
     *
     * @param node Solmu
     */
    public void decreaseKey(Node node) {
        int i = node.binaryHeapIndex;

        if (i == -1) {
            return;
        }

        while (i > 0 && comparator.compare(get(parent(i)), node) >= 0) {
            swap(i, parent(i));
            i = parent(i);
        }
    }

    /**
     * Hakee solmun indeksistä
     *
     * @param i Indeksi
     * @return Solmu
     */
    private Node get(int i) {
        return array[i];
    }

    /**
     * Korjaa keon lisäämisen tai poiston jälkeen
     *
     * @param i Indeksi solmulle
     */
    private void sift(int i) {
        int leftIndex = leftChild(i);
        int rightIndex = rightChild(i);

        int smallestIndex;

        if (rightIndex < size) {
            if (comparator.compare(get(leftIndex), get(rightIndex)) <= 0) {
                smallestIndex = leftIndex;
            } else {
                smallestIndex = rightIndex;
            }

            if (comparator.compare(get(i), get(smallestIndex)) >= 0) {
                swap(i, smallestIndex);
                sift(smallestIndex);
            }
        } else if (leftIndex == (size - 1) && comparator.compare(get(i), get(leftIndex)) >= 0) {
            swap(i, leftIndex);
        }
    }

    /**
     * Onko keko tyhjä
     *
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Keon koko
     *
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * Indeksissä index olevan solmun vanhemman indeksi
     *
     * @param index Indeksi
     * @return Vanhemman indeksi
     */
    private int parent(int index) {
        return index / 2;
    }

    /**
     * Indeksissä index olevan solmun vasemman lapsen indeksi
     *
     * @param index Indeksi
     * @return Vasemman lapsen indeksi
     */
    private int leftChild(int index) {
        return (index == 0 ? 1 : 2 * index);
    }

    /**
     * Indeksissä index olevan solmun oikean lapsen indeksi
     *
     * @param index Indeksi
     * @return Oikean lapsen indeksi
     */
    private int rightChild(int index) {
        return (index == 0 ? 2 : 2 * index + 1);
    }

    /**
     * Vaihtaa indeksissä i olevan solmun sisäisesti tiedossa olevan indeksin
     * oikeaksi
     *
     * @param i Indeksi
     */
    private void updateHeapIndex(int i) {
        get(i).binaryHeapIndex = i;
    }

    /**
     * Vaihtaa kahden solmun paikat keossa
     *
     * @param i Solmun 1 indeksi
     * @param j Solmun 2 indeksi
     */
    private void swap(int i, int j) {
        Node n = array[i];

        array[i] = array[j];
        array[j] = n;

        updateHeapIndex(i);
        updateHeapIndex(j);
    }

    /**
     * Vaihtaa solmun indeksistä i indeksiin j
     *
     * @param i Lähtöindeksi
     * @param j Kohdeindeksi
     */
    private void changeIndex(int i, int j) {
        array[i] = array[j];
        updateHeapIndex(i);
    }

    /**
     * Kasvattaa kekoa tarvittaessa
     */
    private void enlargeHeap() {
        Node[] copy = new Node[array.length * 2];

        for (int i = 0; i < array.length; i++) {
            copy[i] = array[i];
        }

        array = copy;
    }

}
