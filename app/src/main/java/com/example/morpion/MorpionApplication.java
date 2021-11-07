package com.example.morpion;

import com.example.morpion.db.Player;

public class MorpionApplication extends android.app.Application {

    private Player player1;
    private Player player2;

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer1(Player player) {
        this.player1 = player;
    }

    public void setPlayer2(Player player) {
        this.player2 = player;
    }
}
