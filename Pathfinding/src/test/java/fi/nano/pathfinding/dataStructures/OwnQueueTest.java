/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.nano.pathfinding.dataStructures;

import fi.nano.pathfinding.structure.Node;
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

        Node node = new Node(false);

        queue.push(node);

        assertEquals(1, queue.size());
    }

    @Test
    public void TestIsEmpty() {
        OwnQueue queue = new OwnQueue();

        Node node = new Node(false);

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

        Node node = new Node(false);
        Node node2 = new Node(false);

        queue.push(node);
        queue.push(node2);

        assertEquals(2, queue.size());
    }

    @Test
    public void TestPop() {
        OwnQueue queue = new OwnQueue();

        Node node = new Node(false);
        Node node2 = new Node(false);
        Node node3 = new Node(false);

        queue.push(node);
        queue.push(node2);
        queue.push(node3);

        assertEquals(node, queue.pop());
    }

    @Test
    public void TestPop2() {
        OwnQueue queue = new OwnQueue();

        Node node = new Node(false);
        Node node2 = new Node(false);
        Node node3 = new Node(false);

        queue.push(node);
        queue.push(node2);
        queue.push(node3);

        queue.pop();

        assertEquals(node2, queue.pop());
    }

    @Test
    public void TestPeek() {
        OwnQueue queue = new OwnQueue();

        Node node = new Node(false);
        Node node2 = new Node(false);
        Node node3 = new Node(false);

        queue.push(node);
        queue.push(node2);
        queue.push(node3);

        assertEquals(node, queue.peek());
    }

    @Test
    public void TestPeek2() {
        OwnQueue queue = new OwnQueue();

        Node node = new Node(false);
        Node node2 = new Node(false);
        Node node3 = new Node(false);

        queue.push(node);
        queue.push(node2);
        queue.push(node3);

        queue.peek();

        assertEquals(node, queue.peek());
    }

    @Test
    public void TestPeek3() {
        OwnQueue queue = new OwnQueue();

        Node node = new Node(false);
        Node node2 = new Node(false);
        Node node3 = new Node(false);

        queue.push(node);
        queue.push(node2);
        queue.push(node3);

        queue.pop();

        assertEquals(node2, queue.peek());
    }
}
