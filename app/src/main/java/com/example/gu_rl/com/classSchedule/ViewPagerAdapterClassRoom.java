package com.example.gu_rl.com.classSchedule;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ViewPagerAdapterClassRoom extends FragmentPagerAdapter {
    private int numberOfTab;

    public ViewPagerAdapterClassRoom(FragmentManager fm, int numberOfTab) {
        super(fm);
        this.numberOfTab = numberOfTab;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return new CurrentUserScheduleFragment();
            case 1:
                return new OtherClassScheduleFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numberOfTab;
    }

}
