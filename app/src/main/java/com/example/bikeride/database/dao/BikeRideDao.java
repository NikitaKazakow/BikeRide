package com.example.bikeride.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.bikeride.database.entity.BikeRideEntity;

import java.util.Date;
import java.util.List;

@Dao
public interface BikeRideDao {
    @Insert
    void insert(BikeRideEntity obj);

    @Query("SELECT * FROM cycling ORDER BY date DESC")
    List<BikeRideEntity> getBikeRides();

    @Query("SELECT * FROM cycling ORDER BY id DESC LIMIT 1")
    BikeRideEntity getLastRide();

    @Query("SELECT * FROM cycling WHERE date = :date")
    BikeRideEntity getRideByDate(Date date);
}
