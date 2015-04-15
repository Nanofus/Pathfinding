package fi.nano.pathfinding;

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

    /**
     * Solmua edeltävä solmu
     */
    public Node parent;

    /**
     * A*-algoritmin h-arvo
     */
    public double aStar_h;
    /**
     * A*-algoritmin g-arvo
     */
    public double aStar_g;
    /**
     * A*-algoritmin f-arvo
     */
    public double aStar_f;

    /**
     * Dijkstran algoritmin lyhin etäisyys aloitussolmuun
     */
    public double dijkstra_minDistance = Double.POSITIVE_INFINITY;

    /**
     * Leveyshakua varten tieto onko tässä käyty jo
     */
    public boolean breadthfirst_visited = false;

    /**
     * Syvyyshakua varten tieto onko tässä käyty jo
     */
    public boolean depthfirst_visited = false;

    public Node(boolean isWall) {
        this.isWall = isWall;

        this.aStar_g = Double.MAX_VALUE;
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
     * Aseta solmu seinäksi
     *
     * @param isWall
     */
    public void SetWall(boolean isWall) {
        this.isWall = isWall;
    }

    /*@Override
     public String toString() {
     return "(h: " + h + ", g:" + g + ", f:" + f + ", neighbs: " + neighbours.toString() + ")";
     }*/
}
