/**
 * ====================> ArrayListABCProgram <=====================
 * 
 *    Given an ArrayList letterList of letters "A"-"H"
 *    go through the list and make the following changes:
 *         - When an "A" occurs, add another "A" right after it.
 *         - When a "B" occurs, change it to be a "Z".
 *         - When a "C" occurs, remove it from the list.
 *         - When a "D" occurs, you randomly pick another spot and SWAP it with 
 *           that spot via the methods get( ) and set( ) only.
 *         - When an "E" occurs, you randomly pick another spot and SWAP it with 
 *           that spot via the methods add( ) and remove( ) only.
 *         - When an "F" occurs, you randomly pick a letter "A"-"H" and a random 
 *           spot to put that letter into.
 *         - When a "G" occurs, YOU DECIDE what action is taken.  [Make it good!]
 *           Some ideas: 
 *              "G" becomes a different letter and acts on it.
 *              "G" has you jump to another random spot and either act on it or
 *                  just move on from there.  You choose.
 *              "G" causes the entire ArrayList to scramble except for the spot 
 *                  you're on.   
 *              "G" COME UP WITH YOUR OWN!!
 *         - When an "H" occurs you just ignore it.
 *               
 *    
 *    Ex.   letterList:  A B D A C A E H F H G C B
 *          (1)  The 1st A has another one occur, marked as 'A':
 *               letterList:  A 'A' B D A C A E H F H G C B
 *               NOTE:  Skip over this new/2nd 'A' or .... what do you think will happen??
 *          (2)  The B becomes a 'Z':
 *               letterList: A A 'Z' D C A E H F H G C B
 *          (3)  The D is swapped with another spot, using get() & set() ONLY. In this
 *               case it's with the 2nd 'A' in the list.  Know that it could have swapped with a spot after it.
 *               letterList: A 'D' Z 'A' A C A E H F H G C B         
 *          (4)  The 3rd A has another one occur:
 *               letterList: A D Z A A 'A' C A E H F H G C B       
 *               Note: Skip over this 4th 'A'
 *          (5)  The C is removed:
 *               letterList: A D Z A A A A E H F H G C B  
 *          (6)  The 5th A has another one occur:
 *               letterList: A D Z A A A A 'A' E H F H G C B  
 *          (7)  The E is swapped randomly with another spot, using add() & remove() + get() ONLY.
 *               In this case the 2nd H in the list.                
 *               letterList: A D Z A A A A A'H' H F 'E' G C B
 *          (8)  The next H is ignored:
 *               letterList: A D Z E A A A A A H 'H' F E G C B              
 *          (9)  The F picks randomly picks a letter "A"-"H" and a random spot to put it in.  
 *               In this case it picked a 'C' and happen to put it after the 'G'.
 *               letterList: A D Z E A A A A A H H F E G 'C' C B   
 *          (10) The E is occured again and is swapped randomly with another spot. In this case  
 *                  with the Z near the front of the list.
 *               letterList: A D 'E' E A A A A A H H F 'Z' G C C B  
 *          (11) The G picks another letter "A"-"H" and acts on it. In this case it picked an F,
 *                  then that F was acted on which had it pick a random letter, it picked 'D' and 
 *                  put it in a random spot, which in this case fell after the 2nd A in the list.
 *                letterList: A D E E A A A A A H H F Z 'G' C C B  // We are on G                 
 *                letterList: A D E E A A A A A H H F Z 'F' C C B     // G becomes an F, & now acts on it
 *                letterList: A D E E A 'D' A A A H H F Z 'F' C C B // which caused a D to appear and 
 *                                                                  // be placed after the 2nd A
 *           (12) The 2nd to last C gets removed:
 *                 letterList: A D E E A D A A A H H F Z F C B
 *           (13) The last C also gets removed:
 *                 letterList: A D E E A D A A A H H F Z F B           
 *           (14) The last letter, B, changes to a Z
 *                 letterList: A D E E A D A A A H H F Z F Z         
 *       
 */

package io.github.liambloom.softwareEngineering.chapter10;

import java.util.*;

public class ArrayListABC {
    private static Random r = new Random(); 

    public static void main(String[] args) {
        List<Character> a = new ArrayList<>(Arrays.asList('A', 'B', 'D', 'A', 'C', 'A', 'E', 'H', 'F', 'H', 'G', 'C', 'B'));
        doStuff(a);
        System.out.println(a);
    }

    private static void doStuff(List<Character> a) {
        for (int i = 0; i < a.size(); i++) {
            switch (a.get(i)) {
                case 'A':
                    a.add(i++, 'A');
                    break;
                case 'B':
                    a.set(i, 'Z');
                    break;
                case 'C':
                    a.remove(i--);
                    break;
                case 'D': {
                    final int j = r.nextInt(a.size());
                    a.set(i, a.get(j));
                    a.set(j, 'D');
                    break;
                }
                case 'E': {
                    final int j = r.nextInt(a.size());
                    a.remove(i);
                    a.add(a.remove(j));
                    a.add(j, 'E');
                    break;
                }
                case 'F':
                    a.set(i, (char) (r.nextInt(8) + 65));
                    break;
                case 'G':
                    // G becomes a random letter and acts on it
                    a.set(i--, (char) (r.nextInt(8) + 65));
                    break;
                default:
                    assert a.get(i) == 'H';
            }
        }
    }
}
