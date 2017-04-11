package com.github.teocci.recyclercodesample.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.github.teocci.recyclercodesample.R;
import com.github.teocci.recyclercodesample.ui.fragment.FixedTwoWayFragment;
import com.github.teocci.recyclercodesample.ui.fragment.HorizontalFragment;
import com.github.teocci.recyclercodesample.ui.fragment.NavigationDrawerFragment;
import com.github.teocci.recyclercodesample.ui.fragment.NavigationDrawerFragment.NavigationDrawerCallbacks;
import com.github.teocci.recyclercodesample.ui.fragment.VerticalFragment;
import com.github.teocci.recyclercodesample.ui.fragment.VerticalGridFragment;
import com.github.teocci.recyclercodesample.ui.fragment.VerticalStaggeredGridFragment;


public class MainActivity extends AppCompatActivity implements
        NavigationDrawerCallbacks
{
    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment navigationDrawer;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence screenTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationDrawer = (NavigationDrawerFragment) getSupportFragmentManager().
                findFragmentById(R.id.navigation_drawer);
        screenTitle = getTitle();

        // Set up the drawer.
        navigationDrawer.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position)
    {
        // Updates the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        switch (position) {
            case 0:
                ft.replace(R.id.container, VerticalFragment.newInstance());
                break;
            case 1:
                ft.replace(R.id.container, HorizontalFragment.newInstance());
                break;
            case 2:
                ft.replace(R.id.container, VerticalGridFragment.newInstance());
                break;
            case 3:
                ft.replace(R.id.container, VerticalStaggeredGridFragment.newInstance());
                break;
            case 4:
                ft.replace(R.id.container, FixedTwoWayFragment.newInstance());
                break;
            default:
                //Do nothing
                break;
        }

        ft.commit();
    }

    public void restoreActionBar()
    {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(screenTitle);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        if (!navigationDrawer.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handles action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return super.onOptionsItemSelected(item);
    }
}
