package com.example.android.sqliteweather.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ForecastDAO {

    @Insert
    void insert(ForecastEntity forecast);

    @Delete
    void delete(ForecastEntity forecast);

    @Query("SELECT * FROM forecasts")
    LiveData<List<ForecastEntity>> getAllForecasts();

    @Query("SELECT * FROM forecasts WHERE cityName = :locationName LIMIT 1")        // : allows to access parameters
    LiveData<ForecastEntity> getForecastByName(String locationName);

}
