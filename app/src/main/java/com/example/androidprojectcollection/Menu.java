package com.example.androidprojectcollection;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Menu extends AppCompatActivity {

    Button btnChanger;

    boolean[] arr = {false,false,false,false,false};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnChanger = (Button) findViewById(R.id.btnTransform);

//        if(savedInstanceState!=null){
//            boolean[] new_arr = savedInstanceState.getBooleanArray("changes");
//            if(new_arr[0]){
//                btnChanger.setBackgroundColor(Color.RED);
//            }
//            if(new_arr[1]){
//                btnChanger.setTypeface(Typeface.MONOSPACE);
//            }
//            if(new_arr[2]){
//                btnChanger.setTextSize(20);
//            }
//            if(new_arr[3]){
//                btnChanger.setTextColor(Color.BLUE);
//            }if(new_arr[4]){
//                btnChanger.setEnabled(false);
//                btnChanger.setTextColor(Color.GRAY);
//            }
//
//
//        }

    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_menuexercise,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.mItemChange){
            Toast.makeText(this,"Edit object item is clicked",Toast.LENGTH_LONG).show();
        }else if(item.getItemId() == R.id.mItemReset){

            Toast.makeText(this,"Reset object item is clicked",Toast.LENGTH_LONG).show();
            btnChanger.setBackgroundColor(Color.BLACK);
            btnChanger.setTypeface(Typeface.DEFAULT);
            btnChanger.setTextColor(Color.WHITE);
            btnChanger.setTextSize(40);
            btnChanger.setEnabled(true);

            for(int i=0;i<5;i++){
                arr[i]=false;
            }

        }else if(item.getItemId()==R.id.mItemExit){
            finish();
        } else if(item.getItemId()==R.id.mItemChangeColor){
            btnChanger.setBackgroundColor(Color.RED);
            arr[0]=true;
        }
        else if(item.getItemId()==R.id.mItemChangeFont){
            btnChanger.setTypeface(Typeface.MONOSPACE);
            arr[1]=true;
        }
        else if(item.getItemId()==R.id.mItemChangeFontSize){
            btnChanger.setTextSize(20);
            arr[2]=true;
        }
        else if (item.getItemId()==R.id.mItemChangeTextColor){
            btnChanger.setTextColor(Color.BLUE);
            arr[3]=true;
        }else if(item.getItemId()==R.id.mItemChangeSize){
            btnChanger.setEnabled(false);
            btnChanger.setTextColor(Color.GRAY);
            arr[4]=true;
        }



        return true;
    }
//    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putBooleanArray("changes",arr);
    }
//
//    @Override
//    public void onRestoreInstanceState(Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//
//        // Restore colorState array
//        for (int i = 0; i < 5; i++) {
//            colorState[i] = savedInstanceState.getIntArray("colorstate" + i);
//        }
//        // Restore player variable
//        player = savedInstanceState.getInt("player");
//    }

}