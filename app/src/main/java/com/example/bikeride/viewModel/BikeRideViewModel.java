package com.example.bikeride.viewModel;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.bikeride.BR;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;


@FieldDefaults(level = AccessLevel.PRIVATE)
public class BikeRideViewModel extends BaseObservable {

    private final List<Float> speedList;
    private float speed;
    private float average_speed;
    private long distance;
    private long time;

    public BikeRideViewModel() {

        this.speed = 0;
        this.distance = 0;
        this.average_speed = 0;
        this.time = 0;

        this.speedList = new ArrayList<>();

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                setTime();
            }
        }, 0, 1000);
    }

    private float average() {
        float sum = 0;
        float count = 0;
        for (float val: speedList) {
            sum += val;
            count++;
        }
        return  sum / count;
    }

    @Bindable
    public float getSpeed() {
        return this.speed;
    }

    public void setSpeed(float speed) {

        this.speed = speed;

        this.speedList.add(speed);
        this.average_speed = average();

        notifyPropertyChanged(BR.speed);
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
        return this.time;
    }

    public void setTime() {
        this.time++;
        notifyPropertyChanged(BR.time);
    }
}
