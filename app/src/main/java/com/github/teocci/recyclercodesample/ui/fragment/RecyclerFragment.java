package com.github.teocci.recyclercodesample.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.github.teocci.recyclercodesample.NumberPickerDialog;
import com.github.teocci.recyclercodesample.R;
import com.github.teocci.recyclercodesample.SimpleItemTouchCallback;
import com.github.teocci.recyclercodesample.adapters.SimpleAdapter;
import com.github.teocci.recyclercodesample.interfaces.OnNumberSelectedListener;
import com.github.teocci.recyclercodesample.interfaces.OnStartDragListener;

/**
 * Created by teocci.
 *
 * @author teocci@yandex.com on 2017/Apr/11
 */

public abstract class RecyclerFragment extends Fragment implements AdapterView.OnItemClickListener,
        OnStartDragListener
{
    private RecyclerView recyclerView;
    private SimpleAdapter simpleAdapter;
    private ItemTouchHelper itemTouchHelper;

    /**
     * Required Overrides for Sample Fragments
     */

    protected abstract RecyclerView.LayoutManager getLayoutManager();

    protected abstract RecyclerView.ItemDecoration getItemDecoration();

    protected abstract int getDefaultItemCount();

    protected abstract SimpleAdapter getAdapter();

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_recycler, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.section_list);
        recyclerView.setLayoutManager(getLayoutManager());
        recyclerView.addItemDecoration(getItemDecoration());

        recyclerView.getItemAnimator().setAddDuration(500);
        recyclerView.getItemAnimator().setChangeDuration(500);
        recyclerView.getItemAnimator().setMoveDuration(500);
        recyclerView.getItemAnimator().setRemoveDuration(500);

        simpleAdapter = getAdapter();
        simpleAdapter.setItemCount(getDefaultItemCount());
        simpleAdapter.setOnItemClickListener(this);
        simpleAdapter.setOnStartDragListener(this);
        recyclerView.setAdapter(simpleAdapter);

        ItemTouchHelper.Callback callback = new SimpleItemTouchCallback(simpleAdapter);
        itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        inflater.inflate(R.menu.grid_options, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        NumberPickerDialog dialog;
        switch (item.getItemId()) {
            case R.id.action_add:
                dialog = new NumberPickerDialog(getActivity());
                dialog.setTitle("Position to Add");
                dialog.setPickerRange(0, simpleAdapter.getItemCount());
                dialog.setOnNumberSelectedListener(new OnNumberSelectedListener()
                {
                    @Override
                    public void onNumberSelected(int value)
                    {
                        simpleAdapter.addItem(value);
                    }
                });
                dialog.show();

                return true;
            case R.id.action_remove:
                dialog = new NumberPickerDialog(getActivity());
                dialog.setTitle("Position to Remove");
                dialog.setPickerRange(0, simpleAdapter.getItemCount() - 1);
                dialog.setOnNumberSelectedListener(new OnNumberSelectedListener()
                {
                    @Override
                    public void onNumberSelected(int value)
                    {
                        simpleAdapter.removeItem(value);
                    }
                });
                dialog.show();

                return true;
            case R.id.action_empty:
                simpleAdapter.setItemCount(0);
                return true;
            case R.id.action_small:
                simpleAdapter.setItemCount(5);
                return true;
            case R.id.action_medium:
                simpleAdapter.setItemCount(25);
                return true;
            case R.id.action_large:
                simpleAdapter.setItemCount(196);
                return true;
            case R.id.action_scroll_zero:
                recyclerView.scrollToPosition(0);
                return true;
            case R.id.action_smooth_zero:
                recyclerView.smoothScrollToPosition(0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        Toast.makeText(getActivity(),
                "Clicked: " + position + ", index " + recyclerView.indexOfChild(view),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        itemTouchHelper.startDrag(viewHolder);
    }
}
