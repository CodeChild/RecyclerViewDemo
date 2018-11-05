package com.example.defaultaccount.recyclerviewdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DefaultAccount on 2017/9/15.
 */

public class MyAdapter extends RecyclerView.Adapter {
    private List<String> mDataSet = new ArrayList<>();
    private Context mContext;
    public MyAdapter(Context context) {
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(mContext).inflate(R.layout.item_layout, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder mHolder = (MyViewHolder) holder;
        mHolder.textView.setText(mDataSet.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }




    public void refreshItems(List<String> items) {
        mDataSet.clear();
        mDataSet.addAll(items);
        notifyDataSetChanged();
    }


    public void addItem(String item) {
        mDataSet.add(item);
        notifyDataSetChanged();
    }




    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv_text);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(mContext, "第" + (getLayoutPosition() + 1) + "项被选中", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
