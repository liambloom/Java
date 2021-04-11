package dev.liambloom.softwareEngineering.chapter17.rightWrong;

public class TreeNode
{
private Object value;
private TreeNode left, right;

    /*** Constructor for objects of class TreeNode */
    public TreeNode()
    {
        value=null;
        left=right=null;      
    }
    public TreeNode(Object initValue, TreeNode initLeft, TreeNode initRight)
    {
        value=initValue;
        left=initLeft;
        right=initRight;
    }

    public Object getValue(){ return value;}
    public TreeNode getLeft() { return left;}
    public TreeNode getRight() { return right;}
    public void setValue(Object theNewValue) {value = theNewValue;}
    public void setLeft(TreeNode theNewLeft) { left = theNewLeft;}
    public void setRight(TreeNode theNewRight) { right = theNewRight;}  
}
