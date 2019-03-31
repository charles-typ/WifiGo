package com.example.wifigo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import com.github.jorgecastilloprz.FABProgressCircle;
import com.github.jorgecastilloprz.listeners.FABProgressListener;

public class HomeActivity extends AppCompatActivity {

    public Double x;
    public Double y;
    public static FABProgressCircle fabView;
//
//    public HomeActivity() {
//        fabView = (FABProgressCircle) findViewById(R.id.fabProgressCircle);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Testing message, nothing is implemented.", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        fabView = (FABProgressCircle) findViewById(R.id.fabProgressCircle);

        fabView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                fabView.setEnabled(false);
                fabView.show();
                waitForResult();
            }
        });

//        ProgressBar progress = findViewById(R.id.progressBar);
//        progress.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View V){
//                openMap();
//            }
//        });

//        Button butt = findViewById(R.id.button);
//        butt.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View V){
//                openMap();
//            }
//        });
    }

    public void openMap(){
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

//    final FABProgressCircle fabView = (FABProgressCircle) findViewById(R.id.fabProgressCircle);
    public void waitForResult(){
        int SPLASH_TIME_OUT = 2000;
        Intent intent = new Intent(this, DatabaseActivity.class);
        startActivityForResult(intent, 1);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent Intent = new Intent(HomeActivity.this, MapsActivity.class);
                fabView.beginFinalAnimation();
                startActivity(Intent);
                finish();
            }
        }, SPLASH_TIME_OUT);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == 1) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                // The user picked a contact.
                // The Intent's data Uri identifies which contact was selected.
                String ret = data.getStringExtra("result");
                String[] output = ret.split("_");
                x = Double.parseDouble(output[0]);
                y = Double.parseDouble(output[1]);
            }
        }
    }
}
