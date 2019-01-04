package com.codetutor.notificationdemo;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    Button buttonTriggerNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonTriggerNotification = (Button)findViewById(R.id.buttonTriggerNotification);

        buttonTriggerNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MyAppsNotificationManager.getInstance(MainActivity.this).triggerNotification(NotificationDetailsActivity.class,
                        "Notification Title",
                        "Notification Content text. Ideally this should be bit more long and descriptive",
                        "Notification Content text. Ideally this should be bit more long and descriptive",
                         NotificationCompat.PRIORITY_DEFAULT,
                        true,
                        getResources().getInteger(R.integer.notificationId));
            }
        });
    }
}
