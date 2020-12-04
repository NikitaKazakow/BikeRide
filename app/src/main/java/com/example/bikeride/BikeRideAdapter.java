package com.example.bikeride;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bikeride.databinding.BikeRideItemBinding;
import com.example.bikeride.model.BikeRideModel;
import com.example.bikeride.viewModel.BikeRideItemViewModel;

import java.util.LinkedList;
import java.util.List;

public class BikeRideAdapter extends RecyclerView.Adapter<BikeRideAdapter.BikeRideViewHolder> {

    private final List<BikeRideModel> data;

    public BikeRideAdapter() {
        this.data = new LinkedList<>();
    }

    @NonNull
    @Override
    public BikeRideViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bike_ride_item, new LinearLayout(parent.getContext()), false);
        return new BikeRideViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BikeRideViewHolder holder, int position) {
        BikeRideModel bikeRideModel = data.get(position);
        holder.setViewModel(new BikeRideItemViewModel(bikeRideModel));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onViewAttachedToWindow(@NonNull BikeRideViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        holder.bind();
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull BikeRideViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.unbind();
    }

    public void updateData(@Nullable List<BikeRideModel> data) {
        if (data == null || data.isEmpty()) {
            this.data.clear();
        }
        else {
            this.data.clear();
            this.data.addAll(data);
        }
        notifyDataSetChanged();
    }

     static class BikeRideViewHolder extends RecyclerView.ViewHolder {

        BikeRideItemBinding binding;

        BikeRideViewHolder(View itemView) {
            super(itemView);
            bind();
        }

        void bind() {
            if (binding == null) {
                binding = DataBindingUtil.bind(itemView);
            }
        }

        void unbind() {
            if (binding != null) {
                binding.unbind();
            }
        }

        void setViewModel(BikeRideItemViewModel viewModel) {
            if (binding != null) {
                binding.setViewModel(viewModel);
            }
        }
    }
}
