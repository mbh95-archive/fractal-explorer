package com.mbh.fractals;

import javax.swing.*;

public class FractalApplet extends JApplet {
    public void init() {
        FractalExplorer fractalExplorerComponent = new FractalExplorer(this);
        add(fractalExplorerComponent);
        new Thread(fractalExplorerComponent).start();
    }
}
