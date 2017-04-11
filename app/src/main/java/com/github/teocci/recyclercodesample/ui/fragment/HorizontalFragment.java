package com.github.teocci.recyclercodesample.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.github.teocci.recyclercodesample.ui.decoration.InsetDecoration;
import com.github.teocci.recyclercodesample.adapters.SimpleAdapter;

/**
 * Created by teocci.
 *
 * @author teocci@yandex.com on 2017/Apr/11
 */

public class HorizontalFragment extends RecyclerFragment
{
    public static HorizontalFragment newInstance()
    {
        HorizontalFragment fragment = new HorizontalFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager()
    {
        return new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
    }

    @Override
    protected RecyclerView.ItemDecoration getItemDecoration()
    {
        return new InsetDecoration(getActivity());
    }

    @Override
    protected int getDefaultItemCount()
    {
        return 40;
    }

    @Override
    protected SimpleAdapter getAdapter()
    {
        return new SimpleAdapter();
    }
}
