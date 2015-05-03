/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.nano.pathfinding.structure;

import fi.nano.pathfinding.dataStructures.OwnArrayList;
import fi.nano.pathfinding.structure.MazeEntity;

/**
 *
 * @author Nanofus
 */
public class TargetMover {

    MazeEntity entity;
    OwnArrayList<String> movement;
    int row = 1;

    public TargetMover(MazeEntity entity, OwnArrayList<String> movement) {
        this.entity = entity;
        this.movement = movement;
    }

    public void Move() {
        if (row < movement.size()) {
            String[] stringArray = movement.get(row).split(" ");
            entity.MoveRelative(Integer.parseInt(stringArray[0]), Integer.parseInt(stringArray[1]));

            row++;
        }
    }

}