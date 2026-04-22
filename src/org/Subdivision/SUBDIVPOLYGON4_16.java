/*
 *
 * Copyright (C) 2026 Jiri Fajta
 * License: MIT license
 *
 */

package org.subdivision;

public class SUBDIVPOLYGON4_16 {

    /**
     * <p> Number of recursive divisions to split polygon into.</p><p>Ndiv number of recursive divisions [0-4]. 0: no subdivision. ndiv: (ndiv*2)*(ndiv*2) polygons.
     * </p>
     */
    public int ndiv;
    
    /**
     * <p> Set screen space clipping rectangle(x, y, w, h). clipx, clipy - cliph, clipv. 
     * </p>
     */
    public int clipx, clipy, cliph, clipv;
    
    /**
     * <p> RVECTOR3[] rvs is a list that is holding recursive subdivision at each level.</p><p>Once a certain recursive level i is completed for rvs[i], it will be reused for the next neighbor for next recursion at the same level.
     * </p>
     */
    public RVECTOR4[] rvs = new RVECTOR4[]{new RVECTOR4(), new RVECTOR4(), new RVECTOR4(), new RVECTOR4()};//16x16 max. By allocating 4 results in fixed memory size rather then variable.
    /**
     * <p> Root vertex0 attributes for this recursive subdivision.
     * </p>
     */
    public RVECTOR r0 = new RVECTOR();// vertex 0 input root vertex of a triangle
    /**
     * <p> Root vertex1 attributes for this recursive subdivision.
     * </p>
     */
    public RVECTOR r1 = new RVECTOR();// vertex 1 input root vertex of a triangle
    /**
     * <p> Root vertex2 attributes for this recursive subdivision.
     * </p>
     */
    public RVECTOR r2 = new RVECTOR();// vertex 2 input root vertex of a triangle
    /**
     * <p> Root vertex3 attributes for this recursive subdivision.
     * </p>
     */
    public RVECTOR r3 = new RVECTOR();// vertex 2 input root vertex of a triangle
    
    /**
     * <p> Screen space clipping function. True for on, false for off.
     * </p>
     */
    public boolean screenSpaceClipping = true;

    /**
     * <p> Is screen space clipping function turn on.
     * </p>
     * @return returns if screen space clipping is turned on or off.
     */
    public boolean isScreenSpaceClipping() {
        return screenSpaceClipping;
    }

    /**
     * <p> Turn on or off screen space clipping function.
     * </p>
     * @param screenSpaceClipping true for on, false for off.
     */
    public void setScreenSpaceClipping(boolean screenSpaceClipping) {
        this.screenSpaceClipping = screenSpaceClipping;
    }

    /**
     * <p> Number of recursive divisions to split polygon into.
     * </p>
     * @param ndiv number of recursive divisions [0-4]. 0: no subdivision. ndiv: (ndiv*2)*(ndiv*2) polygons.
     */
    public void setNdiv(int ndiv) {
        this.ndiv = ndiv;
    }

    /**
     * <p> Set screen space clipping coordinates. If all projected vertecies on screenspace of a polygon are outside this rectangle then this polygon will not be produced. 
     * </p>
     * @param clipx left bound of the screen.
     * @param clipy top bound of the screen.
     * @param cliph width of the screen.
     * @param clipv height of the screen.
     */
    public void setClip(int clipx, int clipy, int cliph, int clipv) {
        this.clipx = clipx;
        this.clipy = clipy;
        this.cliph = cliph;
        this.clipv = clipv;
    }

    /**
     * <p> Set vertex 0 of this polygon. 
     * </p>
     * @param vertex 3D vertex.
     * @param sxy set screen space x and y component.
     * @param sz set screen space z component.
     * @param u set u from uv texture coordinate.
     * @param v set v from uv texture coordinate.
     * @param c set color vector.
     */
    public void setR0(VECTOR3 vertex, VECTOR2 sxy, int sz, int u, int v, VECTORC c) {
        this.r0.v = vertex;
        this.r0.sxy = sxy;
        this.r0.sz = sz;
        this.r0.uv[0] = u;
        this.r0.uv[1] = v;
        this.r0.c = c;
    }

    /**
     * <p> Set vertex 2 of this polygon. 
     * </p>
     * @param vertex 3D vertex.
     * @param sxy set screen space x and y component.
     * @param sz set screen space z component.
     * @param u set u from uv texture coordinate.
     * @param v set v from uv texture coordinate.
     * @param c set color vector.
     */
    public void setR1(VECTOR3 vertex, VECTOR2 sxy, int sz, int u, int v, VECTORC c) {
        this.r1.v = vertex;
        this.r1.sxy = sxy;
        this.r1.sz = sz;
        this.r1.uv[0] = u;
        this.r1.uv[1] = v;
        this.r1.c = c;
    }

    /**
     * <p> Set vertex 2 of this polygon. 
     * </p>
     * @param vertex 3D vertex.
     * @param sxy set screen space x and y component.
     * @param sz set screen space z component.
     * @param u set u from uv texture coordinate.
     * @param v set v from uv texture coordinate.
     * @param c set color vector.
     */
    public void setR2(VECTOR3 vertex, VECTOR2 sxy, int sz, int u, int v, VECTORC c) {
        this.r2.v = vertex;
        this.r2.sxy = sxy;
        this.r2.sz = sz;
        this.r2.uv[0] = u;
        this.r2.uv[1] = v;
        this.r2.c = c;
    }
    
