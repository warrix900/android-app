package com.example.lazymonster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.lazymonster.R.drawable.buttonshape;

public class silver extends AppCompatActivity {
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
        setContentView(R.layout.activity_silver);

        preferences = getSharedPreferences("user",MODE_PRIVATE);//建立儲存檔
        editor = preferences.edit();

        State(4); //執行傳值建構
        compare = new ArrayList(bl);

        mData.add("整理房間-掃地拖地");limit.add("2");
        mData.add("整理房間-桌椅電腦");limit.add("2");
        mData.add("健身房 1小時");limit.add("3");
        mData.add("與室友一起料理");limit.add("1");

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
            bool =preferences.getBoolean("SilverState"+k,true);
            bl.add(bool);
            str = preferences.getString("Silverid"+k,String.valueOf(k));
            id.add(str);
        }
    }
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            for (int k=0;k<4;k++){
                if (compare.get(k).equals(bl.get(k))){
                }else {
                    count("coin_silver",Integer.parseInt(limit.get(k)));
                }
                editor.putBoolean("SilverState"+k,bl.get(k));
                editor.commit();
            }
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
