package com.example.bikeride.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.example.bikeride.database.DateConverters;

import java.util.Date;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity(tableName = "cycling")
public class BikeRideEntity {
    @PrimaryKey(autoGenerate = true)
    long id;

    @NonNull
    @TypeConverters({DateConverters.class})
    Date date;

    @NonNull
    long distance;

    @NonNull
    int averageSpeed;

    @NonNull
    int maxSpeed;
}
