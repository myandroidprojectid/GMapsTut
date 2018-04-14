package com.selfdev.googlemapstut.queue;

import android.util.Log;

/**
 * Created by Rahul on 18/03/18.
 * Ref @ http://pages.cs.wisc.edu/~mcw/cs367/lectures/stacks.html
 * Ref @ https://www.cs.cmu.edu/~adamchik/15-121/lectures/Stacks%20and%20Queues/Stacks%20and%20Queues.html
 * Ref @ https://introcs.cs.princeton.edu/java/43stack/
 */

public class MyQueue {
    private static String TAG = "MyQueue";
    private int front = -1, rear = -1 , currentSize = 0, totalSize = 0;
    private int intArray[];

    public MyQueue(int size) {
        this.front = 0;
        this.rear = -1;
        this.currentSize = 0;
        this.totalSize = size;
        this.intArray = new int[totalSize];
    }

    // Queue is full when size becomes equal to
    // the capacity
    boolean isFull(MyQueue queue)
    {  return (queue.currentSize == queue.totalSize);
    }

    // Queue is empty when size is 0
    boolean isEmpty(MyQueue queue)
    {  return (queue.currentSize == 0); }


    public void peek() {
        if (front < 0) {
            Log.e(TAG, "peek: THERE IS NO ELEMENT");
            return;
        }

        Log.e(TAG, "peek: "+intArray[front] );
    }

    public void enqueueElement(int item) {
        if (isFull(this)) {
            Log.e(TAG, "enqueueElement: FULL" );
            return;
        }
        this.rear = (rear + 1) % totalSize;
        this.intArray[rear] = item;
        this.currentSize = this.currentSize + 1;
        Log.e(TAG, "enqueueElement: rear = "+rear+ " | front = "+front );
        Log.e(TAG, "enqueueElement: ADDED = "+item );
    }

    public int dequeueElement() {
        if (isEmpty(this)) {
            Log.e(TAG, "dequeueElement: EMPTY" );
            return Integer.MIN_VALUE;
        }

        int item = this.intArray[this.front];
        this.front = (this.front + 1)%this.totalSize;
        this.currentSize = this.currentSize - 1;
        Log.e(TAG, "dequeueElement: rear = "+rear+ " | front = "+front );
        Log.e(TAG, "dequeueElement: REMOVED = "+item );
        return item;
    }

    public void print() {
        Log.e(TAG, "print: ----------------" );
        int temp = front -1;
        do {
            temp = (temp+1)%totalSize;
            Log.e(TAG, "print: "+intArray[temp] );
        } while (temp!=rear);
        Log.e(TAG, "print: ----------------" );
    }













    public void enqueueElements(int item) {
        if (isFull(this)) {
            Log.e(TAG, "enqueueElements: FULL" );
            return;
        }

        rear = (rear+1)%totalSize;
        intArray[rear] = item;
        currentSize++;
    }

    public int dequeueElements() {
        if (isEmpty(this)) {
            Log.e(TAG, "dequeuElements: EMPTY" );
            return Integer.MIN_VALUE;
        }

        int item = intArray[front];
        front = (front+1)%totalSize;
        currentSize--;
        return item;
    }













}
