/*
 *
 * Copyright (C) 2026 Jiri Fajta
 * License: MIT license
 *
 */

package org.subdivisionsimulation.controllers;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import org.subdivisionsimulation.gui.FrameDraw;

public class MouseController extends MouseMotionAdapter {

    private FrameDraw frameDraw = null;

    public MouseController(FrameDraw frameDraw) {
        if (frameDraw == null) {
            throw new IllegalArgumentException("MouseController: variable frameDraw cannot be null");
        }
        this.frameDraw = frameDraw;
    }
    
    @Override
    public void mouseMoved(MouseEvent e) {
        // track movement only if mouse button is hold down.
        this.frameDraw.getMouseStatus().setMouseLocation(e);
        this.frameDraw.setClipping_boundry(this.frameDraw.getMouseStatus().pos_x - (this.frameDraw.getGlobalVariables().clipping_boundry.width >> 1), this.frameDraw.getMouseStatus().pos_y - (this.frameDraw.getGlobalVariables().clipping_boundry.height >> 1), this.frameDraw.getGlobalVariables().clipping_boundry.width, this.frameDraw.getGlobalVariables().clipping_boundry.height);
        frameDraw.repaint_trigger();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // track movement always.
        //mouseStatus.setMouseLocation(e);
    }
}
