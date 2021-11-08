package com.example.morpion.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "player")
public class Player implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "picture")
    private String picture;

    @ColumnInfo(name = "wins")
    private int wins;

    @ColumnInfo(name = "draws")
    private int draws;

    @ColumnInfo(name = "defeats")
    private int defeats;

    public Player(String name, String picture, int wins, int draws, int defeats) {
        this.name = name;
        this.picture = picture;
        this.wins = wins;
        this.draws = draws;
        this.defeats = defeats;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {this.id = id; }

    public String getName() {
        return name;
    }

    public String getPicture() {
        return picture;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }

    public int getDefeats() {
        return defeats;
    }

    public void setDefeats(int defeats) {
        this.defeats = defeats;
    }
}
