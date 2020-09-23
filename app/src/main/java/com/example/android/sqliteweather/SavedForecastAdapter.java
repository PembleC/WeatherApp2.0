package com.example.android.sqliteweather;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.sqliteweather.data.ForecastEntity;

import java.util.ArrayList;
import java.util.List;

public class SavedForecastAdapter extends RecyclerView.Adapter<SavedForecastAdapter.SavedForecastViewHolder> {

    private static final String TAG = SavedForecastAdapter.class.getSimpleName();
    private ArrayList<ForecastEntity> mLocations;
    private OnSavedForecastClickListener mSavedForecastListener;

    public void updateLocationsList(List<ForecastEntity> forecastEntityList) {
        for (int i = 0; i<forecastEntityList.size(); i++){
            Log.d(TAG, "Adapter: Updating Location List with item: "+ i + " = " + forecastEntityList.get(i).cityName);
            addLocation(forecastEntityList.get(i));
        }
    }

    public interface OnSavedForecastClickListener {
        void onSavedForecastClicked(String locationName);
    }

    public SavedForecastAdapter(OnSavedForecastClickListener listener){
        mSavedForecastListener = listener;
        mLocations = new ArrayList<>();
    }

    public void addLocation(ForecastEntity entity) {
        mLocations.add(entity);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SavedForecastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.saved_forecast_item, parent,
                false);

        SavedForecastViewHolder viewHolder = new SavedForecastViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SavedForecastViewHolder holder, int position) {
        //String location = mLocations.get(mLocations.size() - position - 1);
        ForecastEntity location = mLocations.get(mLocations.size() - position - 1);
        holder.bind(location);
    }

    @Override
    public int getItemCount() {
        return mLocations.size();
    }




    public class SavedForecastViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mLocationTV;

        public SavedForecastViewHolder(@NonNull View itemView) {
            super(itemView);
            mLocationTV = (TextView)itemView.findViewById(R.id.tv_location_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            String locationName = mLocationTV.getText().toString();
            Log.d(TAG, "Adapter = Location clicked was: " + locationName);
            mSavedForecastListener.onSavedForecastClicked(locationName);
        }

        void bind(ForecastEntity entity) {
            mLocationTV.setText(entity.cityName);
        }
    }
}
