package com.example.bikeride.viewModel;

import android.content.Context;
import android.content.Intent;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.bikeride.database.asynctask.GetBikeRideByDateAsyncTask;
import com.example.bikeride.model.BikeRideModel;
import com.example.bikeride.view.activity.MainActivity;
import com.example.bikeride.view.activity.RouteMapActivity;

import java.util.Date;
import java.util.concurrent.ExecutionException;

public class BikeRideItemViewModel extends BaseObservable {

    private final BikeRideModel dataModel;

    public BikeRideItemViewModel(BikeRideModel dataModel) {
        this.dataModel = dataModel;
    }

    @Bindable
    public Date getDate() {
        return dataModel.getDate();
    }

    @Bindable
    public long getDistance() {
        return dataModel.getDistance();
    }

    @Bindable
    public float getAverageSpeed() {
        return dataModel.getAverageSpeed();
    }

    @Bindable
    public long getTime() {
        return dataModel.getRideTime();
    }

    public void showRoute(Object context) {
        if (context instanceof MainActivity) {
            GetBikeRideByDateAsyncTask task = new GetBikeRideByDateAsyncTask();
            task.execute(dataModel.getDate());
            try {
                Intent intent = new Intent((Context)context, RouteMapActivity.class);
                intent.putExtra("bikeride", task.get().getId());
                ((MainActivity)context).startActivity(intent);
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
