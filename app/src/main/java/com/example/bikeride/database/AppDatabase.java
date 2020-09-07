package com.example.bikeride.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.bikeride.database.dao.impl.BikeRideDao;
import com.example.bikeride.database.dao.impl.RouteDao;
import com.example.bikeride.database.entity.RouteEntity;

@Database(entities = {
        BikeRideDao.class,
        RouteEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract BikeRideDao getBikeRideDao();
    public abstract RouteDao getRouteDao();
}
