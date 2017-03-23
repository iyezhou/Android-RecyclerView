package me.yezhou.recyclerview.itemtouch;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import me.yezhou.recyclerview.R;
import me.yezhou.recyclerview.model.User;

/**
 * Created by yezhou on 2017/3/22.
 */

public class RecyclerViewItemTouchAdapter extends RecyclerView.Adapter<RecyclerViewItemTouchAdapter.MyViewHolder> implements OnItemTouchListener {

    private List<User> userList;
    private OnItemDragListener dragListener;

    public RecyclerViewItemTouchAdapter(List<User> userList, OnItemDragListener dragListener) {
        this.userList = userList;
        this.dragListener = dragListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_user_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int location) {
        User user = userList.get(location);
        holder.avatar.setImageResource(user.getAvatarResId());
        holder.name.setText(user.getName());
        holder.mobile.setText(user.getMobile());

        holder.avatar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    //传递触摸
                    dragListener.onStartDrag(holder);
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        // 1.数据交换；2.刷新
        Collections.swap(userList, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public boolean onItemRemove(int position) {
        userList.remove(position);
        notifyItemRemoved(position);
        return true;
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
}
