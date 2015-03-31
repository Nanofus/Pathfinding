package fi.nano.pathfinding.algorithms;

import fi.nano.pathfinding.Node;
import fi.nano.pathfinding.NodeComparator;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 * @author Nanofus
 */
public class AStar {

    private Map<Node, Map<Node, Double>> graph;
    private Map<Node, Map<Node, Double>> heuristicMap;

    public AStar() {
    }

    /**
     * The algorithm itself
     *
     * @param sPos Start position
     * @param ePos End position
     * @return Path as a graph
     */
    public ArrayList<Node> FindRoute(Node sPos, Node ePos) {
        Queue<Node> open = new PriorityQueue<>(11, new NodeComparator());
        HashSet<Node> closed = new HashSet<>();

        open.add(sPos);

        ArrayList<Node> path = new ArrayList<>();

        while (!open.isEmpty()) {
            Node node = open.poll();

            if (node.equals(ePos)) {
                return path(path, ePos);
            }

            closed.add(node);

            for (Node neighbour : node.GetNeighbours()) {
                if (closed.contains(neighbour)) {
                    continue;
                }

                double distance = neighbour
            }
        }

    }

    private ArrayList<Node> path(ArrayList<Node> path, Node ePos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
