package fi.nano.pathfinding;

import java.util.Comparator;

/**
 *
 * @author Nanofus
 */
public class NodeComparator implements Comparator<Node> {
        @Override
        public int compare(Node first, Node second) {
            if (first.f > second.f) return 1;
            if (second.f > first.f) return -1;
            return 0;
        }
    } 
