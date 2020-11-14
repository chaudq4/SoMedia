package com.chauduong.somedia.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.chauduong.somedia.R;
import com.chauduong.somedia.databinding.FragmentKnowledgeBinding;

public class KnowledgeFragment extends Fragment {
    FragmentKnowledgeBinding mFragmentKnowledgeBinding;

    public KnowledgeFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentKnowledgeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_knowledge, container, false);
        return mFragmentKnowledgeBinding.getRoot();
    }
}
