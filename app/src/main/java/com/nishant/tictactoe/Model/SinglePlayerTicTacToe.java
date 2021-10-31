package com.nishant.tictactoe.Model;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Parcel;

import com.nishant.tictactoe.Enums.Element;
import com.nishant.tictactoe.Enums.Turn;
import com.nishant.tictactoe.Interface.TicTacToeObserver;

import java.util.ArrayList;
import java.util.Random;

public class SinglePlayerTicTacToe extends TicTacToe {

    public SinglePlayerTicTacToe(){
        super();
        if(this.getTurn()==Turn.Player_2){
            virtualUserMove();
        }
    }

    private void virtualUserMove() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ArrayList<Position> availablePositions = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (positions[i][j] == null) {
                            availablePositions.add(new Position(i, j));
                        }
                    }
                }
                Random random = new Random();
                int random_int = random.nextInt(availablePositions.size());
                addPosition(availablePositions.get(random_int).getX(), availablePositions.get(random_int).getY());
            }
        }, 500);
    }

    @Override
    void changeTurn() {

        if (turn == Turn.Player_1) {
            turn = Turn.Player_2;
            currentElement = Element.CROSS;
            virtualUserMove();
        } else if (turn == Turn.Player_2) {
            turn = Turn.Player_1;
            currentElement = Element.CIRCLE;
        }
        for (TicTacToeObserver listener : listeners) {
            listener.onTurnChange(turn);
        }
    }

}
