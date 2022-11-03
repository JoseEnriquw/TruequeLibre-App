package com.example.truequelibre.ui.trueques;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ViewPagerAdapterTrueque extends FragmentStatePagerAdapter {
    private static int TAB_COUNT = 3;

    public ViewPagerAdapterTrueque(FragmentManager fm) {
        super(fm);
    }

    public ViewPagerAdapterTrueque(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }


    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return FragmentTruequesRecibidos.newInstance();
            case 1:
                return FragmentTruequesEnviados.newInstance();
            case 2:
                return FragmentTruequesAceptados.newInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return TAB_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return FragmentTruequesRecibidos.TITLE;

            case 1:
                return FragmentTruequesEnviados.TITLE;

            case 2:
                return FragmentTruequesAceptados.TITLE;
        }
        return super.getPageTitle(position);
    }
}
