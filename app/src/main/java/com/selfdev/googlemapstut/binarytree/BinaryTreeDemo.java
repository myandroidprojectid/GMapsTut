package com.selfdev.googlemapstut.binarytree;

import android.util.Log;

/**
 * Created by Rahul on 18/03/18.
 * Ref @ http://homepage.divms.uiowa.edu/~sriram/21/spring07/code/tree.java
 * Ref @ https://www.cs.cmu.edu/~adamchik/15-121/lectures/Trees/trees.html
 */

public class BinaryTreeDemo {
    private static final String TAG = "BinaryTreeDemo";

    public class Node {
        public Node leftChild;
        public Node rightChild;
        public int data;
    }

    public class BinarySearchTree {
        Node root;

        // -------------------------------------------------------------

        public void insert(int newData) {
            if (root == null) {
                root = new Node();
                root.data = newData;
            } else {
                Node currentNode = root;
                Node temp;

                while (true) {
                    temp = currentNode;
                    if (newData == currentNode.data) {
                        return;
                    } else if (newData < currentNode.data) {
                        currentNode = currentNode.leftChild;
                        if (currentNode == null) {
                            currentNode = new Node();
                            currentNode.data = newData;
                            temp.leftChild = currentNode;
                            return;
                        }
                    } else {
                        currentNode = currentNode.rightChild;
                        if (currentNode == null) {
                            currentNode = new Node();
                            currentNode.data = newData;
                            temp.rightChild = currentNode;
                            return;
                        }
                    }
                }
            }
        }

        // -------------------------------------------------------------

        public void deleteKey(int key) {
            Node currentNode = root;
            Node temp = root;
            boolean isLeftChild = false;
            // first find the node to delete
            while (currentNode.data!=key) {
                temp = currentNode;
                if (key < currentNode.data) {
                    currentNode = currentNode.leftChild;
                    isLeftChild = true;
                } else {
                    currentNode = currentNode.rightChild;
                    isLeftChild = false;
                }
                if (currentNode==null) {
                    return;
                }
            } // at this point we have found the node to delete

            // if the node to delete doesn't have a child just delete it
            // check if it has child
            if (currentNode.leftChild == null && currentNode.rightChild == null) {
                // node desn't have any child
                if (currentNode == root) {
                    root = null;
                } else if (isLeftChild) {
                    temp.leftChild = null;
                } else {
                    temp.rightChild = null;
                }
            } else if (currentNode.rightChild == null) { // if the node has no right child then replace with left child
                if (currentNode == root) {
                    root = currentNode.leftChild;
                } else if (isLeftChild) {
                    temp.leftChild = currentNode.leftChild;
                } else {
                    temp.rightChild = currentNode.leftChild;
                }
            } else if (currentNode.leftChild == null) { // if the node has no left child then replace with right child
                if (currentNode == root) {
                    root = currentNode.rightChild;
                } else if (isLeftChild) {
                    temp.leftChild = currentNode.rightChild;
                } else {
                    temp.rightChild = currentNode.rightChild;
                }
            } else {
                if (currentNode == root) {
                    // TODO: 19/03/18
                    currentNode.leftChild.rightChild = currentNode.rightChild;
                    root = currentNode.leftChild;
                } else if (isLeftChild) {
                    currentNode.leftChild.rightChild = currentNode.rightChild;
                    temp.leftChild = currentNode.leftChild;
                } else {
                    currentNode.rightChild.leftChild = currentNode.leftChild;
                    temp.rightChild = currentNode.rightChild;
                }
            }


        }
        public boolean delete(int key) // delete node with given key
        {                           // (assumes non-empty list)
            Node current = root;
            Node parent = root;
            boolean isLeftChild = true;

            while(current.data != key)        // search for node
            {
                parent = current;
                if(key < current.data)         // go left?
                {
                    isLeftChild = true;
                    current = current.leftChild;
                }
                else                            // or go right?
                {
                    isLeftChild = false;
                    current = current.rightChild;
                }
                if(current == null)             // end of the line,
                    return false;                // didn't find it
            }  // end while
            // found node to delete

            // if no children, simply delete it
            if(current.leftChild==null &&
                    current.rightChild==null)
            {
                if(current == root)             // if root,
                    root = null;                 // tree is empty
                else if(isLeftChild)
                    parent.leftChild = null;     // disconnect
                else                            // from parent
                    parent.rightChild = null;
            }

            // if no right child, replace with left subtree
            else if(current.rightChild==null)
                if(current == root)
                    root = current.leftChild;
                else if(isLeftChild)
                    parent.leftChild = current.leftChild;
                else
                    parent.rightChild = current.leftChild;

                // if no left child, replace with right subtree
            else if(current.leftChild==null)
                if(current == root)
                    root = current.rightChild;
                else if(isLeftChild)
                    parent.leftChild = current.rightChild;
                else
                    parent.rightChild = current.rightChild;

            else  // two children, so replace with inorder successor
            {
                // get successor of node to delete (current)
                Node successor = getSuccessor(current);

                // connect parent of current to successor instead
                if(current == root)
                    root = successor;
                else if(isLeftChild)
                    parent.leftChild = successor;
                else
                    parent.rightChild = successor;

                // connect successor to current's left child
                successor.leftChild = current.leftChild;
            }  // end else two children
            // (successor cannot have a left child)
            return true;                                // success
        }  // end delete()

        // -------------------------------------------------------------
        // returns node with next-highest value after delNode
        // goes to right child, then right child's left descendents
        public Node getSuccessor(Node currentNode)
        {
            Node successorParent = currentNode;
            Node successor = currentNode;
//            Node current = currentNode.rightChild;   // go to right child
            while(currentNode.rightChild != null)               // until no more
            {                                 // left children,
                successorParent = successor;
                successor = currentNode.rightChild;
                currentNode.rightChild = currentNode.rightChild.leftChild;      // go to left child
            }
            // if successor not
            if(successor != currentNode.rightChild)  // right child,
            {                                 // make connections
                successorParent.leftChild = successor.rightChild;
                successor.rightChild = currentNode.rightChild;
            }
            return successor;
        }

        // -------------------------------------------------------------
        public Node find (int key) {
            Node currentNode = root;
            if (currentNode==null) {
                return null;
            }
            while (currentNode.data != key) {
                if (key < currentNode.data) {
                    currentNode = currentNode.leftChild;
                } else {
                    currentNode = currentNode.rightChild;
                }
                if (currentNode==null) {
                    return null;
                }
            }
            return currentNode;
        }
        // -------------------------------------------------------------

        // This method mainly calls InorderRec()
        public void inorder()  {
            inorderRec(root);
        }

        // A utility function to do inorder traversal of BST
        void inorderRec(Node root) {
            if (root != null) {
                inorderRec(root.leftChild);
                Log.e(TAG, "inorderRec: "+root.data );
                inorderRec(root.rightChild);
            }
        }

        public void preOrder() {
            preOrder(root);
        }

        public void preOrder(Node node) {
            if (node != null) {
                Log.e(TAG, "preOrder: "+node.data );
                preOrder(node.leftChild);
                preOrder(node.rightChild);
            }
        }

        public void postOrder() {
            postOrder(root);
        }

        public void postOrder(Node node) {
            if (node != null) {
                postOrder(node.leftChild);
                postOrder(node.rightChild);
                Log.e(TAG, "postOrder: "+node.data );
            }
        }
    }
}
