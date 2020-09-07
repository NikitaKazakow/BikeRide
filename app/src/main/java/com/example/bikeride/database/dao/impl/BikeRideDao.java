package com.example.bikeride.database.dao.impl;

import android.arch.persistence.room.Dao;

import com.example.bikeride.database.dao.BaseDao;
import com.example.bikeride.database.entity.BikeRideEntity;

@Dao
public abstract class BikeRideDao implements BaseDao<BikeRideEntity> {
    @Override
    public void insert(BikeRideEntity obj) {

    }

    @Override
    public void insert(BikeRideEntity[] obj) {

    }

    @Override
    public void update(BikeRideEntity obj) {

    }

    @Override
    public void delete(BikeRideEntity obj) {

    }
}
