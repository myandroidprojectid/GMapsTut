package com.selfdev.googlemapstut.stack;

import android.util.Log;

/**
 * Created by Rahul on 18/03/18.
 * Ref @ http://pages.cs.wisc.edu/~mcw/cs367/lectures/stacks.html
 * Ref @ https://www.cs.cmu.edu/~adamchik/15-121/lectures/Stacks%20and%20Queues/Stacks%20and%20Queues.html
 * Ref @ https://introcs.cs.princeton.edu/java/43stack/
 */

public class StackDemo {
    private static final String TAG = "StackDemo";
    private int[] intArray;
    private int top = -1;
    private int max = 0;

    public StackDemo(int size) {
        max = size;
        top = -1;
        intArray = new int[max];
    }

    public boolean isFull() {
        return (top == max-1);
    }

    public boolean isEmpty() {
        return (top == -1);
    }

    public void push(int item) {
        if (isFull()) {
            Log.e(TAG, "OverFlow");
            return;
        }

        intArray[++top] = item;

    }

    public void pop() {
        if (isEmpty()) {
            Log.e(TAG, "UnderFlow");
            return;
        }
        top--;
    }

    public void print() {
        for (int i = top; i>-1; i--) {
            Log.e(TAG, "print: "+intArray[i] );
        }
        Log.e(TAG, "print: -----------------");
    }




}
