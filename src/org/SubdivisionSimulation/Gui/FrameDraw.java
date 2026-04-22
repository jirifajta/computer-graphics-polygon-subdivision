/*
 *
 * Copyright (C) 2026 Jiri Fajta
 * License: MIT license
 *
 */

package org.subdivisionsimulation.gui;

import org.subdivisionsimulation.controllers.WindowController;
import org.subdivisionsimulation.controllers.MouseController;
import org.subdivisionsimulation.controllers.KeyboardController;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.subdivisionsimulation.GlobalVariables;
import org.subdivision.RVECTOR3;
import org.subdivision.RVECTOR4;
import org.subdivision.SUBDIVPOLYGON3_16;
import org.subdivision.SUBDIVPOLYGON4_16;
import org.subdivisionsimulation.components.MouseStatus;
import org.subdivisionsimulation.components.PolygonFX;
import org.subdivisionsimulation.interfacemodules.ControlInterface;

public class FrameDraw extends JPanel {
    
    private JFrame frame = null;
    private int frameWidth = 0;
    private int frameHeight = 0;
    //private Rectangle clipping_boundry = null;
    private MouseStatus mouseStatus = null;
    private ControlInterface cbi = null;
    private GlobalVariables globalVariables = null;
    
    //Display contentL
    private ArrayList<Polygon> polygons = null;
    private ArrayList<PolygonFX> polygonFXs = null;
    private final Color colorScreenSpaceClipping = new Color(220,220,220);
    private final Color color_string = new Color(255,255,255);
    private final int defaultStrokeSize = 3;
    private final int screen_text_offset = 30;
    private final int screen_text_delta = 22;
    private final String text_author = "Copyright (C) 2026 MSc Jiří Fajta";
    private final String text_website = "https://github.com/jirifajta";
    private final String text_license = "License: MIT license";
    private final String text_description = "Demonstrate polygon subdivision using recursive algorithm.";
    
    public void createFrame(String frameName, int frameLocation_X, int frameLocation_Y, int frameWidth, int frameHeight, boolean alwaysOnTop, boolean maximized_window, boolean fullScreen) {
        this.frame = new JFrame(frameName);
        this.frame.getContentPane().add(this);
        //this.frame.addKeyListener(this);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(frameWidth, frameHeight);
        this.frame.setSize(frameWidth, frameHeight);//even though using [maximized_window], this is still maximized_window to set.
        this.frame.setLocation(frameLocation_X, frameLocation_Y);
        this.frame.setAlwaysOnTop(alwaysOnTop);
        // Maximize window (but NOT fullscreen)
        this.frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.frame.setUndecorated(fullScreen);//FULLSCREEN
        this.frame.requestFocus();// keyboard active in window without clicking in this application first.
        this.frame.setVisible(true);
    }
    
    public void initContent(){
        this.polygons = new ArrayList<>();
        this.polygonFXs = new ArrayList<>();
        this.mouseStatus = new MouseStatus();
        
        if (this.globalVariables == null) {
            throw new IllegalArgumentException("FrameDraw: variable globalVariables cannot be null");
        }

        this.frame.addComponentListener(new WindowController(this));
        this.addMouseMotionListener(new MouseController(this));
        this.frame.addKeyListener(new KeyboardController(this));
    }

    public JFrame getFrame() {
        return frame;
    }

    public int getFrameWidth() {
        return frameWidth;
    }

    public int getFrameHeight() {
        return frameHeight;
    }

    public void setFrameWidth(int frameWidth) {
        this.frameWidth = frameWidth;
    }

    public void setFrameHeight(int frameHeight) {
        this.frameHeight = frameHeight;
    }

    public MouseStatus getMouseStatus() {
        return mouseStatus;
    }
    
    public void setControlInterface(ControlInterface cbi){
        this.cbi = cbi;
    }

    public ControlInterface getControlInterface() {
        return cbi;
    }
    
    public void setGlobalVariables(GlobalVariables gv){
        this.globalVariables = gv;
    }
    public GlobalVariables getGlobalVariables(){
        return this.globalVariables;
    }
    
    
    
    public void setClipping_boundry(int x, int y, int w, int h){
        this.globalVariables.clipping_boundry.x = x;
        this.globalVariables.clipping_boundry.y = y;
        this.globalVariables.clipping_boundry.width = w;
        this.globalVariables.clipping_boundry.height = h;
    }
    
    
    public void addPolygon(PolygonFX p){
        if(p == null) return;
        polygonFXs.add(p);
    }
    
    public void addPolygon(int[] xpoints, int[] ypoints, Color[] colors, int npoints){
        if(xpoints == null || ypoints == null || npoints<1) return;
        polygonFXs.add(new PolygonFX( xpoints, ypoints, colors, npoints));
    }
    
