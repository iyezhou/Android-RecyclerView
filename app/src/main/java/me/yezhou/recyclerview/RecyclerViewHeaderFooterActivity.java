package me.yezhou.recyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import me.yezhou.recyclerview.adapter.SimpleRecyclerViewAdapter;
import me.yezhou.recyclerview.devider.ListDividerItemDecoration;
import me.yezhou.recyclerview.model.User;
import me.yezhou.recyclerview.view.WrapRecyclerView;

/**
 * Created by yezhou on 2017/3/20.
 */
public class RecyclerViewHeaderFooterActivity extends AppCompatActivity {

    private WrapRecyclerView mRecyclerView;
    private ArrayList<User> mUserList;
    private SimpleRecyclerViewAdapter mAdapter;

    private RecyclerView.ItemDecoration mListDividerItemDecoration;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_header_footer);
        getSupportActionBar().hide();

        mUserList = new ArrayList<User>();
        for (int i = 0; i < 60; i++) {
            User user = new User();
            user.setName("中国" + String.format("%02d", (i+1)));
            user.setAvatarResId((R.drawable.h001 + i % 10));
            user.setMobile("1000" + i);
            mUserList.add(user);
        }

        mRecyclerView = (WrapRecyclerView) this.findViewById(R.id.recyclerview);

        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        TextView headerView1 = new TextView(this);
        headerView1.setLayoutParams(params);
        headerView1.setText("我是HeaderView1");
        headerView1.setTextSize(25);
        headerView1.setGravity(Gravity.CENTER_HORIZONTAL);
        mRecyclerView.addHeaderView(headerView1);

        TextView headerView2 = new TextView(this);
        headerView2.setLayoutParams(params);
        headerView2.setText("我是HeaderView2");
        headerView2.setTextSize(25);
        headerView2.setGravity(Gravity.CENTER_HORIZONTAL);
        mRecyclerView.addHeaderView(headerView2);

        TextView footerView = new TextView(this);
        footerView.setLayoutParams(params);
        footerView.setText("我是FooterView");
        footerView.setTextSize(25);
        footerView.setGravity(Gravity.CENTER_HORIZONTAL);
        mRecyclerView.addFooterView(footerView);

        mAdapter = new SimpleRecyclerViewAdapter(mUserList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
        mListDividerItemDecoration = new ListDividerItemDecoration(this, LinearLayoutManager.VERTICAL);
        mRecyclerView.addItemDecoration(mListDividerItemDecoration);
    }
}
