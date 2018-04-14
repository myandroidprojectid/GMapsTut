package com.selfdev.googlemapstut.services;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.selfdev.googlemapstut.R;

/**
 * Created by Rahul on 23/03/18.
 */

public class ServiceClientActivity extends FragmentActivity {

    private String TAG = ServiceClientActivity.class.getSimpleName();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, UnboundService.class);
        Log.e(TAG, "UnboundService onStart: ==============");
        startService(intent);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Intent intent = new Intent(this, UnboundService.class);
        Log.e(TAG, "UnboundService onStop: " );
        stopService(intent);
    }
}