    public void addPolygonFXs(ArrayList<PolygonFX> ps){
        if(ps == null) return;
        polygonFXs.addAll(ps);
    }
    
    public void clearPolygonFXs(){
        if(polygonFXs == null) return;
        polygonFXs.clear();
    }
    
    public void addPolygon(Polygon p){
        if(p == null) return;
        polygons.add(p);
    }
    
    public void addPolygon(int[] xpoints, int[] ypoints, int npoints){
        if(xpoints == null || ypoints == null || npoints<1) return;
        polygons.add(new Polygon( xpoints, ypoints, npoints));
    }
    
    public void addPolygons(ArrayList<Polygon> ps){
        if(ps == null) return;
        polygons.addAll(ps);
    }
    
    public void clearPolygons(){
        if(polygons == null) return;
        polygons.clear();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        Font myFont = new Font("Serif", Font.PLAIN, 20);
        g.setFont(myFont);
        //Background
        g.setColor(Color.black);
        g.fillRect(0, 0, this.frameWidth + 60, this.frameHeight + 60);
        
        //Set Line Tickness
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(this.defaultStrokeSize)); // thickness = 3 pixels
       
        // draw polygonFXs
        drawPolygonFXs(g, g2);
        
        g2.setStroke(new BasicStroke(this.defaultStrokeSize));
        // draw polygons, this is currently empty list.
        drawPolygons(g);
       
        // draw virtual clip space in screen space
        g.setColor(colorScreenSpaceClipping);
        g.drawRect(globalVariables.clipping_boundry.x , globalVariables.clipping_boundry.y , globalVariables.clipping_boundry.width, globalVariables.clipping_boundry.height);
        
