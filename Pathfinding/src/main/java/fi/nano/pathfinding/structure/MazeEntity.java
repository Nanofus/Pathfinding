package fi.nano.pathfinding.structure;

import fi.nano.pathfinding.dataStructures.OwnArrayList;

public class MazeEntity {

    public int x;
    public int y;

    public MazeEntity(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public void MakeMove(OwnArrayList<Node> path) {
        Node nextNode = path.get(path.size()-1);
        Position newPos = new Position(nextNode.x,nextNode.y);
        path.remove(path.size()-1);
        
        Move(newPos.x,newPos.y);
    }

    private void Move(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
