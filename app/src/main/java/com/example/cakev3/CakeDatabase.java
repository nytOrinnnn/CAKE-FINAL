package com.example.cakev3;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {CakeOrder.class}, version = 1, exportSchema = false)
public abstract class CakeDatabase extends RoomDatabase {
    private static CakeDatabase instance;

    public abstract CakeOrderDao cakeOrderDao();

    public static synchronized CakeDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    CakeDatabase.class,
                    "cake_database"
            ).fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}
