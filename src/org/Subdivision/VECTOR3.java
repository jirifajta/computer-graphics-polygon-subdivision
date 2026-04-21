/*
 *
 * Copyright (C) 2026 Jiri Fajta
 * License: MIT license
 *
 */

package org.Subdivision;

public class VECTOR3 {
    /**
     * <p>Vector x-axis.</p>
     */
    public int x;
    /**
     * <p>Vector y-axis.</p>
     */
    public int y;
    /**
     * <p>Vector z-axis.</p>
     */
    public int z;

    public VECTOR3() {
        this.x = this.y = this.z = 0;
    }
    
    /**
     * <p> set vertex.
     * </p>
     * @param x set vertex x component.
     * @param y set vertex y component.
     * @param z set vertex z component.
     */
    public VECTOR3(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
}
