package com.example.lazymonster;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


public class loading extends AppCompatActivity {
    SharedPreferences preferences;
    String readname;
    SharedPreferences.Editor editor;
    ImageButton st;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        preferences = getSharedPreferences("user", MODE_PRIVATE);//建立儲存檔
        readname = getUsername();//讀取檔案
        editor = preferences.edit();
        st = (ImageButton) findViewById(R.id.st);
        touch(st);
        //判斷是否為新用戶
        if (readname.equals("")) {
            newer();
        } else {
            String welcome = getResources().getString(R.string.welcome_messages);
            String welcome_messages = String.format(welcome, readname);
            Toast.makeText(this, welcome_messages, Toast.LENGTH_SHORT).show();
            //finish(); 若是舊用戶直接跳轉
        }
    }

    public void newer() {  //新用戶
        final EditText editText = new EditText(loading.this);
        editText.setText(readname);
        AlertDialog.Builder dialog = new AlertDialog.Builder(loading.this);
        dialog.setTitle("輸入暱稱");
        dialog.setView(editText);
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                readname = editText.getText().toString();
                editor.putString("username", readname).commit();
                Toast.makeText(getApplicationContext(), "您好，" + readname, Toast.LENGTH_LONG).show();
            }
        });
        dialog.show();
    }

    public String getUsername() {
        String name;
        name = preferences.getString("username", "");//讀取檔案
        return name;
    }

    public void touch(ImageButton n) {
        n.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    v.setBackgroundResource(R.drawable.buttonshape);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    v.setBackgroundResource(R.drawable.downbutton);
                }
                return false;
            }
        });

    }
    public void start(View v){
        finish();
    }
}
