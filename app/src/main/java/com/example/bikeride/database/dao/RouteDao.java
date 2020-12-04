package com.example.bikeride.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.bikeride.database.entity.RouteEntity;

import java.util.List;

@Dao
public interface RouteDao {
    @Insert
    void insert(RouteEntity obj);

    @Query("SELECT * FROM routes WHERE bike_ride_id = :id")
    List<RouteEntity> getRouteByRideId(long id);
}
