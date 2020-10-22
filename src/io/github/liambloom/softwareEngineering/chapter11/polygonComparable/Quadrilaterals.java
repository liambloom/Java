package io.github.liambloom.softwareEngineering.chapter11.polygonComparable;

/**
 * Mr. Marques
 *  Quadrilaterals is "abstract" because you can NOT have 'just' a Quadrilaterals. It has to
 *  be of a certain type. This is why there are no abstract methods in this abstract
 *  class.
 * 
 */


/* 
   (1) Write the abstract 'Quadrilaterals' class and have it implement 'Polygon_Comparable'
   (2) Declare 4 instance variables all of type double: myBase1, myBase2, myHeight, and myArea.
   (3) Write 2 constructors: the default and a 3 parameter one that utilizes the set methods 
       that sets everything to 0 or its parameter list.
   (4) Write/implement getMyCategory() returns "Quadrilaterals"
         NOTE: Write/implement getMyType() in EACH of the subclasses and NOT here!
   (5) Write 'compareTo()' which compares 2 polygons via their area.
*/
public abstract class Quadrilaterals implements Polygon_Comparable
{
    // What visibility should these be?
    double myBase1, myBase2, myHeight, myArea;

    // ---------------------- constructor ---------------------
    public Quadrilaterals() {
        this(0, 0, 0);
    }

    public Quadrilaterals(double myBase1, double myBase2, double myHeight) {
        setMyBase1(myBase1);
        setMyBase2(myBase2);
        setMyHeight(myHeight);

    }

    // ************ abstract method(s) & the overriding of such *************
             
    
    // ********************* Assessor and Mutator methods *************************
    public void setMyBase1(double b1)
    {
         myBase1 = b1;
    }
    public void setMyBase2(double b2)
    {
         myBase2 = b2;
    }    
    public void setMyHeight(double h)
    {
       myHeight = h; 
    }
    public void setMyArea(double a)
    {
        myArea = a;
    }
    
    public double getMyBase1()
    {
        return myBase1;
    }
    public double getMyBase2()
    {
        return myBase2;
    }
    public double getMyHeight()
    {
        return myHeight;
    }    
    
    @Override
    public double getMyArea()
    {
        calculateArea();  // Always calculateArea() first.
        return myArea;
    }

    @Override
    public String getMyCategory() {
        return "Quadrilaterals";
    }
            
    // ************************** toString() ******************************
    @Override
    public String toString() 
    { 
        return "Quadrilateral and I am also a Polygon_Comparable AND my area = " + getMyArea();
    }      
    
}