package fi.nano.pathfinding.dataStructures;

import fi.nano.pathfinding.Node;

/**
 *
 * @author Nanofus
 */
public class OwnBinaryHeap {

    private Node array[];
    private int size;
    private NodeComparator comparator;

    /**
     * Konstruktori
     *
     * @param capacity Keon maksimikapasiteetti
     * @param comparator Komparaattori joka vertaa solmuja algoritmin
     * tarvitsemalla tavalla
     */
    public OwnBinaryHeap(int capacity, NodeComparator comparator) {
        array = new Node[capacity];
        this.comparator = comparator;
    }

    /**
     * Keon objektien määrä
     *
     * @return Määrä
     */
    public int size() {
        return size;
    }

    /**
     * Keon kokonaiskapasiteetti, käytännössä taulukon koko
     *
     * @return Kapasiteetti
     */
    public int capacity() {
        return array.length;
    }

    /**
     * Lisää solmu kekoon
     *
     * @param node Solmu
     * @return Onnistuiko operaatio
     */
    public boolean add(Node node) {
        return offer(node);
    }

    /**
     * Tarjoa solmu keolle
     *
     * @param node Solmu
     * @return Mahtuuko solmu kekoon
     */
    public boolean offer(Node node) {
        if (size >= array.length) {
            return false;
        }
        array[size++] = node;
        siftUp();
        return true;
    }

    /**
     * Kurkka päällimmäinen solmu poistamatta
     *
     * @return Päällimmäinen solmu
     */
    public Node peek() {
        if (size == 0) {
            return null;
        } else {
            return array[0];
        }
    }

    /**
     * Onko tietty solmu keossa
     *
     * @param node Etsitty solmu
     * @return Onko keossa
     */
    public boolean contains(Node node) {
        for (int i = 0; i < array.length; i++) {
            if (node == array[i]) {
                return true;
            }
        }
        return false;
    }

    /**
     * Hakee keon päällimmäisen solmun (ja heittää viimeisen huipulle ja siftaa
     * sen paikalleen)
     *
     * @return Solmu
     */
    public Node poll() {
        if (size == 0) {
            return null;
        }

        Node node = array[0];

        array[0] = array[--size];

        siftDown();
        return node;
    }

    /**
     * Vaihtaa kahden solmun paikkoja
     *
     * @param first
     * @param second
     */
    private void swapPlaces(int first, int second) {
        Node node = array[second];
        array[second] = array[first];
        array[first] = node;
    }

    /**
     * Korjaa keko lisäyksen jälkeen
     */
    private void siftUp() {
        int index = size - 1;

        while (index > 0) {
            int parentIndex = (index + 1) / 2 - 1; // Verrataan parentiin ja nosta hierarkiassa ylemmäksi tarvittaessa

            if (comparator.compare(array[index], array[parentIndex]) >= 0) {
                break;
            }

            swapPlaces(index, parentIndex);
            index = parentIndex;
        }
    }

    /**
     * Korjaa keko poiston jälkeen
     */
    private void siftDown() {
        int index = 0;

        while (index < size) {
            int childIndex = (index + 1) * 2 - 1; // Lapsosen indeksi, verrataan tähän ja tarvittaessa lasketaan hierarkiassa

            if (childIndex > size) {
                break;
            }

            if ((childIndex + 1) < size && comparator.compare(array[index], array[childIndex]) < 0) {
                childIndex++;
            }

            if (comparator.compare(array[index], array[childIndex]) <= 0) {
                break;
            }

            swapPlaces(index, childIndex);
            index = childIndex;
        }
    }

    /**
     * Kasvata taulukkoa sen täyttyessä.
     */
    private void enlargeArray() {
        Node[] copy = new Node[array.length * 2];

        for (int i = 0; i < array.length; i++) {
            copy[i] = array[i];
        }

        array = copy;
    }

}
