/*
 *
 * Copyright (C) 2026 Jiri Fajta
 * License: MIT license
 *
 */

package org.SubdivisionSimulation;

import java.awt.Rectangle;

public class GlobalVariables {

    public long pauze_between_frames = 300;// in milliseconds
    public int ndiv;//active
    public int ndiv_queued;//next to use, this is later passed to [ndiv] above.
    public Rectangle clipping_boundry = new Rectangle();
    public int number_of_polygons_calculated;
    public int showHudMode = 0;
}
