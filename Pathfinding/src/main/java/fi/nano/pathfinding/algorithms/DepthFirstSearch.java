package fi.nano.pathfinding.algorithms;

import fi.nano.pathfinding.Node;
import fi.nano.pathfinding.dataStructures.OwnArrayList;

/**
 * Dijkstran algoritmin toteutus.
 *
 * @author Nanofus
 */
public class DepthFirstSearch implements Algorithm {

    boolean finished = false;
    Node ePos;
    OwnArrayList<Node> path;

    /**
     * Etsii polun solmusta toiseen syvyyshaun avulla.
     *
     * @param sPos Alkusolmu
     * @param ePos Maalisolmu
     * @return
     */
    @Override
    public OwnArrayList<Node> FindPath(Node sPos, Node ePos) {
        this.ePos = ePos;
        
        Search(sPos);

        if (!finished) {
            return null;
        }

        return Pathify(sPos, ePos);
    }

    /**
     * Rekursiivinen metodi jolla etsitään tietä maaliin
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

    /**
     * Luo polkulistan käymällä solmut läpi
     *
     * @param sPos Aloitussolmu
     * @param ePos Lopetussolmu
     * @return
     */
    private OwnArrayList<Node> Pathify(Node sPos, Node ePos) {
        path = new OwnArrayList<>();

        Node start = ePos;

        while (start != sPos) {
            path.add(start);
            start = start.parent;
        }
        path.add(start);

        return path;
    }

}
