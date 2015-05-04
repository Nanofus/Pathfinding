/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.nano.pathfinding;

import fi.nano.pathfinding.structure.TargetMover;
import fi.nano.pathfinding.dataStructures.OwnArrayList;
import fi.nano.pathfinding.structure.AlgorithmRunner;
import fi.nano.pathfinding.structure.MazeEntity;
import fi.nano.pathfinding.structure.MazeReader;
import fi.nano.pathfinding.structure.Node;
import fi.nano.pathfinding.structure.Position;
import fi.nano.pathfinding.ui.Window;
import java.util.concurrent.TimeUnit;
import javax.swing.SwingUtilities;

/**
 *
 * @author Nanofus
 */
public class Pathfinding {

    private long totalTime = 0;
    private int moveDelay;
    private int doorDelay;
    private int waitInMillis;
    private boolean allowDiagonal;
    private String algo;
    private boolean smallTiles;
    private String maze;

    private MazeReader mazeReader;
    private MazeEntity chaser;
    private MazeEntity chased;
    private AlgorithmRunner runner;
    private TargetMover mover;

    private Window window;
    private boolean windowEnabled;
    private boolean logEnabled;

    private boolean done;
    private boolean recalculateNeeded;
    private OwnArrayList<Node> path;
    private int chasedMoveTimer;
    private int doorTimer;
    private int steps;
    private int recalcs;

    Pathfinding(String[] args) {
        ParseArgs(args);
        Init();
        Run();
        System.out.println("\nDONE - Time: " + totalTime * 0.000001 + " ms, steps: " + steps + ", per step: " + (totalTime * 0.000001) / steps + " ms, recalcs: " + recalcs + ", average calc time: " + (totalTime * 0.000001) / recalcs + " ms");
    }

    private void ParseArgs(String[] args) {
        if (args.length > 8) {
            maze = args[0];
            allowDiagonal = Boolean.parseBoolean(args[1]);
            algo = args[2];
            smallTiles = Boolean.parseBoolean(args[3]);
            waitInMillis = Integer.parseInt(args[4]);
            moveDelay = Integer.parseInt(args[5]);
            logEnabled = Boolean.parseBoolean(args[6]);
            windowEnabled = Boolean.parseBoolean(args[7]);
            doorDelay = Integer.parseInt(args[8]);
        } else {
            maze = "121x121 circle";
            allowDiagonal = false;
            algo = "A*";
            smallTiles = false;
            waitInMillis = 200;
            moveDelay = 5;
            logEnabled = true;
            windowEnabled = true;
            doorDelay = 10;
        }
    }

    private void Init() {
        mazeReader = new MazeReader(maze);

        String[] chasedStartPos = mazeReader.GetTargetMovement().get(0).split(" ");

        chaser = new MazeEntity(1, 1);
        chased = new MazeEntity(Integer.parseInt(chasedStartPos[0]), Integer.parseInt(chasedStartPos[1]));
        runner = new AlgorithmRunner(algo, allowDiagonal, mazeReader.GetMaze(), chaser, chased);
        mover = new TargetMover(chased, mazeReader.GetTargetMovement());

        if (windowEnabled) {
            window = new Window(runner, smallTiles, waitInMillis, 1024, 768);
            SwingUtilities.invokeLater(window);
        }

        recalculateNeeded = true;
        path = new OwnArrayList<>();
        chasedMoveTimer = moveDelay;
        doorTimer = doorDelay;
        steps = 0;
    }

    private void Run() {
        while (true) {
            chasedMoveTimer--;
            if (chasedMoveTimer == 0) {
                chasedMoveTimer = moveDelay;
                int oldX = chased.x;
                int oldY = chased.y;
                mover.Move();
                if (chased.x != oldX || chased.y != oldY) {
                    recalculateNeeded = true;
                }
            }
            
            doorTimer--;
            if (doorTimer == 0) {
                doorTimer = doorDelay;
                for (int i = 0; i < runner.GetDoors().size(); i++) {
                    if(runner.GetDoors().get(i).IsWall()) {
                        runner.GetDoors().get(i).SetWall(false);
                    } else {
                        runner.GetDoors().get(i).SetWall(true);
                    }
                }
            }

            if (recalculateNeeded) {
                path = runner.Pathfind(new Position(chaser.x, chaser.y), new Position(chased.x, chased.y));
                totalTime = totalTime + runner.GetRunTime();
                if (logEnabled) {
                    if (path.isEmpty()) {
                        System.out.print("Path not found - ");
                    }
                    System.out.println("Time (ms) spent calculating: " + (runner.GetRunTime() * 0.000001) + " (total: " + (totalTime * 0.000001) + "), path length: " + path.size() + " (time per step: " + (runner.GetRunTime() * 0.000001) / path.size() + ")");
                }
                recalculateNeeded = false;
                recalcs++;
            }

            chaser.MakeMove(path);
            steps++;

            if (windowEnabled) {
                try {
                    TimeUnit.MILLISECONDS.sleep(waitInMillis);
                } catch (InterruptedException ex) {
                }
            }

            if (chaser.x == chased.x && chaser.y == chased.y) {
                break;
            }
        }
    }
}
