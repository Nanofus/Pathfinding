/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.nano.pathfinding.algorithms;

import fi.nano.pathfinding.Pathfinding;
import fi.nano.pathfinding.AlgorithmRunner;
import fi.nano.pathfinding.structure.MazeReader;
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
public class DijkstraTest {

    public DijkstraTest() {
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
    public void TestDijkstra() {
        String[] paramArray = {"junit", "false", "Dijkstra", "5", "10", "10", "false", "false", "false", "0"};
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
    public void TestDijkstraWithDiagonalMovement() {
        String[] paramArray = {"junit", "true", "Dijkstra", "5", "10", "10", "false", "false", "false", "0"};
        Pathfinding p = new Pathfinding(paramArray);
        p.Run();
        int steps = p.GetSteps();

        assertEquals(296, steps);
    }

    /**
     * Testaa algoritmin vastauksen jos reittiä ei löydy
     */
    @Test
    public void TestDijkstraImpossible() {
        String[] paramArray = {"junit_impossible", "false", "Dijkstra", "5", "10", "10", "false", "false", "false", "0"};
        Pathfinding p = new Pathfinding(paramArray);
        p.Run();
        int steps = p.GetSteps();

        assertEquals(10, steps);
    }

    /**
     * Testaa algoritmin vastauksen jos reittiä ei löydy, vinottainen liike
     * sallittu
     */
    @Test
    public void TestDijkstraImpossibleWithDiagonalMovement() {
        String[] paramArray = {"junit_impossible", "true", "Dijkstra", "5", "10", "10", "false", "false", "false", "0"};
        Pathfinding p = new Pathfinding(paramArray);
        p.Run();
        int steps = p.GetSteps();

        assertEquals(10, steps);
    }

    /**
     * Testaa suoruutujen toimintaa
     */
    @Test
    public void TestDijkstraSwamp() {
        String[] paramArray = {"junit_swamp", "false", "Dijkstra", "5", "10", "10", "false", "false", "false", "0"};
        Pathfinding p = new Pathfinding(paramArray);
        p.Run();
        int steps = p.GetSteps();

        assertEquals(91, steps);
    }

    /**
     * Testaa jääruutujen toimintaa
     */
    @Test
    public void TestDijkstraIce() {
        String[] paramArray = {"junit_ice", "false", "Dijkstra", "5", "10", "10", "false", "false", "false", "0"};
        Pathfinding p = new Pathfinding(paramArray);
        p.Run();
        int steps = p.GetSteps();

        assertEquals(91, steps);
    }

    /**
     * Testaa ovien toimintaa
     */
    @Test
    public void TestDijkstraDoors() {
        String[] paramArray = {"junit_doors", "false", "Dijkstra", "5", "20", "20", "false", "false", "false", "0"};
        Pathfinding p = new Pathfinding(paramArray);
        p.Run();
        int steps = p.GetSteps();

        assertEquals(107, steps);
    }

    /**
     * Testaa liikkuvan kohteen toimintaa
     */
    @Test
    public void TestDijkstraMovingTarget() {
        String[] paramArray = {"junit_moving", "false", "Dijkstra", "5", "10", "10", "false", "false", "false", "0"};
        Pathfinding p = new Pathfinding(paramArray);
        p.Run();
        int steps = p.GetSteps();

        assertEquals(210, steps);
    }
}
