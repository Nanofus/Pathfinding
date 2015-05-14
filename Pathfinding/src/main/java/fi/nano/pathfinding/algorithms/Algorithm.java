package fi.nano.pathfinding.algorithms;

import fi.nano.pathfinding.structure.Node;

/**
 * Rajapinta, jonka polunetsintäalgoritmit toteuttavat.
 *
 * @author Nanofus
 */
public interface Algorithm {

    /**
     * Etsii reitin solmujen sPos ja ePos välillä ja palauttaa reitin.
     *
     * @param sPos Aloitussolmu
     * @param ePos Maalisolmu
     * @return Löytyykö polku
     */
    public boolean FindPath(Node sPos, Node ePos);
}
