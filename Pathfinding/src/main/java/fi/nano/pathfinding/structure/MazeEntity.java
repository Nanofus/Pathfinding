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

    public void Move(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public void MoveRelative(int x, int y) {
        this.x = this.x + x;
        this.y = this.y + y;
    }
}
