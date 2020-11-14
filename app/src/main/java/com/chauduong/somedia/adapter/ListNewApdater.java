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

public class ListNewApdater extends ListAdapter<Newfeed,ListNewApdater.NewHolder> {
    private static final String TAG = "ListNewApdater" ;
    ItemClickListener mItemClickListener;
    LayoutInflater mLayoutInflater;

    public ListNewApdater() {
        super(DIFF_CALLBACK);
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
    public void onBindViewHolder(@NonNull NewHolder holder, final int position) {
        DateFormat dateFormatter = new SimpleDateFormat("kk:mm dd-MM-yyyy");
        final Newfeed newfeed = getItem(position);
        Log.d(TAG, "onBindViewHolder: "+newfeed.toString());
        holder.itemNewBinding.tvContent.setText(newfeed.getmContent());
        holder.itemNewBinding.tvUserName.setText(newfeed.getmUser().getFullName());
        holder.itemNewBinding.tvDate.setText(dateFormatter.format(newfeed.getmDate()));
        holder.itemNewBinding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mItemClickListener.onItemClick(getItem(position));
            }
        });
    }

    public class NewHolder extends RecyclerView.ViewHolder {
        ItemNewBinding itemNewBinding;

        public NewHolder(ItemNewBinding itemNewBinding) {
            super(itemNewBinding.getRoot());
            this.itemNewBinding = itemNewBinding;
        }
    }

    public void setmItemClickListener(ItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(Newfeed newfeed);
    }
    private static final DiffUtil.ItemCallback<Newfeed> DIFF_CALLBACK = new DiffUtil.ItemCallback<Newfeed>() {
        @Override
        public boolean areItemsTheSame(@NonNull Newfeed oldItem, @NonNull Newfeed newItem) {
            return oldItem.getmContent().equals(newItem.getmContent());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Newfeed oldItem, @NonNull Newfeed newItem) {
            return oldItem.getmContent().equals(newItem.getmContent());
        }
    };

}
