
package fi.nano.pathfinding;


public class main {
    public static void main(String[] args) {
        MazeReader mazeReader = new MazeReader("maze");
        
        MazeTester mazeTester = new MazeTester("A*", 0, mazeReader.GetMaze(),mazeReader.GetPositions());
        
    }
}
