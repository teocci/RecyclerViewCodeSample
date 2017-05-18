package com.github.teocci.recyclercodesample.adapters;

import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.teocci.recyclercodesample.R;
import com.github.teocci.recyclercodesample.interfaces.ItemTouchListener;
import com.github.teocci.recyclercodesample.interfaces.OnLoadMoreListener;
import com.github.teocci.recyclercodesample.interfaces.OnStartDragListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SimpleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
        implements ItemTouchListener
{
    private static final String TAG = SimpleAdapter.class.getSimpleName();

    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADER = 1;

    private ArrayList<GameItem> items;

    private AdapterView.OnItemClickListener onItemClickListener;
    private OnStartDragListener onStartDragListener;
    private OnLoadMoreListener onLoadMoreListener;

    public SimpleAdapter()
    {
        items = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup container, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(container.getContext());
        if (viewType == VIEW_TYPE_ITEM) {
            View root = inflater.inflate(R.layout.view_match_item, container, false);

            return new VerticalItemHolder(root, this);
        } else if (viewType == VIEW_TYPE_LOADER) {
            View root = inflater.inflate(R.layout.loading_item, container, false);

            return new LoaderViewHolder(root);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder itemHolder, int position)
    {
        if (itemHolder instanceof VerticalItemHolder) {
            GameItem item = items.get(position);
            VerticalItemHolder  verticalItemHolder = (VerticalItemHolder) itemHolder;
            verticalItemHolder.setAwayScore(String.valueOf(item.awayScore));
            verticalItemHolder.setHomeScore(String.valueOf(item.homeScore));

            verticalItemHolder.setAwayName(item.awayTeam);
            verticalItemHolder.setHomeName(item.homeTeam);
        } else if (itemHolder instanceof LoaderViewHolder) {
            LoaderViewHolder loaderViewHolder = (LoaderViewHolder) itemHolder;
            loaderViewHolder.progressBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemViewType(int position)
    {
        return items.get(position) == null ? VIEW_TYPE_LOADER : VIEW_TYPE_ITEM;
    }

    @Override
    public int getItemCount()
    {
        return items == null ? 0 : items.size();
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition)
    {
        Collections.swap(items, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public void onItemDismiss(final RecyclerView.ViewHolder viewHolder)
    {
        items.remove(viewHolder.getAdapterPosition());
        notifyItemRemoved(viewHolder.getAdapterPosition());
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
     * RecyclerView method, notifyItemInserted(), to trigger any enabled item
     * animations in addition to updating the view.
     */
    public void addMoreItems(int count)
    {
        items.addAll(generateDummyData(count));
        Log.e(TAG, "adding " + count + " more elements.");
        notifyDataSetChanged();
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

    public void addLoader() {
        items.add(null);
        notifyItemInserted(items.size() - 1);
    }

    public void removeLoader() {
        items.remove(items.size() - 1);
        notifyItemRemoved(items.size());
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener)
    {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnStartDragListener(OnStartDragListener onStartDragListener)
    {
        this.onStartDragListener = onStartDragListener;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener)
    {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    private void onItemHolderClick(VerticalItemHolder viewHolder)
    {
        if (onItemClickListener != null) {
            onItemClickListener.onItemClick(null, viewHolder.itemView,
                    viewHolder.getAdapterPosition(), viewHolder.getItemId());
        }
    }

    private void onItemHolderStartDrag(VerticalItemHolder viewHolder)
    {
        if (onStartDragListener != null) {
            onStartDragListener.onStartDrag(viewHolder);
        }
    }

    public void onItemHolderOnLoadMore()
    {
        if (onLoadMoreListener != null) {
            onLoadMoreListener.onLoadMore();
        }
    }

    private static class GameItem
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

    private static class LoaderViewHolder extends RecyclerView.ViewHolder
    {
        private ProgressBar progressBar;

        private LoaderViewHolder(View itemView)
        {
            super(itemView);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progress_bar);
        }
    }

    private static class VerticalItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
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

        @Override
        public boolean onTouch(View v, MotionEvent event)
        {
            // Start a drag whenever the handle view it touched
            if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                adapter.onItemHolderStartDrag(this);
            }
            return false;
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
