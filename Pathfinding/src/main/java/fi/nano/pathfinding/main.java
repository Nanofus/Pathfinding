package fi.nano.pathfinding;

import fi.nano.pathfinding.structure.AlgorithmRunner;
import fi.nano.pathfinding.structure.MazeReader;

public class main {

    public static void main(String[] args) {

        boolean allowDiagonal;
        String maze;
        if (args.length > 0) {
            maze = args[0];
            allowDiagonal = Boolean.parseBoolean(args[1]);
        } else {
            allowDiagonal = false;
            maze = "401x401";
        }

        MazeReader mazeReader = new MazeReader(maze);

        AlgorithmRunner aStar = new AlgorithmRunner("A*", 0, allowDiagonal, mazeReader.GetMaze());
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

    }
}
