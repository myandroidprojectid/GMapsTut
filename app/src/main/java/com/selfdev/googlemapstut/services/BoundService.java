package com.selfdev.googlemapstut.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Rahul on 23/03/18.
 */

public class BoundService extends Service {

    private String TAG = "BoundService";
    private Handler mHandler;
    private Messenger messenger;
    private Handler clientHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate: BoundService" );
        getHandler();
        messenger = new Messenger(mHandler);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "onBind: " );
//        return new LocalBinder();
        return messenger.getBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand: " );
        return super.onStartCommand(intent, flags, startId);
    }

    private class LocalBinder extends Binder {

        public BoundService getLocalBinder() {
            return BoundService.this;
        }

    }

    private Handler getHandler() {
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Bundle bundle = msg.getData();
                Log.e(TAG, "BoundServiceX handleMessage: "+bundle.getString("KEY") );
                Log.e(TAG, "BoundServiceX handleMessage: clientHandler = "+clientHandler.hashCode() );

                /*Message message = new Message();
                message.what = 1;
                if (clientHandler!=null) {
                    Log.e(TAG, "BoundServiceX : sending message to client ===============" );
                    clientHandler.sendMessage(message);
                }*/
            }
        };
        Log.e(TAG, "BoundServiceX handleMessage: mHandler = "+mHandler.hashCode() );
        return mHandler;
    }
}