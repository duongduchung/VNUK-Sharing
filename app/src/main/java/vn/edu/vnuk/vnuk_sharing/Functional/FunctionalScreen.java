package vn.edu.vnuk.vnuk_sharing.Functional;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import vn.edu.vnuk.vnuk_sharing.Data;
import vn.edu.vnuk.vnuk_sharing.DataStructure.Announcement;
import vn.edu.vnuk.vnuk_sharing.DataStructure.Deadline;
import vn.edu.vnuk.vnuk_sharing.DataStructure.Syllabus;
import vn.edu.vnuk.vnuk_sharing.R;

public class FunctionalScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_functional_screen);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Activity");

        Intent callSharingIntent = new Intent();

        String[] functions = {"Syllabus", "Announcements", "Deadlines"};

        final ListView listView = (ListView) findViewById(R.id.list_view_function);

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                FunctionalScreen.this,
                android.R.layout.simple_list_item_1,
                functions
        );

        listView.setAdapter(listViewAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {


                    if(position == 0) {
                        FirebaseDatabase.getInstance().getReference().child("root").child("syllabuses").child("syllabus" + "-" + Data.currentCourse.getId()).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                Data.syllabus = dataSnapshot.getValue(Syllabus.class);

                                Intent intent = new Intent(FunctionalScreen.this, SyllabusScreen.class);
                                startActivity(intent);
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });

                    }

                    if(position == 1) {

                        Data.announcementArrayList.clear();
                        FirebaseDatabase.getInstance().getReference().child("root").child("announcements").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                for(int i = 0; i < Data.currentCourse.getAnnoucementsCount(); i++){
                                    Data.announcementArrayList.add(dataSnapshot.child("announcement" + "-" + Data.currentCourse.getId() + "-" + i).getValue(Announcement.class));
                                }

                                Intent intent = new Intent(FunctionalScreen.this, Announcements.class);
                                startActivity(intent);
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });

                    }

                    if(position == 2) {

                        Data.deadlineArrayList.clear();
                        FirebaseDatabase.getInstance().getReference().child("root").child("deadlines").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                for(int i = 0; i < Data.currentCourse.getDeadlinesCount(); i++){
                                    Data.deadlineArrayList.add(dataSnapshot.child("deadline" + "-" + Data.currentCourse.getId() + "-" + i).getValue(Deadline.class));
                                }

                                Intent intent = new Intent(FunctionalScreen.this, DeadlinesScreen.class);
                                startActivity(intent);
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }
                }
            });


    }
}
