package com.chauduong.somedia.home.newfeed;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chauduong.somedia.model.Newfeed;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
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
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Newfeed newfeed= dataSnapshot.getValue(Newfeed.class);
                if(newfeed!=null){
                    mNewfeedView.updateData(newfeed);
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

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
