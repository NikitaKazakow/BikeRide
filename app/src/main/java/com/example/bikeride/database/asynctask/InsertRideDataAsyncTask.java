package com.example.bikeride.database.asynctask;

import android.os.AsyncTask;

import androidx.annotation.NonNull;

import com.example.bikeride.App;
import com.example.bikeride.database.dao.BikeRideDao;
import com.example.bikeride.database.entity.BikeRideEntity;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InsertRideDataAsyncTask extends AsyncTask<BikeRideEntity, Integer, Void> {
    @Override
    protected Void doInBackground(@NonNull BikeRideEntity... bikeRideEntities) {
        BikeRideDao bikeRideDao = App.getInstance().getDatabase().getBikeRideDao();
        bikeRideDao.insert(bikeRideEntities[0]);
        return null;
    }
}