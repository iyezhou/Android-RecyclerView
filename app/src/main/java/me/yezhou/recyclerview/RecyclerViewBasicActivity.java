package me.yezhou.recyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.RadioGroup;

import java.util.ArrayList;

import me.yezhou.recyclerview.adapter.BasicRecyclerViewAdapter;
import me.yezhou.recyclerview.adapter.BasicStaggeredRecyclerViewAdapter;

/**
 * Created by yezhou on 2017/3/20.
 */
public class RecyclerViewBasicActivity extends AppCompatActivity {

    private RadioGroup mRgRecyclerViewLayout = null;

    private RecyclerView recyclerView;
    private ArrayList<String> list;
    private BasicRecyclerViewAdapter mAdapter;
    private BasicStaggeredRecyclerViewAdapter mStaggeredAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);
        getSupportActionBar().hide();

        list = new ArrayList<String>();
        for (int i = 0; i < 60; i++) {
            list.add("Item" + String.format("%02d", (i+1)));
        }

        recyclerView = (RecyclerView) this.findViewById(R.id.recyclerview);
        mAdapter = new BasicRecyclerViewAdapter(list);
        //LayoutManager布局摆放管理器(线性摆放、网格摆放、瀑布流)
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); //默认垂直
        //LinearLayoutManager(Context context, int orientation, boolean reverseLayout)
        //reverseLayout: 数据倒置，从右边开始滑动
        //recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        recyclerView.setAdapter(mAdapter);

        mStaggeredAdapter = new BasicStaggeredRecyclerViewAdapter(list);

        mRgRecyclerViewLayout = (RadioGroup) this.findViewById(R.id.recyclerview_layout);
        mRgRecyclerViewLayout.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.linear:  //列表效果
                        recyclerView.setLayoutManager(new LinearLayoutManager(RecyclerViewBasicActivity.this)); //默认垂直
                        recyclerView.setAdapter(mAdapter);
                        break;

                    case R.id.grid:  //网格效果
                        int column = 3;  //列数
                        recyclerView.setLayoutManager(new GridLayoutManager(RecyclerViewBasicActivity.this, column)); //默认垂直
                        recyclerView.setAdapter(mAdapter);
                        break;

                    case R.id.staggered_grid:  //瀑布流效果
                        //StaggeredGridLayoutManager(int spanCount, int orientation)
                        //spanCount: 列数
                        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL));
                        recyclerView.setAdapter(mStaggeredAdapter);
                        break;
                }
            }
        });
    }
}
