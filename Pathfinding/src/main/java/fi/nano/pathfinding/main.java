package fi.nano.pathfinding;

import fi.nano.pathfinding.dataStructures.OwnArrayList;
import fi.nano.pathfinding.structure.AlgorithmRunner;
import fi.nano.pathfinding.structure.MazeEntity;
import fi.nano.pathfinding.structure.MazeReader;
import fi.nano.pathfinding.structure.Node;
import fi.nano.pathfinding.ui.Window;
import java.util.concurrent.TimeUnit;
import javax.swing.SwingUtilities;

public class main {

    public static void main(String[] args) throws InterruptedException {

        boolean allowDiagonal;
        String maze;
        if (args.length > 0) {
            maze = args[0];
            allowDiagonal = Boolean.parseBoolean(args[1]);
        } else {
            allowDiagonal = false;
            maze = "41x41";
        }

        MazeReader mazeReader = new MazeReader(maze);

        MazeEntity chaser = new MazeEntity(1, 1);
        MazeEntity chased = new MazeEntity(31, 31);
        AlgorithmRunner runner = new AlgorithmRunner("A*", 2, allowDiagonal, mazeReader.GetMaze(), chaser, chased);
        
        Window window = new Window(runner, 1024, 768);
        SwingUtilities.invokeLater(window);
        
        boolean done = false;
        boolean recalculateNeeded = true;
        OwnArrayList<Node> path = new OwnArrayList<>();
        int chasedMoveTimer = 5;
        while (!done) {
            System.out.println("");
            //runner.PrintMaze();

            chasedMoveTimer--;
            if (chasedMoveTimer == 0) {
                chasedMoveTimer = 5;
                recalculateNeeded = true;
            }

            System.out.println(path.size());
            
            if (recalculateNeeded) {
                path = runner.Pathfind();
                recalculateNeeded = false;
            }
            chaser.MakeMove(path);
            TimeUnit.MILLISECONDS.sleep(100);

            if (chaser.x == chased.x && chaser.y == chased.y) {
                done = true;
            }
        }
        System.out.println("\nFINISHED");

        /*AlgorithmRunner aStar = new AlgorithmRunner("A*", 0, allowDiagonal, mazeReader.GetMaze());
         AlgorithmRunner dijkstra = new AlgorithmRunner("Dijkstra", 0, allowDiagonal, mazeReader.GetMaze());
         AlgorithmRunner breadthFirst = new AlgorithmRunner("Breadth-first", 0, allowDiagonal, mazeReader.GetMaze());
         AlgorithmRunner depthFirst = new AlgorithmRunner("Depth-first", 0, allowDiagonal, mazeReader.GetMaze());

         System.out.println("\n\n\n////\n");

         System.out.println("Maze: " + maze);

         System.out.println("\nA*:");
         System.out.println("Runtime: " + aStar.GetRunTime());
         System.out.println("Route length: " + aStar.GetSolution().size());

         System.out.println("\nDijkstra:");
         System.out.println("Runtime: " + dijkstra.GetRunTime());
         System.out.println("Route length: " + dijkstra.GetSolution().size());

         System.out.println("\nBreadth-first:");
         System.out.println("Runtime: " + breadthFirst.GetRunTime());
         System.out.println("Route length: " + breadthFirst.GetSolution().size());

         System.out.println("\nDepth-first:");
         System.out.println("Runtime: " + depthFirst.GetRunTime());
         System.out.println("Route length: " + depthFirst.GetSolution().size());
         */
    }
}
