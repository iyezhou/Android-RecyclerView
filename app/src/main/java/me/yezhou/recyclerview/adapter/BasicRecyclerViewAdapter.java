package me.yezhou.recyclerview.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by yezhou on 2017/3/20.
 */

public class BasicRecyclerViewAdapter extends RecyclerView.Adapter<BasicRecyclerViewAdapter.MyViewHolder> {

    private List<String> list;

    public BasicRecyclerViewAdapter(List<String> list) {
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 创建ViewHolder
        MyViewHolder holder = new MyViewHolder(View.inflate(parent.getContext(), android.R.layout.simple_list_item_1, null));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //绑定数据
        holder.tv.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public MyViewHolder(View view) {
            super(view);
            tv = (TextView)view.findViewById(android.R.id.text1);
        }
    }
}
