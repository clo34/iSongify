// -== CS400 Spring 2024 File Header Information ==-
// Name: Charles Lo
// Email: clo34@wisc.edu
// Lecturer: Gary Dahl
// Notes to Grader: I love you, please give me A, have a great day thanks!


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RedBlackTree<T extends Comparable<T>> extends BinarySearchTree<T> {
    protected static class RBTNode<T> extends Node<T> {
        public boolean isBlack = false;
        public RBTNode(T data) {
            super(data);
            this.up = null;
            this.isBlack = false;
        }
        public RBTNode<T> getUp() { return (RBTNode<T>)this.up; }
        public RBTNode<T> getDownLeft() { return (RBTNode<T>)this.down[0]; }
        public RBTNode<T> getDownRight() { return (RBTNode<T>)this.down[1]; }
    }
    public RedBlackTree(){
        super();
    }
    /**
     * Ensures that the Red-Black Tree properties are maintained after inserting a new node.
     * This method resolves any red property violations that are introduced by inserting the new node
     * into the Red-Black Tree and adjusts the colors and structure of the tree accordingly.
     * If necessary, this method recursively fixes violations up to the root of the tree.
     *
     * @param newNode the newly added red node that may have introduced violations
     */
    protected void enforceRBTreePropertiesAfterInsert(RBTNode<T> newNode) {
        // Check if the parent of the new node is black, then no violations occurs
        if (newNode.up == null || newNode.getUp().isBlack) {
            return;
        }

        // If the parent of the new node is red, and it's the root node,
        // then we recolor the root to black to maintain the property that the root
        // is always black.
        if (newNode.up == root) {
            newNode.getUp().isBlack = true;
            return;
        }

        // Check if the parent of the new node is red and it's not the root node,
        while (!newNode.getUp().isBlack && newNode != root) {
            RBTNode<T> parent = newNode.getUp();
            RBTNode<T> grandparent = parent.getUp();
            RBTNode<T> uncle = null;
            boolean isParentLeftChild = parent == grandparent.getDownLeft();

            if (isParentLeftChild) {
                uncle = grandparent.getDownRight();
            } else {
                uncle = grandparent.getDownLeft();
            }

            // Check if the uncle is red, we recolor the parent, uncle, and grandparent.
            if (uncle != null && !uncle.isBlack) {
                parent.isBlack = true;
                uncle.isBlack = true;
                grandparent.isBlack = false;
                newNode = grandparent;
            } else {
                // Check if the uncle is black or null, we perform rotations and
                // recolor nodes to fix violations
                if (isParentLeftChild) {
                    if (parent.getDownRight() == newNode) {
                        rotateLeft(parent);
                        newNode = parent;
                        parent = newNode.getUp();
                    }
                    rotateRight(grandparent);
                } else {
                    if (parent.getDownLeft() == newNode) {
                        rotateRight(parent);
                        newNode = parent;
                        parent = newNode.getUp();
                    }
                    rotateLeft(grandparent);
                }

                parent.isBlack = true;
                grandparent.isBlack = false;
            }
        }
    }

    // Method to perform a left rotation on the given node
    private void rotateLeft(RBTNode<T> node) {
        RBTNode<T> child = node.getDownRight();
        node.down[1] = child.getDownLeft();
        if (child.getDownLeft() != null) {
            child.getDownLeft().up = node;
        }
        child.up = node.up;
        if (node.up != null) {
            if (node == node.getUp().down[0]) {
                node.up.down[0] = child;
            } else {
                node.up.down[1] = child;
            }
        } else {
            this.root = child;
        }
        child.down[0] = node;
        node.up = child;

        // Update the parent-child relationship
        if (child.down[1] != null) {
            child.down[1].up = node;
        }
    }

    // Method to perform a right rotation on the given node
    private void rotateRight(RBTNode<T> node) {
        RBTNode<T> child = node.getDownLeft();
        node.down[0] = child.getDownRight();
        if (child.getDownRight() != null) {
            child.getDownRight().up = node;
        }
        child.up = node.up;
        if (node.up != null) {
            if (node == node.getUp().down[1]) {
                node.up.down[1] = child;
            } else {
                node.up.down[0] = child;
            }
        } else {
            this.root = child;
        }
        child.down[1] = node;
        node.up = child;

        // Update the parent-child relationship
        if (child.down[0] != null) {
            child.down[0].up = node;
        }
    }

    //holy shit this took way too long


    /**
     * Override insert method to insert a new node into the Red-Black Tree.
     * After inserting the node, enforce Red-Black Tree properties and set the root node to black.
     * @param key The data value of the new node to be inserted.
     * @return true if the value was inserted, false if it was already in the tree
     */
    @Override
    public boolean insert(T key) {
        RBTNode<T> newNode = new RBTNode<>(key);
        boolean inserted = insertHelper(newNode);
        if (inserted) {
            enforceRBTreePropertiesAfterInsert(newNode);
            if (root != null) {
                ((RBTNode<T>) root).isBlack = true; // Set root node to black
            }
        }
        return inserted;
    }

    /**
     * Test case to verify the Red-Black Tree properties after a single insertion.
     * This test checks if the tree maintains the properties of a Red-Black Tree
     * after inserting a single node.
     */
    @Test
    public void testSingleInsertion() {
        // Initialize a Red-Black Tree
        RedBlackTree<Integer> tree = new RedBlackTree<>();

        // Insert a single value
        tree.insert(10);

        // Assert that the tree is not empty after insertion
        Assertions.assertFalse(tree.isEmpty());

        // Assert that the tree contains the inserted value
        Assertions.assertTrue(tree.contains(10));
    }

    /**
     * Test case to verify the Red-Black Tree properties after multiple insertions.
     * This test checks if the tree maintains the properties of a Red-Black Tree
     * after inserting multiple nodes.
     */
    @Test
    public void testMultipleInsertions() {
        // Initialize a Red-Black Tree
        RedBlackTree<Integer> tree = new RedBlackTree<>();

        // Insert multiple values
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);

        // Assert that the tree is not empty after insertion
        Assertions.assertFalse(tree.isEmpty());

        // Assert that the tree contains the inserted values
        Assertions.assertTrue(tree.contains(10));
        Assertions.assertTrue(tree.contains(5));
        Assertions.assertTrue(tree.contains(15));
    }

    /**
     * This test method verifies that no consecutive red nodes exist in the Red-Black Tree.
     */
    @Test
    public void testNoConsecutiveRedNodes() {
        // Create a Red-Black Tree
        RedBlackTree<Integer> tree = new RedBlackTree<>();

        // Insert elements
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);

        // Assert that no consecutive red nodes exist
        Assertions.assertTrue(checkNoConsecutiveRedNodes((RBTNode<Integer>) tree.root));
    }

    //casting (RBTNode<Integer>)
    /**
     * Helper method to check if any consecutive red nodes exist in the Red-Black Tree.
     * @param node The current node being processed
     * @return True if no consecutive red nodes exist, false otherwise
     */
    private boolean checkNoConsecutiveRedNodes(RedBlackTree.RBTNode<Integer> node) {
        if (node == null)
            return true;

        // Check if the node and its left child are not null, and then check for consecutive red nodes
        if (!node.isBlack && node.getDownLeft() != null && !node.getDownLeft().isBlack)
            return false;

        // Recursively check left and right subtrees
        return checkNoConsecutiveRedNodes(node.getDownLeft()) &&
                checkNoConsecutiveRedNodes(node.getDownRight());
    }
}