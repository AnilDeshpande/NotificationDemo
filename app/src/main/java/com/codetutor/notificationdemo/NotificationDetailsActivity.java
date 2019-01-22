package com.codetutor.notificationdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.w3c.dom.Text;

public class NotificationDetailsActivity extends AppCompatActivity {


    TextView textViewNotificationDetails;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_details);

        textViewNotificationDetails =  (TextView)findViewById(R.id.textViewNotificationDetails);
        textViewNotificationDetails.setText(getIntent().getStringExtra("count"));
    }

    @Override
    protected void onNewIntent(Intent intent) {
        textViewNotificationDetails.setText(intent.getStringExtra("count"));
    }
}
