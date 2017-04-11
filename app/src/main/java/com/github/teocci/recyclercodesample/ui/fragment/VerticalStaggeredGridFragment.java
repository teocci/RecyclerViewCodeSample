package com.github.teocci.recyclercodesample.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.github.teocci.recyclercodesample.ui.decoration.InsetDecoration;
import com.github.teocci.recyclercodesample.adapters.SimpleAdapter;
import com.github.teocci.recyclercodesample.adapters.SimpleStaggeredAdapter;

/**
 * Created by teocci.
 *
 * @author teocci@yandex.com on 2017/Apr/11
 */

public class VerticalStaggeredGridFragment extends RecyclerFragment
{
    public static VerticalStaggeredGridFragment newInstance()
    {
        VerticalStaggeredGridFragment fragment = new VerticalStaggeredGridFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager()
    {
        return new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
    }

    @Override
    protected RecyclerView.ItemDecoration getItemDecoration()
    {
        return new InsetDecoration(getActivity());
    }

    @Override
    protected int getDefaultItemCount()
    {
        return 100;
    }

    @Override
    protected SimpleAdapter getAdapter()
    {
        return new SimpleStaggeredAdapter();
    }
}
