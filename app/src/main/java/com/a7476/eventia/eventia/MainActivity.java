package com.a7476.eventia.eventia;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
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


public class MainActivity extends AppCompatActivity{

    private List<Event> eventList = new ArrayList<>();


    private String sort;
    private String your_city;

    private RecyclerView recyclerView;
    private EventsAdapter mAdapter ;


    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private TextView print;
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("events");

    NavigationView navigationView;
    private FirebaseAuth auth;
    private String email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        auth = FirebaseAuth.getInstance();
        //retrieve user email
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        email = user.getEmail();
   //     Toast.makeText(this,"current user is "+email,Toast.LENGTH_LONG).show();

        setContentView(R.layout.activity_main);

        SharedPreferences prefs = getSharedPreferences("myprefs", Context.MODE_PRIVATE);
        your_city = prefs.getString("your_city",null);



        // Drawer Layout
        mDrawerLayout=(DrawerLayout) findViewById(R.id.drawerLayout);
        mToggle= new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Navigation Drawer

        navigationView = (NavigationView)findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected( MenuItem item) {
                // Handle navigation view item clicks here.
                switch (item.getItemId()) {

                    case R.id.nav_create: {

                        startActivity(new Intent(MainActivity.this, CreateEventActivity.class));
                        break;
                    }

                    case R.id.nav_account: {
                        Intent intent = new Intent(MainActivity.this, MyEvent.class);
                        startActivity(intent);
                        break;
                    }

                    case R.id.nav_City : {
                        startActivity(new Intent(MainActivity.this, FirstActivity.class));
                        break;
                    }

                    case R.id.Tech:{
                        startActivity(new Intent(MainActivity.this,Tech.class));
                        break;
                    }

                    case R.id.Sports:{
                        startActivity(new Intent(MainActivity.this,Sports.class));
                        break;
                    }

                    case R.id.Travel:{
                        startActivity(new Intent(MainActivity.this,Travel.class));
                        break;
                    }

                    case R.id.Hobbies:{

                        startActivity(new Intent(MainActivity.this,Hobbies.class));
                        break;
                    }
                    case R.id.logout : {
                        auth.signOut();
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                        finish();
                    }

                }
                //close navigation drawer
                mDrawerLayout.closeDrawers();
                return true;
            }
        });



        //recyclerview



   //     setContentView(R.layout.activity_main);


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new EventsAdapter(eventList);




        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL, 16));

        recyclerView.setAdapter(mAdapter);

        myclicker();


        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                eventList.clear();
             for (com.google.firebase.database.DataSnapshot ds : dataSnapshot.getChildren()){
                 Event event = ds.getValue(Event.class);
                 if (event.getCity().equals(your_city)) {
                     eventList.add(event);
                 }
             }
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    //click listener
    void myclicker(){
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Event event = eventList.get(position);
                Toast.makeText(getApplicationContext(), event.getName() + " is selected!", Toast.LENGTH_SHORT).show();

                // intent

                Intent intent = new Intent(MainActivity.this, EventDetails.class);
                intent.putExtra("eventData",event);
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

}
