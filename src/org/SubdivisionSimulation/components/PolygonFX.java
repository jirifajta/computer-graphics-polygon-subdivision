/*
 *
 * Copyright (C) 2026 Jiri Fajta
 * License: MIT license
 *
 */

package org.subdivisionsimulation.components;

import java.awt.Color;
import java.awt.Polygon;

public class PolygonFX extends Polygon{
    private Color[] rgbs;
    
    public PolygonFX(int[] xpoints, int[] ypoints, Color[] colors, int npoints) {
        this.rgbs = colors;
        super.xpoints = xpoints;
        super.ypoints = ypoints;
        super.npoints = npoints;
    }    

    public Color[] getColor() {
        return rgbs;
    }

    public void setColor(Color[] colors) {
        this.rgbs = colors;
    }
    
    public void print() {
        for(int poly_i = 0 ; poly_i < xpoints.length; poly_i++){
            System.out.print(" vertex["+poly_i +
                "] sx: "+this.xpoints[poly_i] +
                "  sy: "+this.ypoints[poly_i] +
                "  color: "+this.rgbs[poly_i].getRed() +","+this.rgbs[poly_i].getGreen()+","+this.rgbs[poly_i].getBlue()+","+this.rgbs[poly_i].getAlpha()
            );
            System.out.println("");
        }//end  for(int poly_i = 0 ; poly_i < ; poly_i++)
    }
}
