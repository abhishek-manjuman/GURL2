package com.example.gu_rl;

import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.gu_rl.com.classSchedule.ViewPagerAdapterClassRoom;

public class ClassRoomsActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private TabItem currentUserClass, otherClass;
    private TabLayout classSheadule;
    private ViewPager viewPager;
    ViewPagerAdapterClassRoom viewPagerAdapterClassRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_rooms);

        currentUserClass = (TabItem)findViewById(R.id.current_class_schedule_tabitem);
        otherClass = (TabItem)findViewById(R.id.other_class_schedule_tabitem);
        classSheadule = (TabLayout)findViewById(R.id.class_schedule_tablayout);
        viewPager = (ViewPager)findViewById(R.id.class_shedule_viewpager);

        viewPagerAdapterClassRoom = new ViewPagerAdapterClassRoom(getSupportFragmentManager(),classSheadule.getTabCount());
        viewPager.setAdapter(viewPagerAdapterClassRoom);

        classSheadule.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }


        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(classSheadule));

        toolbar = (Toolbar)findViewById(R.id.class_schedule_toolbar);
        setSupportActionBar(toolbar);
       // toolbar.setTitle("Basic Need");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()== android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
