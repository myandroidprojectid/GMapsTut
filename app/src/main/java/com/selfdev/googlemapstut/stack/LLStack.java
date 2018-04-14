package com.selfdev.googlemapstut.stack;

import android.util.Log;

/**
 * Created by Rahul on 18/03/18.
 * Ref @ http://pages.cs.wisc.edu/~mcw/cs367/lectures/stacks.html
 * Ref @ https://www.cs.cmu.edu/~adamchik/15-121/lectures/Stacks%20and%20Queues/Stacks%20and%20Queues.html
 * Ref @ https://introcs.cs.princeton.edu/java/43stack/
 */

public class LLStack {
    private static final String TAG = "LLStack";

    public class Node<T> {
        public Node<T> nextNode;
        public T data;
    }

    public class Stack<T> {
        public Node<T> head;
        public int size;

        public void push(T item) {
            if (head == null) {
                head = new Node<>();
                head.data = item;
            } else {
                Node<T> newNode = new Node<>();
                newNode.data = item;
                newNode.nextNode = head;
                head = newNode;
            }
            size++;
        }

        public void pop() {
            if (head==null) {
                return;
            } else {
                head = head.nextNode;
            }
            size--;
        }


        public void print() {
            Node<T> temp = head;
            while (temp!=null) {
                Log.e(TAG, "print: "+temp.data );
                temp = temp.nextNode;
            }
            Log.e(TAG, "print: ---------------" );
        }
    }
}
