package com.example.morpion.db;


import static androidx.room.ForeignKey.CASCADE;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "score",
        foreignKeys = @ForeignKey(entity = Player.class,
        parentColumns = "id",
        childColumns = "playerId",
        onDelete = CASCADE))
public class Score implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "playerId")
    private int playerId;

    @ColumnInfo(name = "wins")
    private int wins;

    @ColumnInfo(name = "defeats")
    private int defeats;

    @ColumnInfo(name = "draws")
    private int draws;


    public Score(int playerId, int wins, int defeats, int draws){
        this.playerId = playerId;
        this.wins = wins;
        this.defeats = defeats;
        this.draws = draws;
    }

    public int getPlayerId() {
        return playerId;
    }

    public int getWins() {
        return wins;
    }

    public int getDefeats() {
        return defeats;
    }

    public int getDraws() {
        return draws;
    }
}