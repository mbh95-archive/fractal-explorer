package com.mbh.fractals;

import javax.swing.*;
import java.awt.*;

/**
 * Main class for running Fractal-Explorer
 */
public class Main {
    private static final String TITLE = "Fractal-Explorer";
    private static final int DEFAULT_WIDTH = 800;
    private static final int DEFAULT_HEIGHT = 600;

    /**
     * Opens a resizeable JFrame (default size 800x600)
     * containing a FractalExplorer component.
     * Finally starts the FractalExplorer Component.
     *
     * @param args Command line arguments to the program; currently ignored.
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame(TITLE);

        /* Make sure the program will exit when the JFrame is closed */
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        /* Resize the JFrame and its content pane */
        frame.getContentPane().setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        frame.pack();

        /* Center the JFrame on the screen */
        frame.setLocationRelativeTo(null);

        /* Create the FractalExplorer component and add it to the JFrame */
        FractalExplorer fractalExplorerComponent = new FractalExplorer(frame.getContentPane());
        frame.getContentPane().add(fractalExplorerComponent);

        /* Make sure the user can see the JFrame */
        frame.setVisible(true);

        fractalExplorerComponent.run();
    }
}
