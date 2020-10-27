package com.example.bikeride.view.activity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.bikeride.AppLocationListener;
import com.example.bikeride.LocationListenerInterface;
import com.example.bikeride.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BikeRideActivity extends AppCompatActivity implements OnMapReadyCallback, LocationListenerInterface {

    private static final int LOCATION_REQUEST = 1;

    private static boolean locationPermissionGranted;

    private GoogleMap map;
    private LocationManager locationManager;
    private Location lastLocation;
    private long distance;
    private AppLocationListener appLocationListener;

    private List<LatLng> routePointsList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bike_ride);

        initialize();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        Objects.requireNonNull(mapFragment).getMapAsync(this);
    }

    private void initialize(){
        locationPermissionGranted = false;
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

        appLocationListener = new AppLocationListener();
        appLocationListener.setLocationListenerInterface(this);

        routePointsList = new ArrayList<>();
        //Привязать TextView
    }

    private void updateLocationUI() {
        if (map == null) {
            return;
        }
        try {
            if (locationPermissionGranted) {
                //TODO настроить внешний вид карты (убрать или добвить кнопки, настроить поворот камеры в сторону движения и всякое такое). Код ниже чисто для теста

                map.setMyLocationEnabled(true);
                map.getUiSettings().setMyLocationButtonEnabled(true);

                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,1000,5,appLocationListener);
            }
            else {
                //TODO Пользователь не дал разрешения. Сделать окно ошибки (или бескончено спрашивать разрешения) пока что бесконечный запрос разрешений
                checkPermissions();
            }
        } catch (SecurityException e)  {
            Log.e("Exception: %s", e.getMessage());
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case LOCATION_REQUEST: {
                if (grantResults.length == 2 && grantResults[0] == RESULT_OK && grantResults[1] == RESULT_OK)
                    locationPermissionGranted = true;
            }
        }
        updateLocationUI();
    }

    private void drawRoute() {
        final PolylineOptions polylineOptions = new PolylineOptions();
        for (LatLng point : routePointsList) {
            polylineOptions.add(point);
        }

        //TODO настроить красивый цвет и толщину линии отрисовки маршрута (можно еще оптимизировать как-нибудь, чтобы меньше операций было для более быстрой отрисовки)
        polylineOptions.width(5).color(Color.RED);

        map.addPolyline(polylineOptions);

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(routePointsList.get(routePointsList.size() - 1), 15));
    }

    private void checkPermissions(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED)
            requestPermissions(new String []
                    { Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION }, LOCATION_REQUEST);
        else {
            locationPermissionGranted = true;
            updateLocationUI();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        if (location.hasSpeed() && lastLocation != null) {
            distance += lastLocation.distanceTo(location);
            routePointsList.add(new LatLng(location.getLatitude(), location.getLongitude()));
            drawRoute();
        }
        lastLocation = location;
        //textViewDistance.setText(String.valueOf(distance));
        //textViewSpeed.setText(String.valueOf(location.getSpeed()));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.map = googleMap;
        checkPermissions();
    }
}