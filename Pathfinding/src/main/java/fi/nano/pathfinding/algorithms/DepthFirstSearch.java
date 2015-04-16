package fi.nano.pathfinding.algorithms;

import fi.nano.pathfinding.structure.Node;
import fi.nano.pathfinding.dataStructures.OwnArrayList;

/**
 * Ahne syvyyshaku, joka jatkaa lähtökohtaisesti solmun ensimmäistä lasta pitkin.
 *
 * @author Nanofus
 */
public class DepthFirstSearch implements Algorithm {

    boolean finished = false;
    Node ePos;

    /**
     * Etsii polun solmusta toiseen syvyyshaun avulla.
     *
     * @param sPos Alkusolmu
     * @param ePos Maalisolmu
     * @return
     */
    @Override
    public boolean FindPath(Node sPos, Node ePos) {
        this.ePos = ePos;

        Search(sPos);

        return finished;
    }

    /**
     * Rekursiivinen metodi jolla etsitään tietä maaliin
     *
     * @param node Nykyinen solmu
     */
    private void Search(Node node) {
        node.depthfirst_visited = true;

        if (node.equals(ePos)) {
            finished = true;
            return;
        }

        for (int i = 0; i < node.GetNeighbours().size(); i++) {
            Node neighbour = node.GetNeighbours().get(i);

            if (!neighbour.depthfirst_visited) {
                neighbour.parent = node;
                Search(neighbour);
            }
        }
    }
}
