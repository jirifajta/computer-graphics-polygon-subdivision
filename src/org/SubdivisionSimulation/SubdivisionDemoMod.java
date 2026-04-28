/*
 *
 * Copyright (C) 2026 Jiri Fajta
 * License: MIT license
 *
 */

package org.subdivisionsimulation;

import org.subdivisionsimulation.components.PolygonFX;
import java.awt.Color;
import org.subdivision.RVECTOR;
import org.subdivision.SUBDIVPOLYGON3_16;
import org.subdivision.VECTOR2;
import org.subdivisionsimulation.gui.FrameDraw;
import org.subdivision.interfacemodules.AddPolygonInterface;
import org.subdivision.SUBDIVPOLYGON4_16;
import org.subdivisionsimulation.interfacemodules.ControlInterface;

/*
 *
 * This contains the algorithm.
 * 
 */

public class SubdivisionDemoMod implements ControlInterface, AddPolygonInterface{
    /**
     * <p> Easter egg: Nice hidden effect, produce more dynamic colors using color alpha channel.
     * </p>
     * @param easterEgg set to true to make active.
     */
    public boolean easterEgg = false;  
    
    private FrameDraw frameDraw = null;// interface
    private GlobalVariables globalVariables = new GlobalVariables();

    public void setFrameDraw(FrameDraw frameDraw){
        this.frameDraw = frameDraw;
    }
    
    public void setSpeed(long pauze_between_frames){
        this.globalVariables.pauze_between_frames = pauze_between_frames;
    }
    
    // because this is a simulation, ndiv is added first to the que and during execution passed to [ndiv].
    public void setNdiv(int ndiv){
        this.globalVariables.ndiv_queued = ndiv;
    }

    public int lerpIntMidpoints(int a, int b) {
        return a + ((b - a) >> 1);
    }
    
    public void initialize(SUBDIVPOLYGON3_16 divpoly) {
        int scale_polygons = 10;// scale triangles to draw visable on screen later.
        int offset_x_polygons = 0;
        int offset_y_polygons = 0;
        int largestSideOfTheWindow = 1920;

        if (this.frameDraw != null) {
            largestSideOfTheWindow = this.frameDraw.getWidth() > this.frameDraw.getHeight() ? this.frameDraw.getWidth() : this.frameDraw.getHeight();
        }

        this.globalVariables.clipping_boundry.width  = 600 * largestSideOfTheWindow / 1920;// every scale relative to 1080p screen.
        this.globalVariables.clipping_boundry.height = 600 * largestSideOfTheWindow / 1920;
        scale_polygons = scale_polygons * largestSideOfTheWindow / 1920;
        this.globalVariables.number_of_polygons_calculated = 0;
        
        //preset:
        this.globalVariables.ndiv = this.globalVariables.ndiv_queued;
        divpoly.ndiv = this.globalVariables.ndiv;

        // start_poly3 triangle as input. 
        divpoly.r0.v.x = -100;
        divpoly.r0.v.y = -100;
        divpoly.r0.v.z = 0;

        divpoly.r1.v.x = 100;
        divpoly.r1.v.y = -100;
        divpoly.r1.v.z = 0;

        divpoly.r2.v.x = 0;
        divpoly.r2.v.y = 100;
        divpoly.r2.v.z = 0;

        if (this.frameDraw != null) {
            offset_x_polygons = this.frameDraw.getFrameWidth() >> 1;
            offset_y_polygons = this.frameDraw.getFrameHeight() >> 1;
        }
        divpoly.r0.sxy.x = offset_x_polygons - 25 * scale_polygons;
        divpoly.r0.sxy.y = offset_y_polygons - 25 * scale_polygons;

        divpoly.r1.sxy.x = offset_x_polygons + 25 * scale_polygons;
        divpoly.r1.sxy.y = offset_y_polygons - 25 * scale_polygons;

        divpoly.r2.sxy.x = offset_x_polygons + 0 * scale_polygons;
        divpoly.r2.sxy.y = offset_y_polygons + 25 * scale_polygons;

        divpoly.r0.c.r = 255;
        divpoly.r0.c.g = divpoly.r0.c.b = 0;

        divpoly.r1.c.g = 255;
        divpoly.r1.c.r = divpoly.r1.c.b = 0;

        divpoly.r2.c.b = 255;
        divpoly.r2.c.r = divpoly.r2.c.g = 0;
        
        // Easter egg: Nice hidden effect, produce more dynamic colors. Enable this line:
        if(this.easterEgg){
            divpoly.r0.c.a = divpoly.r1.c.a = divpoly.r2.c.a = 128;
        }
        divpoly.r0.sz = divpoly.r1.sz = divpoly.r2.sz = 5;
    }

