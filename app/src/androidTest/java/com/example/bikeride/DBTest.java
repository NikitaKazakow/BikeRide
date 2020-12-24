package com.example.bikeride;

import android.content.Context;
import android.util.Pair;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import com.example.bikeride.database.AppDatabase;
import com.example.bikeride.database.asynctask.InsertRideDataAsyncTask;
import com.example.bikeride.database.asynctask.InsertRouteDataAsyncTask;
import com.example.bikeride.database.dao.BikeRideDao;
import com.example.bikeride.database.dao.RouteDao;
import com.example.bikeride.database.entity.BikeRideEntity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;


public class DBTest {
    private BikeRideDao bikeRideDao;
    private RouteDao routeDao;
    private AppDatabase db;

    private float speed;
    private float average_speed;
    private long distance;
    private long time;

    private List<Pair<Double, Double>> points;

    public DBTest() {
    }

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        bikeRideDao = db.getBikeRideDao();
        routeDao = db.getRouteDao();
        distance = 1200;
        average_speed = (float) 3.45;
        time = 200;
        points = new ArrayList<>();
    }
    @After
    public void closeDb() throws IOException {
        db.close();
    }
    @Test
    public void writeRoadEntity() throws Exception {
        Instant instant = Instant.ofEpochSecond( 1607623899 );

        InsertRideDataAsyncTask rideTask = new InsertRideDataAsyncTask();
        rideTask.execute(new BikeRideEntity(Date.from(instant), distance, average_speed, time));
        try {
            rideTask.get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

    }
    @Test
    public void writeRouteEntity() throws Exception {
        Instant instant = Instant.ofEpochSecond( 1607623899 );

        InsertRouteDataAsyncTask routeTask = new InsertRouteDataAsyncTask();
        points.add(new Pair<>(53.20377, 50.16061));
        points.add(new Pair<>(53.203805, 50.16059166666667));
        points.add(new Pair<>(53.20384, 50.16057));
        routeTask.execute(points);
        try {
            routeTask.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }

}
