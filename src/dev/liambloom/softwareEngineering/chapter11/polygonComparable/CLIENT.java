package dev.liambloom.softwareEngineering.chapter11.polygonComparable;


/**
 * Mr. Marques
 * Polygon_Comparable CLIENT has an ArrayList of Polygons called PolygonShapesList that 'adds' to the end of it,
 * 'adds at an index specified', 'removes' from it at an index specified, 'sets' at an index specified, and
 * 'gets' a Polygon and finds its area.  It also finds the Polygon with the largest and smallest area.
 */

import javax.swing.JOptionPane;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

@SuppressWarnings({"rawtypes", "unchecked"})
public class CLIENT
{
    // **************************************************************************************************
    // ***************************************** init() (MAIN) ******************************************
    // **************************************************************************************************    
    public static void main(String[] args)
    {
    // Declare + define ArrayList 'polygonShapesList' of type Polygon_Comparable
    ArrayList<Polygon_Comparable> polygonShapesList = new ArrayList<>();
    String polygonShapesListItems="";
    int mainMenuSelection;

     do { 
 
        polygonShapesListItems="";
        for (int i = 0; i<polygonShapesList.size(); i++)
            polygonShapesListItems += "(" + i + ") " + polygonShapesList.get(i) + "\n";
         
        mainMenuSelection = Integer.parseInt( JOptionPane.showInputDialog(" Enter your menu selection: \n " +
                       polygonShapesListItems + "\n\n\n" +
                 "(0) Quit. \n" +
                 "(1) Add a new Polygon at the end of the list. \n" +
                 "(2) Add a new Polygon at an index 'i' in the list. \n" +
                 "(3) Remove a Polygon at an index 'i' in the list. \n" +
                 "(4) Set a new Polygon to an index 'i' in the list. \n" +
                 "(5) Get a Polygon at an index 'i' in the list and find the Area of it. \n" +
                 "(6) Find the Polygon with the largest area. \n" +
                 "(7) Find the Polygon with the smallest area.\n"));
        
        switch (mainMenuSelection) {
            case 0 : JOptionPane.showMessageDialog(null,"Good Bye!");  System.exit(0); break;
            case 1 : addPolygonToEndOfList(polygonShapesList); break;
            case 2 : addPolygonAtAnIndex(polygonShapesList); break;            
            case 3 : removePolygonAtAnIndex(polygonShapesList); break;
            case 4 : setPolygonAtAnIndex(polygonShapesList); break;            
            case 5 : getAndFindAreaOfPolygonAtAnIndex(polygonShapesList); break;
            case 6 : findPolygonWithLargestArea(polygonShapesList); break;
            case 7 : findPolygonWithSmallestArea(polygonShapesList); break;
            default:
                JOptionPane.showMessageDialog(null," Thanks for playing PolygonShapesList game! \n Have a good Day!!");
                System.exit(0);
                break;       
        }
        
      } while (/*1==1 changed to remove warning*/true);
    }
    
