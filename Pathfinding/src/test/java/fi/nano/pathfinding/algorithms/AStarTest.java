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
public class AStarTest {

    public AStarTest() {
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
    public void TestAStar() {
        String[] paramArray = {"junit", "false", "A*", "5", "10", "10", "false", "false", "false", "0"};
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
    public void TestAStarWithDiagonalMovement() {
        String[] paramArray = {"junit", "true", "A*", "5", "10", "10", "false", "false", "false", "0"};
        Pathfinding p = new Pathfinding(paramArray);
        p.Run();
        int steps = p.GetSteps();

        assertEquals(296, steps);
    }

    /**
     * Testaa algoritmin vastauksen jos reittiä ei löydy
     */
    @Test
    public void TestAStarImpossible() {
        String[] paramArray = {"junit_impossible", "false", "A*", "5", "10", "10", "false", "false", "false", "0"};
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
    public void TestAStarImpossibleWithDiagonalMovement() {
        String[] paramArray = {"junit_impossible", "true", "A*", "5", "10", "10", "false", "false", "false", "0"};
        Pathfinding p = new Pathfinding(paramArray);
        p.Run();
        int steps = p.GetSteps();

        assertEquals(10, steps);
    }

    /**
     * Testaa suoruutujen toimintaa
     */
    @Test
    public void TestAStarSwamp() {
        String[] paramArray = {"junit_swamp", "false", "A*", "5", "10", "10", "false", "false", "false", "0"};
        Pathfinding p = new Pathfinding(paramArray);
        p.Run();
        int steps = p.GetSteps();

        assertEquals(91, steps);
    }

    /**
     * Testaa jääruutujen toimintaa
     */
    @Test
    public void TestAStarIce() {
        String[] paramArray = {"junit_ice", "false", "A*", "5", "10", "10", "false", "false", "false", "0"};
        Pathfinding p = new Pathfinding(paramArray);
        p.Run();
        int steps = p.GetSteps();

        assertEquals(91, steps);
    }

    /**
     * Testaa ovien toimintaa
     */
    @Test
    public void TestAStarDoors() {
        String[] paramArray = {"junit_doors", "false", "A*", "5", "20", "12", "false", "false", "false", "0"};
        Pathfinding p = new Pathfinding(paramArray);
        p.Run();
        int steps = p.GetSteps();

        assertEquals(107, steps);
    }

    /**
     * Testaa liikkuvan kohteen toimintaa
     */
    @Test
    public void TestAStarMovingTarget() {
        String[] paramArray = {"junit_moving", "false", "A*", "5", "10", "12", "false", "false", "false", "0"};
        Pathfinding p = new Pathfinding(paramArray);
        p.Run();
        int steps = p.GetSteps();

        assertEquals(210, steps);
    }
}
