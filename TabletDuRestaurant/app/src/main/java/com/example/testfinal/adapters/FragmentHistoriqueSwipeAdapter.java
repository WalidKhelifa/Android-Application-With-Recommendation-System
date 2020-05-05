package com.example.testfinal.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.example.testfinal.fragments.HistoriqueFragment;
import com.example.testfinal.fragments.RepasPersoFragment;


public class FragmentHistoriqueSwipeAdapter extends FragmentPagerAdapter {


        public FragmentHistoriqueSwipeAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {

            if(i==0)
            {
                RepasPersoFragment fragment= new RepasPersoFragment();
                /*Bundle bundle= new Bundle();
                bundle.putString("message",String.valueOf(i));
                fragment.setArguments(bundle);*/

                return fragment;
            }
            if(i==1)
            {
                HistoriqueFragment fragment= new HistoriqueFragment();
                /*Bundle bundle= new Bundle();
                bundle.putString("message",String.valueOf(i));
                fragment.setArguments(bundle);*/
                return fragment;
            }

            return null;
        }

        @Override
        public int getCount() {

            return 2;
        }


        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {

            switch (position)
            {
                case 0 : return "Repas personnalisés";
                case 1 : return "Historique des commandes";

            }

            return "error";
        }


}
