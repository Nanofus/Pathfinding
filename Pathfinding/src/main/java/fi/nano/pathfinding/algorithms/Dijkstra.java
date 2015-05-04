package fi.nano.pathfinding.algorithms;

import fi.nano.pathfinding.structure.Node;
import fi.nano.pathfinding.dataStructures.NodeComparator;
import fi.nano.pathfinding.dataStructures.OwnBinaryHeap;

/**
 * Dijkstran algoritmin toteutus.
 *
 * @author Nanofus
 */
public class Dijkstra implements Algorithm {

    OwnBinaryHeap open;
    
    /**
     * Etsii polun solmusta toiseen Dijkstran algoritmilla.
     *
     * @param sPos Alkusolmu
     * @param ePos Maalisolmu
     * @return
     */
    @Override
    public boolean FindPath(Node sPos, Node ePos) {
        sPos.dijkstra_minDistance = 0;

        open = new OwnBinaryHeap(new NodeComparator(1));
        //PriorityQueue<Node> open = new PriorityQueue<>(11,new NodeComparator(1));

        open.add(sPos);

        boolean finished = false;

        while (!open.isEmpty() && !finished) {
            Node node = open.poll();

            if (node.equals(ePos)) {
                finished = true;
            }

            InspectNeighbours(node);
        }

        return finished;
    }

    /**
     * Tutkii solmun naapurit
     * @param node Solmu
     */
    private void InspectNeighbours(Node node) {
        for (int i = 0; i < node.GetNeighbours().size(); i++) {
            Node neighbour = node.GetNeighbours().get(i);
            
            if (neighbour.IsWall()) {
                continue;
            }
            
            boolean isDiagonal = node.GetNeighbourDiagonals().get(i);

            double weight = 10;
            if (isDiagonal) {
                weight = 14;
            }

            double distanceThroughNode = node.dijkstra_minDistance + weight;

            if (distanceThroughNode < neighbour.dijkstra_minDistance) {
                //open.remove(neighbour);
                neighbour.dijkstra_minDistance = distanceThroughNode;
                neighbour.parent = node;
                open.add(neighbour);
            }
        }
    }
}
