/*
 *
 * Copyright (C) 2026 Jiri Fajta
 * License: MIT license
 *
 */

package org.Subdivision;

// recursive vertices.
// recursive element that holds data of any vertex attributes.
public class RVECTOR {
    /**
     * <p>Vertex.</p>
     */
    public VECTOR3 v = new VECTOR3();// 3D vector
    /**
     * <p>Screen coordinate.</p>
     */
    public VECTOR2 sxy = new VECTOR2();// Screen coordinate x,y
    /**
     * <p>Screen coordinate in z direction w.r.t. camera.</p>
     */
    public int sz;// z screen, camera to vertex in screen space.
    /**
     * <p>UV coordinate of a texture.</p>
     */
    public int[] uv = new int[2];// UV for texture
    /**
     * <p>Color of this vertex.</p>
     */
    public VECTORC c = new VECTORC();// Color

    /**
     * <p> Clone data from this RVECTOR object to copy to an independent other RVECTOR object.
     * </p>
     * @return RVECTOR as new independent object.
     */
    public RVECTOR copy(){
        RVECTOR out = new RVECTOR();
        out.v.x = v.x;
        out.v.y = v.y;
        out.v.z = v.z;
        return out;
    }
}
