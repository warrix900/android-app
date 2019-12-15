package com.example.lazymonster;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity {
    TextView b,s,g;
    ImageButton setting,mail,quest,shop;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent it = new Intent(this, loading.class); //建立 Intent 並設定目標 Activity
        startActivity(it); // 啟動 Intent 中的目標

        GifImageView ImageView = findViewById(R.id.dog);
        try{
            GifDrawable gifDrawable = new GifDrawable(getResources(), R.mipmap.gif);
            ImageView.setImageDrawable(gifDrawable);
        }catch (Exception e){
            e.printStackTrace();
        }

        b = (TextView) findViewById(R.id.coin_1);
        s = (TextView) findViewById(R.id.coin_2);
        g = (TextView) findViewById(R.id.coin_3);
        setting = (ImageButton) findViewById(R.id.setting);
        mail = (ImageButton) findViewById(R.id.mail);
        quest = (ImageButton) findViewById(R.id.quest);
        shop = (ImageButton) findViewById(R.id.shop);
        preferences = getSharedPreferences("user",MODE_PRIVATE);//建立儲存檔
        editor = preferences.edit();
        touch(setting);
        touch(mail);
        touch(quest);
        touch(shop);
        check();
    }
    protected void onResume(){
        super.onResume();
        check();
    }
    public void count(String s,int c){
        int result,num;
        num = getCoin(s);
        result = num +c;
        if (result < 0){
            AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
            dialog.setTitle("餘額不足...");
            dialog.setMessage("請大俠繼續努力");
            dialog.show();
        }else {
            editor.putInt(s,result);
            editor.commit();}
        check();
    }
    public void reward(View v){
        for(int i=0;i<8;i++){
            editor.putBoolean("BrownState"+i,true);
            editor.putBoolean("SilverState"+i,true);
            editor.putBoolean("GoldenState"+i,true);
            editor.commit();
        }

    }
    public int getCoin(String s){
        int q;
        q = preferences.getInt(s,0);
        return q;
    }
    public void check(){
        Integer bc,sc,gc;
        //傳值
        bc = getCoin("coin_brown");
        sc = getCoin("coin_silver");
        gc = getCoin("coin_golden");
        //重新整理
        b.setText(bc.toString());
        s.setText(sc.toString());
        g.setText(gc.toString());
    }
    public void setting(View v){
        Intent it = new Intent(this, setting.class); //建立 Intent 並設定目標 Activity
        startActivity(it); // 啟動 Intent 中的目標
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
    public void quest(View v){
        Intent it = new Intent(this, quest.class); //建立 Intent 並設定目標 Activity
        startActivity(it); // 啟動 Intent 中的目標
    }
    public void shop(View v){
        Intent it = new Intent(this, shopping.class); //建立 Intent 並設定目標 Activity
        startActivity(it); // 啟動 Intent 中的目標
    }
    public void comming(View v){
        Toast.makeText(this,"施工中...",Toast.LENGTH_LONG).show();
    }

}