    public void initialize(SUBDIVPOLYGON4_16 divpoly) {
        int scale_polygons = 10;// scale triangles to draw visable on screen later.
        int offset_x_polygons = 0;
        int offset_y_polygons = 0;
        int largestSideOfTheWindow = 1920;

        if (this.frameDraw != null) {
            largestSideOfTheWindow = this.frameDraw.getWidth() > this.frameDraw.getHeight() ? this.frameDraw.getWidth() : this.frameDraw.getHeight();
        }

        this.globalVariables.clipping_boundry.width  = 600 * largestSideOfTheWindow / 1920;// every scale relative to 1080p screen.
        this.globalVariables.clipping_boundry.height = 600 * largestSideOfTheWindow / 1920;
        scale_polygons = scale_polygons * largestSideOfTheWindow / 1920;
        this.globalVariables.number_of_polygons_calculated = 0;
        
        //preset:
        this.globalVariables.ndiv = this.globalVariables.ndiv_queued;
        divpoly.ndiv = this.globalVariables.ndiv;

        
        // start_poly3 triangle as input. 
        divpoly.r0.v.x = -100;
        divpoly.r0.v.y = -100;
        divpoly.r0.v.z = 0;

        divpoly.r1.v.x = 100;
        divpoly.r1.v.y = -100;
        divpoly.r1.v.z = 0;

        divpoly.r2.v.x = 100;
        divpoly.r2.v.y = 100;
        divpoly.r2.v.z = 0;
        
        divpoly.r3.v.x = -100;
        divpoly.r3.v.y = 100;
        divpoly.r3.v.z = 0;

        if (this.frameDraw != null) {
            offset_x_polygons = this.frameDraw.getFrameWidth() >> 1;
            offset_y_polygons = this.frameDraw.getFrameHeight() >> 1;
        }
        
        divpoly.r0.sxy.x = offset_x_polygons - 25 * scale_polygons;
        divpoly.r0.sxy.y = offset_y_polygons - 25 * scale_polygons;

        divpoly.r1.sxy.x = offset_x_polygons + 25 * scale_polygons;
        divpoly.r1.sxy.y = offset_y_polygons - 25 * scale_polygons;

        divpoly.r2.sxy.x = offset_x_polygons + 25 * scale_polygons;
        divpoly.r2.sxy.y = offset_y_polygons + 25 * scale_polygons;

        divpoly.r3.sxy.x = offset_x_polygons - 25 * scale_polygons;
        divpoly.r3.sxy.y = offset_y_polygons + 25 * scale_polygons;

        divpoly.r0.c.r = 255;
        divpoly.r0.c.g = divpoly.r0.c.b = 0;

        divpoly.r1.c.g = 255;
        divpoly.r1.c.r = divpoly.r1.c.b = 0;

        divpoly.r2.c.b = 255;
        divpoly.r2.c.r = divpoly.r2.c.g = 0;

        if(true){
        // purple color
            divpoly.r3.c.r = 148;
            divpoly.r3.c.g = 96;
            divpoly.r3.c.b = 255;
        }else{
        // yelowish color
            divpoly.r3.c.r = divpoly.r3.c.g = 255;
            divpoly.r3.c.b = 128;
        }
        
        // Easter egg: Nice hidden effect, produce more dynamic colors. Enable this line:
        if(this.easterEgg){
            divpoly.r0.c.a = divpoly.r1.c.a = divpoly.r2.c.a = 128;
        }
        divpoly.r0.sz = divpoly.r1.sz = divpoly.r2.sz = divpoly.r3.sz = 5;
    }

