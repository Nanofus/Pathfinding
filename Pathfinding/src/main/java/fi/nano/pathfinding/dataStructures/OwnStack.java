package fi.nano.pathfinding.dataStructures;

import fi.nano.pathfinding.Node;

/**
 *
 * @author Nanofus
 */
public class OwnStack {

    private int capacity = 12;
    private Node[] array;
    private int top;

    /**
     * Konstruktori
     */
    public OwnStack() {
        array = new Node[capacity];
        top = -1;
    }

    /**
     * Laita solmu pinoon, kasvata pinon maksimikokoa tarvittaessa
     *
     * @param node Solmu
     */
    public void push(Node node) {
        if (top + 1 == array.length) {
            enlargeStack();
        }
        array[++top] = node;
    }

    /**
     * Ota pinon päällimmäinen solmu
     *
     * @return Solmu
     */
    public Node pop() {
        return array[top--];
    }

    /**
     * Kurkkaa pinon päällimmäistä solmua ottamatta sitä
     *
     * @return Solmu
     */
    public Node peek() {
        return array[top];
    }

    /**
     * Onko pino täysi
     *
     * @return Totuusarvo
     */
    public boolean isFull() {
        return (top == capacity - 1);
    }

    /**
     * Onko pino tyhjä
     *
     * @return Totuusarvo
     */
    public boolean isEmpty() {
        return (top == -1);
    }

    /**
     * Kasvata pinoa sen täyttyessä.
     */
    private void enlargeStack() {
        Node[] copy = new Node[array.length * 2];

        for (int i = 0; i < array.length; i++) {
            copy[i] = array[i];
        }

        array = copy;
    }

}
