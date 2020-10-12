package com.example.bikeride.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bikeride.database.entity.BikeRideEntity;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;


@FieldDefaults(level = AccessLevel.PRIVATE)
public class BikeRideViewModel extends ViewModel {

    public LiveData<Long> distance;
}
