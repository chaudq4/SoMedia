package com.chauduong.somedia.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

import com.chauduong.somedia.R;
import com.chauduong.somedia.adapter.ViewPagerAdapter;
import com.chauduong.somedia.databinding.FragmentHomeBinding;


public class HomeFragment extends Fragment {
    FragmentHomeBinding mFragmentHomeBinding;
    ViewPagerAdapter mViewPagerAdapter;
    public HomeFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentHomeBinding= DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false);
        return mFragmentHomeBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        mViewPagerAdapter = new ViewPagerAdapter(getFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mFragmentHomeBinding.vpContainer.setAdapter(mViewPagerAdapter);
        mFragmentHomeBinding.tbTitile.setupWithViewPager(mFragmentHomeBinding.vpContainer,true);
    }
}