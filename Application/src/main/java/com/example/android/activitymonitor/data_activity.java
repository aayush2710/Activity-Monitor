package com.example.android.activitymonitor;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.activitymonitor.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static java.lang.Math.floor;

public class data_activity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private CardView cardView , cardView2;
    private TextView Sit , still , walk , run;

    private Button button;
    private SharedPreferences sharedPref;
    private DatabaseReference ref;
    private FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.content_data_activity);
            //Toolbar toolbar = findViewById(R.id.toolbar);
            //setSupportActionBar(toolbar);
            Sit = (TextView) findViewById(R.id.sitting_time) ;
            still = (TextView) findViewById(R.id.standing_time) ;
            walk = (TextView) findViewById(R.id.walking_time) ;
            run = (TextView) findViewById(R.id.running_time) ;
            cardView = (CardView) findViewById(R.id.close);
            sharedPref = data_activity.this.getPreferences(Context.MODE_PRIVATE);
            database = FirebaseDatabase.getInstance();
            ref = database.getReference();
            Refresh();
        database = FirebaseDatabase.getInstance();
        ref = database.getReference();
            ref.orderByValue().addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    System.out.print("TMKC");
                    //hm.put(dataSnapshot.getKey(), Integer.parseInt(dataSnapshot.getValue().toString()));

                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putInt(dataSnapshot.getKey(), Integer.parseInt(dataSnapshot.getValue().toString()));
                    editor.commit();

                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    // hm.put(dataSnapshot.getKey(), Integer.parseInt(dataSnapshot.getValue().toString()));
                    //SharedPreferences sharedPref = DeviceControlActivity.this.getPreferences(Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putInt(dataSnapshot.getKey(), Integer.parseInt(dataSnapshot.getValue().toString()));
                    editor.commit();
                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                    //hm.put(dataSnapshot.getKey(), Integer.parseInt(dataSnapshot.getValue().toString()));
                    //SharedPreferences sharedPref = DeviceControlActivity.this.getPreferences(Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putInt(dataSnapshot.getKey(), Integer.parseInt(dataSnapshot.getValue().toString()));
                    editor.commit();
                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    //  hm.put(dataSnapshot.getKey(), Integer.parseInt(dataSnapshot.getValue().toString()));
                    //SharedPreferences sharedPref = DeviceControlActivity.this.getPreferences(Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putInt(dataSnapshot.getKey(), Integer.parseInt(dataSnapshot.getValue().toString()));
                    editor.commit();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    System.out.print("TMK2C");


                }
            });


            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });

            cardView2 = (CardView) findViewById(R.id.refresh);
            cardView2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Refresh();
                }
            });
        }
        private void Refresh(){
            double value = sharedPref.getInt("Staying still", 0)/40.0;
            double value2 = sharedPref.getInt("Sitting", 0)/40.0;
            double value3 = sharedPref.getInt("Running", 0)/40.0;
            double value4 = sharedPref.getInt("Walking", 0)/40.0;

            Sit.setText((Double.toString(floor(value2/60)) + " Hrs " + Double.toString((value2 - 60*floor(value2/60))) + " mins"));
            still.setText((Double.toString(floor(value/60)) + " Hrs " + Double.toString((value - 60*floor(value/60)))+ " mins"));
            walk.setText((Double.toString(floor(value4/60)) +  " Hrs " + Double.toString((value4 - 60*floor(value4/60))) + " mins"));
            run.setText((Double.toString(floor(value3/60)) + " Hrs " + Double.toString((value3 - 60*floor(value3/60))) + " mins"));



        }

}
