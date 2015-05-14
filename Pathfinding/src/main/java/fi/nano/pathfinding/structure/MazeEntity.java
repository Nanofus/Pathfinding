package fi.nano.pathfinding.structure;

import fi.nano.pathfinding.dataStructures.OwnArrayList;

/**
 * Verkossa liikkuva kohde
 * @author Nanofus
 */
public class MazeEntity {

    /**
     * Sijainti x-akselilla
     */
    public int x;
    /**
     * Sijainti y-akselilla
     */
    public int y;

    /**
     * Konstruktori
     * @param x Aloitussijainti x-akselilla
     * @param y Aloitussijainti y-akselilla
     */
    public MazeEntity(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Liikuttaa kohdetta askeleen verran
     * @param path Lista solmuista joihin liikutaan
     */
    public void MakeMove(OwnArrayList<Node> path) {
        try {
            path.get(path.size() - 1);
        } catch (IndexOutOfBoundsException e) {
            return;
        }

        Node nextNode = path.get(path.size() - 1);
        Position newPos = new Position(nextNode.x, nextNode.y);
        path.remove(path.size() - 1);

        Move(newPos.x, newPos.y);
    }

    /**
     * Päivitetään sijainti
     * @param x Sijainti x-akselilla
     * @param y Sijainti y-akselilla
     */
    public void Move(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Liikutetaan entiteettiä suhteessa nykyiseen sijaintiin
     * @param x Liike x-akselilla
     * @param y Liike y-akselilla
     */
    public void MoveRelative(int x, int y) {
        this.x = this.x + x;
        this.y = this.y + y;
    }
}
