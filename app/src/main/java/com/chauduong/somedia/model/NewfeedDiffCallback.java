package com.chauduong.somedia.model;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import java.util.List;

public class NewfeedDiffCallback extends DiffUtil.Callback {
    List<Newfeed> oldNewfeedList;
    List<Newfeed> newNewfeedList;

    public NewfeedDiffCallback(List<Newfeed> oldNewfeedList, List<Newfeed> newNewfeedList) {
        this.oldNewfeedList = oldNewfeedList;
        this.newNewfeedList = newNewfeedList;
    }

    @Override
    public int getOldListSize() {
        return oldNewfeedList.size();
    }

    @Override
    public int getNewListSize() {
        return newNewfeedList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldNewfeedList.get(oldItemPosition).getmContent().equals(newNewfeedList.get(newItemPosition).getmContent());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldNewfeedList.get(oldItemPosition).getmContent().equals(newNewfeedList.get(newItemPosition).getmContent());
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
