package com.selfdev.notifapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.hw);

        // Get the layouts to use in the custom notification
        RemoteViews notificationLayout = new RemoteViews(getPackageName(), R.layout.n_small);
        RemoteViews notificationLayoutExpanded = new RemoteViews(getPackageName(), R.layout.n_large);

// Apply the layouts to the notification
        final NotificationCompat.Builder customNotification = new NotificationCompat.Builder(this, "22")
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setStyle(new NotificationCompat.DecoratedCustomViewStyle())
                .setCustomContentView(notificationLayout)
                .setCustomBigContentView(notificationLayoutExpanded);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationManager notificationmanager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                // Build Notification with Notification Manager
                notificationmanager.notify(0, customNotification.build());
            }
        });
    }
}
