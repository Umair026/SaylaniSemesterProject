package com.example.omii026.testing.Fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Omii026 on 10/8/2015.
 */
public class PagerAdapter extends FragmentStatePagerAdapter {
    private final int NumOfTabs;

    public PagerAdapter(FragmentManager fm,int NumOfTabs) {
        super(fm);
        this.NumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0: Home tab1 = new Home();
                return  tab1;

            case 1: Groups tab2 = new Groups();
                return  tab2;
            case 2: Friends tab3 = new Friends();
                return  tab3;
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
