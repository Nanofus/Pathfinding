/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.nano.pathfinding.ui;

import fi.nano.pathfinding.structure.AlgorithmRunner;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 *
 * @author Nanofus
 */
public class Window implements Runnable {

    private int windowWidth = 1280;
    private int windowHeight = 720;
    
    private int repaintDelay;

    private JFrame frame;
    
    private Renderer renderer;
    
    private AlgorithmRunner runner;
    
    private final ImageLoader imageLoader;

    /**
     * Luo uuden ikkunan
     * @param runner
     * @param smallTiles
     * @param waitInMillis
     * @param windowWidth Ikkunan leveys
     * @param windowHeight Ikkunan korkeus
     */
    public Window(AlgorithmRunner runner, boolean smallTiles, int repaintDelay, int windowWidth, int windowHeight) {
        
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        
        this.repaintDelay = repaintDelay;
        
        this.runner = runner;

        imageLoader = new ImageLoader(smallTiles);
    }

    @Override
    public void run() {
        frame = new JFrame("Pathfinding");
        
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(windowWidth + frame.getInsets().left + frame.getInsets().right, windowHeight + frame.getInsets().top + frame.getInsets().bottom));

        renderer = new Renderer(imageLoader, runner, repaintDelay, windowWidth, windowHeight);

        frame.add(renderer);
    }

    public void Refresh() {
        frame.repaint();
    }

    public JFrame GetFrame() {
        return frame;
    }
}