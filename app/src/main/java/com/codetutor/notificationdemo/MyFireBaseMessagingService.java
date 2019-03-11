package com.codetutor.notificationdemo;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Set;

public class MyFireBaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = MyFireBaseMessagingService.class.getSimpleName();

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Log.i(TAG," The New token has been generated: "+s);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Set<String> keySet = remoteMessage.getData().keySet();
        for(String key: keySet){
            Log.i(TAG,key+" : "+ remoteMessage.getData().get(key));
        }

    }

    @Override
    public void onMessageSent(String s) {
        super.onMessageSent(s);
    }
}
