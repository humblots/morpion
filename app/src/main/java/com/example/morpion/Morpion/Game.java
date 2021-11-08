package com.example.morpion.Morpion;

import com.example.morpion.db.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Game {

    enum State{Empty, O, X};

    private int n = 3;
    private int moveCount = 0;
    private int playerTurn = 0;
    private State[][] grid;
    private Player[] players = new Player[2];

    public Game(Player player1, Player player2, int gridSize) {
        this.n = gridSize;
        grid = new State[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = State.Empty;
            }
        }
        players[0] = player1;
        players[1] = player2;
    }

    public int move(int x, int y) {
        int res = -1;
        if(grid[x][y] == State.Empty) {
            State state = playerTurn == 0
                    ? State.O
                    : State.X;
            grid[x][y] = state;
            moveCount++;
            res = checkWinner(x, y, state);
        }
        return res;
    }

    public int checkWinner(int x, int y, State s) {
        // check row
        for(int i = 0; i < n; i++){
            if (grid[x][i] != s) break;
            if(i == n-1) return 1;
        }

        // check col
        for(int i = 0; i < n; i++) {
            if (grid[i][y] != s) break;
            if (i == n-1) return 1;
        }

        // check diag (left to right)
        if (x == y) {
            for(int i = 0; i < n; i++){
                if(grid[i][i] != s) break;
                if(i == n-1) return 1;
            }
        }

        // check diag (right to left)
        if (x + y == n-1) {
            for(int i = 0; i < n; i++){
                if(grid[i][(n-1)-i] != s) break;
                if(i == n-1) return 1;
            }
        }

        //check draw
        if(moveCount == (Math.pow(n, 2))){
            return 2;
        }

        return 0;
    }

    // check if able to do a winning move -> make a move
    // check if able to stop a winning move -> make a move
    // make a random move
    public ArrayList<Integer> botMoveCoord() {
        ArrayList<Integer> coord = new ArrayList<>();

        // check if able to win in a row
        if((coord = checkBotWinningMoveCoord()) != null) return coord;

        if((coord = checkBotBlockingMoveCoord()) != null) return coord;

        return botRandomMoveCoord();
    }

    public ArrayList<Integer> checkMoveCoord(State s) {
        ArrayList<Integer> coord = new ArrayList<>();

        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                if (grid[x][y] != s) break;
                if (y == n-2 && grid[x][y+1] == State.Empty) {
                    coord.add(x);
                    coord.add(y+1);
                    return coord;
                };
            }
        }

        // check col
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < n; x++) {
                if (grid[x][y] != s) break;
                if (x == n-2 && grid[x+1][y] == State.Empty) {
                    coord.add(x+1);
                    coord.add(y);
                    return coord;
                }
            }
        }


        for (int i = 0; i < n; i++) {
            if(grid[i][i] != s) break;
            if(i == n-2 && grid[i+1][i+1] == State.Empty) {
                coord.add(i+1);
                coord.add(i+1);
                return coord;
            }
        }

        for(int i = 0; i < n; i++){
            if(grid[i][(n-1)-i] == s) break;
            if(i == n-2 && grid[i+1][i-1] == State.Empty) {
                coord.add(i+1);
                coord.add(i-1);
                return coord;
            }
        }
        return null;
    }

    public ArrayList<Integer> checkBotWinningMoveCoord() {
        // player State
        State s = playerTurn == 0
                ? State.O
                : State.X;

        return  checkMoveCoord(s);
    }

    public ArrayList<Integer> checkBotBlockingMoveCoord() {
        // enemy State
        State s = playerTurn == 0
                ? State.X
                : State.O;

        return  checkMoveCoord(s);
    }

    public ArrayList<Integer> botRandomMoveCoord() {
        ArrayList<ArrayList<Integer>> randomMoveCoords = new ArrayList<>();

        int i = 0;
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                if (grid[x][y] == State.Empty) {
                    ArrayList<Integer> coord = new ArrayList<>();
                    coord.add(0, x);
                    coord.add(1, y);
                    randomMoveCoords.add(coord);
                }
            }
        }

        int index = (int)(Math.random() * randomMoveCoords.size());

        return randomMoveCoords.get(index);

    }

    public void nextPlayer() {
        playerTurn = 1 - playerTurn;
    }

    public State[][] getGrid() {
        return grid;
    }

    public Player[] getPlayers() {
        return players;
    }

    public Player getCurrentPlayer() {
        return players[playerTurn];
    }

    public Player getSecondPlayer() { return players[1 - playerTurn]; }
}
