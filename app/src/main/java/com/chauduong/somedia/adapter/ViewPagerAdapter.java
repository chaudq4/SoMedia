package com.chauduong.somedia.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.chauduong.somedia.home.JobFragment;
import com.chauduong.somedia.home.KnowledgeFragment;
import com.chauduong.somedia.home.newfeed.NewfeedFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new NewfeedFragment();
            case 1:
                return new JobFragment();
            case 2:
                return new KnowledgeFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Newfeeds";
            case 1:
                return "Job";
            case 2:
                return "Knowledge";
        }
        return super.getPageTitle(position);
    }
}
