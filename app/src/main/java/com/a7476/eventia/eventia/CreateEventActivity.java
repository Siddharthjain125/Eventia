package com.a7476.eventia.eventia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.security.PublicKey;

public class CreateEventActivity extends AppCompatActivity {

    private EditText event_name,event_venue,event_city ;
    private Spinner event_category ;
    private EditText event_date ;
    private EditText event_time  ;
    private EditText event_description;
    private Button create_btn ;

    DatabaseReference databaseEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        databaseEvent = FirebaseDatabase.getInstance().getReference("events");

        event_name = (EditText) findViewById(R.id.name);
        event_category= (Spinner) findViewById(R.id.catagory);
        event_date = (EditText) findViewById(R.id.date) ;
        event_description =  (EditText) findViewById(R.id.description) ;
        event_time = (EditText) findViewById(R.id.time) ;
        create_btn = (Button) findViewById(R.id.btn_create);
        event_venue = (EditText) findViewById(R.id.venue);
        event_city = (EditText) findViewById(R.id.city);

        event_city.setFilters(new InputFilter[]{new InputFilter.AllCaps()});

        create_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
             addEvent();

            }
        });
    }

    private void addEvent(){
        String name = event_name.getText().toString().trim();
        String category = event_category.getSelectedItem().toString();
        String date = event_date.getText().toString().trim();
        String time = event_time.getText().toString().trim();
        String description = event_description.getText().toString().trim();
        String venue = event_venue.getText().toString().trim();
        String city = event_city.getText().toString().trim();



        String id = databaseEvent.push().getKey();
        Event event = new Event(id,name,category,date,time,description,venue,city);

        databaseEvent.child(id).setValue(event);
        Toast.makeText(this,"Event Created",Toast.LENGTH_LONG).show();


    }
}
