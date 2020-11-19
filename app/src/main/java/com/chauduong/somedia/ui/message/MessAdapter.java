package com.chauduong.somedia.ui.message;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.chauduong.somedia.R;
import com.chauduong.somedia.adapter.Util;
import com.chauduong.somedia.databinding.ItemMessClientBinding;
import com.chauduong.somedia.databinding.ItemMessUserBinding;
import com.chauduong.somedia.model.Mess;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class MessAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    MessClickListener mMessClickListener;
    List<Mess> messList;

    public MessAdapter(List<Mess> messList) {
        this.messList = messList;
    }

    @Override
    public int getItemViewType(int position) {

        return messList.get(position).getmUser().getUserName().equals(Util.getmUser().getUserName()) ? 1 : 0;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 1) {
            ItemMessUserBinding itemMessUserBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_mess_user, parent, false);
            return new MessViewHolderUser(itemMessUserBinding);
        } else {
            ItemMessClientBinding itemMessClientBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_mess_client, parent, false);
            return new MessViewHolderClient(itemMessClientBinding);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final DateFormat dateFormatter = new SimpleDateFormat("kk:mm dd-MM-yyyy");
        final Mess mess = messList.get(position);
        if (holder instanceof MessViewHolderClient) {
            final MessViewHolderClient messViewHolderClient = (MessViewHolderClient) holder;
            messViewHolderClient.mItemMessClientBinding.tvFullName.setText(mess.getmUser().getFullName());
            messViewHolderClient.mItemMessClientBinding.tvContent.setText(mess.getmContent());
            messViewHolderClient.mItemMessClientBinding.tvDate.setText(dateFormatter.format(mess.getmDate()));
//            messViewHolderClient.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    int isVisible = messViewHolderClient.mItemMessClientBinding.tvDate.getVisibility();
//                    if (isVisible == 0) {
//                        messViewHolderClient.mItemMessClientBinding.tvDate.setText("");
//                        messViewHolderClient.mItemMessClientBinding.tvDate.setVisibility(View.INVISIBLE);
//                    }
//                    if (isVisible == 4) {
//
//                        messViewHolderClient.mItemMessClientBinding.tvDate.setVisibility(View.VISIBLE);
//                    }
//
//                }
//            });
        }
        if (holder instanceof MessViewHolderUser) {
            final MessViewHolderUser messViewHolderUser = (MessViewHolderUser) holder;
            messViewHolderUser.itemMessUserBinding.tvContent.setText(mess.getmContent());
            messViewHolderUser.itemMessUserBinding.tvDate.setText(dateFormatter.format(mess.getmDate()));
//            messViewHolderUser.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    int isVisible = messViewHolderUser.itemMessUserBinding.tvDate.getVisibility();
//                    if (isVisible == 0) {
//                        messViewHolderUser.itemMessUserBinding.tvDate.setText("");
//                        messViewHolderUser.itemMessUserBinding.tvDate.setVisibility(View.INVISIBLE);
//                    }
//                    if (isVisible == 4) {
//
//                        messViewHolderUser.itemMessUserBinding.tvDate.setVisibility(View.VISIBLE);
//                    }
//                }
//            });
        }

    }

    @Override
    public int getItemCount() {
        return messList.size();
    }

    @Override
    public void onClick(View view) {

    }

    class MessViewHolderClient extends RecyclerView.ViewHolder {
        ItemMessClientBinding mItemMessClientBinding;

        public MessViewHolderClient(ItemMessClientBinding mItemMessClientBinding) {
            super(mItemMessClientBinding.getRoot());
            this.mItemMessClientBinding = mItemMessClientBinding;

        }
    }

    class MessViewHolderUser extends RecyclerView.ViewHolder {
        ItemMessUserBinding itemMessUserBinding;

        public MessViewHolderUser(ItemMessUserBinding itemMessUserBinding) {
            super(itemMessUserBinding.getRoot());
            this.itemMessUserBinding = itemMessUserBinding;

        }
    }

    public interface MessClickListener {
        void onMessClick(Mess mess);
    }


}
