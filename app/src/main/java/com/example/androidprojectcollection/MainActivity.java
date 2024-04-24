package com.example.androidprojectcollection;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidprojectcollection.passingintents.PassingIntentsExercise;


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

        Button passingIntents = (Button) findViewById(R.id.passingIntents);
        passingIntents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, PassingIntentsExercise.class);
                startActivity(i);
            }
        });

        Button menu = (Button) findViewById(R.id.menuActivity);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent (MainActivity.this,Menu.class);
                startActivity(i);
            }
        });

    }
}