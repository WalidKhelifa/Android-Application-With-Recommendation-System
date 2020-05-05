package com.example.testfinal.adapters;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.example.testfinal.fragments.MenuCategorieFragment;
import com.example.testfinal.fragments.MenuCategorieFragmentOff;


public class FragmentSwipeAdapter extends FragmentPagerAdapter {

    int x;

    public FragmentSwipeAdapter(FragmentManager fm,int x) {
        super(fm);
        this.x=x;
    }

    @Override
    public Fragment getItem(int i) {
        if(x==1)
        {
            MenuCategorieFragmentOff fragment= new MenuCategorieFragmentOff();
            Bundle bundle= new Bundle();
            bundle.putString("message",String.valueOf(i));
            fragment.setArguments(bundle);

            return fragment;
        }else {

            MenuCategorieFragment fragment = new MenuCategorieFragment();
            Bundle bundle = new Bundle();
            bundle.putString("message", String.valueOf(i));
            fragment.setArguments(bundle);

            return fragment;
        }
    }

    @Override
    public int getCount() {

        return 4;
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        switch (position)
        {
            case 0 : return "Entrées";
            case 1 : return "Plats";
            case 2 : return "Desserts";
            case 3 : return "Boissons";
        }

        return "error";
    }
}
