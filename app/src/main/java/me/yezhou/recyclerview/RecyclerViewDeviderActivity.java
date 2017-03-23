package me.yezhou.recyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

import me.yezhou.recyclerview.adapter.DeviderRecyclerViewAdapter;
import me.yezhou.recyclerview.devider.GridDividerItemDecoration;
import me.yezhou.recyclerview.devider.ListDividerItemDecoration;
import me.yezhou.recyclerview.model.User;

/**
 * Created by yezhou on 2017/3/20.
 */
public class RecyclerViewDeviderActivity extends AppCompatActivity {

    private RadioGroup mRgRecyclerViewLayout = null;

    private RecyclerView mRecyclerView;
    private ArrayList<User> mUserList;
    private DeviderRecyclerViewAdapter mAdapter;

    private RecyclerView.ItemDecoration mListDividerItemDecoration;
    private RecyclerView.ItemDecoration mGridDividerItemDecoration;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devider);
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
        mAdapter = new DeviderRecyclerViewAdapter(mUserList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this)); //默认垂直
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new DeviderRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, User user) {
                Toast.makeText(RecyclerViewDeviderActivity.this, user.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        mListDividerItemDecoration = new ListDividerItemDecoration(this, LinearLayoutManager.VERTICAL);
        mGridDividerItemDecoration = new GridDividerItemDecoration(this);
        mRecyclerView.addItemDecoration(mListDividerItemDecoration);

        mRgRecyclerViewLayout = (RadioGroup) this.findViewById(R.id.recyclerview_layout);
        mRgRecyclerViewLayout.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.linear:  //列表效果
                        mRecyclerView.setLayoutManager(new LinearLayoutManager(RecyclerViewDeviderActivity.this)); //默认垂直
                        mRecyclerView.removeItemDecoration(mGridDividerItemDecoration);
                        mRecyclerView.addItemDecoration(mListDividerItemDecoration);
                        break;

                    case R.id.grid:  //网格效果
                        int column = 3;  //列数
                        mRecyclerView.setLayoutManager(new GridLayoutManager(RecyclerViewDeviderActivity.this, column)); //默认垂直
                        mRecyclerView.removeItemDecoration(mListDividerItemDecoration);
                        mRecyclerView.addItemDecoration(mGridDividerItemDecoration);
                        break;
                }
            }
        });
    }
}
