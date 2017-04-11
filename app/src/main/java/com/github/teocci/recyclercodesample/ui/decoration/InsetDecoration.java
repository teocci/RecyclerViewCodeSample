package com.github.teocci.recyclercodesample.ui.decoration;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.github.teocci.recyclercodesample.R;

/**
 * Created by teocci.
 *
 * @author teocci@yandex.com on 2017/Apr/11
 *
 * ItemDecoration implementation that applies an inset margin
 * around each child of the RecyclerView. The inset value is controlled
 * by a dimension resource.
 */
public class InsetDecoration extends RecyclerView.ItemDecoration
{
    private int insets;

    public InsetDecoration(Context context)
    {
        insets = context.getResources().getDimensionPixelSize(R.dimen.card_insets);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state)
    {
        // We can supply forced insets for each item view here in the Rect
        outRect.set(insets, insets, insets, insets);
    }
}
