/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.nano.pathfinding;

import java.util.ArrayList;

/**
 *
 * @author Nanofus
 */
class MazeTester {

    private String testedAlgorithm;
    private int testMode;
    
    private float doorCloseInterval = 2000;
    
    ArrayList<String> maze;
    ArrayList<String> positions;
    
    public MazeTester(String testedAlgorithm, int testMode, ArrayList<String> maze, ArrayList<String> positions) {
        this.testedAlgorithm = testedAlgorithm;
        this.testMode = testMode;
        this.maze = maze;
        this.positions = positions;
        
        CreateMaze();
        InitializeAlgorithm();
        InitializeTestMode();
        
        Start();
    }

    private void CreateMaze() {
    }

    private void InitializeAlgorithm() {
    }

    private void InitializeTestMode() {
    }

    private void Start() {
    }
    
}