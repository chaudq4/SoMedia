package com.chauduong.somedia.home.newfeed;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.chauduong.somedia.model.Newfeed;
import com.chauduong.somedia.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class NewfeedPresenterImpl implements NewfeedPresenter {
    private NewfeedView mNewfeedView;

    public NewfeedPresenterImpl(NewfeedView mNewfeedView) {
        this.mNewfeedView = mNewfeedView;
    }

    @Override
    public void getNew() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("newfeed");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                MutableLiveData<List<Newfeed>> listMutableLiveData;
                for(DataSnapshot data: dataSnapshot.getChildren()){
                    Newfeed newfeed= data.getValue(Newfeed.class);
                    listMutableLiveData.s
                }
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
