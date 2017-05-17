package com.github.teocci.recyclercodesample.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.github.teocci.recyclercodesample.ui.decoration.DividerDecoration;
import com.github.teocci.recyclercodesample.adapters.SimpleAdapter;

/**
 * Created by teocci.
 *
 * @author teocci@yandex.com on 2017/Apr/11
 */

public class VerticalFragment extends RecyclerFragment
{
    public static VerticalFragment newInstance()
    {
        VerticalFragment fragment = new VerticalFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager()
    {
        return new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
    }

    @Override
    protected RecyclerView.ItemDecoration getItemDecoration()
    {
        //We must draw dividers ourselves if we want them in a list
        return new DividerDecoration(getActivity());
    }

    @Override
    protected int getDefaultItemCount()
    {
        return 100;
    }

    @Override
    protected SimpleAdapter getAdapter()
    {
        return new SimpleAdapter(recyclerView);
    }
}
