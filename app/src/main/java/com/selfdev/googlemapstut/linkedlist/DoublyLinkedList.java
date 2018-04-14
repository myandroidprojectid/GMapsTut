package com.selfdev.googlemapstut.linkedlist;

import android.support.annotation.NonNull;
import android.util.Log;

import java.net.PortUnreachableException;
import java.util.Date;

/**
 * Created by Rahul on 17/03/18.
 * Ref @ https://gist.github.com/es20641/1208340/06d598126d53b048058bc243cbc4e4dd7db9a23a
 */

public class DoublyLinkedList {
    private static final String TAG = "DoublyLinkedList";

    public class Node<T> implements Comparable<T>{
        private Node<T> previousNode;
        private Node<T> nextNode;
        private T data;

        public Node<T> getPreviousNode() {
            return previousNode;
        }

        public void setPreviousNode(Node<T> previousNode) {
            this.previousNode = previousNode;
        }

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
                return 0;
            }
            return -1;
        }
    }

    public class DoublyList<T> {
        private Node<T> head;
        private int listSize;

        public void addNewNode(T data) {
            if (head == null) {
                head = new Node<>();
                head.setData(data);
            } else {
                Node<T> temp = head;
                while (temp.nextNode!=null) {
                    temp = temp.nextNode;
                }

                temp.nextNode = new Node<>();
                temp.nextNode.setData(data);
                temp.nextNode.previousNode = temp;
            }
            listSize++;
        }

        public void addNewNodeAtHead(T data) {
            if (head==null) {
                head = new Node<>();
                head.setData(data);
            } else {
                Node<T> newNode = new Node<>();
                newNode.setData(data);
                newNode.setNextNode(head);
                head = newNode;
            }
            listSize++;
        }

        public void addNewNodeAtTail(T data) {
            if (head==null) {
                head = new Node<>();
                head.setData(data);
            } else {
                Node<T> temp = head;
                while (temp.getNextNode() != null) {
                    temp = temp.getNextNode();
                }

                temp.setNextNode(new Node<T>());
                temp.getNextNode().setData(data);
                temp.getNextNode().setPreviousNode(temp);
            }
            listSize++;
        }

        public void addNewNodeAt(int index, T data) {
            if (head==null) {
                head = new Node<>();
                head.setData(data);
            } else {
                Node<T> temp = head;
                Node<T> previousNode = null;
                for (int i=0; i<index && temp.getNextNode()!=null; i++) {
                    previousNode = temp;
                    temp = temp.getNextNode();
                }

                Node<T> newNode = new Node<>();
                newNode.setData(data);

                if (previousNode!=null) {
                    previousNode.setNextNode(newNode);
                    newNode.setNextNode(temp);
                } else {
                    newNode.setNextNode(temp);
                    head = newNode;
                }

            }
            listSize++;
        }

        public void deleteNodeAt(int index) {
            if (head==null) {
                return;
            } else {
                Node<T> temp = head;
                Node<T> previousNode = null;
                for (int i=0; i<index && temp.getNextNode()!=null; i++) {
                    previousNode = temp;
                    temp = temp.getNextNode();
                }

                if (previousNode!=null) {
                    previousNode.setNextNode(temp.getNextNode());
                } else {
                    head = temp.getNextNode();
                }
            }
            listSize--;
        }

        public void getNodeAt(int index) {
            Node<T> temp = head;
            for (int i=0; i<index && temp.getNextNode()!=null; i++) {
                temp = temp.getNextNode();
            }
            Log.e(TAG, "getNodeAt: p : "+index+" = "+temp.getData() );
        }

        public void print() {
            Node temp = head;
            while (temp != null) {
                Log.e(TAG, "print: "+temp.getData() );
                temp = temp.getNextNode();
            }
            Log.e(TAG, "print: ----------------------------" );
        }
    }
}
