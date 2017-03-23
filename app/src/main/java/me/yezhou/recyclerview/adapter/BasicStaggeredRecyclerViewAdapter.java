package me.yezhou.recyclerview.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yezhou on 2017/3/21.
 */

public class BasicStaggeredRecyclerViewAdapter extends RecyclerView.Adapter<BasicStaggeredRecyclerViewAdapter.MyViewHolder> {

    private List<String> list;
    private List<Integer> heights;

    public BasicStaggeredRecyclerViewAdapter(List<String> list) {
        this.list = list;
        heights = new ArrayList<Integer>();
        for (int i = 0; i < list.size(); i++) {
            heights.add((int) (150 + Math.random()*100));
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 创建ViewHolder
        //Attempt to write to field 'int android.view.ViewGroup$LayoutParams.height' on a null object reference
        //MyViewHolder holder = new MyViewHolder(View.inflate(parent.getContext(), android.R.layout.simple_list_item_1, null));

        //The specified child already has a parent. You must call removeView() on the child's parent first
        //MyViewHolder holder = new MyViewHolder(View.inflate(parent.getContext(), android.R.layout.simple_list_item_1, parent));

        //View inflate(int resource, ViewGroup root, boolean attachToRoot)
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false));

        //若tv.getLayoutParams()有父布局R.layout.listitem，则不会报错
        //MyViewHolder holder = new MyViewHolder(View.inflate(viewGroup.getContext(), R.layout.listitem, null));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //绑定数据
        ViewGroup.LayoutParams params = holder.tv.getLayoutParams();
        params.height = heights.get(position);
        holder.tv.setBackgroundColor(Color.rgb((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255)));
        holder.tv.setLayoutParams(params);
        holder.tv.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv;

        public MyViewHolder(View view) {
            super(view);
            tv = (TextView)view.findViewById(android.R.id.text1);
            //tv = (TextView)view.findViewById(R.id.tv);
            tv.setTextColor(Color.WHITE);
        }
    }

}
