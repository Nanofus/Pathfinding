package fi.nano.pathfinding;

/**
 * Apuluokka sijaintitietoja varten.
 * @author Nanofus
 */
public class Position {
    
    /**
     * Sijainti x-akselilla
     */
    public int x;
    /**
     * Sijainti y-akselilla
     */
    public int y;
    
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Liikuta sijaintia koordinaatistossa
     * @param x X-akselilla
     * @param y Y-akselilla
     */
    public void Move(int x, int y) {
        this.x = this.x+x;
        this.y = this.y+y;
    }
    
    /**
     * Aseta sijainti koordinaatistossa
     * @param x X-akselilla
     * @param y Y-akselilla
     */
    public void SetPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
}
