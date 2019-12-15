package com.example.lazymonster;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class shopping extends AppCompatActivity {
    Button b1,b2,b3,s1,g1;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);
        preferences = getSharedPreferences("user",MODE_PRIVATE);//建立儲存檔
        editor = preferences.edit();
        b1 = (Button)findViewById(R.id.b1);
        b2 = (Button)findViewById(R.id.b2);
        b3 = (Button)findViewById(R.id.b3);
        s1 = (Button)findViewById(R.id.s1);
        g1 = (Button)findViewById(R.id.g1);
    }
    public int getCoin(String s){
        int q;
        q = preferences.getInt(s,0);
        return q;
    }
    public void count(String s,int c){
        int result,num;
        num = getCoin(s);
        result = num +c;
        if (result < 0){
            AlertDialog.Builder dialog = new AlertDialog.Builder(shopping.this);
            dialog.setTitle("餘額不足...");
            dialog.setMessage("請大俠繼續努力");
            dialog.show();
        }else {
            Toast.makeText(this,"銘謝惠顧",Toast.LENGTH_SHORT).show();
            editor.putInt(s,result);
            editor.commit();}
    }
    public void buy(View v){
        count("coin_brown",-1);
        Toast.makeText(this,"消耗 1 個銅幣",Toast.LENGTH_SHORT).show();
    }
    public void buyplus(View v){
        count("coin_silver",-3);
        Toast.makeText(this,"消耗 3 個銀幣",Toast.LENGTH_SHORT).show();
    }
    public void buybuy(View v){
        count("coin_golden",-2);
        Toast.makeText(this,"消耗 2 個金幣",Toast.LENGTH_SHORT).show();
    }
}
