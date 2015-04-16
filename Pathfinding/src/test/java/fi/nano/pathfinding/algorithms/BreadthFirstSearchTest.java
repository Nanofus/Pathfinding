package fi.nano.pathfinding.algorithms;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import fi.nano.pathfinding.structure.AlgorithmRunner;
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
        MazeReader mazeReader = new MazeReader("junit");
        AlgorithmRunner mazeTester = new AlgorithmRunner("Breadth-first", 0, false, mazeReader.GetMaze());

        OwnArrayList<Node> path = mazeTester.GetSolution();

        assertEquals(418, path.size());
    }
    
    /**
     * Tarkistaa löydettiinkö oikean mittainen reitti niin, että vinottainen liike on sallittua
     */
    @Test
    public void TestWithDiagonalMovement() {
        MazeReader mazeReader = new MazeReader("junit");
        AlgorithmRunner mazeTester = new AlgorithmRunner("Breadth-first", 0, true, mazeReader.GetMaze());

        OwnArrayList<Node> path = mazeTester.GetSolution();

        assertEquals(296, path.size());
    }
    
    /**
     * Testaa algoritmin vastauksen jos reittiä ei löydy
     */
    @Test
    public void TestImpossible() {
        MazeReader mazeReader = new MazeReader("junit_impossible");
        AlgorithmRunner mazeTester = new AlgorithmRunner("Breadth-first", 0, false, mazeReader.GetMaze());

        OwnArrayList<Node> path = mazeTester.GetSolution();

        assertEquals(0, path.size());
    }
    
    /**
     * Testaa algoritmin vastauksen jos reittiä ei löydy, vinottainen liike sallittu
     */
    @Test
    public void TestImpossibleWithDiagonalMovement() {
        MazeReader mazeReader = new MazeReader("junit_impossible");
        AlgorithmRunner mazeTester = new AlgorithmRunner("Breadth-first", 0, true, mazeReader.GetMaze());

        OwnArrayList<Node> path = mazeTester.GetSolution();

        assertEquals(0, path.size());
    }
}
