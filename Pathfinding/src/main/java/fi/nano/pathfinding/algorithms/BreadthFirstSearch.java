package fi.nano.pathfinding.algorithms;

import fi.nano.pathfinding.Node;
import fi.nano.pathfinding.dataStructures.OwnArrayList;
import fi.nano.pathfinding.dataStructures.OwnQueue;

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
        OwnQueue q = new OwnQueue();

        q.push(sPos);
        
        sPos.breadthfirst_visited = true;

        boolean finished = false;

        while (!q.isEmpty() && !finished) {
            Node node = q.pop();

            if (node.equals(ePos)) {
                finished = true;
            }

            for (int i = 0; i < node.GetNeighbours().size(); i++) {
                Node neighbour = node.GetNeighbours().get(i);

                if (!neighbour.breadthfirst_visited) {
                    neighbour.parent = node;
                    neighbour.breadthfirst_visited = true;

                    q.push(neighbour);
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
