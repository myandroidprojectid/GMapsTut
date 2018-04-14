package com.selfdev.googlemapstut.threadpool;

import android.util.Log;

/**
 * Created by Rahul on 24/03/18.
 */

public class DownloadRunnable implements Runnable {

    private String TAG = DownloadRunnable.class.getSimpleName();

    @Override
    public void run() {
        Log.e(TAG, "run: " );
    }
}