    // **************************************************************************************************
    // ******************************** addPolygon section ********************************************    
    // **************************************************************************************************    
   public static void addPolygonToEndOfList(ArrayList<Polygon_Comparable> pList)
    {
     int polygonSelection;
     boolean badSelection;
     int pListEndPosition;
     
      pListEndPosition = ( (pList.size() == 0) ? 0 : pList.size()-1);
     
      do {
        polygonSelection = Integer.parseInt (JOptionPane.showInputDialog(" Enter your menu selection from these categories: \n " +
                                  "(1) Triangle  (2) Quadrilateral  (3) Agon  (4) Rounds \n") ); //Polygon.showCategories()); 
        badSelection=false;
        switch (polygonSelection) {
            case 1 : addTriangleAtAnIndex(pList,pListEndPosition);  break;
            case 2 : addQuadrilateralAtAnIndex(pList,pListEndPosition);  break;
            case 3 : addAgonAtAnIndex(pList,pListEndPosition);  break;
            case 4 : addRoundAtAnIndex(pList,pListEndPosition);  break;                                    
            default: 
                  JOptionPane.showMessageDialog(null," ERROR! \n Please choose only from the listed categories");
                  badSelection = true;
                  break;
        }
     
     } while (badSelection == true);
     
    }    
    // ***********************************************************************************************
    public static void addPolygonAtAnIndex(List<Polygon_Comparable> pList)
    {
     int polygonSelection;
     boolean badSelection;
     int indexSelection;
     
      do {
        indexSelection = Integer.parseInt(JOptionPane.showInputDialog("Enter an index number 0 - " + (pList.size()-1)));
        if ( !( 0 <= indexSelection && indexSelection <= pList.size()-1))
            JOptionPane.showMessageDialog(null,"Enter ONLY numbers 0 - " + (pList.size()-1),"Warning",JOptionPane.PLAIN_MESSAGE);  // "Enter ONLY numbers 0 - " + pList.size()-1, "Warning!!",JOptionPane.WARNING_MESSAGE);
      } while (! (0 <= indexSelection && indexSelection <= pList.size()-1) );
     
     
      do {
        polygonSelection = Integer.parseInt (JOptionPane.showInputDialog(" Enter your menu selection from these categories: \n " +
                                  "(1) Triangle  (2) Quadrilateral  (3) Agon  (4) Rounds \n") ); //Polygon.showCategories()); 
        badSelection=false;
        switch (polygonSelection) {
            case 1 : addTriangleAtAnIndex(pList,indexSelection);  break;
            case 2 : addQuadrilateralAtAnIndex(pList,indexSelection);  break;
            case 3 : addAgonAtAnIndex(pList,indexSelection);  break;
            case 4 : addRoundAtAnIndex(pList,indexSelection);  break;                                    
            default: 
                  JOptionPane.showMessageDialog(null," ERROR! \n Please choose only from the listed categories");
                  badSelection = true;
                  break;
        }
     
     } while (badSelection == true);          
        
    }
    // --------------------------------------------------------------------------------------------
    public static void addTriangleAtAnIndex(List pList,int index)
    {
    int choice;
    double b, h;
    
        choice = Integer.parseInt( JOptionPane.showInputDialog(null, "Please select a Triangle from the following list: \n"
                                     + " (1) Scalene  (2) Isosceles  (3) Equilateral ") );  //   + Triangles.typesAvailable()) );  
        b = Double.parseDouble( JOptionPane.showInputDialog(null, "Please type in the base of the Triangle: \n") );    
        h = Double.parseDouble( JOptionPane.showInputDialog(null, "Please type in the height of the Triangle: \n") );          
                              
        Triangles triangle = null;

        while (triangle == null) {
            switch (choice) {
                case 1:  
                    triangle = new Scalene(b, h);
                    break;
                case 2: 
                    triangle = new Isosceles(b, h);
                    break;
                case 3:
                    triangle = new Equilateral(b, h);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, " ERROR! \n Please choose only from the listed categories");
                    break;
            }
        }
        

