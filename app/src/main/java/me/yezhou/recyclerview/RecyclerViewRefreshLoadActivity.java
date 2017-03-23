package me.yezhou.recyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import me.yezhou.recyclerview.adapter.SimpleRecyclerViewAdapter;
import me.yezhou.recyclerview.devider.ListDividerItemDecoration;
import me.yezhou.recyclerview.model.User;
import me.yezhou.recyclerview.refreshload.OnLoadMoreScrollListener;
import me.yezhou.recyclerview.view.LoadMoreView;
import me.yezhou.recyclerview.view.WrapRecyclerView;

/**
 * Created by yezhou on 2017/3/20.
 */
public class RecyclerViewRefreshLoadActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = "yezhou";

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private LoadMoreView mLoadMoreView;

    private WrapRecyclerView mRecyclerView;
    private ArrayList<User> mUserList;
    private SimpleRecyclerViewAdapter mAdapter;
    private RecyclerView.ItemDecoration mListDividerItemDecoration;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh_load);
        getSupportActionBar().hide();

        mUserList = new ArrayList<User>();
        for (int i = 0; i < 20; i++) {
            User user = new User();
            user.setName("中国" + String.format("%02d", (i+1)));
            user.setAvatarResId((R.drawable.h001 + i % 10));
            user.setMobile("1000" + i);
            mUserList.add(user);
        }

        // 下拉刷新通过实现 SwipeRefreshLayout.OnRefreshListener 接口来完成
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setColorSchemeResources(
                R.color.orange,
                R.color.green,
                R.color.pink,
                R.color.cyan
        );
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mRecyclerView = (WrapRecyclerView) this.findViewById(R.id.recyclerview);

        mLoadMoreView = new LoadMoreView(this);
        mRecyclerView.addFooterView(mLoadMoreView);
        mLoadMoreView.setOnLoadMoreListener(new LoadMoreView.OnLoadMoreListener() {
            @Override
            public void onLoadMore(View view) {
                loadMore(0);
            }
        });

        mAdapter = new SimpleRecyclerViewAdapter(mUserList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager); //默认垂直
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);
        // 分割线
        mListDividerItemDecoration = new ListDividerItemDecoration(this, LinearLayoutManager.VERTICAL);
        mRecyclerView.addItemDecoration(mListDividerItemDecoration);
        mAdapter.setOnItemClickListener(new SimpleRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, User user) {
                Toast.makeText(RecyclerViewRefreshLoadActivity.this, user.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        // 加载更多通过 LinearLayoutManager 来获取 RecyclerView 是否滑动到底部来实现
        // 滑动监听：RecyclerView.addOnScrollListener(RecyclerView.OnScrollListener)
        mRecyclerView.addOnScrollListener(new OnLoadMoreScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int currentPage) {
                Log.i(TAG, "当前页: " + currentPage);
                loadMore(currentPage);
            }
        });
    }

    @Override
    public void onRefresh() {
        Observable.timer(3, TimeUnit.SECONDS).map(new Function<Long, List<User>>() {
            @Override
            public List<User> apply(@NonNull Long aLong) throws Exception {
                List<User> userList = new ArrayList<User>();
                for (int i = 0; i < 3; i++) {
                    User user = new User();
                    user.setName("上海" + String.format("%02d", (i+1)));
                    user.setAvatarResId((R.drawable.h005 + i % 10));
                    user.setMobile("2000" + i);
                    userList.add(user);
                }
                return userList;
            }
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<User>>() {
            @Override
            public void accept(@NonNull List<User> userList) throws Exception {
                mUserList.addAll(0, userList);
                mRecyclerView.notifyDataSetChanged();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void loadMore(int currentPage) {
        mLoadMoreView.startLoading();
        Observable.timer(3, TimeUnit.SECONDS).map(new Function<Long, List<User>>() {
            @Override
            public List<User> apply(@NonNull Long aLong) throws Exception {
                List<User> userList = new ArrayList<User>();
                for (int i = mUserList.size(); i < mUserList.size()+5; i++) {
                    User user = new User();
                    user.setName("中国" + String.format("%02d", (i+1)));
                    user.setAvatarResId((R.drawable.h001 + i % 10));
                    user.setMobile("1000" + i);
                    userList.add(user);
                }
                return userList;
            }
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<User>>() {
            @Override
            public void accept(@NonNull List<User> userList) throws Exception {
                mLoadMoreView.finishLoading();
                int size = mUserList.size();
                if (size <= 30) {
                    mUserList.addAll(userList);
                    mRecyclerView.notifyItemRangeInserted(size, userList.size());
                } else {
                    Toast.makeText(RecyclerViewRefreshLoadActivity.this, "已加载全部", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
