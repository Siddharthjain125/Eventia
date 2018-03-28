package com.a7476.eventia.eventia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class EventDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        Log.d("problem","detail oncreate log");
        Event event = (Event) getIntent().getSerializableExtra("eventData");
        Toast.makeText(this,event.getName(),Toast.LENGTH_LONG).show();

        TextView tw = (TextView) findViewById(R.id.name);
        tw.setText(event.getCategory());
    }
}
