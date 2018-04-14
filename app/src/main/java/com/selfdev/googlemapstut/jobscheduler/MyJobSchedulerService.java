package com.selfdev.googlemapstut.jobscheduler;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;

/**
 * Created by Rahul on 23/03/18.
 */

//https://medium.com/google-developers/scheduling-jobs-like-a-pro-with-jobscheduler-286ef8510129

public class MyJobSchedulerService extends JobService {
    private String TAG = MyJobSchedulerService.class.getSimpleName();

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        Log.e(TAG, "onStartJob: ------------" );
        new MyThread(jobParameters).start();
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        Log.e(TAG, "onStopJob: ++++++++++++" );
        return false;
    }

    public class MyThread extends Thread {
        private JobParameters jobParameters;

        public MyThread(JobParameters jobParameters) {
            this.jobParameters = jobParameters;
        }

        @Override
        public void run() {
            super.run();
            try {
                Thread.sleep(2000);
                jobFinished(jobParameters,false);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
