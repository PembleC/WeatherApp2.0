package com.example.android.sqliteweather;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.android.sqliteweather.data.ForecastEntity;
import com.example.android.sqliteweather.data.SavedForecastRepository;

import java.util.List;

public class SavedForecastViewModel extends AndroidViewModel {
    private SavedForecastRepository mRepository;


    public SavedForecastViewModel(@NonNull Application application) {
        super(application);
        mRepository = new SavedForecastRepository(application);
    }

    public void insertSavedForecast(ForecastEntity entity){
        mRepository.insertSavedForecast(entity);
    }

    public void deleteSavedForecast(ForecastEntity entity){
        mRepository.deleteSavedForecast(entity);
    }

    public LiveData<List<ForecastEntity>> getAllForecasts(){
        return mRepository.getAllForecasts();
    }

    public LiveData<ForecastEntity> getForecastByName(String locationName){
        return mRepository.getForecastByName(locationName);
    }
}
