package com.chauduong.somedia.ui.setting;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.chauduong.somedia.R;
import com.chauduong.somedia.databinding.FragmentSettingBinding;
import com.chauduong.somedia.session.SessionManager;

public class SettingFragment extends Fragment implements SettingView, View.OnClickListener {
    FragmentSettingBinding fragmentSettingBinding;
    SettingPresenterImpl settingPresenter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        fragmentSettingBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_setting, container, false);

        return fragmentSettingBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        settingPresenter = new SettingPresenterImpl(this);
        registerListener();
    }

    private void registerListener() {
        fragmentSettingBinding.btnLogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogout:
                settingPresenter.logout(getContext());
                break;
            default:
                break;
        }
    }

    @Override
    public void exitApp() {
        getActivity().finish();
    }

}
