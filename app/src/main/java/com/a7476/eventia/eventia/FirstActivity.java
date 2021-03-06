package com.a7476.eventia.eventia;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


public class FirstActivity extends AppCompatActivity {

    private Spinner your_city;
    private Button insert_city;
    SharedPreferences sharedpreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        insert_city = (Button)findViewById(R.id.insert_city);
        your_city = (Spinner) findViewById(R.id.your_city);
//        your_city.setFilters(new InputFilter[]{new InputFilter.AllCaps()});



        insert_city.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String city = your_city.getSelectedItem().toString().trim();

                sharedpreferences = getSharedPreferences("myprefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.putString("your_city", city).commit();

                Intent intent = new Intent(FirstActivity.this, MainActivity.class);
                startActivity(intent);
                finish();



            }
        });




    }
}
