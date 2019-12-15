package com.example.lazymonster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

public class quest extends AppCompatActivity {
    ImageButton b,s,g;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest);
        b = (ImageButton) findViewById(R.id.b);
        s = (ImageButton) findViewById(R.id.s);
        g = (ImageButton) findViewById(R.id.g);
        touch(b);
        touch(s);
        touch(g);

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
    public void b(View v){
        Intent it = new Intent(this, brown.class); //建立 Intent 並設定目標 Activity
        startActivity(it); // 啟動 Intent 中的目標
    }
    public void s(View v){
        Intent it = new Intent(this, silver.class); //建立 Intent 並設定目標 Activity
        startActivity(it); // 啟動 Intent 中的目標
    }
    public void g(View v){
        Intent it = new Intent(this, golden.class); //建立 Intent 並設定目標 Activity
        startActivity(it); // 啟動 Intent 中的目標
    }
}
