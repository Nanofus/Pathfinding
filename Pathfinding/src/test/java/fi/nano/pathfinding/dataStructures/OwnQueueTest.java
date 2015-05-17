/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.nano.pathfinding.dataStructures;

import fi.nano.pathfinding.structure.Node;
import java.util.ArrayDeque;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Nanofus
 */
public class OwnQueueTest {

    public OwnQueueTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void TestPush() {
        OwnQueue queue = new OwnQueue();

        Node node = new Node();

        queue.push(node);

        assertEquals(1, queue.size());
    }

    @Test
    public void TestIsEmpty() {
        OwnQueue queue = new OwnQueue();

        Node node = new Node();

        queue.push(node);

        assertEquals(false, queue.isEmpty());
    }

    @Test
    public void TestIsEmpty2() {
        OwnQueue queue = new OwnQueue();
        assertEquals(true, queue.isEmpty());
    }

    @Test
    public void TestPushMultiple() {
        OwnQueue queue = new OwnQueue();

        Node node = new Node();
        Node node2 = new Node();

        queue.push(node);
        queue.push(node2);

        assertEquals(2, queue.size());
    }

    @Test
    public void TestPop() {
        OwnQueue queue = new OwnQueue();

        Node node = new Node();
        Node node2 = new Node();
        Node node3 = new Node();

        queue.push(node);
        queue.push(node2);
        queue.push(node3);

        assertEquals(node, queue.pop());
    }

    @Test
    public void TestPop2() {
        OwnQueue queue = new OwnQueue();

        Node node = new Node();
        Node node2 = new Node();
        Node node3 = new Node();

        queue.push(node);
        queue.push(node2);
        queue.push(node3);

        queue.pop();

        assertEquals(node2, queue.pop());
    }

    @Test
    public void TestPeek() {
        OwnQueue queue = new OwnQueue();

        Node node = new Node();
        Node node2 = new Node();
        Node node3 = new Node();

        queue.push(node);
        queue.push(node2);
        queue.push(node3);

        assertEquals(node, queue.peek());
    }

    @Test
    public void TestPeek2() {
        OwnQueue queue = new OwnQueue();

        Node node = new Node();
        Node node2 = new Node();
        Node node3 = new Node();

        queue.push(node);
        queue.push(node2);
        queue.push(node3);

        queue.peek();

        assertEquals(node, queue.peek());
    }

    @Test
    public void TestPeek3() {
        OwnQueue queue = new OwnQueue();

        Node node = new Node();
        Node node2 = new Node();
        Node node3 = new Node();

        queue.push(node);
        queue.push(node2);
        queue.push(node3);

        queue.pop();

        assertEquals(node2, queue.peek());
    }

    @Test
    public void CompareToArrayDeque() {
        long runTime;
        long startTime;

        OwnQueue queue = new OwnQueue();
        ArrayDeque<Node> deque = new ArrayDeque<>();

        System.out.println("\nOwnQueue:");

        startTime = System.nanoTime();
        queue.push(new Node());
        runTime = System.nanoTime() - startTime;
        System.out.println("Push: " + runTime + "ns");
        queue = new OwnQueue();

        startTime = System.nanoTime();
        for (int i = 0; i < 200; i++) {
            queue.push(new Node());
        }
        runTime = System.nanoTime() - startTime;
        System.out.println("Push x 200: " + runTime + "ns");

        startTime = System.nanoTime();
        queue.peek();
        runTime = System.nanoTime() - startTime;
        System.out.println("Peek: " + runTime + "ns");

        startTime = System.nanoTime();
        queue.pop();
        runTime = System.nanoTime() - startTime;
        System.out.println("Pop: " + runTime + "ns");

        startTime = System.nanoTime();
        queue.isEmpty();
        runTime = System.nanoTime() - startTime;
        System.out.println("IsEmpty: " + runTime + "ns");

        System.out.println();

        System.out.println("ArrayDeque:");

        startTime = System.nanoTime();
        deque.push(new Node());
        runTime = System.nanoTime() - startTime;
        System.out.println("Push: " + runTime + "ns");
        deque = new ArrayDeque();
        
        startTime = System.nanoTime();
        for (int i = 0; i < 200; i++) {
            deque.push(new Node());
        }
        runTime = System.nanoTime() - startTime;
        System.out.println("Push x 200: " + runTime + "ns");

        startTime = System.nanoTime();
        deque.peek();
        runTime = System.nanoTime() - startTime;
        System.out.println("Peek: " + runTime + "ns");

        startTime = System.nanoTime();
        deque.pop();
        runTime = System.nanoTime() - startTime;
        System.out.println("Pop: " + runTime + "ns");

        startTime = System.nanoTime();
        deque.isEmpty();
        runTime = System.nanoTime() - startTime;
        System.out.println("IsEmpty: " + runTime + "ns");

        System.out.println();
        assertEquals(true, true);
    }
}
