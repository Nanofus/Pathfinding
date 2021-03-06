package fi.nano.pathfinding.structure;

import fi.nano.pathfinding.dataStructures.OwnArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Luokka joka lukee sokkelon tekstitiedostosta.
 */
public class MazeReader {

    private OwnArrayList<String> maze;
    private OwnArrayList<String> targetMovement;
    private String startPos;

    /**
     * Konstruktori lataa sokkelon tekstitiedostosta.
     *
     * @param mazeName Ladattavan tasokansion nimi
     */
    public MazeReader(String mazeName) {

        maze = new OwnArrayList();
        targetMovement = new OwnArrayList();

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

        System.out.println("Reading target movement...");

        try {
            BufferedReader lineReader = new BufferedReader(new FileReader("mazes/" + mazeName + "/targetMovement.txt"));
            while ((line = lineReader.readLine()) != null) {
                targetMovement.add(line);
            }
        } catch (IOException ex) {
            System.out.println("Can't load target movements!");
            System.exit(1);
        }

        System.out.println("Reading start position...");

        try {
            BufferedReader lineReader = new BufferedReader(new FileReader("mazes/" + mazeName + "/startPosition.txt"));
            startPos = lineReader.readLine();
        } catch (IOException ex) {
            System.out.println("Can't load start position!");
            System.exit(1);
        }

        //Fix sille että tiedostojen alkuun tulee ylimääräinen kysymysmerkki jostain merkistöenkoodauksen mysteerisyystä
        //maze.set(0, maze.get(0).substring(1));
    }

    public OwnArrayList<String> GetMaze() {
        return maze;
    }

    public OwnArrayList<String> GetTargetMovement() {
        return targetMovement;
    }
    
    public String GetStartPosition() {
        return startPos;
    }

}
