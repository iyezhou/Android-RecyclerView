package me.yezhou.recyclerview.itemtouch;

import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import me.yezhou.recyclerview.R;

/**
 * Created by yezhou on 2017/3/22.
 */

public class ItemTouchHelperCallback extends ItemTouchHelper.Callback {

    private OnItemTouchListener itemTouchListener;

    public ItemTouchHelperCallback(OnItemTouchListener itemTouchListener) {
        this.itemTouchListener = itemTouchListener;
    }

    // Callback回调监听时首先调用的，用来判断当前是什么动作，比如判断方向（意思就是我要监听哪个方向的拖动）
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        // 方向常量：up,down,left,right
        int up = ItemTouchHelper.UP; //0x0001
        int down = ItemTouchHelper.DOWN; //0x0010
//		ItemTouchHelper.LEFT
//		ItemTouchHelper.RIGHT
        // 需要监听的drag拖拽方向是哪两个方向
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        // 需要监听的swipe侧滑方向是哪个方向
//		int swipeFlags = 0;
        int swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        int flags = makeMovementFlags(dragFlags, swipeFlags);
        return flags;
    }

    @Override
    public boolean isLongPressDragEnabled() {
        // 是否允许长按拖拽效果
        return true;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder srcHolder, RecyclerView.ViewHolder targetHolder) {
        if (srcHolder.getItemViewType() != targetHolder.getItemViewType()) {
            return false;
        }
        // 在拖拽的过程当中不断地调用adapter.notifyItemMoved(from, to);
        boolean result = itemTouchListener.onItemMove(srcHolder.getAdapterPosition(), targetHolder.getAdapterPosition());
        return result;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder holder, int direction) {
        // 监听侧滑
        // 1.删除数据
        // 2.调用adapter.notifyItemRemove(position)
        itemTouchListener.onItemRemove(holder.getAdapterPosition());
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        // 判断选中状态
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            viewHolder.itemView.setBackgroundColor(viewHolder.itemView.getContext().getResources().getColor(R.color.colorAccent));
        }
        super.onSelectedChanged(viewHolder, actionState);
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        // 恢复
        viewHolder.itemView.setBackgroundColor(Color.TRANSPARENT);
        super.clearView(recyclerView, viewHolder);
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        // 添加侧滑动画效果
        // dX: 水平方向移动的增量（负:往左; 正:往右）范围：0~View.getWidth  0~1
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            //透明度动画
            float alpha = 1 - Math.abs(dX) / viewHolder.itemView.getWidth();
            viewHolder.itemView.setAlpha(alpha); //1~0
            viewHolder.itemView.setScaleX(alpha); //1~0
            viewHolder.itemView.setScaleY(alpha); //1~0
        }
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }
}
