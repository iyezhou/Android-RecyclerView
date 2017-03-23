package me.yezhou.recyclerview.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by yezhou on 2017/3/22.
 */

public class WrapRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private RecyclerView.Adapter mAdapter;

    private ArrayList<View> mHeaderViewInfos;
    private ArrayList<View> mFooterViewInfos;

    public WrapRecyclerViewAdapter(ArrayList<View> headerViewInfos, ArrayList<View> footerViewInfos, RecyclerView.Adapter adapter) {
        mAdapter = adapter;

        if (headerViewInfos == null) {
            mHeaderViewInfos = new ArrayList<View>();
        } else {
            mHeaderViewInfos = headerViewInfos;
        }

        if (footerViewInfos == null) {
            mFooterViewInfos = new ArrayList<View>();
        } else {
            mFooterViewInfos = footerViewInfos;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /*
        if (viewType == RecyclerView.INVALID_TYPE) {  //header
            return new WrapViewHolder(mHeaderViewInfos.get(0));
        } else if(viewType == RecyclerView.INVALID_TYPE - 1) {  //footer
            return new WrapViewHolder(mFooterViewInfos.get(0));
        }
        */
        int numHeaders = getHeadersCount();
        if (RecyclerView.INVALID_TYPE - viewType >= 0 && RecyclerView.INVALID_TYPE - viewType < numHeaders) {
            return new WrapViewHolder(mHeaderViewInfos.get(RecyclerView.INVALID_TYPE - viewType));
        } else if (RecyclerView.INVALID_TYPE - viewType >= numHeaders) {
            return new WrapViewHolder(mFooterViewInfos.get(RecyclerView.INVALID_TYPE - viewType - numHeaders));
        }
        // Footer (off-limits positions will throw an IndexOutOfBoundsException)
        return mAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        // Header
        int numHeaders = getHeadersCount();
        if (position < numHeaders) {
            return ;
        }
        //Adapter Body
        final int adjPosition = position - numHeaders;
        int adapterCount = 0;
        if (mAdapter != null) {
            adapterCount = mAdapter.getItemCount();
            if (adjPosition < adapterCount) {
                mAdapter.onBindViewHolder(holder, adjPosition);
                return ;
            }
        }
        //Footer
    }

    @Override
    public int getItemViewType(int position) {
        // 判断当前条目是什么类型的，以此决定渲染什么视图
        // Header部分
        int numHeaders = getHeadersCount();
        if (position < numHeaders) {
            //return RecyclerView.INVALID_TYPE;
            return RecyclerView.INVALID_TYPE - position;
        }
        // 正常条目部分
        // Adapter
        final int adjPosition = position - numHeaders;
        int adapterCount = 0;
        if (mAdapter != null) {
            adapterCount = mAdapter.getItemCount();
            if (adjPosition < adapterCount) {
                return mAdapter.getItemViewType(adjPosition);
            }
        }
        // Footer部分
        //return RecyclerView.INVALID_TYPE - 1;
        return RecyclerView.INVALID_TYPE - position + adapterCount;
    }

    @Override
    public int getItemCount() {
        if (mAdapter != null) {
            return getFootersCount() + getHeadersCount() + mAdapter.getItemCount();
        } else {
            return getFootersCount() + getHeadersCount();
        }
    }

    public int getHeadersCount() {
        return mHeaderViewInfos.size();
    }

    public int getFootersCount() {
        return mFooterViewInfos.size();
    }

    public static class WrapViewHolder extends RecyclerView.ViewHolder {
        public WrapViewHolder(View view) {
            super(view);
        }
    }

    public final void notifyDataSetChangedDispatch() {
        mAdapter.notifyDataSetChanged();
    }

    public final void notifyItemRangeInsertedDispatch(int positionStart, int itemCount) {
        mAdapter.notifyItemRangeInserted(positionStart, itemCount);
    }

    public void notifyItemRangeRemovedDispatch(int positionStart, int itemCount) {
        mAdapter.notifyItemRangeRemoved(positionStart, itemCount);
    }

    public final void notifyItemRangeChangedDispatch(int positionStart, int itemCount) {
        mAdapter.notifyItemRangeChanged(positionStart, itemCount);
    }

    public final void notifyItemInsertedDispatch(int position) {
        mAdapter.notifyItemInserted(position);
    }

    public final void notifyItemRemovedDispatch(int position) {
        mAdapter.notifyItemRemoved(position);
    }

    public final void notifyItemChangedDispatch(int position) {
        mAdapter.notifyItemChanged(position);
    }

    public final void notifyItemMovedDispatch(int fromPosition, int toPosition) {
        mAdapter.notifyItemMoved(fromPosition, toPosition);
    }



}
