package com.example.lazymonster;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import static com.example.lazymonster.R.drawable.buttonshape;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
    private List<String> mData; //內容
    private List<String> limit; // 獎勵數量
    private List<String> id; //任務事件ID
    private List<Boolean> bl; //是否禁用


    MyAdapter(List<String> data,List<String> limi,List<String>bb,List<Boolean> beal) {
        mData = data;
        limit = limi;
        id = bb;
        bl = beal;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        // 宣告元件
        private TextView txtItem;
        private TextView txtItem2;
        private Button btn;


        public ViewHolder(View itemView) {
            super(itemView);
            txtItem = (TextView) itemView.findViewById(R.id.txtItem);
            txtItem2 = (TextView) itemView.findViewById(R.id.txtItem2);
            btn = (Button) itemView.findViewById(R.id.btnRemove);

            // 點擊項目時
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(),
                            "click " +getAdapterPosition(),Toast.LENGTH_SHORT).show();
                }
            });
            // 點擊項目中的Button時
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // 按下Button要做的事
                    view.setEnabled(false);
                    view.setBackgroundResource(buttonshape);
                    bl.set(getAdapterPosition(),false);
                    switch (id.get(getAdapterPosition())){
                        case "0":
                            int q;
                            q = Integer.parseInt(limit.get(0));
                            limit.set(getAdapterPosition(),q+"");
                            break;
                        case "1":
                            int a;
                            a = Integer.parseInt(limit.get(1));
                            limit.set(getAdapterPosition(),a+"");
                            break;
                        case "2":
                            int b;
                            b = Integer.parseInt(limit.get(2));
                            limit.set(getAdapterPosition(),b+"");

                            break;
                        case "3":
                            int c;
                            c = Integer.parseInt(limit.get(3));
                            limit.set(getAdapterPosition(),c+"");
                            break;
                        case "4":
                            int d;
                            d = Integer.parseInt(limit.get(4));
                            limit.set(getAdapterPosition(),d+"");
                            break;
                        case "5":
                            int e;
                            e = Integer.parseInt(limit.get(5));
                            limit.set(getAdapterPosition(),e+"");
                            break;
                        case "6":
                            int f;
                            f = Integer.parseInt(limit.get(6));
                            limit.set(getAdapterPosition(),f+"");
                            break;
                        case "7":
                            int g;
                            g = Integer.parseInt(limit.get(7));
                            limit.set(getAdapterPosition(),g+"");
                            break;
                    }
                }
            });

        }
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        //連結布局檔 list_item
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lsit_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {
        //設置txtItem要顯示的內容
        holder.txtItem.setText(mData.get(position)); //這行很重要
        holder.txtItem2.setText(limit.get(position));
        holder.btn.setEnabled(bl.get(position));
        if(holder.btn.isEnabled()){

        }else {
            holder.btn.setBackgroundResource(buttonshape);
        }
    }

    @Override
    public int getItemCount() {
        return mData.size(); //傳回數量
    }
}
