package com.example.bikeride.viewModel;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.bikeride.BR;
import com.example.bikeride.database.asynctask.InsertDataAsyncTask;
import com.example.bikeride.database.entity.BikeRideEntity;
import com.example.bikeride.view.activity.BikeRideActivity;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;

public class BikeRideViewModel extends BaseObservable {
    private int counter;
    private float speed;
    private float average_speed;
    private long distance;
    private long time;

    private final Timer timer;

    public BikeRideViewModel() {
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

    public void finishRide(Object activity) {
        if (activity instanceof BikeRideActivity) {
            timer.cancel();
            InsertDataAsyncTask task = new InsertDataAsyncTask();
            task.execute(new BikeRideEntity(new Date(), distance, average_speed, time));
            try {
                task.get();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
            ((BikeRideActivity) activity).stopLocationListen();
            ((BikeRideActivity) activity).finish();
        }
    }
}
