/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.nano.pathfinding.dataStructures;

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
}
