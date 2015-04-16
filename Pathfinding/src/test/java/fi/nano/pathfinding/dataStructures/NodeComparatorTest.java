/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.nano.pathfinding.dataStructures;

import fi.nano.pathfinding.structure.Node;
import fi.nano.pathfinding.dataStructures.NodeComparator;
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
public class NodeComparatorTest {

    public NodeComparatorTest() {
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
    public void CompareAStarNodesFirstBigger() {
        NodeComparator nodeComparator = new NodeComparator(0);

        Node one = new Node(false);
        Node two = new Node(false);

        one.aStar_f = 5;
        two.aStar_f = 4;

        assertEquals(1, nodeComparator.compare(one, two));
    }

    @Test
    public void CompareAStarNodesSecondBigger() {
        NodeComparator nodeComparator = new NodeComparator(0);

        Node one = new Node(false);
        Node two = new Node(false);

        one.aStar_f = 4;
        two.aStar_f = 5;

        assertEquals(-1, nodeComparator.compare(one, two));
    }

    @Test
    public void CompareAStarNodesSameValue() {
        NodeComparator nodeComparator = new NodeComparator(0);

        Node one = new Node(false);
        Node two = new Node(false);

        one.aStar_f = 5;
        two.aStar_f = 5;

        assertEquals(0, nodeComparator.compare(one, two));
    }

    @Test
    public void CompareDijkstraNodesFirstBigger() {
        NodeComparator nodeComparator = new NodeComparator(1);

        Node one = new Node(false);
        Node two = new Node(false);

        one.dijkstra_minDistance = 5;
        two.dijkstra_minDistance = 4;

        assertEquals(1, nodeComparator.compare(one, two));
    }

    @Test
    public void CompareDijkstraNodesSecondBigger() {
        NodeComparator nodeComparator = new NodeComparator(1);

        Node one = new Node(false);
        Node two = new Node(false);

        one.dijkstra_minDistance = 4;
        two.dijkstra_minDistance = 5;

        assertEquals(-1, nodeComparator.compare(one, two));
    }

    @Test
    public void CompareDijkstraNodesSameValue() {
        NodeComparator nodeComparator = new NodeComparator(1);

        Node one = new Node(false);
        Node two = new Node(false);

        one.dijkstra_minDistance = 5;
        two.dijkstra_minDistance = 5;

        assertEquals(0, nodeComparator.compare(one, two));
    }
}