    public void start_poly(){
        switch (this.globalVariables.shapeToggle.getCurrentIndex()) {
            case 0:
                start_poly3();
                start_poly4();
                break;
            case 1:
                start_poly3();
                break;
            case 2:
                start_poly4();
                break;
            default:
                break;
        }
    }
    
    public void start_poly3() {
        // initialize main ombject for recursive polycon subdevision.
        SUBDIVPOLYGON3_16 divpoly = new SUBDIVPOLYGON3_16();
        initialize(divpoly);
        
        if (!clipTest(divpoly, divpoly.r0.sxy, divpoly.r1.sxy, divpoly.r2.sxy)) {
            return;// root triangle not on screen.
        }

        //ROOT
        //System.out.println("\nROOT: " + divpoly.getString());

        if (divpoly.ndiv > divpoly.rvs.length) {
            System.out.println("WARNING: divpoly.ndiv " + divpoly.ndiv + " > divpoly.rv.length " + divpoly.rvs.length + ". So, change to max supported ndiv = " + divpoly.rvs.length);
            divpoly.ndiv = divpoly.rvs.length;
            this.globalVariables.ndiv = divpoly.ndiv;
        }
        this.globalVariables.ndiv_max = divpoly.rvs.length;
        
        //ORIGINAL TRIANGLE:
        if(this.frameDraw != null){
            // for live simulation 
            this.frameDraw.addAndDrawPolygonFX(divpoly, new Color(0,128,0), globalVariables.pauze_between_frames);
            //this.addPolygon(divpoly.r0, divpoly.r1, divpoly.r2);
        }
        //START DIVISION
        if (divpoly.ndiv > 0) {
            int level = 0;
            divpoly.rvs[level].level = level;

            divpoly.rvs[level].r01 = calculateMidpoints(divpoly.r0, divpoly.r1);//01
            divpoly.rvs[level].r12 = calculateMidpoints(divpoly.r1, divpoly.r2);//12
            divpoly.rvs[level].r20 = calculateMidpoints(divpoly.r2, divpoly.r0);//20
            
            //calculateMidpoints3(mid01, mid12, mid20, level);  //MIDDEL
            calculateMidpoints3_continue(divpoly, level, divpoly.rvs[level].r01, divpoly.rvs[level].r12, divpoly.rvs[level].r20);

            //calculateMidpoints3(v_root0, mid01, mid20, level);//LEFT
            calculateMidpoints3_continue(divpoly, level, divpoly.r0, divpoly.rvs[level].r01, divpoly.rvs[level].r20);

            //calculateMidpoints3(v_root1, mid12, mid01, level);//RIGHT
            calculateMidpoints3_continue(divpoly, level, divpoly.r1, divpoly.rvs[level].r12, divpoly.rvs[level].r01);

            //calculateMidpoints3(v_root2, mid20, mid12, level);//DOWN
            calculateMidpoints3_continue(divpoly, level, divpoly.r2, divpoly.rvs[level].r20, divpoly.rvs[level].r12);
        } else {
            this.addPolygon(divpoly.r0, divpoly.r1, divpoly.r2);
        }//end if (divpoly.ndiv > 0)
        
        if(this.frameDraw != null){
            this.frameDraw.clearPolygonFXs();
            this.frameDraw.clearPolygons();
        }
    }
    
