package com.chauduong.somedia.ui.message;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.chauduong.somedia.R;
import com.chauduong.somedia.adapter.Util;
import com.chauduong.somedia.databinding.FragmentRoomchatBinding;
import com.chauduong.somedia.model.Mess;
import com.chauduong.somedia.model.User;
import com.chauduong.somedia.session.SessionManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class MessFragment extends Fragment implements MessView, View.OnClickListener {
    private static final String TAG = "MessFragment";
    MessPresenterImpl mMessPresenter;
    FragmentRoomchatBinding mFragmentRoomchatBinding;
    List<Mess> messList;
    MessAdapter mMessAdapter;
    List<User> userList;
    UserOnlineAdapter mUserOnlineAdapter;

    public MessFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentRoomchatBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_roomchat, container, false);
        return mFragmentRoomchatBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        messList = new ArrayList<>();
        userList = new ArrayList<>();
        initPresenter();
        initView();
        registerListener();
    }

    private void registerListener() {
        mFragmentRoomchatBinding.btnSend.setOnClickListener(this);
    }

    private void initPresenter() {
        mMessPresenter = new MessPresenterImpl(this);
        mMessPresenter.getMess();
        mMessPresenter.getListUser();
    }

    private void initView() {

        mMessAdapter = new MessAdapter(getContext(), messList);
        mFragmentRoomchatBinding.rvListMess.setAdapter(mMessAdapter);
        mFragmentRoomchatBinding.rvListMess.setHasFixedSize(true);
        mUserOnlineAdapter = new UserOnlineAdapter(getContext(), userList);
        mFragmentRoomchatBinding.rvListOnline.setAdapter(mUserOnlineAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mFragmentRoomchatBinding.rvListOnline.setLayoutManager(linearLayoutManager);
        mFragmentRoomchatBinding.rvListOnline.setHasFixedSize(true);
    }

    @Override
    public void update(Mess mess) {
        Log.d(TAG, "update: " + mess.toString());
        messList.add(mess);
        mMessAdapter.notifyItemInserted(messList.size() - 1);
        mFragmentRoomchatBinding.rvListMess.scrollToPosition(messList.size() - 1);

    }

    @Override
    public void addUserOnline(User user) {
//        Log.d(TAG, "addUserOnline: "+ user.toString());
        userList.add(user);
        mUserOnlineAdapter.notifyDataSetChanged();
        mFragmentRoomchatBinding.rvListOnline.scrollToPosition(messList.size() - 1);

    }

    @Override
    public void updateUserOnline(User user) {
        for (User old : userList) {
            if (user.getId().equals(old.getId())) {
                int index = userList.indexOf(old);
                old.setFullName(user.getFullName());
                old.setOnline(user.isOnline());
                mUserOnlineAdapter.notifyItemChanged(index);
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSend:
                String content = mFragmentRoomchatBinding.edtContent.getText().toString();
                if (content.length() == 0) {
                    Toasty.warning(getContext(), "Nhập nội dung tin nhắn").show();
                    return;
                }
                Mess mess = new Mess(SessionManager.getInstance(getContext()).getmUser(), new Date(), content, null);
                mMessPresenter.addMess(mess);
                mFragmentRoomchatBinding.edtContent.setText("");
                break;
        }
    }


}
