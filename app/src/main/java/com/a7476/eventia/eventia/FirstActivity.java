package com.a7476.eventia.eventia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class FirstActivity extends AppCompatActivity {

    private EditText your_city;
    private Button insert_city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        insert_city = (Button)findViewById(R.id.insert_city);
        your_city = (EditText)findViewById(R.id.your_city);
        your_city.setFilters(new InputFilter[]{new InputFilter.AllCaps()});



        insert_city.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String city = your_city.getText().toString().trim();
                Intent intent = new Intent(FirstActivity.this, MainActivity.class);
                intent.putExtra("city",city);
                startActivity(intent);



            }
        });




    }
}
