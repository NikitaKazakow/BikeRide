package com.example.bikeride.database.asynctask;

import android.os.AsyncTask;

import com.example.bikeride.App;
import com.example.bikeride.database.entity.BikeRideEntity;

import java.util.ArrayList;
import java.util.List;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class GetBikeRidesListAsyncTask extends AsyncTask<BikeRideEntity, Integer, List<BikeRideEntity>> {
    @Override
    protected List<BikeRideEntity> doInBackground(BikeRideEntity... bikeRideEntities) {
        List<BikeRideEntity> bikeRideEntityList = App.getInstance().getDatabase().getBikeRideDao().getBikeRides();
        if (bikeRideEntityList == null || bikeRideEntityList.isEmpty()) {
            return new ArrayList<>();
        }
        else {
            return bikeRideEntityList;
        }
    }
}
