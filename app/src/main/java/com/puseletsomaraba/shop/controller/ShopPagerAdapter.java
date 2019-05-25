package com.puseletsomaraba.shop.controller;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.puseletsomaraba.shop.views.fragments.BooksFragment;
import com.puseletsomaraba.shop.views.fragments.FoodFragment;
import com.puseletsomaraba.shop.views.fragments.MedicineFragment;
import com.puseletsomaraba.shop.views.fragments.MusicFragment;
import com.puseletsomaraba.shop.views.fragments.PerfumeFragment;

public class ShopPagerAdapter extends FragmentPagerAdapter {



    public ShopPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0 : return new FoodFragment();
            case 1 : return new MusicFragment();
            case 2 : return new BooksFragment();
            case 3 : return new MedicineFragment();
            case 4 : return new PerfumeFragment();
        }

        return null;
    }

    @Override
    public int getCount() {
        return 5;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0: return  "Food";
            case 1: return  "Music";
            case 2: return  "Books";
            case 3: return  "Medicine";
            case 4: return  "Perfume";

        }
        return null;
    }




}
