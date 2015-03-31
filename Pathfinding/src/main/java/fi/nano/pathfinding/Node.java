package fi.nano.pathfinding;

import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author Nanofus
 */
public class Node {
    
    private ArrayList<Node> neighbours;
    private boolean isWall;
    
    private Map<Node, Double> heuristic;
    
    public Node parent;
    public double h;
    public double g;
    public double f;
    
    public Node(boolean isWall, Map<Node, Double> heuristic) {
        this.isWall = isWall;
        
        this.g = Double.MAX_VALUE;
        this.heuristic = heuristic;
    }
    
    public void CalcG(Node other) {
        this.h = heuristic.get(other);
        this.f = h + g;
    }
    
    public void SetNeighbour(Node node) {
        neighbours.add(node);
    }
    
    public ArrayList<Node> GetNeighbours() {
        return neighbours;
    }
    
    public boolean IsWall() {
        return isWall;
    }
    
    public void SetWall(boolean isWall) {
        this.isWall = isWall;
    }
    
}
