package com.nishant.tictactoe.Model;

import com.nishant.tictactoe.Enums.ButtonAction;

public class Btn {
    private ButtonAction buttonAction;
    private String buttonLabel;

    public Btn(String buttonLabel,ButtonAction buttonAction){
        this.buttonLabel=buttonLabel;
        this.buttonAction=buttonAction;
    }

    public ButtonAction getButtonAction() {
        return buttonAction;
    }

    public String getButtonLabel() {
        return buttonLabel;
    }
}
