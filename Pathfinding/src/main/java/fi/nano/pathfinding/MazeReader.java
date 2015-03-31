package fi.nano.pathfinding;

import fi.nano.pathfinding.dataStructures.OArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Luokka joka lukee sokkelon tekstitiedostosta.
 */
public class MazeReader {

    private OArrayList<String> maze;
    private OArrayList<String> doors;

    /**
     * Konstruktori lataa sokkelon tekstitiedostosta.
     *
     * @param mazeName Ladattavan tasokansion nimi
     */
    public MazeReader(String mazeName) {

        maze = new OArrayList();
        doors = new OArrayList();

        System.out.println("Reading maze...");

        String line;

        try {
            BufferedReader lineReader = new BufferedReader(new FileReader("mazes/" + mazeName + "/maze.txt"));
            while ((line = lineReader.readLine()) != null) {
                maze.add(line);
            }
            //System.out.println("Maze width: " + maze.get(0).length());
            //System.out.println("Maze height: " + maze.size());
        } catch (IOException ex) {
            System.out.println("Can't load maze!");
            System.exit(1);
        }

        System.out.println("Reading door positions...");

        try {
            BufferedReader lineReader = new BufferedReader(new FileReader("mazes/" + mazeName + "/doors.txt"));
            while ((line = lineReader.readLine()) != null) {
                doors.add(line);
            }
        } catch (IOException ex) {
            System.out.println("Can't load door positions!");
            System.exit(1);
        }

        //Fix sille että tiedostojen alkuun tulee ylimääräinen kysymysmerkki jostain merkistöenkoodauksen mysteerisyystä
        //maze.set(0, maze.get(0).substring(1));
    }

    public OArrayList<String> GetMaze() {
        return maze;
    }

    public OArrayList<String> GetDoors() {
        return doors;
    }

}
