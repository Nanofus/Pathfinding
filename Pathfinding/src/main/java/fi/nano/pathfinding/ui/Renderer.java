package fi.nano.pathfinding.ui;

import fi.nano.pathfinding.structure.AlgorithmRunner;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Renderer extends JPanel {

    private final ImageLoader imageLoader;

    private final AlgorithmRunner runner;

    private final int repaintDelay = 5;

    private int windowWidth;
    private int windowHeight;

    /**
     * Luo uuden piirtäjän
     *
     * @param imageLoader Kuvanlataaja
     * @param runner Algorithminajaja
     * @param windowWidth Ikkunan leveys
     * @param windowHeight Ikkunan korkeus
     */
    public Renderer(ImageLoader imageLoader, AlgorithmRunner runner, int windowWidth, int windowHeight) {

        setOpaque(true);
        setBackground(Color.BLACK);

        this.imageLoader = imageLoader;

        this.runner = runner;

        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;

        StartTimer();
    }

    private void StartTimer() {
        ActionListener taskPerformer = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //System.out.println("asd");
                repaint();
            }
        };
        new Timer(repaintDelay, taskPerformer).start();
    }

    /**
     * Asettaa ikkunalle uuden koon.
     *
     * @param x Leveys pikseleissä
     * @param y Korkeus pikseleissä
     */
    public void SetWindowSize(int x, int y) {
        windowWidth = x;
        windowHeight = y;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        PaintLevel(g);
    }

    private void PaintLevel(Graphics g) {
        DrawTileContents(g);
    }

    private void DrawTileContents(Graphics g) {
        BufferedImage drawnImage;

        // Tilet sisältöineen
        for (int i = 0; i < runner.width; i++) {
            for (int j = 0; j < runner.height; j++) {
                BufferedImage drawnImageBottom = null;
                switch (runner.ContentsOfTile(i, j)) {
                    case 'X':
                        drawnImage = imageLoader.GetImage("Wall");
                        break;
                    case ' ':
                        drawnImage = imageLoader.GetImage("Floor");
                        break;
                    case '.':
                        drawnImage = imageLoader.GetImage("Path");
                        break;
                    case '1':
                        drawnImage = imageLoader.GetImage("Chaser");
                        break;
                    case '2':
                        drawnImage = imageLoader.GetImage("Chased");
                        break;
                    default:
                        drawnImage = imageLoader.GetImage("Wall");
                        break;
                }
                g.drawImage(drawnImage, i * drawnImage.getWidth(), j * drawnImage.getHeight(), null);
            }
        }
    }
}