        pList.add(index, triangle);
    }
    // --------------------------------------------------------------------------------------------
    public static void addQuadrilateralAtAnIndex(List pList,int index)
    {
    int choice;
    double b1,b2,h;
    
        choice = Integer.parseInt( JOptionPane.showInputDialog(null, "Please select a Quadrilateral from the following list: \n"
                                    + " (1) Trapezoid (2) Isosceles Trapezoid (3) Parallelogram  (4) Rhombus (5) Rectangle (6) Square   ") );

        b1 = Double.parseDouble( JOptionPane.showInputDialog(null," Enter the base: \n") );        
        h = Double.parseDouble( JOptionPane.showInputDialog(null," Enter the height: \n") );   
        
        Quadrilaterals quadrilateral = null;

        while (quadrilateral == null) {
            switch (choice) {
                case 1:
                    b2 = Double.parseDouble(JOptionPane.showInputDialog(null, " Enter 2nd base: \n"));
                    quadrilateral = new Trapezoid(b1, b2, h);
                    break;
                case 2:
                    b2 = Double.parseDouble(JOptionPane.showInputDialog(null, " Enter 2nd base: \n"));
                    quadrilateral = new IsoscelesTrapezoid(b1, b2, h);
                    break;
                case 3:
                    quadrilateral = new Parallelogram(b1, h);
                    break;
                case 4:
                    quadrilateral = new Rhombus(b1, h);
                    break;
                case 5:
                    quadrilateral = new Rectangle(b1, h);
                    break;
                case 6:
                    if (b1 != h) {
                        JOptionPane.showMessageDialog(null, " ERROR! \n A square's base and height must be equal");
                    }
                    else
                        quadrilateral = new Square(b1);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, " ERROR! \n Please choose only from the listed categories");
                    break;
            }
        }
                  
        pList.add(index, quadrilateral);  
    }    
    // --------------------------------------------------------------------------------------------    
    public static void addAgonAtAnIndex(List pList,int index)
    {
    int choice;
    double side;
    
        choice = Integer.parseInt( JOptionPane.showInputDialog(null, "Please select a Agon from the following list: \n"
                         + " (1)Pentagon (2) Hexagon (3) Heptagon (4) Octagon (5) Nonagon (6) Decagon (7) Dodecagon \n") );
        side = Double.parseDouble( JOptionPane.showInputDialog(null, "Please enter the length of the side: \n" ) );

        Agon agon = null;

        while (agon == null) {
            switch (choice) {
                case 1:
                    agon = new Pentagon(side);
                    break;
                case 2:
                    agon = new Hexagon(side);
                    break;
                case 3:
                    agon = new Heptagon(side);
                    break;
                case 4:
                    agon = new Octagon(side);
                    break;
                case 5:
                    agon = new Nonagon(side);
                    break;
                case 6:
                    agon = new Decagon(side);
                    break;
                case 7:
                    agon = new Dodecagon(side);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, " ERROR! \n Please choose only from the listed categories");
                    break;
            }
        }
        
        pList.add(index, agon);   
    }    
    // --------------------------------------------------------------------------------------------    
    public static void addRoundAtAnIndex(List pList,int index)
    {
    int choice;
    double radius1, radius2;

        choice = Integer.parseInt( JOptionPane.showInputDialog(null, "Please select a Round from the following list: \n"
                                    + " (1) Circle + \n (2) SemiCircle + \n (3) Ellipse + \n  ") );
        radius1 = Double.parseDouble( JOptionPane.showInputDialog(null, "Please enter the length of the radius: \n" ) );
                         
        Rounds round = null;

        while (round == null) {
            switch (choice) {
                case 1:
                    round = new Circle(radius1);
                    break;
                case 2:
                    round = new SemiCircle(radius1);
                    break;
                case 3:
                    radius2 = Double.parseDouble(
                            JOptionPane.showInputDialog(null, "Please enter the length of the radius2: \n"));
                    round = new Ellipse(radius1, radius2);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, " ERROR! \n Please choose only from the listed categories");
                    break;
            }
        }
                   
        pList.add(index, round);
    }    
   
     // **************************************************************************************************    
     // *************************** removePolygon section ********************************************     
     // **************************************************************************************************
     public static void removePolygonAtAnIndex(ArrayList pList)
    {
    int i;
    String output="";
    int indexSelection;
    
        for (i=0; i<pList.size(); i++)
            output += "(" + i +") " + (Polygon_Comparable)pList.get(i) +"\n";

      do {
        indexSelection = Integer.parseInt( JOptionPane.showInputDialog("Please enter a selection for Deletion from the list: \n" + output));
        if ( !( 0 <= indexSelection && indexSelection <= pList.size()-1))
            JOptionPane.showMessageDialog(null,"Enter ONLY numbers 0 - " + (pList.size()-1),"Warning",JOptionPane.PLAIN_MESSAGE);  // "Enter ONLY numbers 0 - " + pList.size()-1, "Warning!!",JOptionPane.WARNING_MESSAGE);
      } while ( !(0 <= indexSelection && indexSelection <= pList.size()-1));
  
        pList.remove(indexSelection);

        output="";
        for (i=0; i<pList.size(); i++)
            output += "(" + i +") " + (Polygon_Comparable)pList.get(i) +"\n";
        JOptionPane.showMessageDialog(null," Remove is successful.  The new list is: \n" + output);
                                               
    }

    // **************************************************************************************************    
    // ************************************* setPolygonAtAnIndex ******************************************
    // **************************************************************************************************
    public static void setPolygonAtAnIndex(List pList)
    {
     int polygonSelection;
     boolean badSelection;
     int indexSelection;
     
      do {
        indexSelection = Integer.parseInt(JOptionPane.showInputDialog("Enter an index number 0 - " + pList.size()));
        if ( !( 0 <= indexSelection && indexSelection <= pList.size()-1))
            JOptionPane.showMessageDialog(null, "Enter ONLY numbers 0 - " + (pList.size()-1), "Warning!!",JOptionPane.WARNING_MESSAGE);
      } while ( !( 0 <= indexSelection && indexSelection <= pList.size()-1));
     
     
      do {
        polygonSelection = Integer.parseInt (JOptionPane.showInputDialog(" Enter your menu selection from these categories: \n " +
                                  "(1) Triangle  (2) Quadrilateral  (3) Agon  (4) Rounds \n") ); //Polygon.showCategories()); 
        badSelection=false;
        switch (polygonSelection) {
            case 1 : setTriangleAtAnIndex(pList,indexSelection);  break;
            case 2 : setQuadrilateralAtAnIndex(pList,indexSelection);  break;
            case 3 : setAgonAtAnIndex(pList,indexSelection);  break;
            case 4 : setRoundAtAnIndex(pList,indexSelection);  break;                                    
            default: 
                  JOptionPane.showMessageDialog(null," ERROR! \n Please choose only from the listed categories");
                  badSelection = true;
                  break;
        }
     
     } while (badSelection == true);
          
    }
    // --------------------------------------------------------------------------------------------
    public static void setTriangleAtAnIndex(List pList,int index)
    {
    int choice;
    double b, h;
    
        choice = Integer.parseInt( JOptionPane.showInputDialog(null, "Please select a Triangle from the following list: \n"
                                     + " (1) Scalene  (2) Isosceles  (3) Equilateral ") );  //   + Triangles.typesAvailable()) );  
        b = Double.parseDouble( JOptionPane.showInputDialog(null, "Please type in the base of the Triangle: \n") );    
        h = Double.parseDouble( JOptionPane.showInputDialog(null, "Please type in the height of the Triangle: \n") );          
                                 
        // Yes, I just copied and pasted this. Yes, I know that's not good 
        // code. But the framework you gave us had the same thing twice, so
        // that's what I'm doing.

        Triangles triangle = null;

        while (triangle == null) {
            switch (choice) {
                case 1:
                    triangle = new Scalene(b, h);
                    break;
                case 2:
                    triangle = new Isosceles(b, h);
                    break;
                case 3:
                    triangle = new Equilateral(b, h);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, " ERROR! \n Please choose only from the listed categories");
                    break;
            }
        }

        pList.set(index, triangle);
    }
    // --------------------------------------------------------------------------------------------
    public static void setQuadrilateralAtAnIndex(List pList,int index)
    {
    int choice;
    double b1,b2,h;
    
        choice = Integer.parseInt( JOptionPane.showInputDialog(null, "Please select a Quadrilateral from the following list: \n"
                                    + " (1) Trapezoid (2) Isosceles Trapezoid (3) Parallelogram  (4) Rhombus (5) Rectangle (6) Square   ") );

        b1 = Double.parseDouble( JOptionPane.showInputDialog(null," Enter the base: \n") );        
        h = Double.parseDouble( JOptionPane.showInputDialog(null," Enter the height: \n") );        
        Quadrilaterals quadrilateral = null;

        while (quadrilateral == null) {
            switch (choice) {
                case 1:
                    b2 = Double.parseDouble(JOptionPane.showInputDialog(null, " Enter 2nd base: \n"));
                    quadrilateral = new Trapezoid(b1, b2, h);
                    break;
                case 2:
                    b2 = Double.parseDouble(JOptionPane.showInputDialog(null, " Enter 2nd base: \n"));
                    quadrilateral = new IsoscelesTrapezoid(b1, b2, h);
                    break;
                case 3:
                    quadrilateral = new Parallelogram(b1, h);
                    break;
                case 4:
                    quadrilateral = new Rhombus(b1, h);
                    break;
                case 5:
                    quadrilateral = new Rectangle(b1, h);
                    break;
                case 6:
                    if (b1 != h) {
                        JOptionPane.showMessageDialog(null, " ERROR! \n A square's base and height must be equal");
                    } else
                        quadrilateral = new Square(b1);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, " ERROR! \n Please choose only from the listed categories");
                    break;
            }
        }

        pList.set(index, quadrilateral);
            
    }    
    // --------------------------------------------------------------------------------------------    
    public static void setAgonAtAnIndex(List pList,int index)
    {
    int choice;
    double side;
    
        choice = Integer.parseInt( JOptionPane.showInputDialog(null, "Please select a Agon from the following list: \n"
                         + " (1)Pentagon (2) Hexagon (3) Heptagon (4) Octagon (5) Nonagon (6) Decagon (7) Dodecagon \n") );
        side = Double.parseDouble( JOptionPane.showInputDialog(null, "Please enter the length of the side: \n" ) );
        Agon agon = null;

        while (agon == null) {
            switch (choice) {
                case 1:
                    agon = new Pentagon(side);
                    break;
                case 2:
                    agon = new Hexagon(side);
                    break;
                case 3:
                    agon = new Heptagon(side);
                    break;
                case 4:
                    agon = new Octagon(side);
                    break;
                case 5:
                    agon = new Nonagon(side);
                    break;
                case 6:
                    agon = new Decagon(side);
                    break;
                case 7:
                    agon = new Dodecagon(side);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, " ERROR! \n Please choose only from the listed categories");
                    break;
            }
        }

        pList.set(index, agon);
            
    }    
    // --------------------------------------------------------------------------------------------    
    public static void setRoundAtAnIndex(List pList,int index)
    {
    int choice;
    double radius1, radius2;

        choice = Integer.parseInt( JOptionPane.showInputDialog(null, "Please select a Round from the following list: \n"
                                    + " (1) Circle + \n (2) SemiCircle + \n (3) Ellipse + \n  ") );
        radius1 = Double.parseDouble( JOptionPane.showInputDialog(null, "Please enter the length of the radius: \n" ) );
                                    
        Rounds round = null;

        while (round == null) {
            switch (choice) {
                case 1:
                    round = new Circle(radius1);
                    break;
                case 2:
                    round = new SemiCircle(radius1);
                    break;
                case 3:
                    radius2 = Double.parseDouble(
                            JOptionPane.showInputDialog(null, "Please enter the length of the radius2: \n"));
                    round = new Ellipse(radius1, radius2);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, " ERROR! \n Please choose only from the listed categories");
                    break;
            }
        }

        pList.set(index, round);   
            
    }        
    
     // **************************************************************************************************
     // *************************** findAreaOfPolygon section ******************************************** 
     // **************************************************************************************************
     public static void getAndFindAreaOfPolygonAtAnIndex(ArrayList pList)
    {
    int i;
    String output="";
    int areaSelection;
    Polygon_Comparable p;
    
        for (i=0; i<pList.size(); i++)
            output += "(" + i +") " + (Polygon_Comparable) pList.get(i) +"\n";
    
        areaSelection = Integer.parseInt( JOptionPane.showInputDialog("Please enter a selection for Area from the list: \n" + output));

        // I assume this is what you wanted, but I'm not certain
        p = (Polygon_Comparable) pList.get(areaSelection);
        
        JOptionPane.showMessageDialog(null, "Area of the " + p.getMyType() + " " + p.getMyCategory() + " = " + p.getMyArea());
        
    }    

    // **************************************************************************************************
    // ******************** find Largest/Smallest Area Of Polygon section ******************************* 
    // **************************************************************************************************
    // IDK what information you 
    public static void findPolygonWithLargestArea(ArrayList<Polygon_Comparable> pList)
    {
        if (pList.size() == 0) {
            JOptionPane.showMessageDialog(null, "There is no largest polygon in an empty list");
            return;
        }
        Iterator<Polygon_Comparable> iter = pList.iterator();
        Polygon_Comparable max = iter.next();
        while (iter.hasNext()) {
            Polygon_Comparable next = iter.next();
            if (max.compareTo(next) < 0)
                max = next;
        }
        JOptionPane.showMessageDialog(null, "The largest polygon is the " + max.getMyType() + " with the area " + max.getMyArea());
    }
    public static void findPolygonWithSmallestArea(ArrayList<Polygon_Comparable> pList)
    {
        if (pList.size() == 0) {
            JOptionPane.showMessageDialog(null, "There is no smallest polygon in an empty list");
            return;
        }
        Iterator<Polygon_Comparable> iter = pList.iterator();
        Polygon_Comparable min = iter.next();
        while (iter.hasNext()) {
            Polygon_Comparable next = iter.next();
            if (min.compareTo(next) > 0)
                min = next;
        }
        JOptionPane.showMessageDialog(null,
                "The smallest polygon is the " + min.getMyType() + " with the area " + min.getMyArea());
    }    
    

} // CLIENT