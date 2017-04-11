package com.github.teocci.recyclercodesample.adapters;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridLayout;
import android.widget.TextView;

import com.github.teocci.recyclercodesample.R;

public class GameItemView extends GridLayout
{
    private TextView homeScore, awayScore;

    public GameItemView(Context context)
    {
        super(context);
    }

    public GameItemView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public GameItemView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate()
    {
        super.onFinishInflate();

        homeScore = (TextView) findViewById(R.id.text_score_home);
        awayScore = (TextView) findViewById(R.id.text_score_away);
    }

    @Override
    public String toString()
    {
        return awayScore.getText() + "v" + homeScore.getText()
                + ": " + getLeft() + "," + getTop()
                + ": " + getMeasuredWidth() + "x" + getMeasuredHeight();
    }
}
