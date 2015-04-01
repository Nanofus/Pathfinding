package fi.nano.pathfinding;

import fi.nano.pathfinding.algorithms.Pathfinding;
import fi.nano.pathfinding.algorithms.AStar;
import fi.nano.pathfinding.algorithms.Dijkstra;
import fi.nano.pathfinding.dataStructures.OwnArrayList;

/**
 * Ohjeman "pääluokka", ajattaa sokkelossa eri algoritmit ja vastaa sokkelon
 * toimintojen käyttämisestä.
 *
 * @author Nanofus
 */
class AlgorithmRunner {

    private Pathfinding algorithm;

    private String testedAlgorithm;
    private int testMode;

    private float doorCloseInterval = 2000;
    private boolean allowDiagonalMovement = false;

    private OwnArrayList<String> maze;

    private OwnArrayList<Node> path = new OwnArrayList<>();

    private Node[][] parsedMaze;

    private int width;
    private int height;

    /**
     * Konstruktori
     *
     * @param testedAlgorithm Algoritmi, jota testataan. Esimerkiksi "Dijkstra"
     * tai "A*".
     * @param testMode Käytetäänkö itsestään sulkeutuvia seiniä
     * @param allowDiagonalMovement Sallitaanko ruudukossa liikkuminen vinottain
     * @param maze Sokkelo tiedostosta luetussa tekstirivimuodossa
     */
    public AlgorithmRunner(String testedAlgorithm, int testMode, boolean allowDiagonalMovement, OwnArrayList<String> maze) {
        this.testedAlgorithm = testedAlgorithm;
        this.testMode = testMode;
        this.allowDiagonalMovement = allowDiagonalMovement;
        this.maze = maze;

        if (testedAlgorithm.equals("A*")) {
            algorithm = new AStar();
        } else if (testedAlgorithm.equals("Dijkstra")) {
            algorithm = new Dijkstra();
        }

        CreateMaze();
    }

    /**
     * Luo sokkelon tiedostosta luettujen tietojen perusteella.
     */
    private void CreateMaze() {
        width = ((String) maze.get(0)).length();
        height = maze.size();

        System.out.println("Width: " + width);
        System.out.println("Height: " + height);

        parsedMaze = new Node[width][height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Node node;
                if (((String) maze.get(j)).charAt(i) == ' ') {
                    node = new Node(false);
                } else {
                    node = new Node(true);
                }
                parsedMaze[i][j] = node;
            }
        }

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Node node = parsedMaze[i][j];
                if (!node.IsWall()) {

                    if ((i - 1 >= 0) && (i - 1 < width)) {
                        if (!parsedMaze[i - 1][j].IsWall()) {
                            node.SetNeighbour(parsedMaze[i - 1][j], false);
                        }
                    }
                    if ((i + 1 >= 0) && (i + 1 < width)) {
                        if (!parsedMaze[i + 1][j].IsWall()) {
                            node.SetNeighbour(parsedMaze[i + 1][j], false);
                        }
                    }
                    if ((j - 1 >= 0) && (j - 1 < height)) {
                        if (!parsedMaze[i][j - 1].IsWall()) {
                            node.SetNeighbour(parsedMaze[i][j - 1], false);
                        }
                    }
                    if ((j + 1 >= 0) && (j + 1 < height)) {
                        if (!parsedMaze[i][j + 1].IsWall()) {
                            node.SetNeighbour(parsedMaze[i][j + 1], false);
                        }
                    }

                    if (allowDiagonalMovement) {
                        if ((i - 1 >= 0) && (i - 1 < width) && (j - 1 >= 0) && (j - 1 < height)) {
                            if (!parsedMaze[i - 1][j - 1].IsWall()) {
                                node.SetNeighbour(parsedMaze[i - 1][j - 1], true);
                            }
                        }
                        if ((i + 1 >= 0) && (i + 1 < width) && (j + 1 >= 0) && (j + 1 < height)) {
                            if (!parsedMaze[i + 1][j + 1].IsWall()) {
                                node.SetNeighbour(parsedMaze[i + 1][j + 1], true);
                            }
                        }
                        if ((i + 1 >= 0) && (i + 1 < width) && (j - 1 >= 0) && (j - 1 < height)) {
                            if (!parsedMaze[i + 1][j - 1].IsWall()) {
                                node.SetNeighbour(parsedMaze[i + 1][j - 1], true);
                            }
                        }
                        if ((i - 1 >= 0) && (i - 1 < width) && (j + 1 >= 0) && (j + 1 < height)) {
                            if (!parsedMaze[i - 1][j + 1].IsWall()) {
                                node.SetNeighbour(parsedMaze[i - 1][j + 1], true);
                            }
                        }
                    }

                }
            }
        }

        //PrintMaze();
        Run();
    }

    /**
     * Tulostaa sokkelon komentoriville. X kuvaa seinää, välilyönti tyhjää tilaa
     * ja piste kuljettua reittiä, jos sellainen on jo etsitty.
     */
    private void PrintMaze() {
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                if (parsedMaze[i][j].IsWall()) {
                    System.out.print("X");
                } else {
                    if (path.contains(parsedMaze[i][j])) {
                        System.out.print(".");
                    } else {
                        System.out.print(" ");
                    }
                }
            }
            System.out.println();
        }
    }

    /**
     * Käynnistää algoritmin. Ajanotto tulee myös tänne.
     */
    private void Run() {
        path = algorithm.FindPath(parsedMaze[0][1], parsedMaze[width - 1][height - 2]); //Reitti sokkelossa etsitään toisen rivin ensimmäisestä toiseksi viimeisen viimeiseen ruutuun.

        if (path == null) {
            System.out.println("Path not found!");
        } else {
            System.out.println("Path found successfully!");
            System.out.println("Path length: " + path.size());
            System.out.println();
            PrintMaze();
            System.out.println();
        }
    }
}
