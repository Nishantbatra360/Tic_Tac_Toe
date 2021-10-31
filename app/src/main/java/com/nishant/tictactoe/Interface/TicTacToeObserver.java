package com.nishant.tictactoe.Interface;

import com.nishant.tictactoe.Enums.Result;
import com.nishant.tictactoe.Enums.Turn;


public interface TicTacToeObserver {
    public void onTurnChange(Turn currentTurn);

    public void onPositionFilled(int x, int y);

    public void onGameOver(Result result);
}
