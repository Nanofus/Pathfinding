package fi.nano.pathfinding.algorithms;

import fi.nano.pathfinding.structure.Node;
import fi.nano.pathfinding.dataStructures.OwnQueue;

/**
 * Leveyshaku.
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
    public boolean FindPath(Node sPos, Node ePos) {
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

        return finished;
    }
}
