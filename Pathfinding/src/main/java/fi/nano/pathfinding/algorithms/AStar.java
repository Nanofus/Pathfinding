package fi.nano.pathfinding.algorithms;

import fi.nano.pathfinding.Node;
import fi.nano.pathfinding.NodeComparator;
import fi.nano.pathfinding.dataStructures.OwnArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * A*-polunetsintäalgoritmin toteutus.
 * @author Nanofus
 */
public class AStar implements Algorithm {

    public AStar() {
    }

    /**
     * A*-algoritmin polunetsintä
     *
     * @param sPos Aloitussijainti
     * @param ePos Loppusijainti
     * @return Polku listana nodeista
     */
    @Override
    public OwnArrayList<Node> FindPath(Node sPos, Node ePos) {

        HashSet<Node> closed = new HashSet<>();
        PriorityQueue<Node> open = new PriorityQueue<>(11, new NodeComparator(0));

        open.add(sPos);

        sPos.aStar_g = 0;

        boolean finished = false;

        while (!open.isEmpty()) {
            Node node = open.poll();

            if (node.equals(ePos)) {
                finished = true;
            }

            closed.add(node);

            for (int i = 0; i < node.GetNeighbours().size(); i++) {
                Node neighbour = node.GetNeighbours().get(i);
                boolean isDiagonal = node.GetNeighbourDiagonals().get(i);

                double weight = 1;
                if (isDiagonal) {
                    weight = 1.4;
                }

                double temp_g = node.aStar_g + weight;
                double temp_f = temp_g + neighbour.aStar_h;

                if (closed.contains(neighbour) && (temp_f > neighbour.aStar_f)) {
                    continue;
                }

                if (!open.contains(neighbour) || (temp_f < neighbour.aStar_f)) {
                    neighbour.parent = node;
                    open.add(neighbour);
                    neighbour.aStar_g = temp_g;
                    neighbour.aStar_f = temp_f;

                    if (open.contains(neighbour)) {
                        open.remove(neighbour);
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
     * Käy läpi solmujen vanhemmat ja muodostaa niistä listan
     * @param sPos Aloitussolmu
     * @param ePos Maalisolmu
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
