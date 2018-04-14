package com.selfdev.googlemapstut.services;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.selfdev.googlemapstut.R;

/**
 * Created by Rahul on 23/03/18.
 */

public class HandlerActivity extends FragmentActivity {
    private String TAG = HandlerActivity.class.getSimpleName();
    private Handler handlerThreadHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        Thread thread = newThread();
        thread.start();
        Log.e(TAG, "onCreate: " );

        HandlerThread handlerThread = newHandlerThread();
        handlerThread.start();

        handlerThreadHandler = new Handler(handlerThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: " );
        if (mHandler!=null) {
            Message message = new Message();
            message.what = 3;
            Log.e(TAG, "handleMessage: -------------> 1. RUNNING ON : "+Thread.currentThread().getName() );
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    Log.e(TAG, "handleMessage: -------------> 2. RUNNING ON : "+Thread.currentThread().getName() );
                }
            });
        }

        handlerThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                Log.e(TAG, "run: RUNNING ON HT : "+Thread.currentThread().getName() );
            }
        });


    }

    private Handler mHandler;
    private void newHandler() {
        mHandler = new Handler(){

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Log.e(TAG, "handleMessage: -------------> "+msg.what );
                if (msg.what == 1) {
                    Log.e(TAG, "handleMessage: -------------> MESSAGE FROM THREAD");
                    Log.e(TAG, "handleMessage THREAD NAME 1 : "+Thread.currentThread().getName() );
                } else if (msg.what == 2) {
                    Log.e(TAG, "handleMessage: -------------> MESSAGE FROM RUNNABLE" );
                }  else if (msg.what == 3) {
                    Log.e(TAG, "handleMessage THREAD NAME 3 : "+Thread.currentThread().getName() );
                    Log.e(TAG, "handleMessage: -------------> MESSAGE FROM UI" );
                } else if (msg.what == 99) {
                    Log.e(TAG, "handleMessage: -------------> MESSAGE FROM MAIN" );
                }
            }
        };
    }

    private Thread newThread() {
        Thread newThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Log.e(TAG, "run 1: THREAD NAME : "+Thread.currentThread().getName() );
                Looper.prepare();
                Log.e(TAG, "run 2: " );
                newHandler();
                Log.e(TAG, "run 3: " );
                Message message = new Message();
                message.what = 1;
                mHandler.sendMessage(message);
                Looper.loop();
                Log.e(TAG, "run 4: " );
                /*Message message = new Message();
                message.what = 1;
                mHandler.sendMessage(message);*/
                /*try {
                    Log.e(TAG, "run: SLEEP" );
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
                /*Log.e(TAG, "run: AWAKE" );
                Message message = new Message();
                message.what = 1;
                mHandler.sendMessage(message);*/

            }
        });
        return newThread;
    }

    private HandlerThread newHandlerThread() {
        HandlerThread newHandlerThread = new HandlerThread("H_T"){
            @Override
            public void run() {
                super.run();
            }
        };
        return newHandlerThread;
    }
}
