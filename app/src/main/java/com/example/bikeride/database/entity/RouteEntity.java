package com.example.bikeride.database.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

@Entity(foreignKeys = @ForeignKey(entity = BikeRideEntity.class,
        parentColumns = "id",
        childColumns = "bike_ride_id",
        onDelete = CASCADE))
public class RouteEntity {
    @PrimaryKey(autoGenerate = true)
    long id;

    @NonNull
    long latitude; //Широта

    @NonNull
    long longitude; //Долгота

    @NonNull
    @ColumnInfo(name = "bike_ride_id")
    long bikeRideId;
}
