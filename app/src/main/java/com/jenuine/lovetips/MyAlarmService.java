package com.jenuine.lovetips;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import java.util.Calendar;

public class MyAlarmService extends Service {
    private static MyAlarmService myAlarmService;

    public static MyAlarmService getInstance() {
        if (myAlarmService == null)
            myAlarmService = new MyAlarmService();
        return myAlarmService;
    }


    public void setTime(int DATE) {

        Calendar calendar = Calendar.getInstance();
        //set notification for date --> 8th January 2015 at 9:06:00 PM
        calendar.set(Calendar.MONTH, 6);
        calendar.set(Calendar.YEAR, 2015);
        calendar.set(Calendar.DAY_OF_MONTH, DATE);

        calendar.set(Calendar.HOUR_OF_DAY, 12);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.AM_PM, Calendar.PM);
        Toast.makeText(getApplicationContext(), calendar.getActualMaximum(Calendar.DAY_OF_MONTH) + "", Toast.LENGTH_LONG).show();
        Intent myIntent = new Intent(getApplicationContext(), MyReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, myIntent, 0);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);


    }


    @Override
    public IBinder onBind(Intent arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }

}