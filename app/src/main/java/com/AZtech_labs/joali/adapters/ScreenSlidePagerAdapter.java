package com.AZtech_labs.joali.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
    private ArrayList<Fragment> fragmentList= new ArrayList<Fragment>();

    public ScreenSlidePagerAdapter(FragmentManager fm,  ArrayList<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }


    @Override
    public Fragment getItem(int position) {
        if (position >= 0 && position < fragmentList.size()){
            return fragmentList.get(position);
        }

        return fragmentList.get(1);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