    /**
     * <p> Set vertex 3 of this polygon. 
     * </p>
     * @param vertex 3D vertex.
     * @param sxy set screen space x and y component.
     * @param sz set screen space z component.
     * @param u set u from uv texture coordinate.
     * @param v set v from uv texture coordinate.
     * @param c set color vector.
     */
    public void setR3(VECTOR3 vertex, VECTOR2 sxy, int sz, int u, int v, VECTORC c) {
        this.r3.v = vertex;
        this.r3.sxy = sxy;
        this.r3.sz = sz;
        this.r3.uv[0] = u;
        this.r3.uv[1] = v;
        this.r3.c = c;
    }
    
    /**
     * <p> Set vertex 0 of this polygon. 
     * </p>
     * @param vertex_x 3D vertex component x.
     * @param vertex_y 3D vertex component y.
     * @param vertex_z 3D vertex component z.
     * @param sx set screen space x component.
     * @param sy set screen space y component.
     * @param sz set screen space z component.
     * @param u set u from uv texture coordinate.
     * @param v set v from uv texture coordinate.
     * @param r set color red.
     * @param g set color green.
     * @param b set color blue.
     * @param a set color alpha.
     */
    public void setR0(int vertex_x, int vertex_y, int vertex_z, int sx, int sy, int sz, int u, int v, int  r, int  g, int  b, int  a) {
        this.r0.v = new VECTOR3(vertex_x, vertex_y, vertex_z);
        this.r0.sxy = new VECTOR2(sx, sy);
        this.r0.sz = sz;
        this.r0.uv[0] = u;
        this.r0.uv[1] = v;
        this.r0.c = new VECTORC(r, g, b, a);
    }

    /**
    * <p> Set vertex 1 of this polygon. 
    * </p>
    * @param vertex_x 3D vertex component x.
    * @param vertex_y 3D vertex component y.
    * @param vertex_z 3D vertex component z.
    * @param sx set screen space x component.
    * @param sy set screen space y component.
    * @param sz set screen space z component.
    * @param u set u from uv texture coordinate.
    * @param v set v from uv texture coordinate.
    * @param r set color red.
    * @param g set color green.
    * @param b set color blue.
    * @param a set color alpha.
    */
    public void setR1(int vertex_x, int vertex_y, int vertex_z, int sx, int sy, int sz, int u, int v, int  r, int  g, int  b, int  a) {
        this.r1.v = new VECTOR3(vertex_x, vertex_y, vertex_z);
        this.r1.sxy = new VECTOR2(sx, sy);
        this.r1.sz = sz;
        this.r1.uv[0] = u;
        this.r1.uv[1] = v;
        this.r1.c = new VECTORC(r, g, b, a);
    }

    /**
    * <p> Set vertex 2 of this polygon. 
    * </p>
    * @param vertex_x 3D vertex component x.
    * @param vertex_y 3D vertex component y.
    * @param vertex_z 3D vertex component z.
    * @param sx set screen space x component.
    * @param sy set screen space y component.
    * @param sz set screen space z component.
    * @param u set u from uv texture coordinate.
    * @param v set v from uv texture coordinate.
    * @param r set color red.
    * @param g set color green.
    * @param b set color blue.
    * @param a set color alpha.
    */
    public void setR2(int vertex_x, int vertex_y, int vertex_z, int sx, int sy, int sz, int u, int v, int  r, int  g, int  b, int  a) {
        this.r2.v = new VECTOR3(vertex_x, vertex_y, vertex_z);
        this.r2.sxy = new VECTOR2(sx, sy);
        this.r2.sz = sz;
        this.r2.uv[0] = u;
        this.r2.uv[1] = v;
        this.r2.c = new VECTORC(r, g, b, a);
    }
    
   /**
    * <p> Set vertex 3 of this polygon. 
    * </p>
    * @param vertex_x 3D vertex component x.
    * @param vertex_y 3D vertex component y.
    * @param vertex_z 3D vertex component z.
    * @param sx set screen space x component.
    * @param sy set screen space y component.
    * @param sz set screen space z component.
    * @param u set u from uv texture coordinate.
    * @param v set v from uv texture coordinate.
    * @param r set color red.
    * @param g set color green.
    * @param b set color blue.
    * @param a set color alpha.
    */
    public void setR3(int vertex_x, int vertex_y, int vertex_z, int sx, int sy, int sz, int u, int v, int  r, int  g, int  b, int  a) {
        this.r3.v = new VECTOR3(vertex_x, vertex_y, vertex_z);
        this.r3.sxy = new VECTOR2(sx, sy);
        this.r3.sz = sz;
        this.r3.uv[0] = u;
        this.r3.uv[1] = v;
        this.r3.c = new VECTORC(r, g, b, a);
    }

    /**
     * <p> Get vertex values as string.
     * </p>
     * @return String as values store in SUBDIVPOLYGON3_16 object.
     */
    public String getString() {
        return "RVECTOR4{" + "r0=" + r0.v.x + " " + r0.v.y + " " + r0.v.z + ", r1=" + r1.v.x + " " + r1.v.y + " " + r1.v.z + ", r2=" + r2.v.x + " " + r2.v.y + " " + r2.v.z + '}';
    }
}