    public void start_poly4() {
        // initialize main ombject for recursive polycon subdevision.
        SUBDIVPOLYGON4_16 divpoly = new SUBDIVPOLYGON4_16();
        initialize(divpoly);
        
        if (!clipTest(divpoly, divpoly.r0.sxy, divpoly.r1.sxy, divpoly.r2.sxy, divpoly.r3.sxy)) {
            return;
        }

        //ROOT
        //System.out.println("\nROOT: " + divpoly.getString());

        if (divpoly.ndiv > divpoly.rvs.length) {
            System.out.println("WARNING: divpoly.ndiv " + divpoly.ndiv + " > divpoly.rv.length " + divpoly.rvs.length + ". So, change to max supported ndiv = " + divpoly.rvs.length);
            divpoly.ndiv = divpoly.rvs.length;
            this.globalVariables.ndiv = divpoly.ndiv;
        }
        this.globalVariables.ndiv_max = divpoly.rvs.length;
        
        //ORIGINAL QUAD:
        if(this.frameDraw != null){
            // for live simulation 
            this.frameDraw.addAndDrawPolygonFX(divpoly, new Color(0,128,0), globalVariables.pauze_between_frames);
            //this.addPolygon(divpoly.r0, divpoly.r1, divpoly.r2);
        }
        
        //START DIVISION
        if (divpoly.ndiv > 0) {
            int level = 0;
            divpoly.rvs[level].level = level;

            divpoly.rvs[level].r01 = calculateMidpoints(divpoly.r0, divpoly.r1);//01
            divpoly.rvs[level].r12 = calculateMidpoints(divpoly.r1, divpoly.r2);//12
            divpoly.rvs[level].r23 = calculateMidpoints(divpoly.r2, divpoly.r3);//23
            divpoly.rvs[level].r30 = calculateMidpoints(divpoly.r3, divpoly.r0);//30
            divpoly.rvs[level].r0123 = calculateMidpoints(divpoly.rvs[level].r01, divpoly.rvs[level].r23);// cneter of quad.

            calculateMidpoints4_continue(divpoly, level, divpoly.r0, divpoly.rvs[level].r01, divpoly.rvs[level].r0123, divpoly.rvs[level].r30);
            calculateMidpoints4_continue(divpoly, level, divpoly.r1, divpoly.rvs[level].r12, divpoly.rvs[level].r0123, divpoly.rvs[level].r01);
            calculateMidpoints4_continue(divpoly, level, divpoly.r2, divpoly.rvs[level].r23, divpoly.rvs[level].r0123, divpoly.rvs[level].r12);
            calculateMidpoints4_continue(divpoly, level, divpoly.r3, divpoly.rvs[level].r30, divpoly.rvs[level].r0123, divpoly.rvs[level].r23);

        } else {
            this.addPolygon(divpoly.r0, divpoly.r1, divpoly.r2, divpoly.r3);
        }//end if (divpoly.ndiv > 0)
        
        if(this.frameDraw != null){
            this.frameDraw.clearPolygonFXs();
            this.frameDraw.clearPolygons();
        }
    }

    // recursive funcion
    //div = [1-4]
    //level = 0;//[0-3]
    private void calculateMidpoints3(SUBDIVPOLYGON3_16 divpoly, int level) {
        int level_plus_one = level + 1;
        
        // checks the root first.
        if (!SubdivisionDemoMod.this.clip_not_out_of_bound(divpoly, level)) {
           return;
        }

        // NEXT LEVEL = processing PREVIOUS LEVEL
        if (level_plus_one < divpoly.ndiv) {
            // BRANCH
            divpoly.rvs[level_plus_one].r01 = calculateMidpoints(divpoly.rvs[level].r0_ptr, divpoly.rvs[level].r1_ptr);
            divpoly.rvs[level_plus_one].r12 = calculateMidpoints(divpoly.rvs[level].r1_ptr, divpoly.rvs[level].r2_ptr);
            divpoly.rvs[level_plus_one].r20 = calculateMidpoints(divpoly.rvs[level].r2_ptr, divpoly.rvs[level].r0_ptr);
            divpoly.rvs[level_plus_one].level = level;// level of the midpoints.

            if(this.frameDraw != null){
                // for live simulation 
                this.frameDraw.addAndDrawPolygonFX(divpoly.rvs[level], Color.GRAY, globalVariables.pauze_between_frames);
            }

            //// next subdevision ////
            //calculateMidpoints3(mid01, mid12, mid20, level_plus_one);  //MIDDEL
            calculateMidpoints3_continue(divpoly, level_plus_one, divpoly.rvs[level_plus_one].r01, divpoly.rvs[level_plus_one].r12, divpoly.rvs[level_plus_one].r20);

             //calculateMidpoints3(v_root0, mid01, mid20, level_plus_one);//LEFT
            calculateMidpoints3_continue(divpoly, level_plus_one, divpoly.rvs[level].r0_ptr, divpoly.rvs[level_plus_one].r01, divpoly.rvs[level_plus_one].r20);

            //calculateMidpoints3(v_root`, md12, mid01, level_plus_one);//RIGHT
            calculateMidpoints3_continue(divpoly, level_plus_one, divpoly.rvs[level].r1_ptr, divpoly.rvs[level_plus_one].r12, divpoly.rvs[level_plus_one].r01);

            //calculateMidpoints3(v_root2, mid20, mid12, level_plus_one);//DOWN
            calculateMidpoints3_continue(divpoly, level_plus_one, divpoly.rvs[level].r2_ptr, divpoly.rvs[level_plus_one].r20, divpoly.rvs[level_plus_one].r12);
        } else {
            // LEAF
            this.addPolygon(divpoly.rvs[level].r0_ptr, divpoly.rvs[level].r1_ptr, divpoly.rvs[level].r2_ptr);
        }//end if (level_plus_one < divpoly.ndiv)
    }
    
