package com.example.bikeride.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.bikeride.database.entity.BikeRideEntity;

import java.util.List;

@Dao
public interface BikeRideDao {
    @Insert
    void insert(BikeRideEntity obj);

    @Insert
    void insert(BikeRideEntity[] obj);

    @Update
    void update(BikeRideEntity obj);

    @Delete
    void delete(BikeRideEntity obj);

    @Query("SELECT * FROM cycling")
    List<BikeRideEntity> getBikeRides();
}
