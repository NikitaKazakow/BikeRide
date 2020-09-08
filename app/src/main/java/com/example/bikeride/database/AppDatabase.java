package com.example.bikeride.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.bikeride.database.dao.impl.BikeRideDao;
import com.example.bikeride.database.dao.impl.RouteDao;
import com.example.bikeride.database.entity.BikeRideEntity;
import com.example.bikeride.database.entity.RouteEntity;

@Database(entities = {
        BikeRideEntity.class,
        RouteEntity.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract BikeRideDao getBikeRideDao();
    public abstract RouteDao getRouteDao();
}
