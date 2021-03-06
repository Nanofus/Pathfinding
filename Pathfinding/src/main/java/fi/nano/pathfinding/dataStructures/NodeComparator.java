package fi.nano.pathfinding.dataStructures;

import fi.nano.pathfinding.structure.Node;
import java.util.Comparator;

/**
 * Komparaattori solmujen vertailua varten.
 *
 * @author Nanofus
 */
public class NodeComparator implements Comparator<Node> {

    private final int algoId;

    public NodeComparator(int algoId) {
        this.algoId = algoId;
    }

    /**
     * Vertaa kahta solmua keskenään tietyn algoritmin parametrien mukaan
     * @param first Solmu 1
     * @param second Solmu 2
     * @return
     */
    @Override
    public int compare(Node first, Node second) {
        
        //A*
        if (algoId == 0) {
            if (first.aStar_f > second.aStar_f) {
                return 1;
            }
            if (first.aStar_f < second.aStar_f) {
                return -1;
            }
            return 0;
            
        //Dijkstra
        } else if (algoId == 1) {
            if (first.dijkstra_minDistance > second.dijkstra_minDistance) {
                return 1;
            }
            if (second.dijkstra_minDistance > first.dijkstra_minDistance) {
                return -1;
            }
            return 0;
            
        } else {
            return 0;
        }
    }
}
