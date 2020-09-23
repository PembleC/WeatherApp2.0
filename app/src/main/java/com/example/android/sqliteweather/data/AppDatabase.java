package com.example.android.sqliteweather.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {ForecastEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ForecastDAO forecastDAO();
    private static volatile AppDatabase INSTANCE;   // The one single instance of the database

    static AppDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (AppDatabase.class){       // Ensures only a single thread can enter this block at a time
                if(INSTANCE == null){               // Double check to make sure its the null instance
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "forecast_db"
                    ).build();
                }
            }
        }
        return INSTANCE;
    }

}
