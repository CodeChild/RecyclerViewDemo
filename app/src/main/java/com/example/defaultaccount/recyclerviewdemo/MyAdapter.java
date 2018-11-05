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
    public final static int ITEM_TYPE_HEADER = 0;
    public final static int ITEM_TYPE_TEXT = 1;
    private List<String> mDataSet = new ArrayList<>();
    private Context mContext;

    public MyAdapter(Context context) {
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        if (viewType == ITEM_TYPE_HEADER) {
            itemView = LayoutInflater.from(mContext).inflate(R.layout.item_header, parent, false);
            return new HeaderViewHolder(itemView);
        } else {
            itemView = LayoutInflater.from(mContext).inflate(R.layout.item_layout, parent, false);
            return new TextViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeaderViewHolder)
            ((HeaderViewHolder) holder).imageView.setImageResource(R.drawable.logo);
        else
            ((TextViewHolder) holder).textView.setText(mDataSet.get(position-1));
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

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? ITEM_TYPE_HEADER : ITEM_TYPE_TEXT;
    }

    public class TextViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public TextViewHolder(View itemView) {
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
    public class HeaderViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        public HeaderViewHolder(View itemView) {
            super(itemView);
            imageView=(ImageView) itemView.findViewById(R.id.iv_image);
        }
    }
}
