package com.example.morpion.db;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

public interface ScoreDao {

    @Query("SELECT * FROM score WHERE playerId = :playerId")
    Score getPlayerScore(int playerId);

    @Query("INSERT OR REPLACE INTO score(playerId, wins) VALUES (:playerId, :wins);")
    void insertNewWin(int playerId, int wins);

    @Query("INSERT OR REPLACE INTO score(playerId, defeats) VALUES (:playerId, :defeats);")
    void insertNewDefeat(int playerId, int defeats);

    @Query("INSERT OR REPLACE INTO score(playerId, draws) VALUES (:playerId, :draws);")
    void insertNewDraw(int playerId, int draws);

    @Insert
    void insert(Score score);

    @Insert
    long[] insertAll(Score... scores);

    @Delete
    void delete(Score score);

    @Update
    void update(Score score);
}
