package com.nishant.tictactoe.CustomDialogs;

import androidx.annotation.NonNull;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nishant.tictactoe.R;

public class MultiPlayerInformationDialogActivity extends Dialog implements View.OnClickListener {
    onPlayerInformationDialogListener playerInformationDialogListener;
    Button startBtn,cancelBtn;
    EditText playerOneEt,playerTwoEt;

    public MultiPlayerInformationDialogActivity(@NonNull Context context, onPlayerInformationDialogListener playerInformationDialogListener) {
        super(context);
        this.playerInformationDialogListener=playerInformationDialogListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_player_information_dialog);

        startBtn=findViewById(R.id.playerInfoDialogStartBtn);
        cancelBtn=findViewById(R.id.playerInfoDialogCancelBtn);
        playerOneEt=findViewById(R.id.playerOneNameET);
        playerTwoEt=findViewById(R.id.playerTwoNameET);

        startBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);
        Window window=this.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onClick(View view) {
//        switch (view.getId()){
//            case R.id.playerInfoDialogStartBtn:
//                break;
//            case R.id.playerInfoDialogCancelBtn:
//                Toast.makeText(getContext().getApplicationContext(),"Cancel clicked",Toast.LENGTH_SHORT).show();
//                Log.e("cancel","clicked");
//                break;
//        }
        if (view==startBtn){
            playerInformationDialogListener.onStart(playerOneEt.getText().toString(),playerTwoEt.getText().toString());
        }
        else if(view==cancelBtn){
            this.hide();
        }
    }

    public interface onPlayerInformationDialogListener{
        void onStart(String playerOne,String playerTwo);
    }
}