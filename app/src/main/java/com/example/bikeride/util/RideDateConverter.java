package com.example.bikeride.util;

import androidx.annotation.NonNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class RideDateConverter {
    @NonNull
    public static String convert(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy k:mm", Locale.ROOT);
        return dateFormat.format(date);
    }
}
