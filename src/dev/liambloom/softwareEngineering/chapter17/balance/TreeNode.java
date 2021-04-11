package dev.liambloom.softwareEngineering.chapter17.balance;

class TreeNode<T> {
    TreeNode<T> left, right;
    T data;

    public TreeNode(final T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
