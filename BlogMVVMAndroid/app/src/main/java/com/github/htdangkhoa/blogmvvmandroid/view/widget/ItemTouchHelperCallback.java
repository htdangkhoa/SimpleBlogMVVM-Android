package com.github.htdangkhoa.blogmvvmandroid.view.widget;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by dangkhoa on 4/13/18.
 */

public class ItemTouchHelperCallback extends ItemTouchHelper.Callback {
    IItemTouch iItemTouch;

    public ItemTouchHelperCallback(IItemTouch iItemTouch) {
        this.iItemTouch = iItemTouch;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        return makeMovementFlags(0, ItemTouchHelper.START);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                          RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        iItemTouch.onSwiped(viewHolder, direction);
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                            float dX, float dY, int actionState, boolean isCurrentlyActive) {
        if (actionState == android.support.v7.widget.helper.ItemTouchHelper.ACTION_STATE_SWIPE) {
            float alpha = 1 - (Math.abs(dX) / recyclerView.getWidth());
            viewHolder.itemView.setAlpha(alpha);
        }

        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }

    public interface IItemTouch {
        void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDirection);
    }
}
