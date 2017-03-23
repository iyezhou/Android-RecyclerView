package me.yezhou.recyclerview.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import me.yezhou.recyclerview.R;
import me.yezhou.recyclerview.model.User;

/**
 * Created by yezhou on 2017/3/20.
 */

public class DeviderRecyclerViewAdapter extends RecyclerView.Adapter<DeviderRecyclerViewAdapter.MyViewHolder> implements View.OnClickListener {

    private List<User> userList;
    private OnItemClickListener mOnItemClickListener;

    public DeviderRecyclerViewAdapter(List<User> userList) {
        this.userList = userList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 创建ViewHolder
        View view = View.inflate(parent.getContext(), R.layout.layout_user_item, null);
        //将创建的View注册点击事件
        view.setOnClickListener(this);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //绑定数据
        User user = userList.get(position);
        holder.name.setText(user.getName());
        holder.avatar.setImageResource(user.getAvatarResId());
        //holder.mobile.setText(user.getMobile());
        //设置Tag
        holder.itemView.setTag(user);
    }

    @Override
    public void onClick(View view) {
        if (mOnItemClickListener != null) {
            //使用getTag方法获取数据
            mOnItemClickListener.onItemClick(view, (User) view.getTag());
        }
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView avatar;
        TextView mobile;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            avatar = (ImageView) view.findViewById(R.id.avatar);
            mobile = (TextView) view.findViewById(R.id.mobile);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, User user);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
}