    // recursive funcion
    private void calculateMidpoints4(SUBDIVPOLYGON4_16 divpoly, int level) {
        int level_plus_one = level + 1;
        
        // checks the root first.
        if (!clip_not_out_of_bound(divpoly, level)) {
           return;
        }

        // NEXT LEVEL = processing PREVIOUS LEVEL
        if (level_plus_one < divpoly.ndiv) {
            // BRANCH
            divpoly.rvs[level_plus_one].r01 = calculateMidpoints(divpoly.rvs[level].r0_ptr, divpoly.rvs[level].r1_ptr);
            divpoly.rvs[level_plus_one].r12 = calculateMidpoints(divpoly.rvs[level].r1_ptr, divpoly.rvs[level].r2_ptr);
            divpoly.rvs[level_plus_one].r23 = calculateMidpoints(divpoly.rvs[level].r2_ptr, divpoly.rvs[level].r3_ptr);
            divpoly.rvs[level_plus_one].r30 = calculateMidpoints(divpoly.rvs[level].r3_ptr, divpoly.rvs[level].r0_ptr);
            divpoly.rvs[level_plus_one].r0123 = calculateMidpoints(divpoly.rvs[level_plus_one].r01, divpoly.rvs[level_plus_one].r23);//center.
            divpoly.rvs[level_plus_one].level = level;// level of the midpoints.

            if(this.frameDraw != null){
                // for live simulation 
                this.frameDraw.addAndDrawPolygonFX(divpoly.rvs[level], Color.GRAY, globalVariables.pauze_between_frames);
            }
            
            //// next subdevision ////
            calculateMidpoints4_continue(divpoly, level_plus_one, divpoly.rvs[level].r0_ptr, divpoly.rvs[level_plus_one].r01, divpoly.rvs[level_plus_one].r0123, divpoly.rvs[level_plus_one].r30);

            calculateMidpoints4_continue(divpoly, level_plus_one, divpoly.rvs[level].r1_ptr, divpoly.rvs[level_plus_one].r12, divpoly.rvs[level_plus_one].r0123, divpoly.rvs[level_plus_one].r01);

            calculateMidpoints4_continue(divpoly, level_plus_one,  divpoly.rvs[level].r2_ptr, divpoly.rvs[level_plus_one].r23, divpoly.rvs[level_plus_one].r0123, divpoly.rvs[level_plus_one].r12);

            calculateMidpoints4_continue(divpoly, level_plus_one, divpoly.rvs[level].r3_ptr, divpoly.rvs[level_plus_one].r30, divpoly.rvs[level_plus_one].r0123, divpoly.rvs[level_plus_one].r23);
        } else {
            // LEAF
            this.addPolygon(divpoly.rvs[level].r0_ptr, divpoly.rvs[level].r1_ptr, divpoly.rvs[level].r2_ptr, divpoly.rvs[level].r3_ptr);
        }//end if (level_plus_one < divpoly.ndiv)
    }
    
    
    // call: calculateMidpoints3_continue(divpoly.rvs[level], divpoly.rvs[level].r2_ptr, divpoly.rvs[level].r20, divpoly.rvs[level].r12);
    private void calculateMidpoints3_continue(SUBDIVPOLYGON3_16 divpoly, int level, RVECTOR r0, RVECTOR r1, RVECTOR r2) {
        divpoly.rvs[level].r0_ptr = r0;//copy pointer sub_root
        divpoly.rvs[level].r1_ptr = r1;//copy pointer
        divpoly.rvs[level].r2_ptr = r2;//copy pointer
        if (SubdivisionDemoMod.this.clip_not_out_of_bound(divpoly, level)) {// returns true if visible in space.
            calculateMidpoints3(divpoly, level);
        }
    }

