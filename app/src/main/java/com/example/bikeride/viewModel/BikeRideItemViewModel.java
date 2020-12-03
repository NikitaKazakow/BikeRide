package com.example.bikeride.viewModel;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.bikeride.model.BikeRideModel;

import java.util.Date;

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
}
