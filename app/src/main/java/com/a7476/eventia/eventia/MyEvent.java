package com.a7476.eventia.eventia;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MyEvent extends AppCompatActivity {

    private List<Event> myList = new ArrayList<>();
    private EventsAdapter myAdapter;

    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("events");
    private FirebaseAuth auth;
    private String email;
    private String your_city;
    private RecyclerView recyclerView;

    private Event event;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_event);


        myAdapter = new EventsAdapter(myList);

        auth = FirebaseAuth.getInstance();
        //retrieve user email
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        email = user.getEmail();


        SharedPreferences prefs = getSharedPreferences("myprefs", Context.MODE_PRIVATE);
        your_city = prefs.getString("your_city",null);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        myAdapter = new EventsAdapter(myList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL, 16));

        recyclerView.setAdapter(myAdapter);


        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Event event = myList.get(position);
                Toast.makeText(getApplicationContext(), event.getName() + " is selected!", Toast.LENGTH_SHORT).show();

                // intent

                Intent intent = new Intent(MyEvent.this, EventDetails.class);
                intent.putExtra("eventData",event);
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {
                final Event event = myList.get(position);
                showUpdateDelete(event);
            }
        }));

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    Event event = ds.getValue(Event.class);
                    if(event.getHost().equals(email)){
                        myList.add(event);
                    }
                }
                myAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }
    private void showUpdateDelete(Event event){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_box,null);
        dialogBuilder.setView(dialogView);
        final Button dialogUpdate = (Button) dialogView.findViewById(R.id.dialogUpdate);
        final Button dialogDelete = (Button) dialogView.findViewById(R.id.dialogDelete);

        dialogUpdate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){


                Intent intent = new Intent(MyEvent.this, UpdateEvent.class);
               // intent.putExtra("eventData",event);
                startActivity(intent);
            }
        });

        dialogDelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
             //   deleteEvent(event);
            }
        });

        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
    }

}
