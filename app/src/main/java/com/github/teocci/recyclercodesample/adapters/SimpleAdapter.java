package com.github.teocci.recyclercodesample.adapters;

import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.github.teocci.recyclercodesample.R;
import com.github.teocci.recyclercodesample.interfaces.ItemTouchListener;
import com.github.teocci.recyclercodesample.interfaces.OnStartDragListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SimpleAdapter extends RecyclerView.Adapter<SimpleAdapter.VerticalItemHolder>
        implements ItemTouchListener, OnStartDragListener
{
    private static final String TAG = SimpleAdapter.class.getSimpleName();
    private ArrayList<GameItem> items;

    private AdapterView.OnItemClickListener onItemClickListener;

    public SimpleAdapter()
    {
        items = new ArrayList<>();
    }

    /**
     * A common adapter modification or reset mechanism. As with ListAdapter,
     * calling notifyDataSetChanged() will trigger the RecyclerView to update
     * the view. However, this method will not trigger any of the RecyclerView
     * animation features.
     */
    public void setItemCount(int count)
    {
        items.clear();
        items.addAll(generateDummyData(count));

        notifyDataSetChanged();
    }

    /**
     * Inserting a new item at the head of the list. This uses a specialized
     * RecyclerView method, notifyItemInserted(), to trigger any enabled item
     * animations in addition to updating the view.
     */
    public void addItem(int position)
    {
        if (position > items.size()) return;

        items.add(position, generateDummyItem());
        notifyItemInserted(position);
    }

    /**
     * Inserting a new item at the head of the list. This uses a specialized
     * RecyclerView method, notifyItemRemoved(), to trigger any enabled item
     * animations in addition to updating the view.
     */
    public void removeItem(int position)
    {
        if (position >= items.size()) return;

        items.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public VerticalItemHolder onCreateViewHolder(ViewGroup container, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(container.getContext());
        View root = inflater.inflate(R.layout.view_match_item, container, false);

        return new VerticalItemHolder(root, this);
    }

    @Override
    public void onBindViewHolder(VerticalItemHolder itemHolder, int position)
    {
        GameItem item = items.get(position);

        itemHolder.setAwayScore(String.valueOf(item.awayScore));
        itemHolder.setHomeScore(String.valueOf(item.homeScore));

        itemHolder.setAwayName(item.awayTeam);
        itemHolder.setHomeName(item.homeTeam);
    }

    @Override
    public int getItemCount()
    {
        return items.size();
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener)
    {
        this.onItemClickListener = onItemClickListener;
    }

    private void onItemHolderClick(VerticalItemHolder viewHolder)
    {
        if (onItemClickListener != null) {
            onItemClickListener.onItemClick(null, viewHolder.itemView,
                    viewHolder.getAdapterPosition(), viewHolder.getItemId());
        }
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition)
    {
        Collections.swap(items, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public void onItemDismiss(int position)
    {
        items.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder)
    {
        Log.e(TAG, "onStartDrag");
    }

    public static class GameItem
    {
        public String homeTeam;
        public String awayTeam;
        public int homeScore;
        public int awayScore;

        public GameItem(String homeTeam, String awayTeam, int homeScore, int awayScore)
        {
            this.homeTeam = homeTeam;
            this.awayTeam = awayTeam;
            this.homeScore = homeScore;
            this.awayScore = awayScore;
        }
    }

    public static class VerticalItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
            View.OnTouchListener
    {
        private TextView homeScore, awayScore;
        private TextView homeName, awayName;

        private SimpleAdapter adapter;

        public VerticalItemHolder(View itemView, SimpleAdapter adapter)
        {
            super(itemView);
            itemView.setOnClickListener(this);

            this.adapter = adapter;

            homeScore = (TextView) itemView.findViewById(R.id.text_score_home);
            awayScore = (TextView) itemView.findViewById(R.id.text_score_away);
            homeName = (TextView) itemView.findViewById(R.id.text_team_home);
            awayName = (TextView) itemView.findViewById(R.id.text_team_away);
        }

        @Override
        public void onClick(View v)
        {
            adapter.onItemHolderClick(this);
        }

        public void setHomeScore(CharSequence homeScore)
        {
            this.homeScore.setText(homeScore);
        }

        public void setAwayScore(CharSequence awayScore)
        {
            this.awayScore.setText(awayScore);
        }

        public void setHomeName(CharSequence homeName)
        {
            this.homeName.setText(homeName);
        }

        public void setAwayName(CharSequence awayName)
        {
            this.awayName.setText(awayName);
        }

        @Override
        public boolean onTouch(View v, MotionEvent event)
        {
            // Start a drag whenever the handle view it touched
            if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                adapter.onStartDrag(this);
            }
            return false;
        }
    }

    public static GameItem generateDummyItem()
    {
        Random random = new Random();
        return new GameItem("Upset Home", "Upset Away",
                random.nextInt(100),
                random.nextInt(100));
    }

    public static List<SimpleAdapter.GameItem> generateDummyData(int count)
    {
        ArrayList<SimpleAdapter.GameItem> items = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            items.add(new SimpleAdapter.GameItem("Losers", "Winners", i, i + 5));
        }

        return items;
    }
}
