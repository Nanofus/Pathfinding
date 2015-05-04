package fi.nano.pathfinding.structure;

import fi.nano.pathfinding.structure.Node;
import fi.nano.pathfinding.algorithms.Algorithm;
import fi.nano.pathfinding.algorithms.AStar;
import fi.nano.pathfinding.algorithms.BreadthFirstSearch;
import fi.nano.pathfinding.algorithms.DepthFirstSearch;
import fi.nano.pathfinding.algorithms.Dijkstra;
import fi.nano.pathfinding.dataStructures.OwnArrayList;

/**
 * Ohjeman "pääluokka", ajattaa sokkelossa eri algoritmit ja vastaa sokkelon
 * toimintojen käyttämisestä.
 *
 * @author Nanofus
 */
public class AlgorithmRunner {

    private Algorithm algorithm;

    private String testedAlgorithm;
    private int testMode;

    private float doorCloseInterval = 2000;
    private boolean allowDiagonalMovement = false;

    private OwnArrayList<String> maze;

    private OwnArrayList<Node> path = new OwnArrayList<>();

    private Node[][] parsedMaze;

    private long runTime;

    public int width;
    public int height;

    private boolean hasRun = false;

    private MazeEntity chaser;
    private MazeEntity chased;

    /**
     * Konstruktori
     *
     * @param testedAlgorithm Algoritmi, jota testataan. Esimerkiksi "Dijkstra"
     * tai "A*".
     * @param allowDiagonalMovement Sallitaanko ruudukossa liikkuminen vinottain
     * @param maze Sokkelo tiedostosta luetussa tekstirivimuodossa
     */
    public AlgorithmRunner(String testedAlgorithm, boolean allowDiagonalMovement, OwnArrayList<String> maze, MazeEntity chaser, MazeEntity chased) {
        this.testedAlgorithm = testedAlgorithm;
        this.allowDiagonalMovement = allowDiagonalMovement;
        this.maze = maze;

        this.chaser = chaser;
        this.chased = chased;

        switch (testedAlgorithm) {
            case "A*":
                algorithm = new AStar();
                break;
            case "Dijkstra":
                algorithm = new Dijkstra();
                break;
            case "Breadth-first":
                algorithm = new BreadthFirstSearch();
                break;
            case "Depth-first":
                algorithm = new DepthFirstSearch();
                break;
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
                node.x = i;
                node.y = j;
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
    }

    /**
     * Resetoi sokkelon solmut
     */
    private void ResetNodes() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                parsedMaze[i][j].Reset();
            }
        }
    }

    /**
     * Tulostaa sokkelon komentoriville. X kuvaa seinää, välilyönti tyhjää tilaa
     * ja piste kuljettua reittiä, jos sellainen on jo etsitty. 1 ja 2 ovat
     * liikkuja ja maali.
     */
    public void PrintMaze() {
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                System.out.print(ContentsOfTile(i, j));
            }
            System.out.println();
        }
    }

    public char ContentsOfTile(int x, int y) {
        if (chaser != null && chased != null) {
            if (chaser.x == x && chaser.y == y) {
                return '1';
            } else if (chased.x == x && chased.y == y) {
                return '2';
            } else if (parsedMaze[x][y].IsWall()) {
                return 'X';
            } else {
                if (path.contains(parsedMaze[x][y])) {
                    return '.';
                } else {
                    return ' ';
                }
            }
        } else {
            if (path.contains(parsedMaze[x][y])) {
                return '.';
            } else {
                return ' ';
            }
        }
    }

    /**
     * Etsii uuden polun
     *
     * @param start Alkusijainti
     * @param end Loppusijainti
     * @return Uusi reitti
     */
    public OwnArrayList<Node> Pathfind(Position start, Position end) {
        hasRun = false;
        ResetNodes();
        return Run(start.x, start.y, end.x, end.y);
    }

    /**
     * Käynnistää algoritmin. Ajanotto tulee myös tänne.
     */
    private OwnArrayList<Node> Run(int sx, int sy, int ex, int ey) {
        //System.out.println("\n-----");
        //System.out.println("Starting algorithm: " + testedAlgorithm);

        Node start = parsedMaze[sx][sy];
        Node end = parsedMaze[ex][ey];

        long startTime = System.nanoTime();

        //System.out.println(parsedMaze[0][1].x+","+parsedMaze[0][1].y);
        //System.out.println(parsedMaze[width - 1][height - 2].x+","+parsedMaze[width - 1][height - 2].y);
        boolean success = algorithm.FindPath(start, end); //Reitti sokkelossa etsitään toisen rivin ensimmäisestä toiseksi viimeisen viimeiseen ruutuun.

        runTime = System.nanoTime() - startTime;
        //System.out.println("Finished in " + runTime + " milliseconds\n-----\n");

        path = new OwnArrayList<>();

        if (!success) {
            //System.out.println("Path not found!");
        } else {
            //System.out.println("Path found successfully!");
            path = Pathify(start, end);
            //System.out.println("Path length: " + path.size());
            //System.out.println();
            //PrintMaze();
            //System.out.println();
        }

        hasRun = true;

        if (path != null) {
            return path;
        } else {
            return new OwnArrayList<>();
        }
    }

    /**
     * Onko algoritmin suoritus valmis
     *
     * @return Totuusarvo
     */
    public boolean HasRun() {
        return hasRun;
    }

    public long GetRunTime() {
        return runTime;
    }

    /**
     * Käy läpi solmujen vanhemmat ja muodostaa niistä listan
     *
     * @param sPos Aloitussolmu
     * @param ePos Maalisolmu
     * @return
     */
    private OwnArrayList<Node> Pathify(Node sPos, Node ePos) {
        //System.out.println("Pathifying...");

        OwnArrayList<Node> foundPath = new OwnArrayList<>();

        Node start = ePos;

        while (start != sPos) {
            foundPath.add(start);
            //System.out.println(start.x+","+start.y+","+start.parent);
            start = start.parent;
        }
        foundPath.add(start);

        return foundPath;
    }
}
