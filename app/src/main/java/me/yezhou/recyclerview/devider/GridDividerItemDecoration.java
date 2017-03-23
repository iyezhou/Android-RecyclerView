package me.yezhou.recyclerview.devider;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import me.yezhou.recyclerview.R;

/**
 * Created by yezhou on 2017/3/21.
 */

public class GridDividerItemDecoration extends RecyclerView.ItemDecoration {

    private Drawable mDivider;
    private int[] attrs= new int[]{
            android.R.attr.listDivider
            //R.drawable.item_divider  // 自定义分割线
    };

    public GridDividerItemDecoration(Context context) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs);
        mDivider = typedArray.getDrawable(0);
        typedArray.recycle();
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        drawVertical(c, parent);
        drawHorizontal(c, parent);
    }

    private void drawHorizontal(Canvas c, RecyclerView parent) {
        // 绘制水平间隔线
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int left = child.getLeft() - params.leftMargin;
            int right = child.getRight()+ params.rightMargin;
            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    private void drawVertical(Canvas c, RecyclerView parent) {
        // 绘制垂直间隔线(垂直的矩形)
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int left = child.getRight() + params.rightMargin;
            int right = left + mDivider.getIntrinsicWidth();
            int top = child.getTop() - params.topMargin;
            int bottom = child.getBottom() + params.bottomMargin;
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    @Override
    @Deprecated
    public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
        // 四个方向的偏移值
        int right = mDivider.getIntrinsicWidth();
        int bottom = mDivider.getIntrinsicHeight();
        if (isLastColum(parent, itemPosition)) {  //最后一列
            right = 0;
        }
        if (isLastRow(parent, itemPosition)) {  //最后一行
            bottom = 0;
        }
        // 四个方向的偏移值
        outRect.set(0, 0, right, bottom);
    }

    /**
     * 判断是否最后一行
     * @param parent
     * @param position
     * @return
     */
    private boolean isLastRow(RecyclerView parent, int position) {
        int spanCount =  getSpanCount(parent);
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            int childCount = parent.getAdapter().getItemCount();
            int rowCount = (int) Math.ceil(childCount * 1.0 / spanCount);  // 总行数
            int currRow = (int) Math.ceil((position + 1.0) / spanCount);  // 总行数
            if (currRow == rowCount) {
                Log.i("yezhou", "LastRow: " + position);
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否最后一列
     * @param parent
     * @param position
     * @return
     */
    private boolean isLastColum(RecyclerView parent, int position) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            int spanCount = getSpanCount(parent);  // 总列数
            if ((position + 1) % spanCount == 0) {
                return true;
            }
        }
        return false;
    }

    private int getSpanCount(RecyclerView parent) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            GridLayoutManager lm = (GridLayoutManager) layoutManager;
            int spanCount = lm.getSpanCount();
            return spanCount;
        }
        return 0;
    }
}
