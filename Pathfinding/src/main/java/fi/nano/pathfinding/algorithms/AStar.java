package fi.nano.pathfinding.algorithms;

import fi.nano.pathfinding.structure.Node;
import fi.nano.pathfinding.dataStructures.NodeComparator;
import fi.nano.pathfinding.dataStructures.OwnBinaryHeap;

/**
 * A*-polunetsintäalgoritmin toteutus.
 *
 * @author Nanofus
 */
public class AStar implements Algorithm {

    OwnBinaryHeap open;
    //PriorityQueue<Node> open = new PriorityQueue<>(11,new NodeComparator(0));

    Node end;

    /**
     * A*-algoritmin polunetsintä
     *
     * @param sPos Aloitussijainti
     * @param ePos Loppusijainti
     * @return Polku listana nodeista
     */
    @Override
    public boolean FindPath(Node sPos, Node ePos) {
        open = new OwnBinaryHeap(new NodeComparator(0));
        
        end = ePos;

        sPos.aStar_g = 0;
        sPos.aStar_f = sPos.aStar_h;
        open.add(sPos);
        sPos.aStar_open = true;

        boolean finished = false;

        while (!open.isEmpty() && !finished) {
            Node node = open.poll();
            node.aStar_open = false;
            node.aStar_closed = true;

            if (node.equals(end)) {
                finished = true;
                break;
            }

            for (int i = 0; i < node.GetNeighbours().size(); i++) {
                InspectNeighbour(node, node.GetNeighbours().get(i), node.GetNeighbourDiagonals().get(i));
            }
        }

        return finished;
    }

    private int EstimateDistance(Node node) {
        int xDiffer = end.x - node.x;
        int yDiffer = end.y - node.y;
        return (int) Math.sqrt((xDiffer * xDiffer) + (yDiffer * yDiffer));
    }

    private void InspectNeighbour(Node node, Node neighbour, boolean isDiagonal) {
        if (neighbour.aStar_closed || neighbour.IsWall()) {
            return;
        }

        int temp_h = EstimateDistance(neighbour);

        int weight = 10;
        if (!isDiagonal) {
            weight = 15;
        }
        
        int temp_g = node.aStar_g + weight;

        if (!neighbour.aStar_open) {
            neighbour.parent = node;

            neighbour.aStar_g = temp_g;
            neighbour.aStar_h = temp_h;
            neighbour.aStar_f = neighbour.aStar_h + neighbour.aStar_g;

            open.add(neighbour);
            
            neighbour.aStar_open = true;
        } else {
            if (temp_g < neighbour.aStar_g) {
                neighbour.parent = node;

                neighbour.aStar_g = temp_g;
                neighbour.aStar_h = temp_h;
                neighbour.aStar_f = neighbour.aStar_h + neighbour.aStar_g;

                open.decreaseKey(neighbour);
            }
        }
    }
}