        // Display text
        drawHud(g);
    }

    private void drawPolygonFXs(Graphics g, Graphics2D g2) {
        if(polygonFXs == null){
            return;
        }
            
        for (int poly_i = 0; poly_i < polygonFXs.size(); poly_i++) {
            if(polygonFXs.get(poly_i) == null) {
                continue;
            }
            if (polygonFXs.get(poly_i).npoints != 1) {
                if (polygonFXs.get(poly_i).getColor()[0].getRGB() == Color.gray.getRGB()) {
                    // This is a debug function, to show which triangle is active to subdivide.
                    // This line is thinner be shore that the final triangle line overlaps this gray lin.
                    g2.setStroke(new BasicStroke(2)); // thickness = 2 pixels
                } else {
                    g2.setStroke(new BasicStroke(3)); // thickness = 3 pixels
                }
                
                // calculate average color of all vertecies of a polygon.
                int re = 0;
                int gr = 0;
                int bl = 0;
                int al = 0;
                for (int vertex_i = 0; vertex_i < polygonFXs.get(poly_i).npoints; vertex_i++) {
                    re += polygonFXs.get(poly_i).getColor()[vertex_i].getRed();
                    gr += polygonFXs.get(poly_i).getColor()[vertex_i].getGreen();
                    bl += polygonFXs.get(poly_i).getColor()[vertex_i].getBlue();
                    al += polygonFXs.get(poly_i).getColor()[vertex_i].getAlpha();
                }//end for
                re /= polygonFXs.get(poly_i).npoints;
                gr /= polygonFXs.get(poly_i).npoints;
                bl /= polygonFXs.get(poly_i).npoints;
                al /= polygonFXs.get(poly_i).npoints;
                g.setColor(new Color(re,gr,bl,al));
            } else {
                g.setColor(polygonFXs.get(poly_i).getColor()[0]);
            }
            g.drawPolygon(polygonFXs.get(poly_i));
        }//end  for(int poly_i = 0 ; poly_i < ; poly_i++)
    }
    
    private void drawPolygons(Graphics g) {
        g.setColor(new Color(200, 20, 20));
        for (int poly_i = 0; poly_i < polygons.size(); poly_i++) {
            g.drawPolygon(polygons.get(poly_i));
        }//end  for(int poly_i = 0 ; poly_i < ; poly_i++)
    }

    private void drawHud(Graphics g) {
        int screen_text_step = screen_text_offset;
        if (this.globalVariables.showHudToggle.getCurrentIndex() < 2) {
            g.setColor(color_string);
            g.drawString("Key numeric 0-4 or", 20, screen_text_step);
            screen_text_step += screen_text_delta;
            g.drawString("Key page up/down", 20, screen_text_step);
            int division = (1 << this.globalVariables.ndiv);
            g.drawString("subdevision level: " + this.globalVariables.ndiv + " (queued level: " + this.globalVariables.ndiv_queued + ")", 200, screen_text_step);
            screen_text_step += screen_text_delta;
            g.drawString("Key - / +:", 20, screen_text_step);
            g.drawString("slow / fast pause: " + this.globalVariables.pauze_between_frames + " ms", 200, screen_text_step);
            screen_text_step += screen_text_delta;
            g.drawString("Key S:", 20, screen_text_step);
            g.drawString("Shape: " + this.globalVariables.shapeToggle.getCurrentLable(), 200, screen_text_step);
            screen_text_step += screen_text_delta;
            g.drawString("Key H:", 20, screen_text_step);
            g.drawString("toggle HUD mode: " + this.globalVariables.showHudToggle.getCurrentLable(), 200, screen_text_step);
            screen_text_step += screen_text_delta;
            g.drawString("Mouse clip space:", 20, screen_text_step);
            g.drawString("" + mouseStatus.pos_x + " , " + mouseStatus.pos_y, 200, screen_text_step);
            screen_text_step += screen_text_delta;
            g.drawString("Subdevision level:", 20, screen_text_step);
            g.drawString(division + " x " + division, 200, screen_text_step);
            screen_text_step += screen_text_delta;
            g.drawString("Polygon count:", 20, screen_text_step);
            g.drawString(this.globalVariables.number_of_polygons_calculated + " / " + (division * division), 200, screen_text_step);
        }
        if (this.globalVariables.showHudToggle.getCurrentIndex() == 1) {
            screen_text_step += screen_text_delta + 10;
            g.drawString(text_description, 20, screen_text_step);
            screen_text_step += screen_text_delta;
            g.drawString(text_author, 20, screen_text_step);
            screen_text_step += screen_text_delta;
            g.drawString(text_website, 20, screen_text_step);
            screen_text_step += screen_text_delta;
            g.drawString(text_license, 20, screen_text_step);
        }
    }
    
    public void clearFrame(){
        this.clearPolygons();
        this.clearPolygonFXs();
        this.repaint_trigger();
    }

    public void repaint_trigger() {
        repaint();
    }
    
    public void dispose() {
        frame.dispose();
    }

    public void sleep(long sleep_milliseconds) {
        try {
            Thread.sleep(sleep_milliseconds);
        } catch (InterruptedException ex) {
        }
    }
    
    public void addAndDrawPolygonFX(RVECTOR3 crv, Color color, long sleep_milliseconds) {
        PolygonFX p_temp0;
        p_temp0 = new PolygonFX(
                new int[]{crv.r0_ptr.sxy.x, crv.r1_ptr.sxy.x, crv.r2_ptr.sxy.x},
                new int[]{crv.r0_ptr.sxy.y, crv.r1_ptr.sxy.y, crv.r2_ptr.sxy.y},
                new Color[]{color,color,color},
                3
        );
        this.addPolygon(p_temp0);
        this.repaint_trigger();
        this.sleep(sleep_milliseconds);
    }
    
    public void addAndDrawPolygonFX(SUBDIVPOLYGON3_16 divpoly, Color color, long sleep_milliseconds) {
        PolygonFX p_temp0;
        p_temp0 = new PolygonFX(
                new int[]{divpoly.r0.sxy.x, divpoly.r1.sxy.x, divpoly.r2.sxy.x},
                new int[]{divpoly.r0.sxy.y, divpoly.r1.sxy.y, divpoly.r2.sxy.y},
                new Color[]{color,color,color},
                3
        );
        this.addPolygon(p_temp0);
        this.repaint_trigger();
        this.sleep(sleep_milliseconds);
    }
    
    public void addAndDrawPolygonFX(RVECTOR4 crv, Color color, long sleep_milliseconds) {
        PolygonFX p_temp0;
        p_temp0 = new PolygonFX(
                new int[]{crv.r0_ptr.sxy.x, crv.r1_ptr.sxy.x, crv.r2_ptr.sxy.x, crv.r3_ptr.sxy.x},
                new int[]{crv.r0_ptr.sxy.y, crv.r1_ptr.sxy.y, crv.r2_ptr.sxy.y, crv.r3_ptr.sxy.y},
                new Color[]{color,color,color,color},
                4
        );
        this.addPolygon(p_temp0);
        this.repaint_trigger();
        this.sleep(sleep_milliseconds);
    }
    
    public void addAndDrawPolygonFX(SUBDIVPOLYGON4_16 divpoly, Color color, long sleep_milliseconds) {
        PolygonFX p_temp0;
        p_temp0 = new PolygonFX(
                new int[]{divpoly.r0.sxy.x, divpoly.r1.sxy.x, divpoly.r2.sxy.x, divpoly.r3.sxy.x},
                new int[]{divpoly.r0.sxy.y, divpoly.r1.sxy.y, divpoly.r2.sxy.y, divpoly.r3.sxy.y},
                new Color[]{color,color,color,color},
                4
        );
        this.addPolygon(p_temp0);
        this.repaint_trigger();
        this.sleep(sleep_milliseconds);
    }
    

}
