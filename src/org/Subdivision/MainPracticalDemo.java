/*
 *
 * Copyright (C) 2026 Jiri Fajta
 * License: MIT license
 *
 */

package org.Subdivision;

import java.awt.Color;
import java.util.ArrayList;
import org.Subdivision.Interface.AddPolygonInterface;
import org.SubdivisionSimulation.PolygonFX;

public class MainPracticalDemo implements AddPolygonInterface{
    private ArrayList<PolygonFX> polygonFXs = new ArrayList<>();
    
    void StartDemo(){
        Subdivision subdiv = new Subdivision();
        SUBDIVPOLYGON3_16 polydiv = new SUBDIVPOLYGON3_16();
        subdiv.setAddPolygonInterface(this);
        
        /*
         * Set demo triangle polygon.
         * In practice, use [SUBDIVPOLYGON3_16] and set your own triangle polygons using polydiv.setR0-2(...).
         */
        subdiv.initializeRootPolygon(polydiv);
        
        /*
         * Set this to false if you want to recieve all polygons to clip later in 3D
         */
        //polydiv.setScreenSpaceClipping(false);
        
        polydiv.ndiv = 1;// override default setting from [Subdivision.initializeRootPolygon].
        subdiv.start(polydiv);
        System.out.println("Divisions: " + polydiv.ndiv + ", " + (1<<polydiv.ndiv) +" x "+(1<<polydiv.ndiv) + ", polygon count: "+((1<<polydiv.ndiv)*(1<<polydiv.ndiv)));
        printPolygons();
    }

    
    // define here how to process generated polygons. Like add to a list, pass to GPU and so on.
    // Polygon data is passed by [Subdivision] object.
    @Override
    public void addPolygon(RVECTOR r0, RVECTOR r1, RVECTOR r2) {
        PolygonFX poly = new PolygonFX(
                    new int[]{r0.sxy.x, r1.sxy.x, r2.sxy.x},
                    new int[]{r0.sxy.y, r1.sxy.y, r2.sxy.y},
                    new Color[]{r0.c.getColor(), r1.c.getColor(), r2.c.getColor()},
                    3
            );
        polygonFXs.add(poly);
    }
    
    public void printPolygons(){
        for(int poly_i = 0 ; poly_i < polygonFXs.size(); poly_i++){
            System.out.println("poly["+poly_i+"]:");
                polygonFXs.get(poly_i).print();
        }//end  for(int poly_i = 0 ; poly_i < ; poly_i++)
    }
 
    public static void main(String[] args) {
        MainPracticalDemo demo = new MainPracticalDemo();
        demo.StartDemo();
    }

}
