package com.github.teocci.recyclercodesample.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.github.teocci.recyclercodesample.ui.decoration.InsetDecoration;
import com.github.teocci.recyclercodesample.adapters.SimpleAdapter;

/**
 * Created by teocci.
 *
 * @author teocci@yandex.com on 2017/Apr/11
 */

public class VerticalGridFragment extends RecyclerFragment
{
    public static VerticalGridFragment newInstance()
    {
        VerticalGridFragment fragment = new VerticalGridFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager()
    {
        return new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
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
        return new SimpleAdapter();
    }
}
