/*
 *
 * Copyright (C) 2026 Jiri Fajta
 * License: MIT license
 *
 */

package org.SubdivisionSimulation;

import java.awt.Dimension;
import java.awt.Toolkit;
import org.SubdivisionSimulation.Gui.FrameDraw;

public class MainSubdivisionSimulation {

    public static void main(String[] args) {
        SubdivisionDemoMod subdiv = new SubdivisionDemoMod();
        FrameDraw frameDraw = new FrameDraw();
        // create frame to display on screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frameDraw.createFrame("Polygon subdevision simulator", 0, 0, (int)screenSize.getWidth(), (int)screenSize.getHeight(), false, true,false);
        frameDraw.setGlobalVariables(subdiv.getGlobalVariables());
        frameDraw.initContent();
        
        frameDraw.setControlInterface(subdiv);
        subdiv.setFrameDraw(frameDraw);// set call functions in [subdiv].
        subdiv.setSpeed(50);//milliseconds
        subdiv.setNdiv(4);
        
        //repeat simulation
        while (true) {
            frameDraw.clearFrame();
            subdiv.start();
        }
    }
}
