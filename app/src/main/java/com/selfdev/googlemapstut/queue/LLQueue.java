package com.selfdev.googlemapstut.queue;

import android.util.Log;

/**
 * Created by Rahul on 18/03/18.
 * Ref @ http://pages.cs.wisc.edu/~mcw/cs367/lectures/stacks.html
 * Ref @ https://www.cs.cmu.edu/~adamchik/15-121/lectures/Stacks%20and%20Queues/Stacks%20and%20Queues.html
 * Ref @ https://introcs.cs.princeton.edu/java/43stack/
 */

public class LLQueue {
    public static final String TAG = "LLQueue";

    public class Node<T> {
        public Node<T> nextNode;
        public T data;
    }

    public class Queue<T> {
        Node<T> front, rear;
        int size;

        public void enqueue(T item) {
            if (front==null) {
                front = new Node<>();
                front.data = item;
                rear = front;
            } else {
                rear.nextNode = new Node<>();
                rear.nextNode.data = item;
                rear = rear.nextNode;
            }
            size++;
        }

        public void dequeue() {
            if (front==null) {
                return;
            } else {
                front = front.nextNode;
                if (front==null) {
                    rear = null;
                }
            }
            size--;
        }

        public void peek() {
            if (front==null) {
                return;
            }
            Log.e(TAG, "peek: "+front.data );
        }

        public void print() {
            Node<T> temp = front;
            while (temp!=null) {
                Log.e(TAG, "print: "+temp.data );
                temp = temp.nextNode;
            }

            Log.e(TAG, "print: --------------" );
        }
    }
}
