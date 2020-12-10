package com.example.bikeride.viewModel;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.bikeride.BR;
import com.example.bikeride.BikeRideAdapter;
import com.example.bikeride.database.asynctask.GetBikeRidesListAsyncTask;
import com.example.bikeride.database.entity.BikeRideEntity;
import com.example.bikeride.model.BikeRideModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainViewModel extends BaseObservable {

    private BikeRideAdapter adapter;
    private List<BikeRideModel> data;

    public MainViewModel() {
        data = new ArrayList<>();
        adapter = new BikeRideAdapter();
    }

    @Bindable
    public List<BikeRideModel> getData() {
        return this.data;
    }

    @Bindable
    public BikeRideAdapter getAdapter() {
        return this.adapter;
    }


    void populateData() {
        GetBikeRidesListAsyncTask task = new GetBikeRidesListAsyncTask();
        task.execute();
        List<BikeRideEntity> dataList = null;
        try {
            dataList = task.get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        if (dataList != null && !dataList.isEmpty()) {
            data.clear();
            for (BikeRideEntity entity : dataList) {
                BikeRideModel model = new BikeRideModel(entity.getDate(), entity.getDistance(), entity.getAverageSpeed(), entity.getRideTime());
                data.add(model);
            }
        }
        notifyPropertyChanged(BR.data);
    }

    public void setUp() {
        populateData();
    }
}