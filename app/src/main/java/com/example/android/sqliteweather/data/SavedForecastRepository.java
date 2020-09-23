package com.example.android.sqliteweather.data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class SavedForecastRepository {
    private ForecastDAO mDAO;

    public SavedForecastRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mDAO = db.forecastDAO();
    }

    public void insertSavedForecast(ForecastEntity entity){
        new InsertAsyncTask(mDAO).execute(entity);
    }

    public void deleteSavedForecast(ForecastEntity entity){
        new DeleteAsyncTask(mDAO).execute(entity);
    }

    public LiveData<List<ForecastEntity>> getAllForecasts(){
        return mDAO.getAllForecasts();
    }

    public LiveData<ForecastEntity> getForecastByName(String locationName){
        return mDAO.getForecastByName(locationName);
    }


    // This class will handle the Inserts
    private static class InsertAsyncTask extends AsyncTask<ForecastEntity, Void, Void>{
        private  ForecastDAO mAsyncTaskDAO; // Create a local version of DAO
        InsertAsyncTask(ForecastDAO dao){
            mAsyncTaskDAO = dao;
        }

        @Override
        protected Void doInBackground(ForecastEntity... forecastEntities) {
            mAsyncTaskDAO.insert(forecastEntities[0]);     // This is the forecast that will be added to db
            return null;
        }
    }

    // This class will handle the Deletes
    private static class DeleteAsyncTask extends AsyncTask<ForecastEntity, Void, Void>{
        private  ForecastDAO mAsyncTaskDAO; // Create a local version of DAO
        DeleteAsyncTask(ForecastDAO dao){
            mAsyncTaskDAO = dao;
        }

        @Override
        protected Void doInBackground(ForecastEntity... forecastEntities) {
            mAsyncTaskDAO.delete(forecastEntities[0]);     // This is the forecast that will be removed from the db
            return null;
        }
    }
}