    private void calculateMidpoints4_continue(SUBDIVPOLYGON4_16 divpoly, int level, RVECTOR r0, RVECTOR r1, RVECTOR r2, RVECTOR r3) {
        divpoly.rvs[level].r0_ptr = r0;//copy pointer sub_root
        divpoly.rvs[level].r1_ptr = r1;//copy pointer
        divpoly.rvs[level].r2_ptr = r2;//copy pointer
        divpoly.rvs[level].r3_ptr = r3;//copy pointer
        if (clip_not_out_of_bound(divpoly, level)) {// returns true if visible in space.
            calculateMidpoints4(divpoly, level);
        }
    }

    public RVECTOR calculateMidpoints(RVECTOR a, RVECTOR b) {
        RVECTOR mid_out = new RVECTOR();
        mid_out.v.x = lerpIntMidpoints(a.v.x, b.v.x);
        mid_out.v.y = lerpIntMidpoints(a.v.y, b.v.y);
        mid_out.v.z = lerpIntMidpoints(a.v.z, b.v.z);

        mid_out.sxy.x = lerpIntMidpoints(a.sxy.x, b.sxy.x);
        mid_out.sxy.y = lerpIntMidpoints(a.sxy.y, b.sxy.y);

        mid_out.uv[0] = lerpIntMidpoints(a.uv[0], b.uv[0]);
        mid_out.uv[1] = lerpIntMidpoints(a.uv[1], b.uv[1]);

        mid_out.c.r = lerpIntMidpoints(a.c.r, b.c.r);
        mid_out.c.g = lerpIntMidpoints(a.c.g, b.c.g);
        mid_out.c.b = lerpIntMidpoints(a.c.b, b.c.b);
        mid_out.c.a = lerpIntMidpoints(a.c.a, b.c.a);
        
        mid_out.sz = lerpIntMidpoints(a.sz, b.sz);

        return mid_out;
    }

    public boolean clip_not_out_of_bound(SUBDIVPOLYGON3_16 divpoly, int level) {
        return clipTest(divpoly, divpoly.rvs[level].r0_ptr.sxy, divpoly.rvs[level].r1_ptr.sxy, divpoly.rvs[level].r2_ptr.sxy);
    }
    
    public boolean clip_not_out_of_bound(SUBDIVPOLYGON4_16 divpoly, int level) {
        return clipTest(divpoly, divpoly.rvs[level].r0_ptr.sxy, divpoly.rvs[level].r1_ptr.sxy, divpoly.rvs[level].r2_ptr.sxy, divpoly.rvs[level].r3_ptr.sxy);
    }
//// SCREEN SPACE, is polygon entirely out of screen.
    public boolean clipTest(SUBDIVPOLYGON3_16 divpoly, VECTOR2 v0, VECTOR2 v1, VECTOR2 v2) {
        if(!divpoly.isScreenSpaceClipping()){
            return true;
        }
        long minX, maxX, minY, maxY;
        
        //update from mouse
        divpoly.clipx = globalVariables.clipping_boundry.x;
        divpoly.clipy = globalVariables.clipping_boundry.y;
        divpoly.clipw = globalVariables.clipping_boundry.width;
        divpoly.cliph = globalVariables.clipping_boundry.height;

        minX = Math.min(v0.x, Math.min(v1.x, v2.x));
        maxX = Math.max(v0.x, Math.max(v1.x, v2.x));

        minY = Math.min(v0.y, Math.min(v1.y, v2.y));
        maxY = Math.max(v0.y, Math.max(v1.y, v2.y));

        // outside screen?
        if (maxX < divpoly.clipx || minX > divpoly.clipx + divpoly.clipw) {
            return false;
        }
        if (maxY < divpoly.clipy || minY > divpoly.clipy + divpoly.cliph) {
            return false;
        }
        return true; // potentially visible
    }

