package io.github.liambloom.softwareEngineering.chapter16.linkedListUI;

/**
* Mr. Marques' SHELL for myArrayListLL_WrapperClasses.
* >>> Please delete the word "SHELL" below. <<<
* 
* Your "myArrayListLL" duplicates a Java ArrayList via your User Defined LinkedList
* You will complete the code to the following methods we have studied:
*   (1) size() // I did this very difficult one for you.  You're welcome!
            (1) add()          // Add a Node at the end of the list.
            (2) size()         // I did this very difficult one for you.  You're welcome!
            (3) get()          // Get a Node at a particular index.
            (4) set()          // Set a Node at a particular index.
            (5) remove()       // Remove a Node at a particular index.
            (6) addAtIndex()   // Add-a-Node at a particular index.
* 
*/

import javax.swing.JOptionPane;
import io.github.liambloom.softwareEngineering.chapter16.LinkedList;

public class LinkedListClientWrapper extends LinkedList<Object> // delete the word SHELL
{
    /*** Constructor for objects of class myArrayListLL */
    /*public LinkedListClientWrapper() // delete the word SHELL
    {
        //super();
    }*/

    // =================================== helper methods
    // ===================================
    public boolean isBadSelection(int selection) {
        return (selection < 0 || selection > 6);
    }

    // -------------------------------------------------------------------------------------------------------------
    public void printBadSelectionMessage() {
        JOptionPane.showMessageDialog(null, "Input was incorrect. \n  Please choose again.\n", "Bad Input",
                JOptionPane.INFORMATION_MESSAGE);
    }

    // -------------------------------------------------------------------------------------------------------------
    public int menu() {
        int selection = 0;
        String choices = "";

        // print ArrayList choices here, let 0 = quit
        choices += printList("return list") + "\n\n\n" + "Your choices are: \n"
                + " (0) Quit \n (1) Add an Object \n (2) Size: number of Nodes in the list \n"
                + " (3) Get an Object at index value \n (4) Set an Object at index \n"
                + " (5) Remove an Object at index \n (6) Add an Object at index \n"
                + " \n Please enter your selection.";

        selection = Integer.parseInt(JOptionPane.showInputDialog(null, choices));
        return selection;
    }

    // -------------------------------------------------------------------------------------------------------------
    public void doArrayListMethod(int selection) {

        switch (selection) {
            case 0:
                JOptionPane.showMessageDialog(null, "Have a good day!!", "Good Bye", JOptionPane.PLAIN_MESSAGE);
                break;
            case 1:
                add();
                break;
            case 2:
                size();
                break;
            case 3:
                get();
                break;
            case 4:
                set();
                break;
            case 5:
                remove();
                break;
            case 6:
                addAtIndex();
                break;
            default:
                JOptionPane.showMessageDialog(null, "Illegal Entry.  Try again.", "ERROR", JOptionPane.ERROR_MESSAGE);
                break;
        } // switch

    }

    // -------------------------------------------------------------------------------------------------------------
    public int size() {
        printList("The size is " + super.size());
        return super.size();
    }

    // -------------------------------------------------------------------------------------------------------------
    public void get() {
        int selection;

        if (super.size() == 0) {
            JOptionPane.showMessageDialog(null, "Nothing in the list", "Empty list", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        do {
            selection = Integer.parseInt(JOptionPane
                    .showInputDialog("Enter a number " + (super.size() == 1 ? "1 ONLY!!" : "1 - " + super.size())));
            if (selection <= 0 || selection > super.size())
                JOptionPane.showMessageDialog(null, "Enter the proper number, please!!", "ERROR",
                        JOptionPane.INFORMATION_MESSAGE);
        } while (selection <= 0 || selection > super.size());

        JOptionPane.showMessageDialog(null, super.get(selection), "Value at " + selection, JOptionPane.INFORMATION_MESSAGE);
    }

    // -------------------------------------------------------------------------------------------------------------
    public void add() {
        Object n = null;

        n = addObjectMenu("Add");

        super.add(n);

        printList("Add Node to the end");
    } // add

    // -------------------------------------------------------------------------------------------------------------
    public Object addObjectMenu(String caller)
    // Asks the user what they want to add. Returns that object.
    {
        Object n = null;
        String choice = "", value = "";
        int selection;

        // Ask user what they would like to add
        do {
            choice = "(1) Character \n (2) Short \n (3) Integer \n (4) Long \n (5) Float \n (6) Double \n (7) Boolean ";
            selection = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter Object choice: \n " + choice,
                    caller + ": Choose an Object", JOptionPane.INFORMATION_MESSAGE));
            if (selection <= 0 || selection > 7)
                JOptionPane.showMessageDialog(null, "Please enter only within the proper choice boundaries", "ERROR",
                        JOptionPane.INFORMATION_MESSAGE);
        } while (selection <= 0 || selection > 7);

        value = JOptionPane.showInputDialog("Enter the value: ");
        // add the new node to the tail
        switch (selection) {
            case 1:
                n = Character.valueOf(value.charAt(0));
                break;
            case 2:
                n = Short.valueOf(Short.parseShort(value));
                break;
            case 3:
                n = Integer.valueOf(Integer.parseInt(value));
                break;
            case 4:
                n = Long.valueOf(Long.parseLong(value));
                break;
            case 5:
                n = Float.valueOf(Float.parseFloat(value));
                break;
            case 6:
                n = Double.valueOf(Double.parseDouble(value));
                break;
            case 7:
                n = Boolean.valueOf(value);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Error!!", "Error", JOptionPane.INFORMATION_MESSAGE);
                break;
        } // switch

        return n;
    }

