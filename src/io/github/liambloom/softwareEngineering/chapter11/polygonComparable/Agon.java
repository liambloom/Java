package io.github.liambloom.softwareEngineering.chapter11.polygonComparable;

/**
 * Mr. Marques
 *  Agon is "abstract" because you can NOT have 'just' a Agon. It has to
 *  be of a certain type. This is why there are no abstract methods in this abstract
 *  class.
 */

/* 
   x (1) Write the abstract 'Agon' class and have it implement 'Polygon_Comparable'
   (2) Declare 2 instance variables all of type double: mySideLength and myArea.
   (3) Declare another variable MY_NUM_SIDES as a final int.
   (4) Write 2 constructors: (a) the default (b) another that takes in the number
       of 'sides' and the 'length'. Define MY_NUM_SIDES to equal 'sides'.
   (4) Use set-methods in the constructors to set the variables values.
   (5) Write/implement getMyCategory() returns "Agon"
         NOTE: Write/implement getMyType() in EACH of the subclasses and NOT here!
   (6) Write 'compareTo()' which compares 2 polygons via their area.
*/

// Class heading   
public abstract class Agon implements Polygon_Comparable
{
    double mySideLength, myArea;
    final int MY_NUM_SIDES;


   // *********** Constructors ****************
   public Agon() {
        this(0, 0); 
        // This is a terrible idea
        // The default behavior should not be illegal behavior
   }
   public Agon(final int sides, final double length) {
       MY_NUM_SIDES = sides;
       setMySideLength(length);
       calculateArea();
   }

   
   
    // ************ abstract method(s) & the overiding of such ************
    
    
    // ********************* Assessor and Mutator methods *************************
    @Override
    public double getMyArea()
    {
        calculateArea();  // Always calculateArea() first.
        return myArea;
    }
    public double getMyNumSides()
    {
        return MY_NUM_SIDES;
    }
    // Why are there getters and setters if there is no checks performed?
    // You should have checks, but if you're not going to, just make the field public
    public double getMySideLength()
    {
        return mySideLength;
    }
    public void setMySideLength(double s)
    {
        mySideLength = s;
    }
    
    @Override
    public void calculateArea()
    {
        // This only works for regular polygons, and not all classes you want us to have are regular
        myArea = 1.0/4 * MY_NUM_SIDES * Math.pow(mySideLength,2) * (1.0/ Math.tan(Math.PI / MY_NUM_SIDES));
    }
    // Here too. Don't have a default implementation if you don't want it to be used
    @Override
    public String getMyCategory() {
        return "Agon";
    }
    
    // ************************** toString() ******************************
    @Override
    public String toString() 
    { 
        return getMyType() + " and I am also an Agon and I am also a Polygon_Comparable AND my area = " + getMyArea();
    }    
    
}