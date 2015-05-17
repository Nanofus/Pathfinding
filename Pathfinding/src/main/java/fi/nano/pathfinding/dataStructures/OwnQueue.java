package fi.nano.pathfinding.dataStructures;

import fi.nano.pathfinding.structure.Node;

/**
 * Jonon toteutus.
 *
 * @author Nanofus
 */
public class OwnQueue {

    private Node[] array;
    private int capacity = 16;
    private int size = 0;

    private int front = 0;
    private int back = -1;

    /**
     * Konstruktori
     */
    public OwnQueue() {
        array = new Node[capacity];
    }

    /**
     * Onko jono tyhjä
     *
     * @return Totuusarvo
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Jonon pituus
     *
     * @return Totuusarvo
     */
    public int size() {
        return size;
    }

    /**
     * Pullauta jonon vanhin alkio ulos
     *
     * @return Solmu
     */
    public Node pop() {
        if (isEmpty()) {
            return null;
        }
        size--;
        Node node = array[front];
        front = update(front);

        return node;
    }

    /**
     * Kurkataan jonon vanhinta alkiota ottamatta sitä ulos
     *
     * @return Solmu
     */
    public Node peek() {
        if (isEmpty()) {
            return null;
        }
        return array[front];
    }

    /**
     * Laitetaan uusi alkio jonoon
     *
     * @param node Solmu
     */
    public void push(Node node) {
        if (size == array.length) {
            enlargeQueue();
        }
        back = update(back);
        array[back] = node;
        size++;
    }

    /**
     * Päivittää jonon pään
     *
     * @param i Jonon häntä
     * @return Uusi häntä
     */
    private int update(int i) {
        if (++i == array.length) {
            i = 0;
        }
        return i;
    }

    /**
     * Kasvata taulukkoa sen täyttyessä.
     */
    private void enlargeQueue() {
        Node[] copy = new Node[array.length * 2];

        for (int i = 0; i < array.length; i++) {
            copy[i] = array[i];
        }

        array = copy;

        front = 0;
        back = size - 1;
    }
}
