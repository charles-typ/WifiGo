package com.example.wifigo;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
//import android.support.annotation.NonNull;
//import android.text.TextUtils;
//import android.util.Log;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;

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

            public void onClick(View V) {
                opendatabase();
            }
            //public void onClick(View V){
             //   openMap();
            //}
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
    }


    public void openMap(){
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }
    public void opendatabase(){
        Intent intent = new Intent(this, DatabaseActivity.class);
        while(true) {
            double[] args = {0.1, 0.1};
            intent.putExtra("location", args);
            startActivityForResult(intent, 1);
            finishActivity(1);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == 1) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                // The user picked a contact.
                // The Intent's data Uri identifies which contact was selected.
                String ret = data.getDataString();
                String[] output = ret.split("_");
                //TODO add UI logic here
                // Do something with the contact here (bigger example below)
            }
        }
    }
}
