package com.nishant.tictactoe.Model;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.nishant.tictactoe.R;

public class HighScoreActivity extends AppCompatActivity {
    private TextView scoreLabel;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

        scoreLabel=findViewById(R.id.scoreLabel);
        sharedPreferences=getSharedPreferences("MySharedPref",MODE_PRIVATE);

        if (sharedPreferences.getInt("highscore",0)==0){
            scoreLabel.setText("No high scores yet");
        }
        else {
            scoreLabel.setText(sharedPreferences.getString("name","")+": "+sharedPreferences.getInt("highscore",0));
        }
    }
}