package com.ashomok.eNumbers.activities;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.ashomok.eNumbers.R;
import com.ashomok.eNumbers.menu.Menu;
import com.ashomok.eNumbers.menu.RowsAdapter;
import com.ashomok.eNumbers.menu.Row;
import com.ashomok.eNumbers.menu.RowClickListener;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActionBarDrawerToggle toggle;

    private ListView mDrawerList;

    private DrawerLayout mDrawerLayout;

    private CharSequence mTitle;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.start_activity_layout);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setLogo(R.mipmap.ic_launcher);

        //Left menu settings
        try {
            mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
            toggle = new ActionBarDrawerToggle(
                    this,
                    mDrawerLayout,
                    toolbar,
                    R.string.navigation_drawer_open,
                    R.string.navigation_drawer_close) {
            };

            toggle.setDrawerIndicatorEnabled(true);

            if (getSupportActionBar() != null) {
                getSupportActionBar().setHomeButtonEnabled(true);
            }

            // Set the drawer toggle as the DrawerListener
            mDrawerLayout.setDrawerListener(toggle);

            mDrawerList = (ListView) findViewById(R.id.lv_navigation_drawer);

            List<Row> menuItems = Menu.getRows();

            mDrawerList.setAdapter(new RowsAdapter(this, menuItems));

            mDrawerList.setOnItemClickListener(new DrawerItemClickListener(this));

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(mTitle);
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        toggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        toggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStackImmediate();
        } else {
            super.onBackPressed();
        }
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        private RowClickListener rowClickListener;


        public DrawerItemClickListener(Context context) {
            rowClickListener = new RowClickListener(context);
        }

        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {

            rowClickListener.onRowClicked(position);

            // Highlight the selected item, update the title, and close the drawer
            mDrawerList.setItemChecked(position, true);
            RelativeLayout layout = (RelativeLayout) findViewById(R.id.menu);
            mDrawerLayout.closeDrawer(layout);
        }
    }
}