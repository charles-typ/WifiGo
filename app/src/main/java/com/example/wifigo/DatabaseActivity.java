package com.example.wifigo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.app.Activity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.Query;
import com.google.firebase.database.ChildEventListener;
import android.widget.Toast;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiInfo;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.Manifest.permission;
import java.util.jar.Manifest;
import android.net.wifi.SupplicantState;


public class DatabaseActivity extends AppCompatActivity {
    private static final String TAG = "DatabaseActivity";
    private DatabaseReference mDatabase;
    private WifiManager wifimg;
    private WifiInfo wifiin;
    private static double max_x;
    private static double max_y;
    protected static Integer rssi;
    protected  static String ssid;
    private static final int LOCATION = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        setContentView(R.layout.activity_main);
    }
    @Override
    protected void onStart() {
        super.onStart();
        tryToReadSSID();
        basicReadWrite();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if(grantResults[0] == PackageManager.PERMISSION_GRANTED && requestCode == LOCATION){
            //User allowed the location and you can read it now
            tryToReadSSID();
        }
    }

    private void tryToReadSSID() {
        //If requested permission isn't Granted yet
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //Request permission from user
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION);
        }else{//Permission already granted
            wifimg = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
            wifiin= wifimg.getConnectionInfo();
            if(wifiin.getSupplicantState() == SupplicantState.COMPLETED){
                String ssid = wifiin.getSSID();//Here you can access your SSID
                System.out.println(ssid);
            }
        }
    }


    public void basicReadWrite() {
        // FIXME
        //wifimg = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        //wifiin = wifimg.getConnectionInfo();
        Double x = 1.0;
        Double y = 1.0;
        ssid = wifiin.getSSID();
        rssi = wifiin.getRssi();
        Integer speed = wifiin.getLinkSpeed();
        Data instance = new Data(x, y, rssi, ssid, speed);
        mDatabase.child(ssid).setValue(instance);


        Query myTopPostsQuery = mDatabase.orderByChild("strength");
        myTopPostsQuery.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                Log.d(TAG, "onChildAdded:" + dataSnapshot.getKey());
                // A new comment has been added, add it to the displayed list
                Data newData = dataSnapshot.getValue(Data.class);
                if (newData.strength > rssi) {
                    max_x = newData.x;
                    max_y = newData.y;
                } else {
                    Log.d(TAG, "Should change new network");
                }
                Intent returnIntent = new Intent();
                String result = Double.toString(max_x) + "_" + Double.toString(max_y);
                returnIntent.putExtra("result", result);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
                Log.d(TAG, "onChildChanged:" + dataSnapshot.getKey());
                // A new comment has been added, add it to the displayed list
                Data newData = dataSnapshot.getValue(Data.class);
                if (newData.strength > rssi) {
                    max_x = newData.x;
                    max_y = newData.y;
                } else {
                    Log.d(TAG, "Should change new network");
                }
                Intent returnIntent = new Intent();
                String result = Double.toString(max_x) + "_" + Double.toString(max_y);
                returnIntent.putExtra("result", result);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.d(TAG, "onChildRemoved:" + dataSnapshot.getKey());
                // A new comment has been added, add it to the displayed list
                Data newData = dataSnapshot.getValue(Data.class);
                if (newData.strength > rssi) {
                    max_x = newData.x;
                    max_y = newData.y;
                } else {
                    Log.d(TAG, "Should change new network");
                }
                Intent returnIntent = new Intent();
                String result = Double.toString(max_x) + "_" + Double.toString(max_y);
                returnIntent.putExtra("result", result);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
                Log.d(TAG, "onChildMoved:" + dataSnapshot.getKey());
                // A new comment has been added, add it to the displayed list
                Data newData = dataSnapshot.getValue(Data.class);
                if (newData.strength > rssi) {
                    max_x = newData.x;
                    max_y = newData.y;
                } else {
                    Log.d(TAG, "Should change new network");
                }
                Intent returnIntent = new Intent();
                String result = Double.toString(max_x) + "_" + Double.toString(max_y);
                returnIntent.putExtra("result", result);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "postComments:onCancelled", databaseError.toException());
                Intent returnIntent = new Intent();
                String result = Double.toString(max_x) + "_" + Double.toString(max_y);
                returnIntent.putExtra("result", result);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        });
    }
}
