package com.example.android.sqliteweather.data;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "forecasts")    // DB Table name
public class ForecastEntity implements Serializable {
    @PrimaryKey
    @NonNull
    public String cityName;         // Only really needs to hold the city name
}