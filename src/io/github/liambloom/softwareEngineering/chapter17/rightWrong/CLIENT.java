package io.github.liambloom.softwareEngineering.chapter17.rightWrong;

/**
 *  * Mr. Marques 
 * 
 *  Tree_Building_RIGHT_WRONG_CLIENT: This program will build a tree w/ the values in      
 *  'nums'.   When completed, the Tree will look like:
 *  
 *           8
            / \
          /     \
        /         \
       4           12
      / \          / \
     /   \        /   \
    2     6      10   14
   / \   / \    / \   / \
  1   3 5   7  9  11 13 15 

 *  
 *  
 *  OutPut:
 *   Print tree 'InOrder': 
 *   1 , 2 , 3 , 4 , 5 , 6 , 7 , 8 , 9 , 10 , 11 , 12 , 13 , 14 , 15 , 
 *   Print tree 'PreOrder':
 *   8 , 4 , 2 , 1 , 3 , 6 , 5 , 7 , 12 , 10 , 9 , 11 , 14 , 13 , 15 , 
 *   Print tree 'PostOrder':
 *   1 , 3 , 2 , 5 , 7 , 6 , 4 , 9 , 11 , 10 , 13 , 15 , 14 , 12 , 8 , 
 *  
 * 
*/
import java.util.*;

public class CLIENT
{

    public static void main()
    {
    ArrayList nums = new ArrayList(); 
    TreeNode root=null; 
    int i;
   
        // Put the numbers into the Arraylist 'nums'
        getNumbers(nums);
        
        // RIGHT!!!
        // You MUST set the root outside of the method or it will be NULL.
        root = new TreeNode(nums.get(0),null,null);  // Set root w/ 1st value.

        //--------------------WRONG!!!-----------------
        // makeTreeTest(root,nums);  // WRONG!!!
        
        for(i=1; i<nums.size(); i++)  // Make the rest of the tree w/ 1 value at a time.
            placeNodeInTree(root,new TreeNode(nums.get(i),null,null));        
            
        // ----------------------------  Printing the tree ----------------------------             
        // NOTE: there are 3 ways to print a tree.  PreOrder, InOrder, and PostOrder. 
        //       Trace thru each and understand for the AP test.
        //
        // -- InOrder --    // This IS the one we use and makes the most sense!!!
        System.out.println(" Print tree 'InOrder':    -- This IS the one we regularly use! -- ");
        printTreeInOrder(root);
        System.out.println("\n\n");
        
        // -- PreOrder --     
        System.out.println(" Print tree 'PreOrder':");
        printTreePreOrder(root);           
        System.out.println("\n\n");
        
        // -- PostOrder --     
        System.out.println(" Print tree 'PostOrder':");
        printTreePostOrder(root);           
        System.out.println("\n\n");        
        
    } // main

// --------------------WRONG!!!-----------------
// This method will NOT work because root2 from here will not stay in memory when we 
//  return back to main().  Thus, the whole tree is lost.  :(
public static void makeTreeTest(TreeNode root2, ArrayList nums)
{
int i;
        for(i=0; i<nums.size(); i++)  // Make the tree STARTING at 0.  WRONG!!!
            placeNodeInTree(root2,new TreeNode(nums.get(i),null,null));  
}    
// --------------------WRONG!!!-----------------
    
    // -----------------------------------------------------------------------------
    public static void getNumbers(ArrayList nums)
    {   
        nums.add(new Integer(8));      nums.add(new Integer(4)); 
        nums.add(new Integer(12));     nums.add(new Integer(2)); 
        nums.add(new Integer(6));      nums.add(new Integer(10));  
        nums.add(new Integer(14));     nums.add(new Integer(1));  
        nums.add(new Integer(3));      nums.add(new Integer(5));   
        nums.add(new Integer(7));      nums.add(new Integer(9));   
        nums.add(new Integer(11));     nums.add(new Integer(13)); 
        nums.add(new Integer(15));
    }

   // -----------------------------------------------------------------------------
   public static void placeNodeInTree(TreeNode t, TreeNode n)
   {
// ---- used for makeTreeTest() ----
// if (t==null)       
//    t = n;
    
       if ( ((Integer)n.getValue()).intValue() < ((Integer)t.getValue()).intValue()) {
            if (t.getLeft()==null)
                t.setLeft(n);
            else
                placeNodeInTree(t.getLeft(),n);                
       }else  {
            if (t.getRight()==null)
                t.setRight(n);
            else
                placeNodeInTree(t.getRight(),n);  
       }
       
    } // buildTree

    // --------------------------------------------------------------------------------
    // "Traversal": Exhaust left side, then print node, then exhaust right side
    // output : 1 , 2 , 3 , 4 , 5 , 6 , 7 , 8 , 9 , 10 , 11 , 12 , 13, 14 , 15 , 
    public static void printTreeInOrder(TreeNode t)
    {
        if (t != null) {
            printTreeInOrder(t.getLeft());
            System.out.print(t.getValue() + " , ");
            printTreeInOrder(t.getRight());
        }        
    }
    
    // --------------------------------------------------------------------------------
    // "Traversal": Print the node as each occurs.
    // output : 8 , 4 , 2 , 1 , 3 , 6 , 5 , 7 , 12 , 10 , 9 , 11 , 14, 13 , 15 , 
    public static void printTreePreOrder(TreeNode t)
    {
        if (t != null) {
            System.out.print(t.getValue() + " , ");
            printTreePreOrder(t.getLeft());
            printTreePreOrder(t.getRight());
        }        
    }
    
    // --------------------------------------------------------------------------------
    // "Traversal": Exhaust left then right side printing the 'root' of each subtree last.
    // output : 1 , 3 , 2 , 5 , 7 , 6 , 4 , 9 , 11 , 10 , 15 , 14 , 13, 12 , 8 , 
    public static void printTreePostOrder(TreeNode t)
    {
        if (t != null) {
            printTreePostOrder(t.getLeft());
            printTreePostOrder(t.getRight());
            System.out.print(t.getValue() + " , ");            
        }        
    }    
    
} // Tree_Building_CLIENT
