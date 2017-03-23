package me.yezhou.recyclerview.itemtouch;

import android.support.v7.widget.RecyclerView;

/**
 * Created by yezhou on 2017/3/22.
 */

public interface OnItemDragListener {
    /**
     * 回调开始拖拽
     * @param viewHolder
     */
    void onStartDrag(RecyclerView.ViewHolder viewHolder);
}
