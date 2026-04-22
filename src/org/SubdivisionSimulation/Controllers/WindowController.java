/*
 *
 * Copyright (C) 2026 Jiri Fajta
 * License: MIT license
 *
 */

package org.subdivisionsimulation.controllers;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import org.subdivisionsimulation.gui.FrameDraw;

public class WindowController extends ComponentAdapter {

    private FrameDraw frameDraw = null;

    public WindowController(FrameDraw frameDraw) {
        if (frameDraw == null) {
            throw new IllegalArgumentException("WindowController: variable frameDraw cannot be null");
        }
        this.frameDraw = frameDraw;
    }

    @Override
    public void componentResized(ComponentEvent e) {
        frameDraw.setFrameWidth(frameDraw.getFrame().getWidth());
        frameDraw.setFrameHeight(frameDraw.getFrame().getHeight());
        frameDraw.repaint_trigger();
    }
}
