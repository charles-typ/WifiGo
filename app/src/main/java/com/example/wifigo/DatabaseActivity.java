package com.example.wifigo;

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

public class DatabaseActivity extends AppCompatActivity {
    private static final String TAG = "DatabaseActivity";
    private DatabaseReference mDatabase;
    private WifiManager wifimg;
    private WifiInfo wifiin;
    private static double max_x;
    private static double max_y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        basicReadWrite();
    }
    public void basicReadWrite() {
        // FIXME
        Double x = 1.0;
        Double y = 1.0;
        String ssid = wifiin.getSSID();
        final int rssi = wifiin.getRssi();
        int speed = wifiin.getLinkSpeed();
        Data instance = new Data(x, y, rssi, ssid, speed);
        mDatabase.child(ssid).setValue(instance);


        Query myTopPostsQuery = mDatabase.child(ssid).orderByChild("strength");
        myTopPostsQuery.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                Log.d(TAG, "onChildAdded:" + dataSnapshot.getKey());
                // A new comment has been added, add it to the displayed list
                Data value = dataSnapshot.getValue(Data.class);
                if (value.strength > rssi) {
                    max_x = value.x;
                    max_y = value.y;
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
                Data value = dataSnapshot.getValue(Data.class);
                if (value.strength > rssi) {
                    max_x = value.x;
                    max_y = value.y;
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
                Data value = dataSnapshot.getValue(Data.class);
                if (value.strength > rssi) {
                    max_x = value.x;
                    max_y = value.y;
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
                Data value = dataSnapshot.getValue(Data.class);
                if (value.strength > rssi) {
                    max_x = value.x;
                    max_y = value.y;
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
