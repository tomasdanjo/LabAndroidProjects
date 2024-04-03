package com.example.androidprojectcollection;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Connect3 extends AppCompatActivity {
    int player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect3);

        String text = "Thomas Danjo Manulat | Connect 3";
        Toast toast = Toast.makeText(this,text,Toast.LENGTH_SHORT);
        toast.show();

        Random rand = new Random();
        player = rand.nextInt(2)+1;
        TextView playerLabel = (TextView) findViewById(R.id.playerTurn);
        playerLabel.setText("Player "+player+"'s turn");

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

        int[][] colorState = {
                {0,0,0,0,0},
                {0,0,0,0,0},
                {0,0,0,0,0},
                {0,0,0,0,0},
                {0,0,0,0,0}
        };

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
                            playerLabel.setText("Player "+player+"'s turn");
                        }else if(winner(colorState)==0){
                            //tie
                            playerLabel.setText("Tie");
                            //disable buttons
                            disableBtns(btns);
                        }else{
                            playerLabel.setText("Player "+winner(colorState)+" won!");
                            //disable buttons
                            disableBtns(btns);
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

                player = rand.nextInt(2)+1;
                playerLabel.setText("Player "+player+"'s turn");
            }
        });


    }

    public void enableBtns(Button[][] btns){
        for(int i=0;i< btns.length;i++){
            for(int j=0;j<btns[i].length;j++){
                btns[i][j].setEnabled(true);
            }
        }
    }

    public void disableBtns(Button[][] btns){
        for(int i=0;i< btns.length;i++){
            for(int j=0;j<btns[i].length;j++){
                btns[i][j].setEnabled(false);
            }
        }
    }

    public int winner(int[][] colorstate){
        //check for horizontal
        for(int i=0;i<colorstate.length;i++){
            for(int j=0;j<colorstate[i].length-2;j++){
                if(colorstate[i][j]!=0 && colorstate[i][j]==colorstate[i][j+1] &&  colorstate[i][j]==colorstate[i][j+2]){
                    return colorstate[i][j];
                }
            }
        }

        for(int i=0;i<colorstate.length-2;i++){
            for(int j=0;j<colorstate[i].length;j++){
                if(colorstate[i][j]!=0 && colorstate[i][j]==colorstate[i+1][j] &&  colorstate[i][j]==colorstate[i+2][j]){
                    return colorstate[i][j];
                }
            }
        }

        for(int i=2;i<colorstate.length;i++){
            for(int j=0;j<colorstate[i].length-2;j++){
                if(colorstate[i][j]!=0 && colorstate[i][j]==colorstate[i-1][j+1] &&  colorstate[i][j]==colorstate[i-2][j+2]){
                    return colorstate[i][j];
                }
            }
        }

        for(int i=0;i<colorstate.length-2;i++){
            for(int j=0;j<colorstate[i].length-2;j++){
                if(colorstate[i][j]!=0 && colorstate[i][j]==colorstate[i+2][j+2] &&  colorstate[i][j]==colorstate[i+1][j+1]){
                    return colorstate[i][j];
                }
            }
        }

        boolean istie = true;

        for(int i=0;i<colorstate.length;i++){
            for(int j=0;j<colorstate[i].length;j++){
                if(colorstate[i][j]==0){
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