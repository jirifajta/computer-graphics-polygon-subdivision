/*
 *
 * Copyright (C) 2026 Jiri Fajta
 * License: MIT license
 *
 */

package org.subdivision;

public class VECTOR2 {
    /**
     * <p>Point on x-axis.</p>
     */
    public int x;
    /**
     * <p>Point on y-axis.</p>
     */
    public int y;
    
    public VECTOR2() {
        this.x = this.y = 0;
    }
    
    /**
     * <p> set 2D vector.
     * </p>
     * @param x set point on x-axis.
     * @param y set point on y-axis.
     */
    public VECTOR2(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
}
