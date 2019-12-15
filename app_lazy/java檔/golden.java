package com.example.lazymonster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.lazymonster.R.drawable.buttonshape;

public class golden extends AppCompatActivity {
    private RecyclerView recycler_view;
    private MyAdapter adapter;
    private ArrayList<String> mData = new ArrayList<>();
    private ArrayList<String> limit = new ArrayList<>();
    private ArrayList<String> id = new ArrayList<>();
    private List<Boolean> bl = new ArrayList<>(); //是否禁用
    private List<Boolean> compare;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_golden);

        preferences = getSharedPreferences("user",MODE_PRIVATE);//建立儲存檔
        editor = preferences.edit();

        State(5); //執行傳值建構
        compare = new ArrayList(bl);

        mData.add("床單/窗簾清洗");limit.add("1");id.add("0");
        mData.add("廚房打掃");limit.add("2");id.add("1");
        mData.add("洗衣機清洗");limit.add("3");id.add("2");
        mData.add("約會成功一次");limit.add("4");id.add("3");
        mData.add("連續一個月完成某任務");limit.add("5");id.add("4");

        //自訂格線
        DividerItemDecoration decoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        Drawable dividerDrawable = getResources().getDrawable(R.drawable.divider);
        decoration.setDrawable(dividerDrawable);

        //連結元件
        recycler_view = (RecyclerView) findViewById(R.id.recycle);
        // 設置RecyclerView為列表型態
        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        // 設置格線(這裡用自訂格線)
        recycler_view.addItemDecoration(decoration);
        //將資料交給adapter
        adapter = new MyAdapter(mData,limit,id,bl);
        //設置adapter給recycle_view
        recycler_view.setAdapter(adapter);

    }
    protected void onResume(){
        super.onResume();
        //重整
        recycler_view.setAdapter(adapter);
    }
    public void count(String s,int c){
        int result,num;
        num = getCoin(s);
        result = num +c;
        if (result < 0){
            Toast.makeText(this,"餘額不足...",Toast.LENGTH_LONG).show();
        }else {
            editor.putInt(s,result);
            editor.commit();}
    }
    public int getCoin(String s){
        int q;
        q = preferences.getInt(s,0);
        return q;
    }
    private void State(int i){
        for (int k =0;k<i;k++){
            boolean bool;
            String str;
            bool =preferences.getBoolean("GoldenState"+k,true);
            bl.add(bool);
            str = preferences.getString("Goldenid"+k,String.valueOf(k));
            id.add(str);
        }
    }
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            for (int k=0;k<5;k++){
                if (compare.get(k).equals(bl.get(k))){
                }else {
                    count("coin_golden",Integer.parseInt(limit.get(k)));
                }
                editor.putBoolean("GoldenState"+k,bl.get(k));
                editor.commit();
            }
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
