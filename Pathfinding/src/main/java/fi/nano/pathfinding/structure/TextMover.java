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
public class TextMover {

    MazeEntity entity;
    OwnArrayList<String> movement;
    int row = 0;

    public TextMover(MazeEntity entity, OwnArrayList<String> movement) {
        this.entity = entity;
        this.movement = movement;
    }

    public void Move() {
        if (row < movement.size()) {
            String[] stringArray = movement.get(row).split(" ");
            int[] values = new int[stringArray.length];
            for (int i = 0; i < stringArray.length; i++) {
                values[i] = Integer.parseInt(stringArray[i]);
            }

            entity.MoveRelative(values[0], values[1]);

            row++;
        }
    }

}
