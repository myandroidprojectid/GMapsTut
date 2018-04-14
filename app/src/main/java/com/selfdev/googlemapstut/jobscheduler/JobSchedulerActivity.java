package com.selfdev.googlemapstut.jobscheduler;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.selfdev.googlemapstut.R;

/**
 * Created by Rahul on 23/03/18.
 */

public class JobSchedulerActivity extends FragmentActivity {
    private String TAG = JobSchedulerActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Log.e(TAG, "onCreate: " );

        JobScheduler jobScheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);

        jobScheduler.schedule(new JobInfo.Builder(99,new ComponentName(this,MyJobSchedulerService.class))
                .setPeriodic(10000)
                .build()
        );
    }
}