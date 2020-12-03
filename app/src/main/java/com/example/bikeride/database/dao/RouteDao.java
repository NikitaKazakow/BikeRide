package com.example.bikeride.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.bikeride.database.entity.RouteEntity;

import java.util.List;

@Dao
public interface RouteDao {
    @Insert
    void insert(RouteEntity obj);

    @Insert
    void insert(RouteEntity[] obj);

    @Update
    void update(RouteEntity obj);

    @Delete
    void delete(RouteEntity obj);

    @Query("SELECT * FROM routes")
    List<RouteEntity> getBikeRides();
}
