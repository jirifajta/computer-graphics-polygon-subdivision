/*
 *
 * Copyright (C) 2026 Jiri Fajta
 * License: MIT license
 *
 */

package org.SubdivisionSimulation;

import java.awt.event.MouseEvent;

public class MouseStatus {

    public int pos_x = 0;
    public int pos_y = 0;

    public void setMouseLocation(MouseEvent e) {
        pos_x = e.getX();
        pos_y = e.getY();
    }

    public int getPos_x() {
        return pos_x;
    }

    public int getPos_y() {
        return pos_y;
    }
    
    public int[] getPos_xy() {
        return new int[]{pos_x, pos_y};
    }
    
}
