package com.example.bikeride.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Update;

@Dao
public interface BaseDao<T> {
    @Insert
    void insert(T obj);

    @Insert
    void insert(T[] obj);

    @Update
    void update(T obj);

    @Delete
    void delete(T obj);
}
