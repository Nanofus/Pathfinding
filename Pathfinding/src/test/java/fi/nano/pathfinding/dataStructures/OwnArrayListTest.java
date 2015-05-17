/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.nano.pathfinding.dataStructures;

import fi.nano.pathfinding.structure.Node;
import java.util.ArrayList;
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
public class OwnArrayListTest {

    public OwnArrayListTest() {
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
        OwnArrayList<Integer> list = new OwnArrayList<>();

        list.add(5);

        assertEquals(1, list.size());
    }

    @Test
    public void TestAddAndRemove() {
        OwnArrayList<Integer> list = new OwnArrayList<>();

        list.add(5);
        list.add(6);

        assertEquals(6, (int) list.remove(1));
    }

    @Test
    public void TestRemoveResizing() {
        OwnArrayList<Integer> list = new OwnArrayList<>();

        list.add(5);
        list.add(6);
        list.add(6);
        list.add(6);
        list.add(6);
        list.remove(1);

        assertEquals(4, list.size());
    }

    @Test
    public void TestContainsTrue() {
        OwnArrayList<Integer> list = new OwnArrayList<>();

        list.add(5);
        list.add(6);

        assertEquals(true, list.contains(5));
    }

    @Test
    public void TestContainsFalse() {
        OwnArrayList<Integer> list = new OwnArrayList<>();

        list.add(5);
        list.add(6);

        assertEquals(false, list.contains(10));
    }

    @Test
    public void TestIndexOf() {
        OwnArrayList<Integer> list = new OwnArrayList<>();

        list.add(5);
        list.add(6);
        list.add(6);
        list.add(6);

        assertEquals(1, list.indexOf(6));
    }

    @Test
    public void TestIsEmptyFalse() {
        OwnArrayList<Integer> list = new OwnArrayList<>();

        list.add(5);
        list.add(6);
        list.add(6);
        list.add(6);

        assertEquals(false, list.isEmpty());
    }

    @Test
    public void TestIsEmptyTrue() {
        OwnArrayList<Integer> list = new OwnArrayList<>();

        assertEquals(true, list.isEmpty());
    }

    @Test
    public void TestIsEmptyTrueAfterEmptying() {
        OwnArrayList<Integer> list = new OwnArrayList<>();

        list.add(5);
        list.remove(list.indexOf(5));

        assertEquals(true, list.isEmpty());
    }

