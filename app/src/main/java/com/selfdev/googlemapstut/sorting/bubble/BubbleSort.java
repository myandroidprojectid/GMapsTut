package com.selfdev.googlemapstut.sorting.bubble;

import android.util.Log;

/**
 * Created by Rahul on 20/03/18.
 */

public class BubbleSort {
    private static final String TAG = "BubbleSort";
    int [] array = {3,6,61,43,12,5,32,67,12,33,54,32};
    int n = 12;

    public void bubbleSort() {
        for (int i=0; i<n-1; i++) {
            Log.e(TAG, "bubbleSort: i = "+i );
            Log.e(TAG, "bubbleSort: n-i-1 = "+(n-i-1) );
            Log.e(TAG, "bubbleSort: ------------------------" );
            for (int j=0; j<n-i-1; j++) {
                Log.e(TAG, "bubbleSort: j = "+j );
                if (array[j]>=array[j+1]) {
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
            Log.e(TAG, "bubbleSort: ______________________________________________________" );
        }

        for (int i=0; i<n; i++) {
            Log.e(TAG, "bubbleSort: "+array[i] );
        }
    }
}