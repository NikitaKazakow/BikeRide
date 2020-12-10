package com.example.bikeride.database.asynctask;

import android.os.AsyncTask;

import com.example.bikeride.App;
import com.example.bikeride.database.entity.RouteEntity;

import java.util.ArrayList;
import java.util.List;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class GetRoutesListAsyncTask extends AsyncTask<Long, Integer, List<RouteEntity>> {
    @Override
    protected List<RouteEntity> doInBackground(Long... longs) {
        List<RouteEntity> routeEntityList = App.getInstance().getDatabase().getRouteDao().getRouteByRideId(longs[0]);
        if (routeEntityList == null || routeEntityList.isEmpty()) {
            return new ArrayList<>();
        }
        else {
            return routeEntityList;
        }
    }
}
