package com.chauduong.somedia.ui.examination;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.chauduong.somedia.R;
import com.chauduong.somedia.databinding.FragmentExaminationBinding;


public class ExaminationFragment extends Fragment {
    Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            String second;
            String minute;
            if (msg.arg1 < 10)
                second = "0" + msg.arg1;
            else
                second = "" + msg.arg1;
            if (msg.arg2 < 10)
                minute = "0" + msg.arg2;
            else
                minute = "" + msg.arg2;
            mFragmentExaminationBinding.tvTime.setText(minute + ":" + second);
        }
    };
    FragmentExaminationBinding mFragmentExaminationBinding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mFragmentExaminationBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_examination, container, false);
        return mFragmentExaminationBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int time = 900;
                int second = 60;
                int minute = 14;
                while (time >= 0) {
                    second--;
                    if (second == 0) {
                        minute--;
                        second = 60;
                    }
                    Message message = new Message();
                    message.arg1 = second;
                    message.arg2 = minute;
                    mHandler.sendMessage(message);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    time--;
                }
            }
        });
        thread.start();
    }
}