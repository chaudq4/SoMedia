package com.chauduong.somedia.ui.message;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.chauduong.somedia.R;
import com.chauduong.somedia.databinding.ItemMessClientBinding;
import com.chauduong.somedia.databinding.ItemMessUserBinding;
import com.chauduong.somedia.databinding.ItemOnlineBinding;
import com.chauduong.somedia.model.Mess;
import com.chauduong.somedia.model.User;
import com.chauduong.somedia.session.SessionManager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class UserOnlineAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private static final String TAG = "UserOnlineAdapter";
    List<User> userList;
    Context mContext;

    public UserOnlineAdapter(Context mContext, List<User> userList) {
        this.userList = userList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemOnlineBinding itemOnlineBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_online, parent, false);
        return new UserOnlineHolder(itemOnlineBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof UserOnlineHolder) {
            UserOnlineHolder userOnlineHolder = (UserOnlineHolder) holder;
            userOnlineHolder.itemOnlineBinding.tvFullName.setText(userList.get(position).getFullName());
            if (userList.get(position).isOnline())
                userOnlineHolder.itemOnlineBinding.imvStatus.setImageDrawable(mContext.getDrawable(R.drawable.ic_baseline_brightness_online_1_24));
            else
                userOnlineHolder.itemOnlineBinding.imvStatus.setImageDrawable(mContext.getDrawable(R.drawable.ic_baseline_brightness_offline_1_24));
        }
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    @Override
    public void onClick(View view) {

    }


    class UserOnlineHolder extends RecyclerView.ViewHolder {
        ItemOnlineBinding itemOnlineBinding;

        public UserOnlineHolder(ItemOnlineBinding itemOnlineBinding) {
            super(itemOnlineBinding.getRoot());
            this.itemOnlineBinding = itemOnlineBinding;

        }
    }

    public interface UserOnlineClickListener {
        void onUserClick(User mess);
    }


}
