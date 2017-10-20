package vn.edu.vnuk.vnuk_sharing.DataActions;

import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import vn.edu.vnuk.vnuk_sharing.DataStructure.User;

/**
 * Created by Quangngoc430 on 10/21/2017.
 */

public class ReadData {

    public ReadData(){
        super();
    }

    public void getAllUser(final ArrayList<User> userArrayList){

        userArrayList.clear();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        databaseReference.child("root").child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    userArrayList.add(ds.getValue(User.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}
