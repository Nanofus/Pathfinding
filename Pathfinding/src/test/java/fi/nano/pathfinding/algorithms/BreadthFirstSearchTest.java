package fi.nano.pathfinding.algorithms;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import fi.nano.pathfinding.Pathfinding;
import fi.nano.pathfinding.structure.Node;
import fi.nano.pathfinding.dataStructures.OwnArrayList;
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
public class BreadthFirstSearchTest {

    public BreadthFirstSearchTest() {
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

    /**
     * Tarkistaa löydettiinkö oikean mittainen reitti
     */
    @Test
    public void Test() {
        String[] paramArray = {"junit", "false", "Breadth-first", "false", "0", "5", "false", "false", "10", "10"};
        Pathfinding p = new Pathfinding(paramArray);
        p.Run();
        int steps = p.GetSteps();

        assertEquals(418, steps);
    }

    /**
     * Tarkistaa löydettiinkö oikean mittainen reitti niin, että vinottainen
     * liike on sallittua
     */
    @Test
    public void TestWithDiagonalMovement() {
        String[] paramArray = {"junit", "true", "Breadth-first", "false", "0", "5", "false", "false", "10", "10"};
        Pathfinding p = new Pathfinding(paramArray);
        p.Run();
        int steps = p.GetSteps();

        assertEquals(296, steps);
    }

    /**
     * Testaa algoritmin vastauksen jos reittiä ei löydy
     */
    @Test
    public void TestImpossible() {
        String[] paramArray = {"junit_impossible", "false", "Breadth-first", "false", "0", "5", "false", "false", "10", "10"};
        Pathfinding p = new Pathfinding(paramArray);
        p.Run();
        int steps = p.GetSteps();

        assertEquals(-1, steps);
    }

    /**
     * Testaa algoritmin vastauksen jos reittiä ei löydy, vinottainen liike
     * sallittu
     */
    @Test
    public void TestImpossibleWithDiagonalMovement() {
        String[] paramArray = {"junit_impossible", "true", "Breadth-first", "false", "0", "5", "false", "false", "11", "10"};
        Pathfinding p = new Pathfinding(paramArray);
        p.Run();
        int steps = p.GetSteps();

        assertEquals(-1, steps);
    }
}
