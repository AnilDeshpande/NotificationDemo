package com.codetutor.notificationdemo;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

class MyAppsNotificationManager {

    private Context context;

    private static MyAppsNotificationManager instance;

    private MyAppsNotificationManager(Context context){
        this.context = context;
    }

    public static MyAppsNotificationManager getInstance(Context context){
        if(instance==null){
            instance = new MyAppsNotificationManager(context);
        }
        return instance;
    }

    public void registerNotificationChannelChannel(String channelId, String channelName, String channelDescription) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription(channelDescription);
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    public void triggerNotification(Class targetNotificationActivity, String channelId, String title, String text, String bigText, int priority, boolean autoCancel, int notificationId){

        Intent intent = new Intent(context, targetNotificationActivity);
        intent.putExtra("count", title);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,channelId)
                .setSmallIcon(R.drawable.ic_notification)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_icon_large))
                .setContentTitle(title)
                .setContentText(text)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(bigText))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setChannelId(channelId)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(notificationId,builder.build());
    }

    public void triggerNotification(Class targetNotificationActivity, String channelId, String title, String text, String bigText, int priority, boolean autoCancel, int notificationId, int pendingIntentFlag){

        Intent intent = new Intent(context, targetNotificationActivity);
        intent.putExtra("count", title);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, pendingIntentFlag);


        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,channelId)
                .setSmallIcon(R.drawable.ic_notification)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_icon_large))
                .setContentTitle(title)
                .setContentText(text)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(bigText))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setChannelId(channelId)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(notificationId,builder.build());
    }




}
