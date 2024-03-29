package com.example.morpion.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Player.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract PlayerDao playerDao();
}