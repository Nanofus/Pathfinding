package fi.nano.pathfinding;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Luokka joka lukee tason tekstitiedostosta.
 *
 * @author Nanofus
 */
public class MazeReader {

    private ArrayList<String> maze;
    private ArrayList<String> positions;

    /**
     * Konstruktori lataa sokkelon tekstitiedostosta.
     *
     * @param mazeName Ladattavan tasokansion nimi
     */
    public MazeReader(String mazeName) {
        Scanner in = null;

        maze = new ArrayList();
        positions = new ArrayList();

        System.out.println("Reading maze...");

        try {
            in = new Scanner(new File("mazes/" + mazeName + "/maze.txt"), "UTF-8");
        } catch (FileNotFoundException ex) {
            System.out.println("Can't load maze!");
            System.exit(1);
        }

        while (in.hasNext()) {
            maze.add(in.nextLine());
        }

        System.out.println("Reading start and goal positions...");

        try {
            in = new Scanner(new File("mazes/" + mazeName + "/positions.txt"), "UTF-8");
        } catch (FileNotFoundException ex) {
            System.out.println("Can't load positions!");
            System.exit(1);
        }

        while (in.hasNext()) {
            positions.add(in.nextLine());
        }
        
        //Fix sille että tiedostojen alkuun tulee ylimääräinen kysymysmerkki jostain merkistöenkoodauksen mysteerisyystä
        maze.set(0, maze.get(0).substring(1));
        positions.set(0, positions.get(0).substring(1));

    }

    public ArrayList<String> GetMaze() {
        return maze;
    }

    public ArrayList<String> GetPositions() {
        return positions;
    }

}
