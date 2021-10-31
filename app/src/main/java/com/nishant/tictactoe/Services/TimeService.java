package com.nishant.tictactoe.Services;

import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.Nullable;

import com.nishant.tictactoe.Model.MainActivity;

import java.util.Calendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TimeService extends IntentService {

    public TimeService() {
        super("TimeService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        while (true){
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    Intent broadCaseIntent=new Intent(MainActivity.broadCastAction);
//                    broadCaseIntent.putExtra("hour", Calendar.getInstance().getTime().getHours());
//                    broadCaseIntent.putExtra("minute", Calendar.getInstance().getTime().getMinutes());
//                    broadCaseIntent.putExtra("second", Calendar.getInstance().getTime().getSeconds());
//                    sendBroadcast(broadCaseIntent);
//                    Log.e("sent","true");
//                }
//            },1000);
//        }
        ScheduledExecutorService scheduledExecutorService= Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                Intent broadCaseIntent=new Intent(MainActivity.broadCastAction);
                broadCaseIntent.putExtra("hour", Calendar.getInstance().getTime().getHours());
                broadCaseIntent.putExtra("minute", Calendar.getInstance().getTime().getMinutes());
                broadCaseIntent.putExtra("second", Calendar.getInstance().getTime().getSeconds());
                sendBroadcast(broadCaseIntent);
            }
        },0,1, TimeUnit.SECONDS);
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }
}
