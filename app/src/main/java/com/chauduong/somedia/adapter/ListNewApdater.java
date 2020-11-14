package com.chauduong.somedia.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.chauduong.somedia.R;
import com.chauduong.somedia.databinding.ItemNewBinding;
import com.chauduong.somedia.model.Newfeed;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class ListNewApdater extends RecyclerView.Adapter<ListNewApdater.NewHolder> {
    private static final String TAG = "ListNewApdater" ;
    List<Newfeed> newfeedList;
    ItemClickListener mItemClickListener;
    LayoutInflater mLayoutInflater;


    public ListNewApdater() {
    }

    public void setNewfeedList(List<Newfeed> newfeedList) {
        this.newfeedList = newfeedList;

    }

    @NonNull
    @Override
    public NewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mLayoutInflater == null) {
            mLayoutInflater = LayoutInflater.from(parent.getContext());
        }
        ItemNewBinding binding =
                DataBindingUtil.inflate(mLayoutInflater, R.layout.item_new, parent, false);
        return new NewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NewHolder holder, int position) {
        DateFormat dateFormatter = new SimpleDateFormat("kk:mm dd-MM-yyyy");
        final Newfeed newfeed = newfeedList.get(position);
        Log.d(TAG, "onBindViewHolder: "+newfeed.toString());
        holder.itemNewBinding.tvContent.setText(newfeed.getmContent());
        holder.itemNewBinding.tvUserName.setText(newfeed.getmUser().getFullName());
        holder.itemNewBinding.tvDate.setText(dateFormatter.format(newfeed.getmDate()));
        holder.itemNewBinding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                mItemClickListener.onItemClick(newfeed);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(newfeedList==null) return 0;
        return newfeedList.size();
    }

    public void setmItemClickListener(ItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public class NewHolder extends RecyclerView.ViewHolder {
        ItemNewBinding itemNewBinding;

        public NewHolder(ItemNewBinding itemNewBinding) {
            super(itemNewBinding.getRoot());
            this.itemNewBinding = itemNewBinding;
        }
    }

    public interface ItemClickListener {
        void onItemClick(Newfeed newfeed);
    }

}
