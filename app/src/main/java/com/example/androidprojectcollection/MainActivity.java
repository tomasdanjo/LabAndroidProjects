package com.example.androidprojectcollection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn1 = (Button) findViewById(R.id.btn1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(
                        MainActivity.this,//this activitry
                        LayoutExercise.class
                        );//destination activity
                startActivity(intent1);
            }
        });

        Button colormatchbtn = (Button) findViewById(R.id.color_match_btn);
        colormatchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ColorMatching.class);

                startActivity(i);
            }
        });

        Button calculatorBtn = (Button) findViewById(R.id.calcBtn);
        calculatorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, CalculatorActivity.class);

                startActivity(i);
            }
        });

        Button connect3 = (Button) findViewById(R.id.connect3);
        connect3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Connect3.class);

                startActivity(i);
            }
        });


    }
}