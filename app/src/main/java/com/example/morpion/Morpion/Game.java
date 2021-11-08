package com.example.morpion.Morpion;

import com.example.morpion.db.Player;

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
