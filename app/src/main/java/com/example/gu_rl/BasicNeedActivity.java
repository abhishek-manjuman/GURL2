package com.example.gu_rl;

import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.example.gu_rl.com.basicNeed.ViewPagerAdapter;

public class BasicNeedActivity extends AppCompatActivity {

    private Toolbar toolbar;
//    private CardView library, sport, cafeteria, waiting_room, washroom;

    private TabItem libraryTab, cafeteriaTab, sportTab, waitingroomTab, washroomTab;
    private TabLayout basicNeedTabs;
    private ViewPager basicNeedViewPager;
    private ImageView tabImage;

    ViewPagerAdapter viewPagerAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_need);

        toolbar = (Toolbar)findViewById(R.id.basic_need_back);

        basicNeedTabs= (TabLayout)findViewById(R.id.basic_need_tabs);
        basicNeedViewPager = (ViewPager)findViewById(R.id.basic_need_viewPager);
        libraryTab = (TabItem)findViewById(R.id.library_tab);
        cafeteriaTab = (TabItem)findViewById(R.id.cafeteria_tab);
        sportTab = (TabItem)findViewById(R.id.sport_tab);
        waitingroomTab = (TabItem)findViewById(R.id.waitingroom_tab);
        washroomTab = (TabItem)findViewById(R.id.washroom_tab);
        //tabImage = (ImageView)findViewById(R.id.tabIcon);

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), basicNeedTabs.getTabCount());
        basicNeedViewPager.setAdapter(viewPagerAdapter);

        basicNeedTabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                basicNeedViewPager.setCurrentItem(tab.getPosition());
//
//                if(tab.getPosition() == 0){
//                    tabImage.setImageResource(R.drawable.library);
//                }else if(tab.getPosition() == 1){
//                    tabImage.setImageResource(R.drawable.sports);
//                }else if(tab.getPosition() == 2){
//                    tabImage.setImageResource(R.drawable.cafeteria);
//                }else if(tab.getPosition() == 3){
//                    tabImage.setImageResource(R.drawable.seatingroom);
//                }else  if(tab.getPosition() == 4){
//                    tabImage.setImageResource(R.drawable.washroom);
//                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        basicNeedViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(basicNeedTabs));

        setSupportActionBar(toolbar);
        //toolbar.setTitle("Basic Need");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
