package com.example.lazymonster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class setting extends AppCompatActivity {
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    ImageButton clear;
    ImageButton resetBool;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        preferences = getSharedPreferences("user", MODE_PRIVATE);//建立儲存檔
        editor = preferences.edit();
        clear = (ImageButton) findViewById(R.id.clear);
        resetBool = (ImageButton) findViewById(R.id.resetBool);
        touch(clear);
        touch(resetBool);

    }

    public void clear(View v) {  //清除所有資料
        editor.clear().commit();
        Toast.makeText(getApplicationContext(), "資料已清除", Toast.LENGTH_LONG).show();
    }
    public void reset(View v){
        for(int i=0;i<8;i++){
            editor.putBoolean("BrownState"+i,true);
            editor.putBoolean("SilverState"+i,true);
            editor.putBoolean("GoldenState"+i,true);
            editor.commit();
        }

    }

    public void touch(ImageButton n){
        n.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    v.setBackgroundResource(R.drawable.buttonshape);
                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    v.setBackgroundResource(R.drawable.downbutton);
                }
                return false;
            }
        });
    }

}
