package fi.nano.pathfinding.algorithms;

import fi.nano.pathfinding.structure.Node;
import fi.nano.pathfinding.dataStructures.NodeComparator;
import fi.nano.pathfinding.dataStructures.OwnBinaryHeap;

/**
 * A*-polunetsintäalgoritmin toteutus.
 *
 * @author Nanofus
 */
public class AStar implements Algorithm {

    OwnBinaryHeap open;

    Node end;

    /**
     * A*-algoritmin polunetsintä
     *
     * @param sPos Aloitussijainti
     * @param ePos Loppusijainti
     * @return Polku listana nodeista
     */
    @Override
    public boolean FindPath(Node sPos, Node ePos) {
        open = new OwnBinaryHeap(new NodeComparator(0));

        end = ePos;

        sPos.aStar_g = 0;
        sPos.aStar_f = sPos.aStar_h;
        open.add(sPos);
        sPos.aStar_open = true;
        sPos.aStar_closed = false;

        boolean finished = false;

        //System.out.println("Starting...");
        while (!open.isEmpty() && !finished) {
            Node node = open.poll();
            node.aStar_open = false;
            //System.out.println("Closing " + node.x + "," + node.y);
            node.aStar_closed = true;

            if (node.equals(end)) {
                finished = true;
                break;
            }

            //System.out.println("Inspecting neighbours..."+node.GetNeighbours().size());
            for (int i = 0; i < node.GetNeighbours().size(); i++) {
                Node neighbour = node.GetNeighbours().get(i);
                if (!neighbour.IsWall() && !neighbour.aStar_closed) {
                    InspectNeighbour(node, neighbour, node.GetNeighbourDiagonals().get(i));
                }
            }
        }

        return finished;
    }

    /**
     * Laskee matkan solmusta maaliin Pythagoraan lauseella.
     *
     * @param node Solmu josta lasketaan
     * @return Etäisyys
     */
    private float EstimateDistance(Node node) {
        int xDiffer = end.x - node.x;
        int yDiffer = end.y - node.y;
        return (float) Math.sqrt((xDiffer * xDiffer) + (yDiffer * yDiffer));
    }

    /**
     * Tutkitaan solmun naapuri
     *
     * @param node Solmu
     * @param neighbour Naapuri
     * @param isDiagonal Onko naapuri vinottain katsottuna solmusta; vinottainen
     * liike on kalliimpaa kuin ei-vinottainen.
     */
    private void InspectNeighbour(Node node, Node neighbour, boolean isDiagonal) {
        //System.out.println("Inspecting neighbour at " + neighbour.x + "," + neighbour.y + ", is wall/door: " + neighbour.IsWall() + "/" + neighbour.IsDoor() + ", open/closed:" + neighbour.aStar_open + "/" + neighbour.aStar_closed);

        float temp_h = EstimateDistance(neighbour);

        // Lasketaan paino
        int weight = 10;
        if (!neighbour.IsIce() && !neighbour.IsSwamp()) {
            if (isDiagonal) {
                weight = 14;
            }
        } else if (neighbour.IsIce()) {
            weight = 5;
            if (isDiagonal) {
                weight = 7;
            }
        } else if (neighbour.IsSwamp()) {
            weight = 20;
            if (isDiagonal) {
                weight = 28;
            }
        }

        float temp_g = node.aStar_g + weight;

        if (!neighbour.aStar_open) {
            neighbour.parent = node;

            neighbour.aStar_g = temp_g;
            neighbour.aStar_h = temp_h;
            neighbour.aStar_f = neighbour.aStar_h + neighbour.aStar_g;

            open.add(neighbour);

            neighbour.aStar_open = true;
        } else {
            if (temp_g < neighbour.aStar_g) {
                neighbour.parent = node;

                neighbour.aStar_g = temp_g;
                neighbour.aStar_h = temp_h;
                neighbour.aStar_f = neighbour.aStar_h + neighbour.aStar_g;

                open.decreaseKey(neighbour);
            }
        }
    }
}
