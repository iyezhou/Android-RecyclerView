package me.yezhou.recyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

import java.util.ArrayList;

import me.yezhou.recyclerview.itemtouch.RecyclerViewItemTouchAdapter;
import me.yezhou.recyclerview.itemtouch.ItemTouchHelperCallback;
import me.yezhou.recyclerview.itemtouch.OnItemDragListener;
import me.yezhou.recyclerview.model.User;

/**
 * Created by yezhou on 2017/3/20.
 */
public class RecyclerViewItemTouchActivity extends AppCompatActivity implements OnItemDragListener {

    private static final String TAG = "yezhou";

    private RecyclerView mRecyclerView;
    private ArrayList<User> mUserList;
    private RecyclerViewItemTouchAdapter mAdapter;
    private ItemTouchHelper mItemTouchHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_touch);
        getSupportActionBar().hide();

        mUserList = new ArrayList<User>();
        for (int i = 0; i < 60; i++) {
            User user = new User();
            user.setName("中国" + String.format("%02d", (i+1)));
            user.setAvatarResId((R.drawable.h001 + i % 10));
            user.setMobile("1000" + i);
            mUserList.add(user);
        }

        mRecyclerView = (RecyclerView) this.findViewById(R.id.recyclerview);
        mAdapter = new RecyclerViewItemTouchAdapter(mUserList, this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this)); //默认垂直
        mRecyclerView.setAdapter(mAdapter);
        //条目触摸帮助类
        ItemTouchHelper.Callback callback = new ItemTouchHelperCallback(mAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        Log.i(TAG, "RecyclerViewItemTouchActivity: onStartDrag");
        //mItemTouchHelper.startDrag(viewHolder);
    }
}
