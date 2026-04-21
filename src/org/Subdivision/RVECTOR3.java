/*
 *
 * Copyright (C) 2026 Jiri Fajta
 * License: MIT license
 *
 */

package org.Subdivision;

public class RVECTOR3 {
    //Midpoints of root triangles at this level.
    //These 6 vertex attributes holds 4 triangles at the same level of subdivisionS.
    /**
     * <p>r01 holds a vertex midpoint of r0_ptr and r1_ptr attributes at recursion level i.</p>
     */
   public RVECTOR r01 = new RVECTOR();
    /**
     * <p>r12 holds a vertex midpoint of r1_ptr and r2_ptr attributes at recursion level i.</p>
     */
   public RVECTOR r12 = new RVECTOR();
    /**
     * <p>r20 holds a vertex midpoint of r2_ptr and r0_ptr attributes at recursion level i.</p>
     */
   public RVECTOR r20 = new RVECTOR();
   
   //Root triangle at this level.
    /**
     * <p>r0_ptr holds a reference vertex0 attributes of a root point at recursion level i.</p>
     */
   public RVECTOR r0_ptr = null;
    /**
     * <p>r1_ptr holds a reference vertex1 attributes of a root point at recursion level i.</p>
     */
   public RVECTOR r1_ptr = null;
    /**
     * <p>r2_ptr holds a reference vertex2 attributes of a root point at recursion level i.</p>
     */
   public RVECTOR r2_ptr = null;
   
    /**
     * <p>Level in the recursion three. 0 mean no subdivision. The higher n the more higher level of subdivisions (more details).</p>
     */
   public int level;// is not needed for recursion, it is just for monitoring.

    /**
     * <p> Get vertex values as string.
     * </p>
     * @return String as values store in RVECTOR3 object.
     */
    public String getString() {
        return "RVECTOR3{" + ", level=" + level + ", r0=" + r0_ptr.v.x +" "+ r0_ptr.v.y +" "+ r0_ptr.v.z +  ", r1=" + r1_ptr.v.x +" "+ r1_ptr.v.y +" "+ r1_ptr.v.z + ", r2=" + r2_ptr.v.x +" "+ r2_ptr.v.y +" "+ r2_ptr.v.z + ", sxy0=" + r0_ptr.sxy.x  + " "+ r0_ptr.sxy.y + ", sxy1=" + r1_ptr.sxy.x  + " "+ r1_ptr.sxy.y + ", sxy2=" + r2_ptr.sxy.x  + " "+ r2_ptr.sxy.y + '}';
    }
    
}


