package com.example.bikeride.database.asynctask;

import android.os.AsyncTask;

import com.example.bikeride.App;
import com.example.bikeride.database.entity.BikeRideEntity;

import java.util.Date;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class GetBikeRideByDateAsyncTask extends AsyncTask<Date, Integer, BikeRideEntity> {
    @Override
    protected BikeRideEntity doInBackground(Date... dates) {
        return App.getInstance().getDatabase().getBikeRideDao().getRideByDate(dates[0]);
    }
}
