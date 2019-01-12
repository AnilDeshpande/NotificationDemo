package com.codetutor.notificationdemo;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static com.codetutor.notificationdemo.R.drawable.ic_icon_large;

public class MainActivity extends AppCompatActivity {


    Button buttonTriggerNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createNotificationChannel();

        buttonTriggerNotification = (Button)findViewById(R.id.buttonTriggerNotification);

        buttonTriggerNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                triggerNotification();
            }
        });
    }

    private void createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel(getString(R.string.NEWS_CHANNEL_ID), getString(R.string.CHANNEL_NEWS), NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription(getString(R.string.CHANNEL_DESCRIPTION));
            notificationChannel.setShowBadge(true);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    private void triggerNotification(){

        Intent intent = new Intent(this, NotificationDetailsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent  = PendingIntent.getActivity(this,0,intent,0);


        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,getString(R.string.NEWS_CHANNEL_ID))
                .setSmallIcon(R.drawable.ic_notification)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_icon_large))
                .setContentTitle("Notification Title")
                .setContentText("Notification Content text. Ideally this should be bit more long and descriptive")
                .setStyle(new NotificationCompat.BigTextStyle().bigText("Notification Content text. Ideally this should be bit more long and descriptive"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setChannelId(getString(R.string.NEWS_CHANNEL_ID))
                .setAutoCancel(true);

        NotificationManagerCompat notificationManagerCompat  = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(getResources().getInteger(R.integer.notificationId),builder.build());
    }


}
