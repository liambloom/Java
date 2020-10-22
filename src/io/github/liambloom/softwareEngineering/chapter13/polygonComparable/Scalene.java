package io.github.liambloom.softwareEngineering.chapter13.polygonComparable;

/**
 * (1) Have the Scalene class extend Triangles.  
 * (2) Write 2 constructors:  (a) the default that calls the super classes default 
 *     constructor and (b) One that takes in 2 doubles representing the base and 
 *     the height.  It should then call the super classes constructor with these 
 *     2 arguments.
 * (3) Override the method getMyType() that returns "Scalene"
 */

public class Scalene extends Triangles
{  

    // Constructors
    public Scalene() {
        super();
    }
    public Scalene(double base, double height) {
        super(base, height);
    }

}