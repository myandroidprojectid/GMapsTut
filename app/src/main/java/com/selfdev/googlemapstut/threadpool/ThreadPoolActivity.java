package com.selfdev.googlemapstut.threadpool;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.gms.maps.SupportMapFragment;
import com.selfdev.googlemapstut.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Rahul on 24/03/18.
 */

//     android threadpoolexecutor example
//    https://ojiofong.github.io/tech/2016/08/29/android-thread-pool/
//    https://www.journaldev.com/1069/threadpoolexecutor-java-thread-pool-example-executorservice
//    http://codetheory.in/android-java-executor-framework/


public class ThreadPoolActivity extends FragmentActivity {
    private String TAG = "ThreadPoolActivity";

    /*
     * Gets the number of available cores
     * (not always the same as the maximum number of cores)
     */
    private static int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();

    // Sets the amount of time an idle thread waits before terminating
    private static final int KEEP_ALIVE_TIME = 1000;

    // Sets the Time Unit to Milliseconds
    private static final TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.MILLISECONDS;

    // Used to update UI with work progress
    private int count = 0;

    private LinearLayout images_ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_container_layout);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        Log.e(TAG, "onCreate: +++++++++" );
        images_ll = (LinearLayout) findViewById(R.id.images_ll);
        buttonClickThreadPool();
    }

    Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            // Do some work that takes 50 milliseconds
            count++;
            try {
                Log.e(TAG, "run: =========> http://placehold.it/120x120&text=image"+count );
                final Bitmap bitmap = getBitmapFromURL("http://placehold.it/120x120&text=image"+count);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        ImageView images = new ImageView(ThreadPoolActivity.this);
                        images.setImageBitmap(bitmap);
                        images_ll.addView(images);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.e(TAG, "handleMessage: ========" );
        }
    };

    public void buttonClickThreadPool() {
        Log.e(TAG, "buttonClickThreadPool: "+NUMBER_OF_CORES );
        count = 0;
        ThreadPoolExecutor mThreadPoolExecutor = new ThreadPoolExecutor(
                NUMBER_OF_CORES + 5,   // Initial pool size
                NUMBER_OF_CORES + 8,   // Max pool size
                KEEP_ALIVE_TIME,       // Time idle thread waits before terminating
                KEEP_ALIVE_TIME_UNIT,  // Sets the Time Unit for KEEP_ALIVE_TIME
                new LinkedBlockingQueue<Runnable>());  // Work Queue

        for (int i = 0; i < 100; i++) {
            mThreadPoolExecutor.execute(mRunnable);
        }
    }

    public Bitmap getBitmapFromURL(String src) {
        try {
            java.net.URL url = new java.net.URL(src);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
