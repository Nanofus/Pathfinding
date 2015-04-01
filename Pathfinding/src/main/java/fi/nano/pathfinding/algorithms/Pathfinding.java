
package fi.nano.pathfinding.algorithms;

import fi.nano.pathfinding.Node;
import fi.nano.pathfinding.dataStructures.OwnArrayList;

/**
 * Rajapinta, jonka polunetsintäalgoritmit toteuttavat.
 * @author Nanofus
 */
public interface Pathfinding {
    /**
     * Etsii reitin solmujen sPos ja ePos välillä ja palauttaa reitin.
     * @param sPos Aloitussolmu
     * @param ePos Maalisolmu
     * @return 
     */
    public OwnArrayList<Node> FindPath(Node sPos, Node ePos);
}
