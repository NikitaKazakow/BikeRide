package com.example.bikeride;

import android.app.Application;

import androidx.databinding.DataBindingUtil;
import androidx.room.Room;

import com.example.bikeride.database.AppDatabase;
import com.example.bikeride.databinding.AppDataBindingComponent;

public class App extends Application {

    public static App instance;

    private AppDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();

        DataBindingUtil.setDefaultComponent(new AppDataBindingComponent());

        instance = this;
        database = Room.databaseBuilder(this, AppDatabase.class, "database").build();
    }

    public static App getInstance() {
        return instance;
    }

    public AppDatabase getDatabase() {
        return database;
    }
}