    @Test
    public void TestEnlargeFilledArray() {
        OwnArrayList<Integer> list = new OwnArrayList<>();

        list.add(5);
        list.add(5);
        list.add(5);
        list.add(5);
        list.add(5);
        list.add(5);
        list.add(5);
        list.add(5);
        list.add(5);
        list.add(5);
        list.add(5);
        list.add(5);
        list.add(5);
        list.add(5);
        list.add(5);
        list.add(5);
        list.add(5);

        assertEquals(17, list.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void TestOutOfBoundsGet() {
        OwnArrayList<Integer> list = new OwnArrayList<>();
        list.add(5);
        list.get(132);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void TestOutOfBoundsRemove() {
        OwnArrayList<Integer> list = new OwnArrayList<>();
        list.add(5);
        list.remove(132);
    }

    @Test
    public void CompareToArrayListWithNodes() {
        long runTime;
        long startTime;
        
        System.out.println("Testing with nodes");

        ArrayList arrayList = new ArrayList<Node>();
        OwnArrayList ownArrayList = new OwnArrayList<Node>();

        System.out.println("\nOwnArrayList:");

        startTime = System.nanoTime();
        ownArrayList.add(new Node());
        runTime = System.nanoTime() - startTime;
        System.out.println("Add: " + runTime + "ns");
        ownArrayList = new OwnArrayList<>();
        
        Node firstNode = new Node();
        Node chosenNode = new Node();
        startTime = System.nanoTime();
        for (int i = 0; i < 200; i++) {
            Node node = new Node();
            if (i == 0) {
                firstNode = node;
            }
            ownArrayList.add(node);
            chosenNode = node;
        }
        runTime = System.nanoTime() - startTime;
        System.out.println("Add x 200: " + runTime + "ns");

        startTime = System.nanoTime();
        ownArrayList.contains(chosenNode);
        runTime = System.nanoTime() - startTime;
        System.out.println("Contains (chosen one last): " + runTime + "ns");

        startTime = System.nanoTime();
        ownArrayList.contains(firstNode);
        runTime = System.nanoTime() - startTime;
        System.out.println("Contains (chosen one first): " + runTime + "ns");

        startTime = System.nanoTime();
        ownArrayList.indexOf(chosenNode);
        runTime = System.nanoTime() - startTime;
        System.out.println("IndexOf (chosen one last): " + runTime + "ns");

        startTime = System.nanoTime();
        ownArrayList.indexOf(firstNode);
        runTime = System.nanoTime() - startTime;
        System.out.println("IndexOf (chosen one first): " + runTime + "ns");

        startTime = System.nanoTime();
        ownArrayList.get(100);
        runTime = System.nanoTime() - startTime;
        System.out.println("Get (101st): " + runTime + "ns");

        startTime = System.nanoTime();
        ownArrayList.remove(100);
        runTime = System.nanoTime() - startTime;
        System.out.println("Remove (101st): " + runTime + "ns");

        startTime = System.nanoTime();
        ownArrayList.isEmpty();
        runTime = System.nanoTime() - startTime;
        System.out.println("IsEmpty: " + runTime + "ns");

        startTime = System.nanoTime();
        ownArrayList.size();
        runTime = System.nanoTime() - startTime;
        System.out.println("Size: " + runTime + "ns");

        System.out.println("\nArrayList:");

        startTime = System.nanoTime();
        arrayList.add(new Node());
        runTime = System.nanoTime() - startTime;
        System.out.println("Add: " + runTime + "ns");
        arrayList = new ArrayList<>();
        
        startTime = System.nanoTime();
        for (int i = 0; i < 200; i++) {
            Node node = new Node();
            if (i == 0) {
                firstNode = node;
            }
            arrayList.add(node);
            chosenNode = node;
        }
        runTime = System.nanoTime() - startTime;
        System.out.println("Add x 200: " + runTime + "ns");

        startTime = System.nanoTime();
        arrayList.contains(chosenNode);
        runTime = System.nanoTime() - startTime;
        System.out.println("Contains (chosen one last): " + runTime + "ns");

        startTime = System.nanoTime();
        arrayList.contains(firstNode);
        runTime = System.nanoTime() - startTime;
        System.out.println("Contains (chosen one first): " + runTime + "ns");

        startTime = System.nanoTime();
        arrayList.indexOf(chosenNode);
        runTime = System.nanoTime() - startTime;
        System.out.println("IndexOf (chosen one last): " + runTime + "ns");

        startTime = System.nanoTime();
        arrayList.indexOf(firstNode);
        runTime = System.nanoTime() - startTime;
        System.out.println("IndexOf (chosen one first): " + runTime + "ns");

        startTime = System.nanoTime();
        arrayList.get(100);
        runTime = System.nanoTime() - startTime;
        System.out.println("Get (101st): " + runTime + "ns");

        startTime = System.nanoTime();
        arrayList.remove(100);
        runTime = System.nanoTime() - startTime;
        System.out.println("Remove (101st): " + runTime + "ns");

        startTime = System.nanoTime();
        arrayList.isEmpty();
        runTime = System.nanoTime() - startTime;
        System.out.println("IsEmpty: " + runTime + "ns");

        startTime = System.nanoTime();
        arrayList.size();
        runTime = System.nanoTime() - startTime;
        System.out.println("Size: " + runTime + "ns");

        System.out.println();
        assertEquals(true, true);
    }

    @Test
    public void CompareToArrayListWithIntegers() {
        long runTime;
        long startTime;
        
        System.out.println("Testing with integers");

        ArrayList arrayList = new ArrayList<>();
        OwnArrayList ownArrayList = new OwnArrayList<>();

        System.out.println("\nOwnArrayList:");

        startTime = System.nanoTime();
        ownArrayList.add(0);
        runTime = System.nanoTime() - startTime;
        System.out.println("Add: " + runTime + "ns");
        ownArrayList = new OwnArrayList<>();
        
        int firstInt = 0;
        int chosenInt = 199;
        startTime = System.nanoTime();
        for (int i = 0; i < 200; i++) {
            ownArrayList.add(i);
        }
        runTime = System.nanoTime() - startTime;
        System.out.println("Add x 200: " + runTime + "ns");

        startTime = System.nanoTime();
        ownArrayList.contains(chosenInt);
        runTime = System.nanoTime() - startTime;
        System.out.println("Contains (chosen one last): " + runTime + "ns");

        startTime = System.nanoTime();
        ownArrayList.contains(firstInt);
        runTime = System.nanoTime() - startTime;
        System.out.println("Contains (chosen one first): " + runTime + "ns");

        startTime = System.nanoTime();
        ownArrayList.indexOf(chosenInt);
        runTime = System.nanoTime() - startTime;
        System.out.println("IndexOf (chosen one last): " + runTime + "ns");

        startTime = System.nanoTime();
        ownArrayList.indexOf(firstInt);
        runTime = System.nanoTime() - startTime;
        System.out.println("IndexOf (chosen one first): " + runTime + "ns");

        startTime = System.nanoTime();
        ownArrayList.get(100);
        runTime = System.nanoTime() - startTime;
        System.out.println("Get (101st): " + runTime + "ns");

        startTime = System.nanoTime();
        ownArrayList.remove(100);
        runTime = System.nanoTime() - startTime;
        System.out.println("Remove (101st): " + runTime + "ns");

        startTime = System.nanoTime();
        ownArrayList.isEmpty();
        runTime = System.nanoTime() - startTime;
        System.out.println("IsEmpty: " + runTime + "ns");

        startTime = System.nanoTime();
        ownArrayList.size();
        runTime = System.nanoTime() - startTime;
        System.out.println("Size: " + runTime + "ns");

        System.out.println("\nArrayList:");

        startTime = System.nanoTime();
        arrayList.add(0);
        runTime = System.nanoTime() - startTime;
        System.out.println("Add: " + runTime + "ns");
        arrayList = new ArrayList<>();
        
        startTime = System.nanoTime();
        for (int i = 0; i < 200; i++) {
            arrayList.add(i);
        }
        runTime = System.nanoTime() - startTime;
        System.out.println("Add x 200: " + runTime + "ns");

        startTime = System.nanoTime();
        arrayList.contains(chosenInt);
        runTime = System.nanoTime() - startTime;
        System.out.println("Contains (chosen one last): " + runTime + "ns");

        startTime = System.nanoTime();
        arrayList.contains(firstInt);
        runTime = System.nanoTime() - startTime;
        System.out.println("Contains (chosen one first): " + runTime + "ns");

        startTime = System.nanoTime();
        arrayList.indexOf(chosenInt);
        runTime = System.nanoTime() - startTime;
        System.out.println("IndexOf (chosen one last): " + runTime + "ns");

        startTime = System.nanoTime();
        arrayList.indexOf(firstInt);
        runTime = System.nanoTime() - startTime;
        System.out.println("IndexOf (chosen one first): " + runTime + "ns");

        startTime = System.nanoTime();
        arrayList.get(100);
        runTime = System.nanoTime() - startTime;
        System.out.println("Get (101st): " + runTime + "ns");

        startTime = System.nanoTime();
        arrayList.remove(100);
        runTime = System.nanoTime() - startTime;
        System.out.println("Remove (101st): " + runTime + "ns");

        startTime = System.nanoTime();
        arrayList.isEmpty();
        runTime = System.nanoTime() - startTime;
        System.out.println("IsEmpty: " + runTime + "ns");

        startTime = System.nanoTime();
        arrayList.size();
        runTime = System.nanoTime() - startTime;
        System.out.println("Size: " + runTime + "ns");

        System.out.println();
        assertEquals(true, true);
    }
    
    @Test
    public void CompareToArrayListWithFloats() {
        long runTime;
        long startTime;
        
        System.out.println("Testing with floats");

        ArrayList arrayList = new ArrayList<>();
        OwnArrayList ownArrayList = new OwnArrayList<>();

        System.out.println("\nOwnArrayList:");

        startTime = System.nanoTime();
        ownArrayList.add(0.1f);
        runTime = System.nanoTime() - startTime;
        System.out.println("Add: " + runTime + "ns");
        ownArrayList = new OwnArrayList<>();
        
        Random random = new Random();
        
        float firstFloat = 0f;
        float chosenFloat = 0f;
        startTime = System.nanoTime();
        for (int i = 0; i < 200; i++) {
            float newFloat = random.nextFloat();
            if (i == 0) {
                firstFloat = newFloat;
            }
            ownArrayList.add(newFloat);
            chosenFloat = newFloat;
        }
        runTime = System.nanoTime() - startTime;
        System.out.println("Add x 200: " + runTime + "ns");

        startTime = System.nanoTime();
        ownArrayList.contains(chosenFloat);
        runTime = System.nanoTime() - startTime;
        System.out.println("Contains (chosen one last): " + runTime + "ns");

        startTime = System.nanoTime();
        ownArrayList.contains(firstFloat);
        runTime = System.nanoTime() - startTime;
        System.out.println("Contains (chosen one first): " + runTime + "ns");

        startTime = System.nanoTime();
        ownArrayList.indexOf(chosenFloat);
        runTime = System.nanoTime() - startTime;
        System.out.println("IndexOf (chosen one last): " + runTime + "ns");

        startTime = System.nanoTime();
        ownArrayList.indexOf(firstFloat);
        runTime = System.nanoTime() - startTime;
        System.out.println("IndexOf (chosen one first): " + runTime + "ns");

        startTime = System.nanoTime();
        ownArrayList.get(100);
        runTime = System.nanoTime() - startTime;
        System.out.println("Get (101st): " + runTime + "ns");

        startTime = System.nanoTime();
        ownArrayList.remove(100);
        runTime = System.nanoTime() - startTime;
        System.out.println("Remove (101st): " + runTime + "ns");

        startTime = System.nanoTime();
        ownArrayList.isEmpty();
        runTime = System.nanoTime() - startTime;
        System.out.println("IsEmpty: " + runTime + "ns");

        startTime = System.nanoTime();
        ownArrayList.size();
        runTime = System.nanoTime() - startTime;
        System.out.println("Size: " + runTime + "ns");

        System.out.println("\nArrayList:");

        startTime = System.nanoTime();
        arrayList.add(new Node());
        runTime = System.nanoTime() - startTime;
        System.out.println("Add: " + runTime + "ns");
        arrayList = new ArrayList<>();
        
        startTime = System.nanoTime();
        for (int i = 0; i < 200; i++) {
            float newFloat = random.nextFloat();
            if (i == 0) {
                firstFloat = newFloat;
            }
            arrayList.add(newFloat);
            chosenFloat = newFloat;
        }
        runTime = System.nanoTime() - startTime;
        System.out.println("Add x 200: " + runTime + "ns");

        startTime = System.nanoTime();
        arrayList.contains(chosenFloat);
        runTime = System.nanoTime() - startTime;
        System.out.println("Contains (chosen one last): " + runTime + "ns");

        startTime = System.nanoTime();
        arrayList.contains(firstFloat);
        runTime = System.nanoTime() - startTime;
        System.out.println("Contains (chosen one first): " + runTime + "ns");

        startTime = System.nanoTime();
        arrayList.indexOf(chosenFloat);
        runTime = System.nanoTime() - startTime;
        System.out.println("IndexOf (chosen one last): " + runTime + "ns");

        startTime = System.nanoTime();
        arrayList.indexOf(firstFloat);
        runTime = System.nanoTime() - startTime;
        System.out.println("IndexOf (chosen one first): " + runTime + "ns");

        startTime = System.nanoTime();
        arrayList.get(100);
        runTime = System.nanoTime() - startTime;
        System.out.println("Get (101st): " + runTime + "ns");

        startTime = System.nanoTime();
        arrayList.remove(100);
        runTime = System.nanoTime() - startTime;
        System.out.println("Remove (101st): " + runTime + "ns");

        startTime = System.nanoTime();
        arrayList.isEmpty();
        runTime = System.nanoTime() - startTime;
        System.out.println("IsEmpty: " + runTime + "ns");

        startTime = System.nanoTime();
        arrayList.size();
        runTime = System.nanoTime() - startTime;
        System.out.println("Size: " + runTime + "ns");

        System.out.println();
        assertEquals(true, true);
    }
}
