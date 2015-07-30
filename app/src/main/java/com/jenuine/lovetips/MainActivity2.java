package com.jenuine.lovetips;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public class MainActivity2 extends AppCompatActivity {

    private MainActivity2 context;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_love);
        context=this;
        Intent service1 = new Intent(context, MyAlarmService.class);
        context.startService(service1);
        MyAlarmService myAlaramService=MyAlarmService.getInstance();
        myAlaramService.setTime(17);

    } //end onCreate


}