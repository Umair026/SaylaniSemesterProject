package com.example.omii026.testing.Fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Omii026 on 10/11/2015.
 */
public class Main_PagerAdapter extends FragmentStatePagerAdapter {
    public Main_PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: Sign_In tab1 = new Sign_In();
                return tab1;
            case 1: Sign_up tab2 = new Sign_up();
                return tab2;
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
