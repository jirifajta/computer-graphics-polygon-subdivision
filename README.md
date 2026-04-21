# Polygon subdivision

A program to demonstrate a recursive polygon subdivision algorithm as an interactive simulation and library. It features stop searching condition for subdivisions if it is out of bounding box. Supports division up to 16x16. With a very small modification, it is possible to divide even much more. The aim of this limitation is to use fixed memory that can be used in the future in C code.<br/>
[Introduction to Recursion at GeeksforGeeks](https://www.geeksforgeeks.org/dsa/introduction-to-recursion-2/)  provides a simple concept of recursive algorithm to learn the basics.<br/>

Polygon subdivision in 3D graphics is used to divide a large polygon into smaller polygons to filter what polygons are in view space. This ensures that only the visible polygons are drawn by GPU's rasterizer. Note that clipping is done in screen space not in clip space (frustum clipping).

<div align="center" width="100% style="margin-bottom:0;">
    <img src="images/Simulation_2.gif">
	<p><i>Recursive 16x16 subdivision algorithm live.</i></p>
</div>
<br/>
<div align="center" width="100% style="margin-bottom:0;">
    <img src="images/Simulation_4.gif">
	<p><i>Interactive simulation 16x16 subdivision live.</i></p>
</div>
<br/>
<div align="center" width="100% style="margin-bottom:0;">
    <img src="images/Simulation_full_8x8.gif">
	<p><i>Full 8x8 subdivision live.</i></p>
</div>
<br/>

# Code
Source code and application are available for Java.
Actual source code for Java library can be found in folder _org.Subdivision_.<br/>
The program has two main functions.

* Practical usage how to call function:
src\org\Subdivision\MainPracticalDemo.java
  - It contains clean code to apply.
* Modified version to simulate on display:
src\org\SubdivisionSimulation\MainSubdivisionSimulation.java
  - Is modified in such a way to update display at every little update during calculation.
<br/>
There is an easter egg hidden in the code!<br/><br/>

**Author:** MSc Jiří Fajta<br/>
**Code implementation date:** 2026<br/>
