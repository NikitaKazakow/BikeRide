package com.example.bikeride.database.asynctask;

import android.os.AsyncTask;
import android.util.Pair;

import com.example.bikeride.App;
import com.example.bikeride.database.dao.BikeRideDao;
import com.example.bikeride.database.dao.RouteDao;
import com.example.bikeride.database.entity.RouteEntity;

import java.util.List;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InsertRouteDataAsyncTask extends AsyncTask<List<Pair<Double, Double>>, Integer, Void> {

    @Override
    protected Void doInBackground(List<Pair<Double, Double>>... lists) {

        BikeRideDao bikeRideDao = App.getInstance().getDatabase().getBikeRideDao();
        RouteDao routeDao = App.getInstance().getDatabase().getRouteDao();

        long id = bikeRideDao.getLastRide().getId();
        for (int i = 0; i < lists[0].size(); i++) {
            routeDao.insert(new RouteEntity(lists[0].get(i).second, lists[0].get(i).first, id));
        }

        return null;
    }
}
