package com.example.bikeride.viewModel;

import android.util.Pair;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.bikeride.BR;
import com.example.bikeride.database.asynctask.InsertRideDataAsyncTask;
import com.example.bikeride.database.asynctask.InsertRouteDataAsyncTask;
import com.example.bikeride.database.entity.BikeRideEntity;
import com.example.bikeride.view.activity.BikeRideActivity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;

public class BikeRideViewModel extends BaseObservable {

    private int counter;
    private float speed;
    private float average_speed;
    private long distance;
    private long time;

    private final List<Pair<Double, Double>> points;

    private final Timer timer;

    public BikeRideViewModel() {
        points = new ArrayList<>();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                setTime();
            }
        }, 0, 1000);
    }

    private float average() {
        float temp = (this.average_speed * counter) + this.speed;
        counter++;
        return  temp / counter;
    }

    @Bindable
    public float getSpeed() {
        return this.speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
        notifyPropertyChanged(BR.speed);

        this.average_speed = average();
        notifyPropertyChanged(BR.averageSpeed);
    }

    @Bindable
    public long getDistance() {
        return this.distance;
    }

    public void setDistance(long distance) {
        this.distance = distance;
        notifyPropertyChanged(BR.distance);
    }

    @Bindable
    public float getAverageSpeed() {
        return this.average_speed;
    }

    @Bindable
    public long getTime() {
        return time;
    }

    public void setTime() {
        this.time++;
        notifyPropertyChanged(BR.time);
    }

    public void addPoint(double longitude, double latitude) {
        points.add(new Pair<>(longitude, latitude));
    }

    public void finishRide(Object activity) {
        if (activity instanceof BikeRideActivity) {
            timer.cancel();

            InsertRouteDataAsyncTask routeTask = new InsertRouteDataAsyncTask();
            InsertRideDataAsyncTask rideTask = new InsertRideDataAsyncTask();

            rideTask.execute(new BikeRideEntity(new Date(), distance, average_speed, time));
            try {
                rideTask.get();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }

            routeTask.execute(points);
            try {
                routeTask.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

            ((BikeRideActivity) activity).stopLocationListen();
            ((BikeRideActivity) activity).finish();
        }
    }
}
