// When I decided to try this in rust, I forgot one important thing:
// Trees are notoriously hard in rust. This doesn't work.

use std::fmt;

fn main() {
    let mut tree = BinarySearchTree::new();
    tree.add_all([50,40,60,30,70,105,20,80,35,5,10,90,100,110,65].into_iter());
}

#[derive(Clone, Debug)]
pub struct BinarySearchTree<E: Ord> {
    root: Option<TreeNode<E>>,
}

impl<E: Ord> BinarySearchTree<E> {
    pub fn new() -> Self {
        Self {
            root: None,
        }
    }

    pub fn add(&mut self, e: E) {
        BinarySearchTree::add_inner(&mut self.root, e);
    }

    fn add_inner(node: &mut Option<TreeNode<E>>, e: E) {
        match node {
            Some(node) => {
                if node.data <= e {
                    BinarySearchTree::add_inner(&mut *node.right, e);
                }
                else {
                    BinarySearchTree::add_inner(&mut *node.left, e);
                }
            }
            None => *node = Some(TreeNode::new(e))
        }
    }

    // No varargs in rust :(
    pub fn add_all(&mut self, es: impl IntoIterator<Item = E>) {
        for e in es {
            self.add(e);
        }
    }

    pub fn balance(&mut self) {
        let mut nodes = Vec::new();
        BinarySearchTree::into_list(&mut nodes, &self.root);
        self.root = BinarySearchTree::add_balanced(&nodes, 0, nodes.len());
    }

    fn into_list<'a, 'b: 'a>(list: &'a mut Vec<&TreeNode<E>>, node: &'b Option<TreeNode<E>>) {
        if let Some(node) = node {
            BinarySearchTree::into_list(list, &*node.left);
            list.push(node);
            BinarySearchTree::into_list(list, &*node.right);
        }
    }

    fn add_balanced(list: &Vec<&TreeNode<E>>, low: usize, high: usize) -> Option<TreeNode<E>> {
        if low >= high {
            return None;
        }
        let middle = (low + high) / 2;
        let root = list[middle];
        root.left = Box::new(BinarySearchTree::add_balanced(list, low, middle));
        root.right = Box::new(BinarySearchTree::add_balanced(list, middle + 1, high));
        Some(root)
    }
}

#[derive(Clone, Debug)]
struct TreeNode<T: Ord> {
    data: T,
    // Less efficient than Option<Box<T>>, but make function arguments easier
    left: Box<Option<TreeNode<T>>>,
    right: Box<Option<TreeNode<T>>>,
}

impl<T: Ord> TreeNode<T> {
    pub fn new(data: T) -> Self {
        Self {
            data,
            left: Box::new(None),
            right: Box::new(None)
        }
    }
}

impl<T: Ord + fmt::Display> fmt::Display for TreeNode<T> {
    fn fmt(&self, f: &mut fmt::Formatter<'_>) -> fmt::Result {
        write!(f, "{}", self.data)
    }
}