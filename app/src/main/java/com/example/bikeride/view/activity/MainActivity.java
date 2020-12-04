package com.example.bikeride.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bikeride.R;
import com.example.bikeride.databinding.ActivityMainBinding;
import com.example.bikeride.viewModel.MainViewModel;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = bind();
        initRecycleView(view);
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.setUp();
    }

    private void initRecycleView(@NonNull View view) {
        RecyclerView recyclerView = view.findViewById(R.id.ride_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
    }

    @NonNull
    private View bind() {
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = new MainViewModel();
        binding.setViewModel(viewModel);
        binding.startRideFab.setOnClickListener(this);
        return binding.getRoot();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.start_ride_fab) {
            startActivity(new Intent(this, BikeRideActivity.class));
        }
    }

    @Override
    public void onBackPressed() {
        //TODO Закрытие приложения
        System.exit(0);
    }
}