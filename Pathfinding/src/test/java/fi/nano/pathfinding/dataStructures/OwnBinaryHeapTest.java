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
public class OwnBinaryHeapTest {

    public OwnBinaryHeapTest() {
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
    public void TestAddAndSize() {
        OwnBinaryHeap heap = new OwnBinaryHeap(new NodeComparator(0));

        Node node = new Node(false, 5);

        heap.add(node);

        assertEquals(1, heap.size());
    }

    @Test
    public void TestAddAndRemove() {
        OwnBinaryHeap heap = new OwnBinaryHeap(new NodeComparator(0));

        Node node = new Node(false, 5);
        Node node2 = new Node(false, 10);

        heap.add(node);
        heap.add(node2);

        assertEquals(node, heap.poll());
    }

    @Test
    public void TestAddAndRemove2() {
        OwnBinaryHeap heap = new OwnBinaryHeap(new NodeComparator(0));

        Node node = new Node(false, 10);
        Node node2 = new Node(false, 5);

        heap.add(node);
        heap.add(node2);

        assertEquals(node2, heap.poll());
    }

    @Test
    public void TestDecreaseKey() {
        OwnBinaryHeap heap = new OwnBinaryHeap(new NodeComparator(0));

        Node node = new Node(false, 10);
        Node node2 = new Node(false, 5);
        Node node3 = new Node(false, 15);

        heap.add(node);
        heap.add(node2);
        heap.add(node3);
        
        node2.aStar_f = 20;
        heap.decreaseKey(node);

        assertEquals(node, heap.poll());
    }
}