    public boolean clipTest(SUBDIVPOLYGON4_16 divpoly, VECTOR2 v0, VECTOR2 v1, VECTOR2 v2, VECTOR2 v3) {
        if(!divpoly.isScreenSpaceClipping()){
            return true;
        }
        long minX, maxX, minY, maxY;
        
        //update from mouse
        divpoly.clipx = globalVariables.clipping_boundry.x;
        divpoly.clipy = globalVariables.clipping_boundry.y;
        divpoly.clipw = globalVariables.clipping_boundry.width;
        divpoly.cliph = globalVariables.clipping_boundry.height;

        minX = Math.min(v0.x, Math.min(v1.x, Math.min(v2.x, v3.x)));
        maxX = Math.max(v0.x, Math.max(v1.x, Math.max(v2.x, v3.x)));

        minY = Math.min(v0.y, Math.min(v1.y, Math.min(v2.y, v3.y)));
        maxY = Math.max(v0.y, Math.max(v1.y, Math.max(v2.y, v3.y)));

        // outside screen?
        if (maxX < divpoly.clipx || minX > divpoly.clipx + divpoly.clipw) {
            return false;
        }
        if (maxY < divpoly.clipy || minY > divpoly.clipy + divpoly.cliph) {
            return false;
        }
        return true; // potentially visible
    }
    
    public GlobalVariables getGlobalVariables() {
        return globalVariables;
    }

    @Override
    public void setDeltaSpeed(long delta_speed) {
        globalVariables.pauze_between_frames += delta_speed;
        if(globalVariables.pauze_between_frames < 10){
            globalVariables.pauze_between_frames = 10;
        }else if(globalVariables.pauze_between_frames > 500){
            globalVariables.pauze_between_frames = 500;
        }
    }

    // In practice, this interface method will be put in a different class. "Redundant" interface was put here to reduce number of classes to implement.
    // See org.SubdivisionClean.Demo for propper implementation.
    @Override
    public void addPolygon(RVECTOR r0, RVECTOR r1, RVECTOR r2) {
        PolygonFX poly = new PolygonFX(
                new int[]{r0.sxy.x, r1.sxy.x, r2.sxy.x},
                new int[]{r0.sxy.y, r1.sxy.y, r2.sxy.y},
                new Color[]{r0.c.getColor(), r1.c.getColor(), r2.c.getColor()},
                3
        );

        if (this.frameDraw != null) {
            // for live simulation 
            this.frameDraw.addPolygon(poly);
            this.frameDraw.repaint_trigger();
            this.frameDraw.sleep(globalVariables.pauze_between_frames);
        }
        this.globalVariables.number_of_polygons_calculated++;
    }

    @Override
    public void addPolygon(RVECTOR r0, RVECTOR r1, RVECTOR r2, RVECTOR r3) {
        PolygonFX poly = new PolygonFX(
                new int[]{r0.sxy.x, r1.sxy.x, r2.sxy.x, r3.sxy.x},
                new int[]{r0.sxy.y, r1.sxy.y, r2.sxy.y, r3.sxy.y},
                new Color[]{r0.c.getColor(), r1.c.getColor(), r2.c.getColor(), r3.c.getColor()},
                4
        );

        if (this.frameDraw != null) {
            // for live simulation 
            this.frameDraw.addPolygon(poly);
            this.frameDraw.repaint_trigger();
            this.frameDraw.sleep(globalVariables.pauze_between_frames);
        }
        this.globalVariables.number_of_polygons_calculated++;
    }
}
