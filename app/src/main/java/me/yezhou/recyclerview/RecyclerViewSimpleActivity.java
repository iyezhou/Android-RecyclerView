package me.yezhou.recyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

import me.yezhou.recyclerview.adapter.SimpleRecyclerViewAdapter;
import me.yezhou.recyclerview.model.User;

/**
 * Created by yezhou on 2017/3/20.
 */
public class RecyclerViewSimpleActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ArrayList<User> mUserList;
    private SimpleRecyclerViewAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);
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
        mAdapter = new SimpleRecyclerViewAdapter(mUserList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this)); //默认垂直
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new SimpleRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, User user) {
                Toast.makeText(RecyclerViewSimpleActivity.this, user.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void addUser(View view) {
        mAdapter.addUser(2);
    }

    public void removeUser(View view) {
        mAdapter.removeUser(2);
    }

    public void updateUser(View view) {
        int position = 2;
        User user = new User();
        user.setName("上海");
        user.setAvatarResId((R.drawable.h001 + position % 10));
        user.setMobile("10086");
        mAdapter.updateUser(position, user);
    }
}
