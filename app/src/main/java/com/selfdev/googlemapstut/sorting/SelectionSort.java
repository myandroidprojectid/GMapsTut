package com.selfdev.googlemapstut.sorting;

import android.util.Log;

/**
 * Created by Rahul on 19/03/18.
 */

public class SelectionSort {

    int [] array = {3,6,61,43,12,5,32,67,12,33,54,32};
    int n = 12;

    public void selectionSort() {
        for (int i=0; i< n-1; i++) {
            int min_id = i;
            for (int j = i+1; j < n; j++ ) {
                if (array[j] < array[min_id]) {
                    min_id = j;
                }
            }

            int temp = array[i];
            array[i] = array[min_id];
            array[min_id] = temp;
        }

        for (int i=0; i<n; i++) {
            Log.e("SelectionSort", "selectionSort: "+array[i] );
        }
    }

    public void iterativeSelectionSort() {
        for (int i=0; i<n-1; i++) {
            int min_id = i;
            for (int j= i+1; j<n; j++) {
                if (array[j]<array[min_id]) {
                    min_id = j;
                }
            }
            int temp = array[min_id];
            array[min_id] = array[i];
            array[i] = temp;
        }

        for (int i=0; i<n; i++) {
            Log.e("SelectionSort", "selectionSort: "+array[i] );
        }
    }

    public int[] recursiveSelectionSort() {
        int[] arr = recursiveSelectionSort(array,0,n);
        return arr;
    }

    public int[] recursiveSelectionSort(int[] unsortedArray, int minimumIndexInUnsorted, int unsortedLength) {
        int actualMinimumIndex = minimumIndexInUnsorted;
        for (int i = minimumIndexInUnsorted+1; i <unsortedLength; i++) {
            if (unsortedArray[i] < unsortedArray[actualMinimumIndex]) {
                actualMinimumIndex = i;
            }
        }
        int actualMin = unsortedArray[actualMinimumIndex];
        unsortedArray[actualMinimumIndex] = unsortedArray[minimumIndexInUnsorted];
        unsortedArray[minimumIndexInUnsorted] = actualMin;
        if (minimumIndexInUnsorted+1<unsortedLength) {
            recursiveSelectionSort(unsortedArray,minimumIndexInUnsorted+1,unsortedLength);
        }
        return unsortedArray;
    }
}