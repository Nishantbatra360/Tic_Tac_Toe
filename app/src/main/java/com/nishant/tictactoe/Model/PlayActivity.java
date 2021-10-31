package com.nishant.tictactoe.Model;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.nishant.tictactoe.Enums.Element;
import com.nishant.tictactoe.Enums.GameType;
import com.nishant.tictactoe.Enums.Turn;
import com.nishant.tictactoe.Enums.Result;
import com.nishant.tictactoe.Interface.TicTacToeObserver;
import com.nishant.tictactoe.R;

import java.util.ArrayList;
import java.util.Random;

public class PlayActivity extends AppCompatActivity implements View.OnClickListener, TicTacToeObserver {

    private LottieAnimationView[][] lottieAnimationViews = new LottieAnimationView[3][3];
    private static final int PLAYER_ONE_RESOURCE_FILE = R.raw.circle;
    private static final int PLAYER_TWO_RESOURCE_FILE = R.raw.cross;
    private TextView playerOneLabel, playerTwoLabel, turnLabel;
    private String playerOneName, playerTwoName;
    private Button playAgainBtn, mainMenuBtn;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private GameType gameType;
    private TicTacToe ticTacToe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);

        Intent intent = getIntent();
        playerOneName = intent.getStringExtra("player-1");
        playerTwoName = intent.getStringExtra("player-2");
        gameType = GameType.valueOf(intent.getStringExtra("GameType"));
        turnLabel = findViewById(R.id.turnLabel);
        playAgainBtn = findViewById(R.id.playAgainBtn);
        mainMenuBtn = findViewById(R.id.gotoMainMenu);

        int playerOneScore = intent.getIntExtra("player-1-score", 0);
        int playerTwoScore = intent.getIntExtra("player-2-score", 0);

        if (gameType == GameType.SINGLE_PLAYER) {
            ticTacToe = new SinglePlayerTicTacToe();
            ticTacToe.addObserver(this);
            playerTwoName = "Computer";
        } else {
            ticTacToe = new MultiPlayerTicTacToe();
            ticTacToe.addObserver(this);
        }
        ticTacToe.setPlayerOneScore(playerOneScore);
        ticTacToe.setPlayerTwoScore(playerTwoScore);

        lottieAnimationViews[0][0] = findViewById(R.id.lav_00);
        lottieAnimationViews[0][1] = findViewById(R.id.lav_01);
        lottieAnimationViews[0][2] = findViewById(R.id.lav_02);
        lottieAnimationViews[1][0] = findViewById(R.id.lav_10);
        lottieAnimationViews[1][1] = findViewById(R.id.lav_11);
        lottieAnimationViews[1][2] = findViewById(R.id.lav_12);
        lottieAnimationViews[2][0] = findViewById(R.id.lav_20);
        lottieAnimationViews[2][1] = findViewById(R.id.lav_21);
        lottieAnimationViews[2][2] = findViewById(R.id.lav_22);
        playerOneLabel = findViewById(R.id.playerOneLabel);
        playerTwoLabel = findViewById(R.id.playerTwoLabel);
        playerOneLabel.setText(playerOneName + ": " + ticTacToe.getPlayerOneScore());
        playerTwoLabel.setText(playerTwoName + ": " + ticTacToe.getPlayerTwoScore());

        for (LottieAnimationView[] lottieAnimationView : lottieAnimationViews) {
            for (LottieAnimationView lottieAnimationV : lottieAnimationView) {
                lottieAnimationV.setOnClickListener(this);
            }
        }
        playAgainBtn.setOnClickListener(this);
        mainMenuBtn.setOnClickListener(this);

        if (ticTacToe.getTurn() == Turn.Player_1) {
            turnLabel.setText("Turn: " + playerOneName);
        } else if (ticTacToe.getTurn() == Turn.Player_2) {
            turnLabel.setText("Turn: " + playerTwoName);
        }
    }

    private void checkHighscore(String name, int currentScore) {
        int highscore = sharedPreferences.getInt("highscore", 0);
        if (currentScore > highscore) {
            editor = sharedPreferences.edit();
            editor.putString("name", name);
            editor.putInt("highscore", currentScore);
            editor.commit();
        }
    }

    @Override
    public void onClick(View view) {
        if (view == lottieAnimationViews[0][0] && !(gameType == GameType.SINGLE_PLAYER && ticTacToe.getTurn() == Turn.Player_2)) {
            ticTacToe.addPosition(0, 0);
        } else if (view == lottieAnimationViews[0][1] && !(gameType == GameType.SINGLE_PLAYER && ticTacToe.getTurn() == Turn.Player_2)) {
            ticTacToe.addPosition(0, 1);
        } else if (view == lottieAnimationViews[0][2] && !(gameType == GameType.SINGLE_PLAYER && ticTacToe.getTurn() == Turn.Player_2)) {
            ticTacToe.addPosition(0, 2);
        } else if (view == lottieAnimationViews[1][0] && !(gameType == GameType.SINGLE_PLAYER && ticTacToe.getTurn() == Turn.Player_2)) {
            ticTacToe.addPosition(1, 0);
        } else if (view == lottieAnimationViews[1][1] && !(gameType == GameType.SINGLE_PLAYER && ticTacToe.getTurn() == Turn.Player_2)) {
            ticTacToe.addPosition(1, 1);
        } else if (view == lottieAnimationViews[1][2] && !(gameType == GameType.SINGLE_PLAYER && ticTacToe.getTurn() == Turn.Player_2)) {
            ticTacToe.addPosition(1, 2);
        } else if (view == lottieAnimationViews[2][0] && !(gameType == GameType.SINGLE_PLAYER && ticTacToe.getTurn() == Turn.Player_2)) {
            ticTacToe.addPosition(2, 0);
        } else if (view == lottieAnimationViews[2][1] && !(gameType == GameType.SINGLE_PLAYER && ticTacToe.getTurn() == Turn.Player_2)) {
            ticTacToe.addPosition(2, 1);
        } else if (view == lottieAnimationViews[2][2] && !(gameType == GameType.SINGLE_PLAYER && ticTacToe.getTurn() == Turn.Player_2)) {
            ticTacToe.addPosition(2, 2);
        } else if (view == playAgainBtn) {
            Intent intent = new Intent(PlayActivity.this, PlayActivity.class);
            intent.putExtra("player-1", playerOneName);
            intent.putExtra("player-2", playerTwoName);
            intent.putExtra("player-1-score", ticTacToe.getPlayerOneScore());
            intent.putExtra("player-2-score", ticTacToe.getPlayerTwoScore());
            intent.putExtra("GameType", gameType.toString());
            startActivity(intent);
            finish();
        } else if (view == mainMenuBtn) {
            finish();
        }
    }

    @Override
    public void onTurnChange(Turn currentTurn) {
        if (currentTurn == Turn.Player_1) {
            turnLabel.setText("Turn: " + playerOneName);
        } else {
            turnLabel.setText("Turn: " + playerTwoName);
        }

    }

    @Override
    public void onPositionFilled(int x, int y) {
        if (ticTacToe.getTurn() == Turn.Player_1) {
            lottieAnimationViews[x][y].setAnimation(PLAYER_ONE_RESOURCE_FILE);
        } else {
            lottieAnimationViews[x][y].setAnimation(PLAYER_TWO_RESOURCE_FILE);
        }
    }

    @Override
    public void onGameOver(Result result) {
        playAgainBtn.setVisibility(View.VISIBLE);
        if (result == Result.DRAW) {
            turnLabel.setText("It's a draw");
        } else if (result == Result.PLAYER_1_Winner) {
            turnLabel.setText(playerOneName + " won");
            playerOneLabel.setText(playerOneName + ": " + ticTacToe.getPlayerOneScore());
            checkHighscore(playerOneName,ticTacToe.getPlayerOneScore());
        } else {
            turnLabel.setText(playerTwoName + " won");
            playerTwoLabel.setText(playerTwoName + ": " + ticTacToe.getPlayerTwoScore());
            checkHighscore(playerTwoName,ticTacToe.getPlayerTwoScore());
        }
    }
}