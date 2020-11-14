package com.chauduong.somedia.home.newfeed;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.chauduong.somedia.model.Newfeed;
import com.chauduong.somedia.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class NewfeedPresenterImpl implements NewfeedPresenter {
    private static final String TAG = "NewfeedPresenterImpl";
    private NewfeedView mNewfeedView;
    List<Newfeed> listMutableLiveData;
    public NewfeedPresenterImpl(NewfeedView mNewfeedView) {
        this.mNewfeedView = mNewfeedView;
    }

    @Override
    public void getNew() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("newfeed");
        listMutableLiveData=new ArrayList<>();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Newfeed> newfeeds = new ArrayList<>();
                for(DataSnapshot data: dataSnapshot.getChildren()){
                    Newfeed newfeed= data.getValue(Newfeed.class);
                    newfeeds.add(newfeed);
                }

                Log.d(TAG, "onDataChange: "+ newfeeds.toString());
                listMutableLiveData= newfeeds;
                Collections.sort(listMutableLiveData);
                Collections.reverse(listMutableLiveData);
                mNewfeedView.updateData(listMutableLiveData);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void addNew(Newfeed newfeed) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("newfeed");
        myRef.push().setValue(newfeed);
    }

    @Override
    public void deleteNew(Newfeed newfeed) {

    }

    @Override
    public void updateNew(Newfeed newfeed) {

    }
}
