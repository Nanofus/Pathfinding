/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.nano.pathfinding.ui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Nanofus
 */
public class ImageLoader {

    private BufferedImage wall;
    private BufferedImage floor;
    private BufferedImage path;
    private BufferedImage door;
    private BufferedImage chaser;
    private BufferedImage chased;
    private BufferedImage swamp;
    private BufferedImage ice;
    private BufferedImage swall;
    private BufferedImage sfloor;
    private BufferedImage spath;
    private BufferedImage sdoor;
    private BufferedImage schaser;
    private BufferedImage schased;
    private BufferedImage sswamp;
    private BufferedImage sice;

    private boolean smallTiles = false;

    public ImageLoader(boolean smallTiles) {
        this.smallTiles = smallTiles;
        try {
            wall = ImageIO.read(new File("graphics/wall.png"));
            floor = ImageIO.read(new File("graphics/floor.png"));
            path = ImageIO.read(new File("graphics/path.png"));
            door = ImageIO.read(new File("graphics/door.png"));
            chaser = ImageIO.read(new File("graphics/chaser.png"));
            chased = ImageIO.read(new File("graphics/chased.png"));
            swamp = ImageIO.read(new File("graphics/swamp.png"));
            ice = ImageIO.read(new File("graphics/ice.png"));
            swall = ImageIO.read(new File("graphics/walls.png"));
            sfloor = ImageIO.read(new File("graphics/floors.png"));
            spath = ImageIO.read(new File("graphics/paths.png"));
            sdoor = ImageIO.read(new File("graphics/doors.png"));
            schaser = ImageIO.read(new File("graphics/chasers.png"));
            schased = ImageIO.read(new File("graphics/chaseds.png"));
            sswamp = ImageIO.read(new File("graphics/swamps.png"));
            sice = ImageIO.read(new File("graphics/ices.png"));
        } catch (IOException ex) {
            System.out.println("Graphics file not found! Exiting...");
            System.exit(1);
        }
    }

    public BufferedImage GetImage(String name) {
        if (!smallTiles) {
            switch (name) {
                case "Wall":
                    return wall;
                case "Floor":
                    return floor;
                case "Path":
                    return path;
                case "Door":
                    return door;
                case "Chaser":
                    return chaser;
                case "Chased":
                    return chased;
                case "Swamp":
                    return swamp;
                case "Ice":
                    return ice;
            }
        } else {
            switch (name) {
                case "Wall":
                    return swall;
                case "Floor":
                    return sfloor;
                case "Path":
                    return spath;
                case "Door":
                    return sdoor;
                case "Chaser":
                    return schaser;
                case "Chased":
                    return schased;
                case "Swamp":
                    return sswamp;
                case "Ice":
                    return sice;
            }
        }
        return null;
    }
}
