package com.github.teocci.recyclercodesample.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.github.teocci.recyclercodesample.R;

public class SimpleStaggeredAdapter extends SimpleAdapter
{
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder itemHolder, int position)
    {
        super.onBindViewHolder(itemHolder, position);

        final View itemView = itemHolder.itemView;
        if (position % 4 == 0) {
            int height = itemView.getContext().getResources()
                    .getDimensionPixelSize(R.dimen.card_staggered_height);
            itemView.setMinimumHeight(height);
        } else {
            itemView.setMinimumHeight(0);
        }
    }
}
