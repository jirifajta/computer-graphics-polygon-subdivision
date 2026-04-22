/*
 *
 * Copyright (C) 2026 Jiri Fajta
 * License: MIT license
 *
 */

package org.subdivision.interfacemodules;

import org.subdivision.RVECTOR;

public interface AddPolygonInterface {
    /**
     * <p> Receive all polygons one by one that is produced by object [Subdivision]. Each polygon consists of r0, r1, r2.
     * </p>
     * @param r0 vertex0 attributes of a polygon that is produced by object [Subdivision].
     * @param r1 vertex1 attributes of a polygon that is produced by object [Subdivision].
     * @param r2 vertex2 attributes of a polygon that is produced by object [Subdivision].
     */
    void addPolygon(RVECTOR r0, RVECTOR r1, RVECTOR r2);
    
    /**
     * <p> Receive all polygons one by one that is produced by object [Subdivision]. Each polygon consists of r0, r1, r2, r3.
     * </p>
     * @param r0 vertex0 attributes of a polygon that is produced by object [Subdivision].
     * @param r1 vertex1 attributes of a polygon that is produced by object [Subdivision].
     * @param r2 vertex2 attributes of a polygon that is produced by object [Subdivision].
     * @param r3 vertex3 attributes of a polygon that is produced by object [Subdivision].
     */
    void addPolygon(RVECTOR r0, RVECTOR r1, RVECTOR r2, RVECTOR r3);
}
