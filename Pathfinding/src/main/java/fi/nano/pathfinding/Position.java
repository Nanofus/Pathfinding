package fi.nano.pathfinding;

public class Position {
    int x;
    int y;
    
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public void Move(int x, int y) {
        this.x = this.x+x;
        this.y = this.y+y;
    }
    
    public void SetPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int GetX() {
        return this.x;
    }
    
    public int GetY() {
        return this.y;
    }
    
}
