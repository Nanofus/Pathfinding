/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.nano.pathfinding.algorithms;

import fi.nano.pathfinding.Node;
import fi.nano.pathfinding.dataStructures.OArrayList;

/**
 *
 * @author Nanofus
 */
public interface Pathfinding {
    public OArrayList<Node> FindPath(Node sPos, Node ePos);
}
