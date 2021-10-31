package com.nishant.tictactoe.Model;

import android.annotation.SuppressLint;
import android.os.Parcel;

import com.nishant.tictactoe.Enums.Element;
import com.nishant.tictactoe.Enums.Turn;
import com.nishant.tictactoe.Interface.TicTacToeObserver;


public class MultiPlayerTicTacToe extends TicTacToe {


    @Override
    void changeTurn() {
        if (turn == Turn.Player_1) {
            turn = Turn.Player_2;
            currentElement = Element.CROSS;
        } else if (turn == Turn.Player_2) {
            turn = Turn.Player_1;
            currentElement = Element.CIRCLE;
        }
        for (TicTacToeObserver listener : listeners) {
            listener.onTurnChange(turn);
        }
    }

}
