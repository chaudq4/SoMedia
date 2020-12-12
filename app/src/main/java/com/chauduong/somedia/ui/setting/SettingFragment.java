package com.chauduong.somedia.ui.setting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.chauduong.somedia.R;
import com.chauduong.somedia.databinding.FragmentSettingBinding;
import com.chauduong.somedia.keycode.Constant;
import com.chauduong.somedia.session.SessionManager;
import com.chauduong.somedia.ui.updateInfo.ChangeInfoActivity;

import es.dmoral.toasty.Toasty;

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
        initData();
    }

    private void initData() {
        fragmentSettingBinding.txtName.setText(SessionManager.getInstance(getContext()).getmUser().getFullName());
    }

    private void registerListener() {
        fragmentSettingBinding.btnLogout.setOnClickListener(this);
        fragmentSettingBinding.txtChangeInfo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogout:
                settingPresenter.logout(getContext());
                break;
            case R.id.txtChangeInfo:
                Intent intent = new Intent(getContext(), ChangeInfoActivity.class);
                startActivityForResult(intent, Constant.REQUEST_CODE_UPDATE_INFO);
                break;
            default:
                break;
        }
    }

    @Override
    public void exitApp() {
        getActivity().finish();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.REQUEST_CODE_UPDATE_INFO)
            if (resultCode == Activity.RESULT_OK) {
                Toasty.success(getContext(), "Cập nhật thành công", Toasty.LENGTH_LONG).show();
            }
    }
}
