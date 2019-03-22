package com.example.gu_rl.com.basicNeed;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {


    private int numberOfTab;

    public ViewPagerAdapter(FragmentManager fm, int numberOfTab) {
        super(fm);
        this.numberOfTab = numberOfTab;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return new LibraryFragment();
            case 1:
                return new SeminarHallFragment();
            case 2:
                return new CafeteriaFragment();
            case 3:
                return new StationeryFragment();
            case 4:
                return new HostelFragment();
            case 5:
                return new SportFragment();
            case 6:
                return new WaitingRoomFragment();
            case 7:
                return new WashroomFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numberOfTab;
    }


}
