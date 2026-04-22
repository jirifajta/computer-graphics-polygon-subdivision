/*
 *
 * Copyright (C) 2026 Jiri Fajta
 * License: MIT license
 *
 */

package org.subdivision;

import java.awt.Color;

public class VECTORC {
    /**
     * <p>Color red.</p>
     */
    public int r;
    /**
     * <p>Color green.</p>
     */
    public int g;
    /**
     * <p>Color blue.</p>
     */
    public int b;
    /**
     * <p>Color alpha.</p>
     */
    public int a;

    public VECTORC() {
        this.r = this.g = this.b = 0;
        this.a = 255;
    }
    
    /**
     * <p> Set color.
     * </p>
     * @param r set color red.
     * @param g set color green.
     * @param b set color blue.
     * @param a set color alpha.
     */
    public VECTORC(int r, int g, int b, int a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }
    
    /**
     * <p> Set color.
     * </p>
     * @param color set color using Color object.
     */
    public VECTORC(Color color) {
        this.r = color.getRed();
        this.g = color.getGreen();
        this.b = color.getBlue(); 
        this.a = color.getAlpha(); 
    }
    
    /**
     * <p> Get color.
     * </p>
     * @return Color get Color object from this object.
     */
    public Color getColor(){
        return new Color(r,g,b,a);
    }

    /**
     * <p> Set color.
     * </p>
     * @param color set color using Color object.
     */
    public void setColor(Color color){
        r = color.getRed();
        g = color.getGreen();
        b = color.getBlue(); 
        a = color.getAlpha(); 
    }
}
