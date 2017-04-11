package com.github.teocci.recyclercodesample.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.github.teocci.recyclercodesample.ui.decoration.InsetDecoration;
import com.github.teocci.recyclercodesample.adapters.SimpleAdapter;
import com.github.teocci.recyclercodesample.layout.FixedGridLayoutManager;

/**
 * Created by teocci.
 *
 * @author teocci@yandex.com on 2017/Apr/11
 */

public class FixedTwoWayFragment extends RecyclerFragment
{
    public static FixedTwoWayFragment newInstance()
    {
        FixedTwoWayFragment fragment = new FixedTwoWayFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager()
    {
        FixedGridLayoutManager manager = new FixedGridLayoutManager();
        manager.setTotalColumnCount(10);

        return manager;
    }

    @Override
    protected RecyclerView.ItemDecoration getItemDecoration()
    {
        return new InsetDecoration(getActivity());
    }

    @Override
    protected int getDefaultItemCount()
    {
        return 5;
    }

    @Override
    protected SimpleAdapter getAdapter()
    {
        return new SimpleAdapter();
    }
}
