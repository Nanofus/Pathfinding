package fi.nano.pathfinding.structure;

/**
 * Apuluokka sijaintitiedon ilmoittamiseen.
 * @author Nanofus
 */
public class Position {
    
    /**
     * Konstruktori
     * @param x Aloitussijainti x-akselilla
     * @param y Aloitussijainti y-akselilla
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * X-sijainti
     */
    public int x = 0;
    
    /**
     * Y-sijainti
     */
    public int y = 0;
}
