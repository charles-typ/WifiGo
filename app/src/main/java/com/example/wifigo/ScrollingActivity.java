package com.example.wifigo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class ScrollingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Testing message, nothing is implemented.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Button butt = findViewById(R.id.button);
        butt.setOnClickListener(new View.OnClickListener(){
            @Override

            public void onClick(View V){
                openMap();
            }
        });
    }

    public void openMap(){
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }
}
