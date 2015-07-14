package com.example.gerard.prueba_viernes;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class PagerAdapter extends FragmentPagerAdapter {

    static Memory mem;
    static Ranking rank;

    final int PAGE_COUNT = 2;
    private String tabTitles[] = new String[] { "Memory", "Ranking" };
    private Context context;
    Fragment tab = null;


    public PagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }


    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                mem = new Memory();
                mem.funcio(this);
                tab = mem;
                break;
            case 1:
                rank = new Ranking();
                tab = rank;
                break;
        }
        return tab;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
