package com.example.bikeride;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class RideActivity extends AppCompatActivity implements LocationListenerInterface {

    private LocationManager locationManager;
    private Location lastLocation;
    private long distance;
    private TextView textViewDistance, textViewSpeed;
    private AppLocationListener appLocationListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_bike_ride);
        init();
    }
    private void init(){
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        appLocationListener = new AppLocationListener();
        appLocationListener.setLocationListenerInterface(this);
        checkPermissions();
        //Привязать TextView
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 201 && grantResults[0]== RESULT_OK)
            checkPermissions();
        // Добавить диалог об ошибке
    }

    private void checkPermissions(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION},201);
        else
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,1000,5,appLocationListener);
    }

    @Override
    public void onLocationChanged(Location location) {
        if(location.hasSpeed() && lastLocation != null)
            distance += lastLocation.distanceTo(location);
        lastLocation = location;
        textViewDistance.setText(String.valueOf(distance));
        textViewSpeed.setText(String.valueOf(location.getSpeed()));
    }
}
