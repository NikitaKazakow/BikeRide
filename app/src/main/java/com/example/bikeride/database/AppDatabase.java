package com.example.bikeride.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.bikeride.database.dao.BikeRideDao;
import com.example.bikeride.database.dao.RouteDao;
import com.example.bikeride.database.entity.BikeRideEntity;
import com.example.bikeride.database.entity.RouteEntity;

@Database(entities = {
        BikeRideEntity.class, RouteEntity.class}, version = 1, exportSchema = false)
@TypeConverters(DateConverters.class)
public abstract class AppDatabase extends RoomDatabase {
    public abstract BikeRideDao getBikeRideDao();
    public abstract RouteDao getRouteDao();
}
