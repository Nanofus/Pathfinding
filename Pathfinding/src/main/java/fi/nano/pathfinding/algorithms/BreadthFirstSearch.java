package fi.nano.pathfinding.algorithms;

import fi.nano.pathfinding.Node;
import fi.nano.pathfinding.dataStructures.NodeComparator;
import fi.nano.pathfinding.dataStructures.OwnArrayList;
import java.util.PriorityQueue;

/**
 * Dijkstran algoritmin toteutus.
 *
 * @author Nanofus
 */
public class BreadthFirstSearch implements Algorithm {

    /**
     * Etsii polun solmusta toiseen leveyshaun avulla.
     *
     * @param sPos Alkusolmu
     * @param ePos Maalisolmu
     * @return
     */
    @Override
    public OwnArrayList<Node> FindPath(Node sPos, Node ePos) {
        PriorityQueue<Node> open = new PriorityQueue<>(11, new NodeComparator(1));

        open.add(sPos);

        boolean finished = false;

        while (!open.isEmpty()) {
            Node node = open.poll();
            node.breadthfirst_visited = true;

            if (node.equals(ePos)) {
                finished = true;
            }

            for (int i = 0; i < node.GetNeighbours().size(); i++) {
                Node neighbour = node.GetNeighbours().get(i);

                if (!neighbour.breadthfirst_visited) {
                    neighbour.parent = node;
                    
                    boolean isDiagonal = node.GetNeighbourDiagonals().get(i);

                    double weight = 1;
                    if (isDiagonal) {
                        weight = 1.4;
                    }

                    open.add(neighbour);
                }
            }
        }

        if (!finished) {
            return null;
        }
        return Pathify(sPos, ePos);
    }

    /**
     * Luo polkulistan käymällä solmut läpi
     *
     * @param sPos Aloitussolmu
     * @param ePos Lopetussolmu
     * @return
     */
    private OwnArrayList<Node> Pathify(Node sPos, Node ePos) {
        OwnArrayList<Node> path = new OwnArrayList<>();

        Node start = ePos;

        while (start != sPos) {
            path.add(start);
            start = start.parent;
        }
        path.add(start);

        return path;
    }

}
