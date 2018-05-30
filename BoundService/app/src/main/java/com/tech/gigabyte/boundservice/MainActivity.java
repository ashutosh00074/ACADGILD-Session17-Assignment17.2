package com.tech.gigabyte.boundservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    BoundService myService;
    boolean isBound = false;

    TextView currenttime, time, date;
    Button btn_start_service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set the activity content from a layout resource
        setContentView(R.layout.activity_main);
        time = (TextView) findViewById(R.id.tv_time);
        date = (TextView) findViewById(R.id.tv_date);
        currenttime = (TextView) findViewById(R.id.textView_current_time);
        btn_start_service = (Button) findViewById(R.id.button_start_service);
        Intent intent = new Intent(MainActivity.this, BoundService.class);

        /*
        Connect to an application service
        Automatically create the service as long as the binding exists
        */
        bindService(intent, myConnection, Context.BIND_AUTO_CREATE);
    }

    public void getcurrenttime(View view) {
        currenttime.setText(myService.getCurrentTime());
        time.setVisibility(View.VISIBLE);
        date.setVisibility(View.VISIBLE);
    }

    //Setting Up BoundService
    private ServiceConnection myConnection = new ServiceConnection() {

        //Service Connected
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            BoundService.MyLocalBinder binder = (BoundService.MyLocalBinder) service;
            myService = binder.getService();
            isBound = true;
        }

        //Service is Disconnected
        public void onServiceDisconnected(ComponentName arg0) {
            isBound = false;
        }

    };

}