package com.example.bikeride.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

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
