package com.nishant.tictactoe.Model;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

import com.nishant.tictactoe.Adapters.ButtonsAdapter;
import com.nishant.tictactoe.CustomDialogs.MultiPlayerInformationDialogActivity;
import com.nishant.tictactoe.CustomDialogs.SinglePlayerInformationDialogActivity;
import com.nishant.tictactoe.Enums.ButtonAction;
import com.nishant.tictactoe.Enums.GameType;
import com.nishant.tictactoe.R;
import com.nishant.tictactoe.Services.TimeService;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MultiPlayerInformationDialogActivity.onPlayerInformationDialogListener,SinglePlayerInformationDialogActivity.onSinglePlayerInformationDialogListener,ButtonsAdapter.OnButtonListener {

    private MultiPlayerInformationDialogActivity multiPlayerInformationDialogActivity;
    private SinglePlayerInformationDialogActivity singlePlayerInformationDialogActivity;
    private boolean isDoubleTap=false;
    public static final String broadCastAction  = "time_broadcast";
    private TextView timeLabel;
    private RecyclerView buttonsRecyclerView;
    private ButtonsAdapter buttonsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timeLabel=findViewById(R.id.timeLabel);
        multiPlayerInformationDialogActivity=new MultiPlayerInformationDialogActivity(MainActivity.this,this);
        singlePlayerInformationDialogActivity=new SinglePlayerInformationDialogActivity(MainActivity.this,this);

        ArrayList<Btn> btnArrayList=new ArrayList<>();
        btnArrayList.add(new Btn("Single Player",ButtonAction.SINGLE_PLAYER));
        btnArrayList.add(new Btn("Multi Player",ButtonAction.MULTI_PLAYER));
        btnArrayList.add(new Btn("View Highscore",ButtonAction.HIGHSCORE));
        buttonsAdapter=new ButtonsAdapter(btnArrayList,this);
        buttonsRecyclerView=findViewById(R.id.buttonsRecyclerView);
        buttonsRecyclerView.setAdapter(buttonsAdapter);


        Intent intent=new Intent(this, TimeService.class);
        startService(intent);

    }

    @Override
    public void onStart(String playerOne, String playerTwo) {
        if(playerOne.trim().length()==0){
            Toast.makeText(getApplicationContext(),"Player-1 name cannot be empty",Toast.LENGTH_SHORT).show();
        }
        else if(playerTwo.trim().length()==0){
            Toast.makeText(getApplicationContext(),"Player-2 name cannot be empty",Toast.LENGTH_SHORT).show();
        }
        else if(playerOne.length()>10){
            Toast.makeText(getApplicationContext(),"Player-1 name cannot be more than 10 letters",Toast.LENGTH_SHORT).show();
        }
        else if(playerTwo.length()>10){
            Toast.makeText(getApplicationContext(),"Player-2 name cannot be more than 10 letters",Toast.LENGTH_SHORT).show();
        }
        else{
            multiPlayerInformationDialogActivity.hide();
            Intent intent=new Intent(MainActivity.this,PlayActivity.class);
            intent.putExtra("player-1",playerOne);
            intent.putExtra("player-2",playerTwo);
            intent.putExtra("GameType",GameType.MULTI_PLAYER.toString());
            startActivity(intent);
        }
    }

    @Override
    public void onStart(String playerOne) {
        if(playerOne.trim().length()==0){
            Toast.makeText(getApplicationContext(),"Player-1 name cannot be empty",Toast.LENGTH_SHORT).show();
        }
        else if(playerOne.length()>10){
            Toast.makeText(getApplicationContext(),"Player-1 name cannot be more than 10 letters",Toast.LENGTH_SHORT).show();
        }
        else {
            singlePlayerInformationDialogActivity.hide();
            Intent intent=new Intent(MainActivity.this,PlayActivity.class);
            intent.putExtra("player-1",playerOne);
            intent.putExtra("GameType", GameType.SINGLE_PLAYER.toString());
            startActivity(intent);
        }
    }

    private BroadcastReceiver broadcastReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int hour=intent.getIntExtra("hour",0);
            int minute=intent.getIntExtra("minute",0);
            int second=intent.getIntExtra("second",0);
            String hourStr=String.valueOf(hour);
            String minuteStr=String.valueOf(minute);
            String secondStr=String.valueOf(second);
            if(hour<10){
                hourStr="0"+hour;
            }
            if(minute<10){
                minuteStr="0"+minute;
            }
            if(second<10){
                secondStr="0"+second;
            }
            timeLabel.setText(hourStr+":"+minuteStr+":"+secondStr);
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(broadcastReceiver,new IntentFilter(broadCastAction));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(broadcastReceiver);
    }

    @Override
    public void onBackPressed() {
        if(!isDoubleTap){
            isDoubleTap=true;
            Toast.makeText(getApplicationContext(),"Press again to exit",Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    isDoubleTap=false;
                }
            },2000);
        }
        else {
            this.finishAffinity();
        }
    }

    @Override
    public void onButtonClick(ButtonAction buttonAction) {
        switch (buttonAction){
            case SINGLE_PLAYER:
                singlePlayerInformationDialogActivity.show();
                break;
            case MULTI_PLAYER:
                multiPlayerInformationDialogActivity.show();
                break;
            case HIGHSCORE:
                Intent intent=new Intent(MainActivity.this,HighScoreActivity.class);
                startActivity(intent);
                break;
        }
    }
}