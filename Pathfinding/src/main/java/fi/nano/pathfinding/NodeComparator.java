package fi.nano.pathfinding;

import java.util.Comparator;

/**
 *
 * @author Nanofus
 */
public class NodeComparator implements Comparator<Node> {
        @Override
        public int compare(Node first, Node second) {
            if (first.aStar_f > second.aStar_f) return 1;
            if (second.aStar_f > first.aStar_f) return -1;
            return 0;
        }
    } 
