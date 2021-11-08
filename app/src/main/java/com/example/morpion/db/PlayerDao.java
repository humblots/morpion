package com.example.morpion.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PlayerDao {
    @Query("SELECT * FROM player WHERE id = :id")
    Player getPlayer(int id);

    @Query("SELECT * FROM player")
    List<Player> getAll();

    @Query("UPDATE player SET wins = wins + 1 WHERE id = :id")
    void updateWins(int id);

    @Query("UPDATE player SET draws = draws + 1 WHERE id = :id")
    void updateDraws(int id);

    @Query("UPDATE player SET defeats = defeats + 1 WHERE id = :id")
    void updateDefeats(int id);

    @Insert
    void insert(Player player);

    @Insert
    long[] insertAll(Player... players);

    @Delete
    void delete(Player player);

    @Update
    void update(Player player);
}
