package com.selfdev.googlemapstut.linkedlist;

import android.support.annotation.NonNull;
import android.util.Log;

/**
 * Created by Rahul on 17/03/18.
 * Ref @ https://gist.github.com/es20641/1208340/06d598126d53b048058bc243cbc4e4dd7db9a23a
 * Ref @ https://www.studytonight.com/data-structures/circular-linked-list
 */

public class SinglyCircularLinkedList {

    private static final String TAG = "SinglyCircularLnkdLst";

    public class Node<T> implements Comparable<T>{
        private Node<T> nextNode;
        private T data;

        public Node<T> getNextNode() {
            return nextNode;
        }

        public void setNextNode(Node<T> nextNode) {
            this.nextNode = nextNode;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        @Override
        public int compareTo(@NonNull T t) {
            if (this.data == t) {
                return 1;
            }
            return 0;
        }
    }

    public class CircularLinkedList<T> {
        private Node<T> head = null;
        private int listSize = 0;

        public void addNewNode(T data) {
            if (head==null) {
                head = new Node<>();
                head.setData(data);
                head.setNextNode(head);
            } else {
                Node<T> temp = head;
                while (temp.getNextNode()!=head) {
                    temp = temp.nextNode;
                }
                temp.nextNode = new Node<>();
                temp.nextNode.setData(data);
                temp.nextNode.setNextNode(head);
            }
            listSize++;
        }

        public void addNodeAtHead(T data) {
            if (head == null) {
                head = new Node<>();
                head.setData(data);
                head.setNextNode(head);
            } else {
                Node<T> temp = head;
                 while (temp.getNextNode()!= head) {
                     temp = temp.getNextNode();
                 }
                 Node<T> newNode = new Node<>();
                 newNode.setData(data);
                 temp.nextNode = newNode;
                 newNode.nextNode = head;
                 head = newNode;
            }
            listSize++;
        }

        public void addNodeAtTail(T data) {
            if (head == null) {
                head = new Node<>();
                head.setData(data);
                head.setNextNode(head);
            } else {
                Node<T> temp = head;
                 while (temp.getNextNode()!= head) {
                     temp = temp.getNextNode();
                 }
                 temp.nextNode = new Node<>();
                 temp.nextNode.setData(data);
                 temp.nextNode.setNextNode(head);
            }
            listSize++;
        }

        public void addNodeAt(int index, T data) {
            Node<T> previousNode = head;
            Node<T> currentNode = head;
            for (int i=0; i<index && currentNode.getNextNode()!=head; i++) {
                previousNode = currentNode;
                currentNode = currentNode.getNextNode();
            }

            Log.e(TAG, "getNodeAt: "+index+" = "+currentNode.getData() );

            if (head == null) {
                head = new Node<>();
                head.setData(data);
                head.setNextNode(head);
            } else {
                Node<T> temp = head;
                 while (temp.getNextNode()!= head) {
                     temp = temp.getNextNode();
                 }
                 temp.nextNode = new Node<>();
                 temp.nextNode.setData(data);
                 temp.nextNode.setNextNode(head);
            }
            listSize++;
        }

        public T getNodeAt(int index) {
            Node<T> temp = head;
            for (int i=0; i<index && temp.getNextNode()!=head; i++) {
                temp = temp.getNextNode();
            }
            Log.e(TAG, "getNodeAt: "+index+" = "+temp.getData() );
            return temp.getData();
        }

        public Node<T> getNode(int index) {
            Node<T> temp = head;
            for (int i=0; i<index && temp.getNextNode()!=head; i++) {
                temp = temp.getNextNode();
            }
            Log.e(TAG, "getNodeAt: "+index+" = "+temp.getData() );
            return temp;
        }

        public void traverse(Node<T> node) {
            Node<T> temp = node;
            do {
                Log.e(TAG, "traverse: "+temp.getData() );
                temp = temp.getNextNode();
            } while (temp.getNextNode() != node.getNextNode());
        }

        public void printNodes() {
            if (head!=null) {
                Node<T> temp = head;
                do {
                    Log.e(TAG, "printNodes: "+temp.getData() );
                    temp = temp.getNextNode();
                } while (temp.getNextNode() != head.getNextNode());

                /*for (int i=0; i < listSize; i++) {
                    Log.e(TAG, "printNodes: "+temp.getData() );
                    temp = temp.getNextNode();
                }*/

                /*while (temp.getNextNode() != head ) {
                    Log.e(TAG, "printNodes: "+temp.getData() );
                    temp = temp.getNextNode();
                }*/
            }
            Log.e(TAG, "printNodes: ===============================" );
        }
    }
}