    // -------------------------------------------------------------------------------------------------------------
    public void set() {
        Object current, n;
        int selection;

        do {
            selection = Integer.parseInt(JOptionPane
                    .showInputDialog("Enter a number " + (super.size() == 1 ? "1 ONLY!!" : "1 - " + super.size())));
            if (selection <= 0 || selection > super.size())
                JOptionPane.showMessageDialog(null, "Enter the proper number, please!!", "ERROR",
                        JOptionPane.INFORMATION_MESSAGE);
        } while (selection <= 0 || selection > super.size());

        super.set(selection - 1, addObjectMenu("Set"));
        
        // ===================================================================================
        // >>>>>>>>>>>>>>>>>>>>>>>>>>>>> YOUR CODE GOES HERE
        // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        // ===================================================================================

        printList("Set at position " + selection);
    }

    // -------------------------------------------------------------------------------------------------------------
    public void remove() {
        //Node current;
        int selection;

        if (super.size() == 0) {
            JOptionPane.showMessageDialog(null, "Nothing to delete!", "Empty List", JOptionPane.INFORMATION_MESSAGE);
            return;
        } else {
            do {
                selection = Integer.parseInt(JOptionPane
                        .showInputDialog("Enter a number " + (super.size() == 1 ? "1 ONLY!!" : "1 - " + super.size())));
                if (selection <= 0 || selection > super.size())
                    JOptionPane.showMessageDialog(null, "Enter the proper number, please!!", "ERROR",
                            JOptionPane.INFORMATION_MESSAGE);
            } while (selection <= 0 || selection > super.size());
        }

        super.remove(selection - 1);
        // ===================================================================================
        // >>>>>>>>>>>>>>>>>>>>>>>>>>>>> YOUR CODE GOES HERE
        // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        // ===================================================================================

        printList("Remove Node #" + selection);
    }

    // -------------------------------------------------------------------------------------------------------------
    public void addAtIndex() {
        Object n = null;
        String choice = "";
        int selection;

        do {
            choice = (super.size() == 0 ? "You MUST enter a 1 ONLY!!" : "1 - " + (super.size() + 1));
            selection = Integer.parseInt(JOptionPane.showInputDialog("Enter a number: (" + choice + ")"));
            if (selection <= 0 || selection > super.size() + 1)
                JOptionPane.showMessageDialog(null, "Enter the proper number, please!!", "ERROR",
                        JOptionPane.INFORMATION_MESSAGE);
        } while (selection <= 0 || selection > super.size() + 2);

        n = addObjectMenu("Add");

        super.add(selection - 1, n);

        // ===================================================================================
        // >>>>>>>>>>>>>>>>>>>>>>>>>>>>> YOUR CODE GOES HERE
        // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        // ===================================================================================

        printList("Add at index " + selection);

    } // addAtIndex

    // -------------------------------------------------------------------------------------------------------------
    private String printList(String message) // why do you think this is private??
    {
        String output = "The list is: \n\t";

        if (super.size() == 0)
            output += "  nothing in the list!";
        else {
            String str = this.toString();
            output += str.substring(1, str.length() - 1) + ".";
        }

        // Return the list to the menu so the user can see the Nodes.
        if (message.compareTo("return list") == 0)
            return output;

        JOptionPane.showMessageDialog(null, message + "\n\n" + output, "", JOptionPane.INFORMATION_MESSAGE);
        return "";
    }

} // myArrayListLL
