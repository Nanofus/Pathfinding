package fi.nano.pathfinding;

import java.util.ArrayList;

/**
 *
 * @author Nanofus
 */
public class Node {

    private final ArrayList<Node> neighbours = new ArrayList<>();
    private final ArrayList<Boolean> isNeighbourDiagonal = new ArrayList<>();
    private boolean isWall;
    public Node parent;
    
    public double aStar_h;
    public double aStar_g;
    public double aStar_f;
    
    public double dijkstra_minDistance = Double.POSITIVE_INFINITY;

    public Node(boolean isWall) {
        this.isWall = isWall;

        this.aStar_g = Double.MAX_VALUE;
    }

    public void SetNeighbour(Node node) {
        neighbours.add(node);
    }
    
    public void SetNeighbourDiagonal(boolean b) {
        isNeighbourDiagonal.add(b);
    }

    public ArrayList<Node> GetNeighbours() {
        return neighbours;
    }
    
    public ArrayList<Boolean> GetNeighbourDiagonals() {
        return isNeighbourDiagonal;
    }

    public boolean IsWall() {
        return isWall;
    }

    public void SetWall(boolean isWall) {
        this.isWall = isWall;
    }

    /*@Override
    public String toString() {
        return "(h: " + h + ", g:" + g + ", f:" + f + ", neighbs: " + neighbours.toString() + ")";
    }*/

}
