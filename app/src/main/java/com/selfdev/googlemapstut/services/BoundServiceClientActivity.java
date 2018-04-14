package com.selfdev.googlemapstut.services;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.selfdev.googlemapstut.R;

/**
 * Created by Rahul on 23/03/18.
 */

public class BoundServiceClientActivity extends FragmentActivity implements OnServiceConnected {

    private String TAG = "BoundService";

    private ServiceConnection serviceConnectionBinder;
    private ServiceConnection serviceConnectionMessenger;
    private Messenger messenger;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Log.e(TAG, "onCreate: " );
        getHandler();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart: " );
        /*serviceConnectionBinder = getServiceConnectionBinder();
        Intent intent = new Intent(this,BoundService.class);
        bindService(intent, serviceConnectionBinder, Context.BIND_AUTO_CREATE);*/
        serviceConnectionMessenger = getServiceConnectionMessenger();
        Intent intent = new Intent(this,BoundService.class);
        bindService(intent,serviceConnectionMessenger,Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: " );
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(serviceConnectionMessenger);
    }

    private ServiceConnection getServiceConnectionBinder() {
        serviceConnectionBinder = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                Log.e(TAG, "onServiceConnected: " );
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                Log.e(TAG, "onServiceDisconnected: " );
            }
        };
        return serviceConnectionBinder;
    }

    private ServiceConnection getServiceConnectionMessenger() {
        Log.e(TAG, "--------getServiceConnectionMessenger: " );
        serviceConnectionMessenger = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                Log.e(TAG, "---------onServiceConnected: " );
                messenger = new Messenger(iBinder);
                ((OnServiceConnected)BoundServiceClientActivity.this).onServiceConnected();
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                Log.e(TAG, "onServiceDisconnected: " );
            }
        };
        return serviceConnectionMessenger;
    }

    private Handler mHandler;

    private Handler getHandler() {
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Log.e(TAG, "BoundServiceX handleMessage: +++++++++++++++++++++++ "+msg.what );
            }
        };
        return mHandler;
    }

    @Override
    public void onServiceConnected() {
        if (messenger!=null) {
            Message message = new Message();
            Bundle bundle = new Bundle();
            bundle.putString("KEY","HELLO WORLD");
            message.setData(bundle);

            Log.e(TAG, "onResume: clientHandler = "+mHandler.hashCode());
//            message.replyTo = new Messenger(mHandler);

            try {
                messenger.send(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
