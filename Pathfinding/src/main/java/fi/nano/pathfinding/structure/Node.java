package fi.nano.pathfinding.structure;

import fi.nano.pathfinding.dataStructures.OwnArrayList;

/**
 * Solmuobjekti
 *
 * @author Nanofus
 */
public class Node {

    private final OwnArrayList<Node> neighbours = new OwnArrayList<>();
    private final OwnArrayList<Boolean> isNeighbourDiagonal = new OwnArrayList<>();
    private boolean isWall;
    private boolean isDoor;
    private boolean isSwamp;
    private boolean isIce;

    /**
     * X-sijainti
     */
    public int x;
    /**
     * Y-sijainti
     */
    public int y;

    /**
     * Solmua edeltävä solmu
     */
    public Node parent;

    /**
     * Suljettu A*:ssa
     */
    public boolean aStar_closed = false;
    /**
     * Suljettu A*:ssa
     */
    public boolean aStar_open = false;
    /**
     * Kekoindeksi lähinnä A*:ta varten
     */
    public int binaryHeapIndex = -1;
    /**
     * A*-algoritmin h-arvo
     */
    public float aStar_h = 0;
    /**
     * A*-algoritmin g-arvo
     */
    public float aStar_g = 0;
    /**
     * A*-algoritmin f-arvo
     */
    public float aStar_f = 0;

    /**
     * Dijkstran algoritmin lyhin etäisyys aloitussolmuun
     */
    public double dijkstra_minDistance = Double.POSITIVE_INFINITY;

    /**
     * Leveyshakua ja syvyyshakua varten tieto onko tässä käyty jo
     */
    public boolean visited = false;

    public Node() {
        Reset();
    }
    
    public Node(int testFValue) {
        Reset();

        this.aStar_g = Integer.MAX_VALUE;
        this.aStar_f = testFValue;
    }

    /**
     * Nollaa solmun
     */
    public void Reset() {
        parent = null;
        aStar_h = 0;
        aStar_g = 0;
        aStar_f = 0;
        visited = false;
        dijkstra_minDistance = Double.POSITIVE_INFINITY;
        binaryHeapIndex = -1;
        aStar_open = false;
        aStar_closed = false;
        //System.out.println(x+","+y+","+aStar_closed);
    }

    /**
     * Aseta solmu toisen naapuriksi
     *
     * @param node Naapurisolmu
     * @param diagonal Onko solmu vinottain tästä katsottuna
     */
    public void SetNeighbour(Node node, boolean diagonal) {
        neighbours.add(node);
        isNeighbourDiagonal.add(diagonal);
    }

    /**
     * Hae naapurit
     *
     * @return Naapurit
     */
    public OwnArrayList<Node> GetNeighbours() {
        return neighbours;
    }

    /**
     * Hae tiedot naapurien vinottaisuuksista
     *
     * @return Lista naapurien vinottaisuustiedoista
     */
    public OwnArrayList<Boolean> GetNeighbourDiagonals() {
        return isNeighbourDiagonal;
    }

    /**
     * Onko tämä solmu seinää
     *
     * @return
     */
    public boolean IsWall() {
        return isWall;
    }
    
    /**
     * Onko tämä ovi
     */
    public boolean IsDoor() {
        return isDoor;
    }

    /**
     * Aseta solmu seinäksi
     *
     * @param isWall
     */
    public void SetWall(boolean isWall) {
        this.isWall = isWall;
    }
    
    /**
     * Asettaa solmun oveksi
     * @param isDoor 
     */
    public void SetDoor(boolean isDoor) {
        this.isDoor = isDoor;
    }
    
    /**
     * Asettaa solmun suoksi
     * @param isSwamp
     */
    public void SetSwamp(boolean isSwamp) {
        this.isSwamp = isSwamp;
    }

    /**
     * Asettaa solmun jääksi
     * @param isIce
     */
    public void SetIce(boolean isIce) {
        this.isIce = isIce;
    }

    public boolean IsSwamp() {
        return this.isSwamp;
    }

    public boolean IsIce() {
        return this.isIce;
    }
}
