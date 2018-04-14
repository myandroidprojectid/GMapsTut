package com.selfdev.googlemapstut.services;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Rahul on 23/03/18.
 */

public class UnboundService extends Service {

    private String TAG = "UnboundService";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "onBind: ++++++++++++++++++++" );
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand: DO SOMETHING" );
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.e(TAG, "run: TN = "+Thread.currentThread().getName() );
            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean stopService(Intent name) {
        Log.e(TAG, "stopService: " );
        return super.stopService(name);
    }
}
