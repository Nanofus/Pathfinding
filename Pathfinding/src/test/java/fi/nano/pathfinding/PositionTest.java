/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.nano.pathfinding;

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
public class PositionTest {
    
    public PositionTest() {
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
    public void TestMoveX() {
        Position position = new Position(1,1);
        
        position.Move(5, -3);
        
        assertEquals(6,position.x);
    }
    
    @Test
    public void TestMoveY() {
        Position position = new Position(1,1);
        
        position.Move(5, -3);
        
        assertEquals(-2,position.y);
    }
    
    @Test
    public void TestSetPosition() {
        Position position = new Position(5,2);
        
        position.SetPosition(-50, 10);
        
        boolean works = false;
        if (position.x == -50 && position.y == 10) {
            works = true;
        }
        
        assertEquals(true,works);
    }
}
