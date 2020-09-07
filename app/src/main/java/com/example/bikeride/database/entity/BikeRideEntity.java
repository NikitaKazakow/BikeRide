package com.example.bikeride.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

@Entity(tableName = "cycling")
public class BikeRideEntity {
    @PrimaryKey(autoGenerate = true)
    long id;

    @NonNull
    Date date;

    @NonNull
    long distance;

    @NonNull
    int averageSpeed;

    @NonNull
    int maxSpeed;
}
