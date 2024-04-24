package com.example.androidprojectcollection;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class Connect3 extends AppCompatActivity {
    int player;
    int[][] colorState = {
            {0,0,0,0,0},
            {0,0,0,0,0},
            {0,0,0,0,0},
            {0,0,0,0,0},
            {0,0,0,0,0}
    };
    String text;
    boolean disable = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Random rand = new Random();

        setContentView(R.layout.activity_connect3);

        Button c1r1 = (Button) findViewById(R.id.c1r1);
        Button c2r1 = (Button) findViewById(R.id.c2r1);
        Button c3r1 = (Button) findViewById(R.id.c3r1);
        Button c4r1 = (Button) findViewById(R.id.c4r1);
        Button c5r1 = (Button) findViewById(R.id.c5r1);

        Button c1r2 = (Button) findViewById(R.id.c1r2);
        Button c2r2 = (Button) findViewById(R.id.c2r2);
        Button c3r2 = (Button) findViewById(R.id.c3r2);
        Button c4r2 = (Button) findViewById(R.id.c4r2);
        Button c5r2 = (Button) findViewById(R.id.c5r2);

        Button c1r3 = (Button) findViewById(R.id.c1r3);
        Button c2r3 = (Button) findViewById(R.id.c2r3);
        Button c3r3 = (Button) findViewById(R.id.c3r3);
        Button c4r3 = (Button) findViewById(R.id.c4r3);
        Button c5r3 = (Button) findViewById(R.id.c5r3);

        Button c1r4 = (Button) findViewById(R.id.c1r4);
        Button c2r4 = (Button) findViewById(R.id.c2r4);
        Button c3r4 = (Button) findViewById(R.id.c3r4);
        Button c4r4 = (Button) findViewById(R.id.c4r4);
        Button c5r4 = (Button) findViewById(R.id.c5r4);

        Button c1r5 = (Button) findViewById(R.id.c1r5);
        Button c2r5 = (Button) findViewById(R.id.c2r5);
        Button c3r5 = (Button) findViewById(R.id.c3r5);
        Button c4r5 = (Button) findViewById(R.id.c4r5);
        Button c5r5 = (Button) findViewById(R.id.c5r5);

        Button[][] btns = {
                {c1r1,c2r1,c3r1,c4r1,c5r1},
                {c1r2,c2r2,c3r2,c4r2,c5r2},
                {c1r3,c2r3,c3r3,c4r3,c5r3},
                {c1r4,c2r4,c3r4,c4r4,c5r4},
                {c1r5,c2r5,c3r5,c4r5,c5r5}
        };


        if(savedInstanceState!=null){
            super.onRestoreInstanceState(savedInstanceState);

            // Restore colorState array
            for (int i = 0; i < 5; i++) {
                colorState[i] = savedInstanceState.getIntArray("colorstate" + i);
            }
            // Restore player variable
            player = savedInstanceState.getInt("player");
            text = savedInstanceState.getString("header_text");
            disable = savedInstanceState.getBoolean("disable_btn");
            if(disable){
                disableBtns(btns);

            }else{
                enableBtns(btns);
            }


        }else{
            player = rand.nextInt(2)+1;
            text = "Player "+player+"'s turn";
        }




        String texttoast = "Thomas Danjo Manulat | Connect 3";
        Toast toast = Toast.makeText(this,texttoast,Toast.LENGTH_SHORT);
        toast.show();


        TextView playerLabel = (TextView) findViewById(R.id.playerTurn);
        playerLabel.setText(text);

        if(player==1){
            playerLabel.setTextColor(Color.BLACK);
        }else{
            playerLabel.setTextColor(Color.RED);
        }




        refreshColor(colorState,btns);

        for(int i=0;i<5;i++){
            int finalI = i;
            btns[0][i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean columnisfull = true;
                    for(int j=4;j>=0;j--){
                        if(colorState[j][finalI]==0){
                            colorState[j][finalI] = player;
                            columnisfull=false;
                            break;
                        }
                    }
                    refreshColor(colorState,btns);

                    if(columnisfull){
                        Toast toast = Toast.makeText(Connect3.this,"Select another column",Toast.LENGTH_SHORT);
                        toast.show();
                    }else{


                        if(winner(colorState)==-1){
                            player = (player==1)?2:1;
                            text = "Player "+player+"'s turn";
                            playerLabel.setText(text);
                            if(player==1){
                                playerLabel.setTextColor(Color.BLACK);
                            }else{

                                playerLabel.setTextColor(Color.RED);
                            }
                        }else if(winner(colorState)==0){
                            //tie
                            text = "Tie";
                            playerLabel.setText(text);
                            //disable buttons
                            disableBtns(btns);
                            disable = true;
                        }else{
                            text = "Player "+winner(colorState)+" won!";
                            playerLabel.setText(text);
                            //disable buttons
                            disableBtns(btns);
                            disable = true;
                        }

                    }



                }
            });
        }

        Button resetBtn = (Button) findViewById(R.id.resetBtn);
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i=0;i< colorState.length;i++){
                    for(int j=0;j<colorState[i].length;j++){
                        colorState[i][j]=0;
                    }
                }
                refreshColor(colorState,btns);
                enableBtns(btns);
                disable = false;
                player = rand.nextInt(2)+1;
                playerLabel.setText("Player "+player+"'s turn");
                if(player==1){
                    playerLabel.setTextColor(Color.BLACK);
                }else{

                    playerLabel.setTextColor(Color.RED);
                }
            }
        });

    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        // Save colorState array
        for (int i = 0; i < 5; i++) {
            savedInstanceState.putIntArray("colorstate" + i, colorState[i]);
        }
        // Save player variable
        savedInstanceState.putInt("player", player);
        savedInstanceState.putString("header_text",text);
        savedInstanceState.putBoolean("disable_btn", disable);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // Restore colorState array
        for (int i = 0; i < 5; i++) {
            colorState[i] = savedInstanceState.getIntArray("colorstate" + i);
        }
        // Restore player variable
        player = savedInstanceState.getInt("player");
    }

    public void enableBtns(Button[][] btns){
        for(int i=0;i< btns.length;i++){
            btns[0][i].setEnabled(true);

        }
    }

    public void disableBtns(Button[][] btns){
        for(int i=0;i< btns.length;i++){
            btns[0][i].setEnabled(false);
        }
    }

    public int winner(int[][] colorState){
        //check for horizontal
        int rows = colorState.length;
        int cols = colorState[0].length;

        // Check rows
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols - 2; j++) {
                if (colorState[i][j]!=0 && colorState[i][j] == colorState[i][j+1] && colorState[i][j] == colorState[i][j+2]) {
                    return colorState[i][j];
                }
            }
        }

        // Check columns
        for (int j = 0; j < cols; j++) {
            for (int i = 0; i < rows - 2; i++) {
                if (colorState[i][j]!=0 && colorState[i][j] == colorState[i+1][j] && colorState[i][j] == colorState[i+2][j]) {
                    return colorState[i][j];
                }
            }
        }

        // Check diagonals
        for (int i = 0; i < rows - 2; i++) {
            for (int j = 0; j < cols - 2; j++) {
                if (colorState[i][j]!=0 && colorState[i][j] == colorState[i+1][j+1] && colorState[i][j] == colorState[i+2][j+2]) {
                    return colorState[i][j];
                }
            }
        }

        // Check reverse diagonals
        for (int i = 0; i < rows - 2; i++) {
            for (int j = 2; j < cols; j++) {
                if (colorState[i][j]!=0 && colorState[i][j] == colorState[i+1][j-1] && colorState[i][j] == colorState[i+2][j-2]) {
                    return colorState[i][j];
                }
            }
        }

        boolean istie = true;

        for(int i=0;i<colorState.length;i++){
            for(int j=0;j<colorState[i].length;j++){
                if(colorState[i][j]==0){
                    istie = false;
                }
            }
        }

        if(istie)return 0;
        else return -1;

    }

    public void refreshColor(int[][] colorState, Button[][] btns){
        for(int i=0;i<colorState.length;i++){
            for(int j=0;j<colorState[i].length;j++){
                switch (colorState[i][j]){
                    case 0:
                        btns[i][j].setBackgroundColor(Color.WHITE);
                        break;
                    case 1:
                        btns[i][j].setBackgroundColor(Color.BLACK);
                        break;
                    case 2:
                        btns[i][j].setBackgroundColor(Color.RED);
                        break;
                }
            }
        }
    }
}