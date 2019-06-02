package com.codetutor.notificationdemo;

import android.provider.ContactsContract;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;
import java.util.Set;

public class MyFireBaseMessagingService extends  FirebaseMessagingService {

    private static final String TAG = MyFireBaseMessagingService.class.getSimpleName();

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Log.i(getString(R.string.DEBUG_TAG),"New token: "+s);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.i(getString(R.string.DEBUG_TAG),"Remote Message received");

        RemoteMessage.Notification notification = remoteMessage.getNotification();
        Map<String, String> data =  remoteMessage.getData();

        if (notification !=null && data !=null){
            Log.i(getString(R.string.DEBUG_TAG),"Message received from Rest API with both with data as well");
        } else if(remoteMessage.getNotification()!=null){
            Log.i(getString(R.string.DEBUG_TAG),"Message received from console");
        }else if (remoteMessage.getData()!=null){
            Log.i(getString(R.string.DEBUG_TAG),"Message received from Rest API");
        }
    }
}
