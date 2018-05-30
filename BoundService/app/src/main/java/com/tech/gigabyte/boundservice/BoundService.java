package com.tech.gigabyte.boundservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by GIGABYTE on 6/5/2017.
 */

public class BoundService extends Service {

    //Base interface for a remotable object, the core part of a lightweight remote procedure call mechanism
    private final IBinder myBinder = new MyLocalBinder();

    //Binding Service
    @Override
    public IBinder onBind(Intent arg0) {
        // TODO Auto-generated method stub
        return myBinder;
    }

    //Formatting and parsing dates in a locale-sensitive manner
    public String getCurrentTime() {
        SimpleDateFormat dateformat =
                new SimpleDateFormat("HH:mm:ss  dd/MMM/yyyy", Locale.US);
        return (dateformat.format(new Date()));
    }

    class MyLocalBinder extends Binder {
        BoundService getService() {
            return BoundService.this;
        }
    }
}