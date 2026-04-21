/*
 *
 * Copyright (C) 2026 Jiri Fajta
 * License: MIT license
 *
 */

package org.SubdivisionSimulation.Controllers;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import org.SubdivisionSimulation.Gui.FrameDraw;

public class KeyboardController extends KeyAdapter {

    private FrameDraw frameDraw = null;

    public KeyboardController(FrameDraw frameDraw) {
        if (frameDraw == null) {
            throw new IllegalArgumentException("KeyboardController: variable frameDraw cannot be null");
        }
        this.frameDraw = frameDraw;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //System.out.println("Pressed: " + e.getKeyCode());
        switch(e.getKeyCode()) {
            case KeyEvent.VK_ESCAPE:
                System.exit(0);
                break;
            case KeyEvent.VK_0:
            case KeyEvent.VK_NUMPAD0:
                this.frameDraw.getGlobalVariables().ndiv_queued = 0;
                break;
            case KeyEvent.VK_1:
            case KeyEvent.VK_NUMPAD1:
                this.frameDraw.getGlobalVariables().ndiv_queued = 1;
                break;
            case KeyEvent.VK_2:
            case KeyEvent.VK_NUMPAD2:
                this.frameDraw.getGlobalVariables().ndiv_queued = 2;
                break;
            case KeyEvent.VK_3:
            case KeyEvent.VK_NUMPAD3:
                this.frameDraw.getGlobalVariables().ndiv_queued = 3;
                break;
            case KeyEvent.VK_4:
            case KeyEvent.VK_NUMPAD4:
                this.frameDraw.getGlobalVariables().ndiv_queued = 4;
                break;
            case KeyEvent.VK_MINUS:
            case KeyEvent.VK_SUBTRACT:
                this.frameDraw.getControlInterface().setDeltaSpeed(10);//slower
                break;
            case KeyEvent.VK_EQUALS:
            case KeyEvent.VK_ADD:
                this.frameDraw.getControlInterface().setDeltaSpeed(-10);//faster
                break;
            case KeyEvent.VK_H:
                this.frameDraw.getGlobalVariables().showHudMode++;
                this.frameDraw.getGlobalVariables().showHudMode = (this.frameDraw.getGlobalVariables().showHudMode > 2 ? 0 : this.frameDraw.getGlobalVariables().showHudMode);
                break;
        }//end switch
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //System.out.println("Released: " + e.getKeyCode());
    }
}
