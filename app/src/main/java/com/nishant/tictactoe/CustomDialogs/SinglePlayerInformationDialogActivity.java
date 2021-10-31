package com.nishant.tictactoe.CustomDialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.nishant.tictactoe.R;

public class SinglePlayerInformationDialogActivity extends Dialog implements View.OnClickListener {
    onSinglePlayerInformationDialogListener playerInformationDialogListener;
    Button startBtn,cancelBtn;
    EditText playerOneEt;

    public SinglePlayerInformationDialogActivity(@NonNull Context context, onSinglePlayerInformationDialogListener playerInformationDialogListener) {
        super(context);
        this.playerInformationDialogListener=playerInformationDialogListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_player_information_dialog);

        startBtn=findViewById(R.id.playerInfoDialogStartBtn);
        cancelBtn=findViewById(R.id.playerInfoDialogCancelBtn);
        playerOneEt=findViewById(R.id.playerOneNameET);

        startBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);
        Window window=this.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.playerInfoDialogStartBtn:
                playerInformationDialogListener.onStart(playerOneEt.getText().toString());
                break;
            case R.id.playerInfoDialogCancelBtn:
                this.hide();
                break;
        }
    }

    public interface onSinglePlayerInformationDialogListener{
        void onStart(String playerOne);
    }
}