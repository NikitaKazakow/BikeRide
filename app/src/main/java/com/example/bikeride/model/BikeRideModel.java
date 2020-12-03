package com.example.bikeride.model;


import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class BikeRideModel {
    long id;

    @NonNull
    Date date;

    @NonNull
    long distance;

    @NonNull
    float averageSpeed;

    @NonNull
    long rideTime;
}
