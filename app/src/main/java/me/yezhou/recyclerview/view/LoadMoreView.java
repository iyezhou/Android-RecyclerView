package me.yezhou.recyclerview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import me.yezhou.recyclerview.R;

/**
 * Created by yezhou on 2017/3/23.
 */

public class LoadMoreView extends RelativeLayout implements View.OnClickListener {

    private TextView textView;
    private ProgressBar progressBar;

    public LoadMoreView(Context context) {
        this(context, null);
    }

    public LoadMoreView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadMoreView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        // View loadMoreView = LayoutInflater.from(context).inflate(R.layout.layout_load_more, null);
        // 解决居中问题
        View loadMoreView = LayoutInflater.from(context).inflate(R.layout.layout_load_more, this, false);
        loadMoreView.setOnClickListener(this);
        textView = (TextView) loadMoreView.findViewById(R.id.textview);
        progressBar = (ProgressBar) loadMoreView.findViewById(R.id.progressbar);
        addView(loadMoreView);
    }

    public void startLoading() {
        textView.setVisibility(INVISIBLE);
        progressBar.setVisibility(VISIBLE);
    }

    public void finishLoading() {
        textView.setVisibility(VISIBLE);
        progressBar.setVisibility(INVISIBLE);
    }

    @Override
    public void onClick(View view) {
        if (mOnLoadMoreListener != null) {
            mOnLoadMoreListener.onLoadMore(view);
        }
    }

    private OnLoadMoreListener mOnLoadMoreListener;

    public void setOnLoadMoreListener(OnLoadMoreListener listener) {
        mOnLoadMoreListener = listener;
    }

    public interface OnLoadMoreListener {
        void onLoadMore(View view);
    }

}
