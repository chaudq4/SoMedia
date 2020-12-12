package com.chauduong.somedia.ui.updateInfo;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.chauduong.somedia.R;
import com.chauduong.somedia.databinding.ActivityChangeInfoBinding;
import com.chauduong.somedia.model.User;
import com.chauduong.somedia.session.SessionManager;

public class ChangeInfoActivity extends Activity implements View.OnClickListener, ChangeInfoView {
    ActivityChangeInfoBinding activityChangeInfoBinding;
    ChangeInfoPresenterImpl mChangeInfoPresenterImpl;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityChangeInfoBinding = DataBindingUtil.setContentView(this, R.layout.activity_change_info);
        initPresenter();
        initView();
        registerListener();
    }

    private void initPresenter() {
        mChangeInfoPresenterImpl = new ChangeInfoPresenterImpl(this);
    }

    private void registerListener() {
        activityChangeInfoBinding.btnCamera.setOnClickListener(this);
        activityChangeInfoBinding.btnUpdate.setOnClickListener(this);
    }

    private void initView() {
        activityChangeInfoBinding.setUser(SessionManager.getInstance(this).getmUser());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCamera:
                break;
            case R.id.btnUpdate:
                User user = new User();
                user.setId(SessionManager.getInstance(this).getmUser().getId());
                user.setFullName(activityChangeInfoBinding.txtFullName.getText().toString());
                user.setPassWord(activityChangeInfoBinding.txtPassword.getText().toString());
                user.setPhone(activityChangeInfoBinding.txtPhone.getText().toString());
                mChangeInfoPresenterImpl.updateInfo(user);
                break;
            default:
                break;
        }
    }

    @Override
    public void onUpdateSuccess(User user) {
        SessionManager.getInstance(this).setmUser(user);
        setResult(Activity.RESULT_OK);
        finish();
    }
}
