
package fi.nano.pathfinding;


public class main {
    public static void main(String[] args) {
        MazeReader mazeReader = new MazeReader("41x41");
        
        AlgorithmRunner algorithmRunner = new AlgorithmRunner("A*", 0, true, mazeReader.GetMaze());
        
    }
}
