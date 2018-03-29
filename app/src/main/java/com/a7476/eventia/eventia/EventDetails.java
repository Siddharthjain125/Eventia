package com.a7476.eventia.eventia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class EventDetails extends AppCompatActivity {

    private TextView tw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        Event event = (Event) getIntent().getSerializableExtra("eventData");
        Toast.makeText(this,event.getName(),Toast.LENGTH_LONG).show();

        tw = (TextView) findViewById(R.id.name);
        tw.setText(event.getName());

        tw = (TextView) findViewById(R.id.CatDetails);
        tw.setText(event.getCategory());

        tw = (TextView) findViewById(R.id.DateDetails);
        tw.setText(event.getDate());

        tw = (TextView) findViewById(R.id.TimeDetails);
        tw.setText(event.getTime());

        tw = (TextView) findViewById(R.id.VenueDetails);
        tw.setText(event.getVenue());

        tw = (TextView) findViewById(R.id.CityDetails);
        tw.setText(event.getCity());

        tw = (TextView) findViewById(R.id.Desc);
        tw.setText(event.getDescription());
    }
}
