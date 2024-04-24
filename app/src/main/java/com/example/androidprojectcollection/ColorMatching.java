package com.example.androidprojectcollection;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class ColorMatching extends AppCompatActivity {
    Boolean auto_win_enabled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_matching);

        //show toast
        String text = "Thomas Danjo Manulat | Color Matching";
        Toast toast = Toast.makeText(this,text,Toast.LENGTH_SHORT);
        toast.show();

        Button returnBtn =  (Button)findViewById(R.id.btnReturn);
        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ColorMatching.this,ColorMatching.class);
                startActivity(i);
            }
        });


        Random rand = new Random();

        //create random color states
        int[][] colorStates = new int[][]{
                {rand.nextInt(3)+1,rand.nextInt(3)+1,rand.nextInt(3)+1},
                {rand.nextInt(3)+1,rand.nextInt(3)+1,rand.nextInt(3)+1},
                {rand.nextInt(3)+1,rand.nextInt(3)+1,rand.nextInt(3)+1}
        };

        Button btn00 = (Button) findViewById(R.id.btn00);
        Button btn01 = (Button) findViewById(R.id.btn01);
        Button btn02 = (Button) findViewById(R.id.btn02);

        Button btn10 = (Button) findViewById(R.id.btn10);
        Button btn11 = (Button) findViewById(R.id.btn11);
        Button btn12 = (Button) findViewById(R.id.btn12);

        Button btn20 = (Button) findViewById(R.id.btn20);
        Button btn21 = (Button) findViewById(R.id.btn21);
        Button btn22 = (Button) findViewById(R.id.btn22);

        //create grid

        Button[][] btnGrid = new Button[][]{
                {btn00,btn01,btn02},
                {btn10,btn11,btn12},
                {btn20,btn21,btn22}
        };

        //set random colors
        for(int i=0;i<colorStates.length;i++){
            for(int j=0;j<colorStates[i].length;j++){
                setColor(btnGrid[i][j],colorStates[i][j]);
            }
        }
        Button auto_win_btn = (Button) findViewById(R.id.auto_win_btn);


        //add button listeners
        for(int i=0;i<btnGrid.length;i++){
            for(int j=0;j<btnGrid[i].length;j++){

                int finalI = i;
                int finalJ = j;
                btnGrid[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //top [i-1][j]

                        if((finalI -1)>=0){
                            //increment the color state
                            colorStates[finalI-1][finalJ] = ((colorStates[finalI-1][finalJ]+1)%3)+1;
                        }
                        //right = [i][j+1]
                        if(finalJ+1<btnGrid[finalI].length){
                            colorStates[finalI][finalJ+1] = ((colorStates[finalI][finalJ+1]+1)%3)+1;
                        }
                        //left = [i][j-1]
                        if(finalJ-1>=0){
                            colorStates[finalI][finalJ-1] = ((colorStates[finalI][finalJ-1]+1)%3)+1;
                        }
                        //bottom = [i+1][j]
                        if(finalI+1<btnGrid[finalI].length){
                            colorStates[finalI+1][finalJ] = ((colorStates[finalI+1][finalJ]+1)%3)+1;
                        }


                        //update color
                        for(int i=0;i<colorStates.length;i++){
                            for(int j=0;j<colorStates[i].length;j++){
                                setColor(btnGrid[i][j],colorStates[i][j]);
                            }
                        }


                        //check if daog
                        if(/*daog*/ endWin(colorStates)){
                            //disable all buttons
                            disableGrid(btnGrid);
                            String text = "You Won!";
                            Toast toast = Toast.makeText(ColorMatching.this,text,Toast.LENGTH_LONG);
                            toast.show();
                            auto_win_btn.setEnabled(false);
                        }else {
                            auto_win_btn.setEnabled(true);
                        }


                    }
                });


            }
        }


//        auto_win_enabled = false;
        auto_win_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int color_state2 = colorStates[1][1];
                int color_state1 = color_state2%3+1;

                colorStates[0][0] = color_state2;
                colorStates[0][2] = color_state2;
                colorStates[1][1] = color_state2;
                colorStates[2][0] = color_state2;
                colorStates[2][2] = color_state2;


                colorStates[0][1] = color_state1;
                colorStates[1][0] = color_state1;
                colorStates[1][2] = color_state1;
                colorStates[2][1] = color_state1;

                for(int i=0;i<colorStates.length;i++){
                    for(int j=0;j<colorStates[i].length;j++){
                        setColor(btnGrid[i][j],colorStates[i][j]);
                    }
                }


            }
        });




    }

    protected void disableGrid(Button[][] btnGrid){
        for(int i=0;i<btnGrid.length;i++){
            for(int j=0;j<btnGrid[i].length;j++){
                btnGrid[i][j].setEnabled(false);
            }
        }
    }

    protected void enableGrid(Button[][] btnGrid){
        for(int i=0;i<btnGrid.length;i++){
            for(int j=0;j<btnGrid[i].length;j++){
                btnGrid[i][j].setEnabled(true);
            }
        }
    }

    protected boolean endWin(int[][] colorStates){
        for(int i=0;i<colorStates.length;i++){
            for(int j=0;j<colorStates[i].length;j++){
                if(colorStates[0][0]!=colorStates[i][j]) return false;
            }
        }
        return true;
    }

    //setColor
    protected void setColor(Button b, int color_state){
        switch (color_state){
            case 1:
                b.setBackgroundColor(Color.BLUE);
                break;
            case 2:
                b.setBackgroundColor(Color.GREEN);
                break;
            case 3:
                b.setBackgroundColor(Color.RED);
                break;
        }
    }
}