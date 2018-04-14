package com.selfdev.googlemapstut.queue;

import android.util.Log;

import java.util.IllegalFormatCodePointException;

/**
 * Created by Rahul on 18/03/18.
 * Ref @ http://pages.cs.wisc.edu/~mcw/cs367/lectures/stacks.html
 * Ref @ https://www.cs.cmu.edu/~adamchik/15-121/lectures/Stacks%20and%20Queues/Stacks%20and%20Queues.html
 * Ref @ https://introcs.cs.princeton.edu/java/43stack/
 */

public class QueueDemo {
    private static final String TAG = "QueueDemo";
    private int totalSize = 0, currentSize = 0, front = 0, rear = -1;
    private int[] intArray;

    public QueueDemo(int size) {
        totalSize = size;
        front = 0;
        rear = -1;
        currentSize = 0;
        intArray = new int[totalSize];
    }

    public boolean isFull() {
        return (currentSize == totalSize);
    }

    public boolean isEmpty() {
        return (currentSize == 0);
    }

    public void enqueue(int item) {
        if (isFull()) {
            Log.e(TAG, "queue: QUEUE is Full");
            return;
        }

        rear = (rear+1)%totalSize;
        intArray[rear] = item;
        currentSize++;
        Log.e(TAG, "queue: ITEM ADDED = "+item );
    }

    public void dequeue() {
        if (isEmpty()) {
            Log.e(TAG, "enqueue: EMPTY");
            return;
        }

        int item = intArray[front];
        front = (front+1)%totalSize;
        currentSize--;
        Log.e(TAG, "enqueue: Item removed = "+item );
    }

    public void peek() {
        if (isEmpty()) {
            Log.e(TAG, "peek: Empty" );
            return;
        }
        Log.e(TAG, "peek: FRONT ITEM = "+intArray[front]);
    }

    public void print() {
        int temp = front -1;
        do {
            temp = (temp+1)%totalSize;
            Log.e(TAG, "print: "+intArray[temp] );
        } while (temp!=rear);
    }


}
