package com.example.morpion.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PlayerDao {
    @Query("SELECT * FROM player")
    List<Player> getAll();

    @Insert
    void insert(Player player);

    @Insert
    long[] insertAll(Player... players);

    @Delete
    void delete(Player player);

    @Update
    void update(Player player);
}
