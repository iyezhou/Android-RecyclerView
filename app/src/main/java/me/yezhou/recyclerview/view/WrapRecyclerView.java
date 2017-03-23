package me.yezhou.recyclerview.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

import me.yezhou.recyclerview.adapter.WrapRecyclerViewAdapter;

/**
 * Created by yezhou on 2017/3/22.
 */

public class WrapRecyclerView extends RecyclerView {

    private ArrayList<View> mHeaderViewInfos = new ArrayList<View>();
    private ArrayList<View> mFooterViewInfos = new ArrayList<View>();
    private Adapter mAdapter;

    public WrapRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void addHeaderView(View v) {
        mHeaderViewInfos.add(v);
        // Wrap the adapter if it wasn't already wrapped.
        if (mAdapter != null) {
            if (!(mAdapter instanceof WrapRecyclerViewAdapter)) {
                mAdapter = new WrapRecyclerViewAdapter(mHeaderViewInfos, mFooterViewInfos, mAdapter);
            }
        }
    }

    public void addFooterView(View v) {
        mFooterViewInfos.add(v);
        // Wrap the adapter if it wasn't already wrapped.
        if (mAdapter != null) {
            if (!(mAdapter instanceof WrapRecyclerViewAdapter)) {
                mAdapter = new WrapRecyclerViewAdapter(mHeaderViewInfos, mFooterViewInfos, mAdapter);
            }
        }
    }

    @Override
    public void setAdapter(Adapter adapter) {
        if (mHeaderViewInfos.size() > 0 || mFooterViewInfos.size() > 0) {
            Log.i("yezhou", "HeaderViewSize=" + mHeaderViewInfos.size() + ", FooterViewSize=" + mFooterViewInfos.size());
            mAdapter = new WrapRecyclerViewAdapter(mHeaderViewInfos, mFooterViewInfos, adapter);
        } else {
            mAdapter = adapter;
        }
        super.setAdapter(mAdapter);
    }

    // 通知分发
    public final void notifyDataSetChanged() {
        if (mAdapter != null) {
            if (!(mAdapter instanceof WrapRecyclerViewAdapter)) {
                ((WrapRecyclerViewAdapter) mAdapter).notifyDataSetChangedDispatch();
            } else {
                mAdapter.notifyDataSetChanged();
            }
        }
    }

    public final void notifyItemRangeInserted(int positionStart, int itemCount) {
        if (mAdapter != null) {
            if (!(mAdapter instanceof WrapRecyclerViewAdapter)) {
                ((WrapRecyclerViewAdapter) mAdapter).notifyItemRangeInsertedDispatch(positionStart, itemCount);
            } else {
                mAdapter.notifyItemRangeInserted(positionStart, itemCount);
            }
        }
    }

    public final void notifyItemRangeRemoved(int positionStart, int itemCount) {
        if (mAdapter != null) {
            if (!(mAdapter instanceof WrapRecyclerViewAdapter)) {
                ((WrapRecyclerViewAdapter) mAdapter).notifyItemRangeRemovedDispatch(positionStart, itemCount);
            } else {
                mAdapter.notifyItemRangeRemoved(positionStart, itemCount);
            }
        }
    }

    public final void notifyItemRangeChangedWrap(int positionStart, int itemCount) {
        if (mAdapter != null) {
            if (!(mAdapter instanceof WrapRecyclerViewAdapter)) {
                ((WrapRecyclerViewAdapter) mAdapter).notifyItemRangeChangedDispatch(positionStart, itemCount);
            } else {
                mAdapter.notifyItemRangeChanged(positionStart, itemCount);
            }
        }
    }

    public final void notifyItemInserted(int position) {
        if (mAdapter != null) {
            if (!(mAdapter instanceof WrapRecyclerViewAdapter)) {
                ((WrapRecyclerViewAdapter) mAdapter).notifyItemInsertedDispatch(position);
            } else {
                mAdapter.notifyItemInserted(position);
            }
        }
    }

    public final void notifyItemRemoved(int position) {
        if (mAdapter != null) {
            if (!(mAdapter instanceof WrapRecyclerViewAdapter)) {
                ((WrapRecyclerViewAdapter) mAdapter).notifyItemRemovedDispatch(position);
            } else {
                mAdapter.notifyItemRemoved(position);
            }
        }
    }

    public final void notifyItemChanged(int position) {
        if (mAdapter != null) {
            if (!(mAdapter instanceof WrapRecyclerViewAdapter)) {
                ((WrapRecyclerViewAdapter) mAdapter).notifyItemChangedDispatch(position);
            } else {
                mAdapter.notifyItemChanged(position);
            }
        }
    }

    public final void notifyItemMoved(int fromPosition, int toPosition) {
        if (mAdapter != null) {
            if (!(mAdapter instanceof WrapRecyclerViewAdapter)) {
                ((WrapRecyclerViewAdapter) mAdapter).notifyItemMovedDispatch(fromPosition, toPosition);
            } else {
                mAdapter.notifyItemMoved(fromPosition, toPosition);
            }
        }
    }

}
