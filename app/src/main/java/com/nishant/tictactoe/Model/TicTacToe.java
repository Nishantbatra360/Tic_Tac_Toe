package com.nishant.tictactoe.Model;

import android.os.Parcelable;
import android.view.View;

import com.nishant.tictactoe.Enums.Element;
import com.nishant.tictactoe.Enums.GameType;
import com.nishant.tictactoe.Enums.Result;
import com.nishant.tictactoe.Enums.Turn;
import com.nishant.tictactoe.Interface.TicTacToeObserver;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public abstract class TicTacToe {
    private static final Element PLAYER_ONE_ELEMENT = Element.CIRCLE;
    private static final Element PLAYER_TWO_ELEMENT = Element.CROSS;
    protected Turn turn;
    private boolean isPlaying = true;
    private int playerOneScore = 0, playerTwoScore = 0;
    protected Element positions[][] = new Element[3][3];
    protected Element currentElement;

    protected ArrayList<TicTacToeObserver> listeners = new ArrayList<>();

    public TicTacToe() {
        setRandomTurn();
    }

    public void setPlayerOneScore(int playerOneScore) {
        this.playerOneScore = playerOneScore;
    }

    public void setPlayerTwoScore(int playerTwoScore) {
        this.playerTwoScore = playerTwoScore;
    }

    public void addObserver(TicTacToeObserver observer) {
        listeners.add(observer);
    }

    public Turn getTurn() {
        return turn;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public int getPlayerOneScore() {
        return playerOneScore;
    }

    public int getPlayerTwoScore() {
        return playerTwoScore;
    }

    public Element getCurrentElement() {
        return currentElement;
    }

    private void setRandomTurn() {
        Random random = new Random();
        int randomInt = random.nextInt(2);
        if (randomInt == 0) {
            turn = Turn.Player_1;
            currentElement = Element.CIRCLE;
        } else {
            turn = Turn.Player_2;
            currentElement = Element.CROSS;
        }
    }

    abstract void changeTurn();

    protected void addPosition(int x, int y) {
        if (isPlaying) {
            if (positions[x][y] == null) {
                positions[x][y] = currentElement;
                for (TicTacToeObserver listener : listeners) {
                    listener.onPositionFilled(x, y);
                }
                Result result=checkWinner();
                if (result == Result.KEEP_PLAYING) {
                    changeTurn();
                } else {
                    for (TicTacToeObserver listener : listeners) {
                        listener.onGameOver(result);
                    }
                }
            }
        }
    }

    private Result checkWinner() {
        boolean row_1 = positions[0][0] == positions[0][1] && positions[0][1] == positions[0][2] && positions[0][0] != null;
        boolean row_2 = positions[1][0] == positions[1][1] && positions[1][1] == positions[1][2] && positions[1][0] != null;
        boolean row_3 = positions[2][0] == positions[2][1] && positions[2][1] == positions[2][2] && positions[2][0] != null;
        boolean column_1 = positions[0][0] == positions[1][0] && positions[1][0] == positions[2][0] && positions[2][0] != null;
        boolean column_2 = positions[0][1] == positions[1][1] && positions[1][1] == positions[2][1] && positions[2][1] != null;
        boolean column_3 = positions[0][2] == positions[1][2] && positions[1][2] == positions[2][2] && positions[2][2] != null;
        boolean diagonal_1 = positions[0][0] == positions[1][1] && positions[1][1] == positions[2][2] && positions[2][2] != null;
        boolean diagonal_2 = positions[0][2] == positions[1][1] && positions[1][1] == positions[2][0] && positions[2][0] != null;
        if (row_1 || row_2 || row_3 || column_1 || column_2 || column_3 || diagonal_1 || diagonal_2) {
            isPlaying = false;
            if (turn == Turn.Player_1) {
                playerOneScore++;
                return Result.PLAYER_1_Winner;
            }
            playerTwoScore++;
            return Result.PLAYER_2_Winner;
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (positions[i][j] == null) {
                    return Result.KEEP_PLAYING;
                }
            }
        }
        isPlaying = false;
        return Result.DRAW;
    }
}
