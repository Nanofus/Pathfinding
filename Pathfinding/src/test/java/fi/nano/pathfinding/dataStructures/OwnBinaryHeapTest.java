/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.nano.pathfinding.dataStructures;

import fi.nano.pathfinding.structure.Node;
import java.util.PriorityQueue;
import java.util.Random;
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

        Node node = new Node(5);

        heap.add(node);

        assertEquals(1, heap.size());
    }

    @Test
    public void TestAddAndRemove() {
        OwnBinaryHeap heap = new OwnBinaryHeap(new NodeComparator(0));

        Node node = new Node(5);
        Node node2 = new Node(10);

        heap.add(node);
        heap.add(node2);

        assertEquals(node, heap.poll());
    }

    @Test
    public void TestAddAndRemove2() {
        OwnBinaryHeap heap = new OwnBinaryHeap(new NodeComparator(0));

        Node node = new Node(10);
        Node node2 = new Node(5);

        heap.add(node);
        heap.add(node2);

        assertEquals(node2, heap.poll());
    }

    @Test
    public void TestDecreaseKey() {
        OwnBinaryHeap heap = new OwnBinaryHeap(new NodeComparator(0));

        Node node = new Node(10);
        Node node2 = new Node(5);
        Node node3 = new Node(15);

        heap.add(node);
        heap.add(node2);
        heap.add(node3);

        node2.aStar_f = 20;
        heap.decreaseKey(node);

        assertEquals(node, heap.poll());
    }

    @Test
    public void CompareToPriorityQueue() {
        long runTime;
        long startTime;

        OwnBinaryHeap heap = new OwnBinaryHeap(new NodeComparator(0));
        PriorityQueue<Node> pq = new PriorityQueue<>(new NodeComparator(0));

        System.out.println("\nOwnBinaryHeap:");

        startTime = System.nanoTime();
        heap.add(new Node());
        runTime = System.nanoTime() - startTime;
        System.out.println("Add: " + runTime + "ns");
        heap = new OwnBinaryHeap(new NodeComparator(0));
        
        Random rand = new Random();
        startTime = System.nanoTime();
        for (int i = 0; i < 100; i++) {
            Node node = new Node();
            node.aStar_f = rand.nextInt(300);
            heap.add(node);
        }
        runTime = System.nanoTime() - startTime;
        System.out.println("Add x 100: " + runTime + "ns");

        startTime = System.nanoTime();
        heap.poll();
        runTime = System.nanoTime() - startTime;
        System.out.println("Poll: " + runTime + "ns");

        startTime = System.nanoTime();
        heap.isEmpty();
        runTime = System.nanoTime() - startTime;
        System.out.println("IsEmpty: " + runTime + "ns");

        startTime = System.nanoTime();
        heap.size();
        runTime = System.nanoTime() - startTime;
        System.out.println("Size: " + runTime + "ns");

        System.out.println("\nPriorityQueue:");
        
        startTime = System.nanoTime();
        pq.add(new Node());
        runTime = System.nanoTime() - startTime;
        System.out.println("Add: " + runTime + "ns");
        pq = new PriorityQueue(new NodeComparator(0));

        startTime = System.nanoTime();
        for (int i = 0; i < 100; i++) {
            Node node = new Node();
            node.aStar_f = rand.nextInt(300);
            pq.add(node);
        }
        runTime = System.nanoTime() - startTime;
        System.out.println("Add x 100: " + runTime + "ns");

        startTime = System.nanoTime();
        pq.poll();
        runTime = System.nanoTime() - startTime;
        System.out.println("Poll: " + runTime + "ns");

        startTime = System.nanoTime();
        pq.isEmpty();
        runTime = System.nanoTime() - startTime;
        System.out.println("IsEmpty: " + runTime + "ns");

        startTime = System.nanoTime();
        pq.size();
        runTime = System.nanoTime() - startTime;
        System.out.println("Size: " + runTime + "ns");

        System.out.println();
        assertEquals(true, true);
    }
}
