package com.example.bikeride.databinding;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bikeride.BikeRideAdapter;
import com.example.bikeride.model.BikeRideModel;

import java.util.List;

public class RecyclerViewDataBinding {
    @BindingAdapter({"app:adapter", "app:data"})
    public void bind(@NonNull RecyclerView recyclerView, BikeRideAdapter adapter, List<BikeRideModel> data) {
        recyclerView.setAdapter(adapter);
        adapter.updateData(data);
    }
}
