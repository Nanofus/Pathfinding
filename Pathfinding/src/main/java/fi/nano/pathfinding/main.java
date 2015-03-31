
package fi.nano.pathfinding;


public class main {
    public static void main(String[] args) {
        MazeReader mazeReader = new MazeReader("41x41");
        
        AlgorithmRunner mazeTester = new AlgorithmRunner("Dijkstra", 0, false, mazeReader.GetMaze());
        
    }
}
