package com.chauduong.somedia.home.newfeed;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.ListAdapter;

import com.chauduong.somedia.R;
import com.chauduong.somedia.adapter.ListNewApdater;
import com.chauduong.somedia.adapter.Util;
import com.chauduong.somedia.databinding.DialogNewfeedBinding;
import com.chauduong.somedia.databinding.FragmentNewfeedsBinding;
import com.chauduong.somedia.model.Newfeed;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class NewfeedFragment extends Fragment implements View.OnClickListener, NewfeedView, ListNewApdater.ItemClickListener {
    private static final String TAG = "NewfeedFragment";
    FragmentNewfeedsBinding mFragmentNewfeedsBinding;
    NewfeedPresenterImpl mNewfeedPresenter;
    ListNewApdater mListNewApdater;
    public NewfeedFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentNewfeedsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_newfeeds, container, false);
        return mFragmentNewfeedsBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        registerListener();
        initView();
        initPresenter();
        mFragmentNewfeedsBinding.rvListNewfeed.setAdapter(mListNewApdater);
        mListNewApdater.setmItemClickListener(this);
    }

    private void initView() {
        mListNewApdater = new ListNewApdater();
    }


    private void initPresenter() {
        mNewfeedPresenter = new NewfeedPresenterImpl(this);
        mNewfeedPresenter.getNew();
    }

    private void registerListener() {
        mFragmentNewfeedsBinding.btnAddNew.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAddNew:
                addNew();
                break;
            default:
                break;
        }
    }

    private void addNew() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Post newfeed");

        final EditText input = new EditText(getActivity());
        input.setInputType(InputType.TYPE_CLASS_TEXT );
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String content = input.getText().toString();
                if (content == null || content.length() == 0) {
                    Toasty.warning(getContext(), "Please insert content!").show();
                    return;
                }
                DateFormat dateFormatter = new SimpleDateFormat("kk:mm dd-MM-yyyy");
                dateFormatter.setLenient(false);
                Date today = new Date();
                Newfeed newfeed = new Newfeed(Util.getmUser(),today,content,null);
                mNewfeedPresenter.addNew(newfeed);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }


    @Override
    public void updateData(List<Newfeed> listMutableLiveData) {
        mListNewApdater.submitList(listMutableLiveData);
        CountDownTimer countDownTimer = new CountDownTimer(500,500) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                mFragmentNewfeedsBinding.rvListNewfeed.scrollToPosition(0);
            }
        }.start();
    }

    @Override
    public void onItemClick(Newfeed newfeed) {

    }
}
