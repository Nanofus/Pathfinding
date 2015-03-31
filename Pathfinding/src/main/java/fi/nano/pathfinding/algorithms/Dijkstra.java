/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.nano.pathfinding.algorithms;

import fi.nano.pathfinding.Node;
import fi.nano.pathfinding.NodeComparator;
import fi.nano.pathfinding.dataStructures.OArrayList;
import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 *
 * @author Nanofus
 */
public class Dijkstra implements Pathfinding {

    @Override
    public OArrayList<Node> FindPath(Node sPos, Node ePos) {
        sPos.dijkstra_minDistance = 0;

        PriorityQueue<Node> open = new PriorityQueue<>(11, new NodeComparator());

        open.add(sPos);

        boolean finished = false;

        while (!open.isEmpty()) {
            Node node = open.poll();

            if (node.equals(ePos)) {
                finished = true;
            }

            for (int i = 0; i < node.GetNeighbours().size(); i++) {
                Node neighbour = node.GetNeighbours().get(i);
                boolean isDiagonal = node.GetNeighbourDiagonals().get(i);

                double weight = 1;
                if (isDiagonal) {
                    weight = 1.4;
                }

                double distanceThroughNode = node.dijkstra_minDistance + weight;

                if (distanceThroughNode < neighbour.dijkstra_minDistance) {
                    open.remove(neighbour);
                    neighbour.dijkstra_minDistance = distanceThroughNode;
                    neighbour.parent = node;
                    open.add(neighbour);
                }
            }
        }

        if (!finished) {
            return null;
        }
        return Pathify(sPos, ePos);
    }

    private OArrayList<Node> Pathify(Node sPos, Node ePos) {
        OArrayList<Node> path = new OArrayList<>();

        Node start = ePos;

        while (start != sPos) {
            path.add(start);
            start = start.parent;
        }
        path.add(start);

        return path;
    }

}
