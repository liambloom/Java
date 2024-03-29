package dev.liambloom.softwareEngineering.chapter11.polygonComparable;

/**
 * Mr. Marques
 *  Triangles is "abstract" because you can NOT have 'just' a Triangle. It has to
 *  be of a certain type. This is why there are no abstract methods in this abstract
 *  class.
 */

/*
   x (1) Write the abstract 'Triangles' class and have it implement 'Polygon_Comparable' 
   x (2) It has 3 instance variables all of type double: myBase, myHeight, and myArea.
   (3) It has 2 constructors: the default and one that takes in 2 doubles for the base and height.
   (4) Write/implement getMyCategory() returns "Triangles"
         NOTE: Write/implement getMyType() in EACH of the subclasses and NOT here!
   (5) Write 'compareTo()' which compares 2 polygons via their area.
*/
   
// Class heading   
public abstract class Triangles implements Polygon_Comparable
{
// Instance variables
double myBase, myHeight, myArea;


   // *********** Constructors ****************
   public Triangles() {
    this(0, 0);
   }
    public Triangles(double base, double height) {
        setMyBase(base);
        setMyHeight(height);
    }  
   
    // ************ abstract getMyCategory() and getMyType() methods overidem *************
   
    @Override
    public String getMyCategory() {
       return "Triangles";
    }

  
    // **************** Accessor/Mutator Methods ****************    
    // ---- overriden abstract methods calculateArea() & getMyArea() ----
    @Override
    public void calculateArea()
    {
        myArea = 0.5 * myBase * myHeight;    
    }  

    @Override
    public double getMyArea()
    {     
        calculateArea(); // Always calculateArea() first.
        return myArea;
    }  
    public double getMyBase()
    {
        return myBase;
    }
    public void setMyBase(double b)
    {
        myBase = b;
    }
    public void setMyHeight(double h)
    {
        myHeight = h;
    }
    
    // ************************* compareTo() *************************************
    
    
    // ************************** toString() ******************************
    public String toString() 
    { 
        return getMyType() + " and I am also a Triangle and I am also a Polygon_Comparable AND my area = " + getMyArea();
    }
        
} // class Triangles