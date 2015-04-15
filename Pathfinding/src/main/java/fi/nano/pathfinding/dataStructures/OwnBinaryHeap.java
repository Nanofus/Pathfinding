package fi.nano.pathfinding.dataStructures;

import fi.nano.pathfinding.Node;
import fi.nano.pathfinding.NodeComparator;

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
     * @param n Solmu
     * @return Onnistuiko operaatio
     */
    public boolean add(Node n) {
        return offer(n);
    }

    /**
     * Tarjoa solmu keolle
     *
     * @param n Solmu
     * @return Mahtuuko solmu kekoon
     */
    public boolean offer(Node n) {
        if (size >= array.length) {
            return false;
        }
        array[size++] = n;
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
     * Hakee keon päällimmäisen solmun
     *
     * @return Solmu
     */
    public Node poll() {
        if (size == 0) {
            return null;
        }

        Node n = array[0];

        array[0] = array[--size];

        siftDown();
        return n;
    }

    /**
     * Vaihtaa kahden solmun paikkoja
     *
     * @param n1
     * @param n2
     */
    private void swapPlaces(int n1, int n2) {
        Node n = array[n2];
        array[n2] = array[n1];
        array[n1] = n;
    }

    /**
     * Korjaa keko lisäyksen jälkeen
     */
    private void siftUp() {
        int i = size - 1;

        while (i > 0) {
            int p = (i + 1) / 2 - 1;

            if (comparator.compare(array[i], array[p]) >= 0) {
                break;
            }

            swapPlaces(i, p);
            i = p;
        }
    }

    /**
     * Korjaa keko poiston jälkeen
     */
    private void siftDown() {
        int i = 0;

        while (i < size) {
            int n = (i + 1) * 2 - 1;

            if (n > size) {
                break;
            }
            if ((n + 1) < size && comparator.compare(array[i], array[n]) < 0) {
                n++;
            }
            if (comparator.compare(array[i], array[n]) <= 0) {
                break;
            }

            swapPlaces(i, n);
            i = n;
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
