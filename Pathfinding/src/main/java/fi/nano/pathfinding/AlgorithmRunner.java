package fi.nano.pathfinding;

import fi.nano.pathfinding.algorithms.Algorithm;
import fi.nano.pathfinding.algorithms.AStar;
import fi.nano.pathfinding.algorithms.BreadthFirstSearch;
import fi.nano.pathfinding.algorithms.DepthFirstSearch;
import fi.nano.pathfinding.algorithms.Dijkstra;
import fi.nano.pathfinding.dataStructures.OwnArrayList;
import fi.nano.pathfinding.structure.MazeEntity;
import fi.nano.pathfinding.structure.Node;
import fi.nano.pathfinding.structure.Position;

/**
 * Ohjeman "pääluokka", ajattaa sokkelossa eri algoritmit ja vastaa sokkelon
 * toimintojen käyttämisestä.
 *
 * @author Nanofus
 */
public class AlgorithmRunner {

    private Algorithm algorithm;

    private String testedAlgorithm;

    private float doorCloseInterval = 2000;
    private boolean allowDiagonalMovement = false;

    private OwnArrayList<String> maze;

    private OwnArrayList<Node> path = new OwnArrayList<>();

    private Node[][] parsedMaze;

    private OwnArrayList<Node> doors = new OwnArrayList<>();

    private long runTime;
    private long nodeCleanTime;

    /**
     * Sokkelon leveys
     */
    public int width;
    /**
     * Sokkelon korkeus
     */
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
     * @param chaser Takaa-ajaja
     * @param chased Takaa-ajettu
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

        CreateNodes();
        SetNodeDiagonality();
    }

    /**
     * Luo sokkelon solmut
     */
    private void CreateNodes() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Node node = new Node();
                if (((String) maze.get(j)).charAt(i) == ' ') {
                } else if (((String) maze.get(j)).charAt(i) == 'D') {
                    node.SetDoor(true);
                    doors.add(node);
                } else if (((String) maze.get(j)).charAt(i) == 'd') {
                    node.SetWall(true);
                    node.SetDoor(true);
                    doors.add(node);
                } else if (((String) maze.get(j)).charAt(i) == 's') {
                    node.SetSwamp(true);
                } else if (((String) maze.get(j)).charAt(i) == 'i') {
                    node.SetIce(true);
                } else {
                    node.SetWall(true);
                }
                parsedMaze[i][j] = node;
                node.x = i;
                node.y = j;
            }
        }
    }

    /**
     * Asettaa solmulle tiedon siitä, ovatko naapurit vieressä vai vinottain
     */
    private void SetNodeDiagonality() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Node node = parsedMaze[i][j];
                if (GetsNeighbours(node)) {
                    SetNeighbour(node, i - 1, j, false);
                    SetNeighbour(node, i + 1, j, false);
                    SetNeighbour(node, i, j + 1, false);
                    SetNeighbour(node, i, j - 1, false);
                    if (allowDiagonalMovement) {
                        SetNeighbour(node, i - 1, j - 1, true);
                        SetNeighbour(node, i + 1, j - 1, true);
                        SetNeighbour(node, i - 1, j + 1, true);
                        SetNeighbour(node, i + 1, j + 1, true);
                    }

                }
            }
        }
    }

    /**
     * Asettaa solmulle naapurit
     */
    private void SetNeighbour(Node node, int x, int y, boolean diagonal) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            if (GetsNeighbours(parsedMaze[x][y])) {
                node.SetNeighbour(parsedMaze[x][y], diagonal);
            }
        }
    }

    /**
     * Tarkastaa voiko solmulle laittaa naapureita
     */
    private boolean GetsNeighbours(Node node) {
        return !node.IsWall() || (node.IsWall() && node.IsDoor());
    }

    /**
     * Resetoi sokkelon solmut
     */
    private void ResetNodes() {
        long startTime = System.nanoTime();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                parsedMaze[i][j].Reset();
            }
        }
        nodeCleanTime = System.nanoTime() - startTime;
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

    /**
     * Palauttaa merkkinä tiedon siitä, mitä solmussa on. Käyttöliittymää
     * varten.
     *
     * @param x Solmun x-koordinaatti
     * @param y Solmun y-koordinaatti
     * @return
     */
    public char ContentsOfTile(int x, int y) {
        if (chaser != null && chased != null) {
            if (chaser.x == x && chaser.y == y) {
                return '1';
            } else if (chased.x == x && chased.y == y) {
                return '2';
            } else if (!parsedMaze[x][y].IsDoor() && parsedMaze[x][y].IsWall()) {
                return 'X';
            } else if (parsedMaze[x][y].IsDoor() && parsedMaze[x][y].IsWall()) {
                return 'D';
            } else if (parsedMaze[x][y].IsSwamp()) {
                return 's';
            } else if (parsedMaze[x][y].IsIce()) {
                return 'i';
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

    public OwnArrayList<Node> GetDoors() {
        return doors;
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

    public long GetNodeCleanTime() {
        return nodeCleanTime;
    }

    public OwnArrayList<Node> GetPath() {
        return path;
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
