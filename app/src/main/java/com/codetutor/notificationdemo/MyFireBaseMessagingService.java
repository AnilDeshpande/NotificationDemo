package com.codetutor.notificationdemo;

import android.app.PendingIntent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class MyFireBaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = MyFireBaseMessagingService.class.getSimpleName();

    enum PUSH_NOTIFICATION_SOURCE{
        CONSOLE, API_WITHOUT_NOTIFICATION, API_WITH_NOTIFICATION, UNKNOWN_SOURCE;
    }

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Log.i(getString(R.string.DEBUG_TAG),"New token: "+s);
        //Making an API call - Thread, Volley, okHttp, Retrofit
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        PUSH_NOTIFICATION_SOURCE notificationSource = getNotificationSource(remoteMessage);

        Log.i(getString(R.string.DEBUG_TAG),"Remote Message received from : "+notificationSource);

        switch (notificationSource){
            case CONSOLE:
                ((MyApplication)getApplication()).triggerNotificationWithBackStack(NotificationDetailsActivity.class,
                        getString(R.string.NEWS_CHANNEL_ID),
                        remoteMessage.getNotification().getTitle(),
                        remoteMessage.getNotification().getBody(),
                        "This notification is from FCM Console ",
                        NotificationCompat.PRIORITY_HIGH,
                        false,
                        getResources().getInteger(R.integer.notificationId),
                        PendingIntent.FLAG_UPDATE_CURRENT);
                break;
            case API_WITH_NOTIFICATION:
                ((MyApplication)getApplication()).triggerNotificationWithBackStack(NotificationDetailsActivity.class,
                        getString(R.string.NEWS_CHANNEL_ID),
                        remoteMessage.getNotification().getTitle(),
                        remoteMessage.getNotification().getBody(),
                        "This notification is from FCM API call with notification title and body",
                        NotificationCompat.PRIORITY_HIGH,
                        false,
                        getResources().getInteger(R.integer.notificationId),
                        PendingIntent.FLAG_UPDATE_CURRENT);
                break;
            case API_WITHOUT_NOTIFICATION:
                ((MyApplication)getApplication()).triggerNotificationWithBackStack(NotificationDetailsActivity.class,
                        getString(R.string.NEWS_CHANNEL_ID),
                        "Random notification Title",
                        "Random notification body",
                        "This notification is from FCM API call without notification title and body",
                        NotificationCompat.PRIORITY_HIGH,
                        false,
                        getResources().getInteger(R.integer.notificationId),
                        PendingIntent.FLAG_UPDATE_CURRENT);
                break;
            case UNKNOWN_SOURCE:
                Log.i(TAG,"Since it's unknown source, don't want to do anything");
                break;

                default:break;
        }
    }

    private PUSH_NOTIFICATION_SOURCE getNotificationSource(RemoteMessage remoteMessage){

        PUSH_NOTIFICATION_SOURCE notificationSource;

        RemoteMessage.Notification notification = remoteMessage.getNotification();
        Map<String, String> data =  remoteMessage.getData();

        if (notification !=null && data !=null){
            if(data.size()==0){
                notificationSource =  PUSH_NOTIFICATION_SOURCE.CONSOLE;
            }else {
                notificationSource =  PUSH_NOTIFICATION_SOURCE.API_WITH_NOTIFICATION;
            }
        }else if (remoteMessage.getData()!=null){
            notificationSource =  PUSH_NOTIFICATION_SOURCE.API_WITHOUT_NOTIFICATION;
        } else {
            notificationSource = PUSH_NOTIFICATION_SOURCE.UNKNOWN_SOURCE;
        }
        return notificationSource;
    }
}
