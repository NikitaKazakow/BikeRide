package com.example.bikeride.database.dao.impl;

import android.arch.persistence.room.Dao;

import com.example.bikeride.database.dao.BaseDao;
import com.example.bikeride.database.entity.RouteEntity;

@Dao
public abstract class RouteDao implements BaseDao<RouteEntity> {
    @Override
    public void insert(RouteEntity obj) {

    }

    @Override
    public void insert(RouteEntity[] obj) {

    }

    @Override
    public void update(RouteEntity obj) {

    }

    @Override
    public void delete(RouteEntity obj) {

    }
}
