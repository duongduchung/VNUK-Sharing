package vn.edu.vnuk.vnuk_sharing.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import vn.edu.vnuk.vnuk_sharing.DataStructure.Announcement;
import vn.edu.vnuk.vnuk_sharing.DataStructure.Course;
import vn.edu.vnuk.vnuk_sharing.DataStructure.Deadline;
import vn.edu.vnuk.vnuk_sharing.DataStructure.Notification;
import vn.edu.vnuk.vnuk_sharing.DataStructure.Syllabus;
import vn.edu.vnuk.vnuk_sharing.DataTemp.Data;
import vn.edu.vnuk.vnuk_sharing.R;

public class LoginSuccess extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {

    TextView txtUsername, txtLevel;
    ListView listViewNotification;
    ArrayList<Notification> notificationArrayList;
    ArrayList<String> notificationDetailArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View view = navigationView.getHeaderView(0);
        txtUsername = (TextView) view.findViewById(R.id.txtNameOfCurrentUser);
        txtUsername.setText("Username : " + Data.currentUser.getUsername());
        txtLevel = (TextView) view.findViewById(R.id.txtLevelOfCurrentUser);
        if(Data.currentUser.getAccess() == 0){
            txtLevel.setText("Level   : Teacher");
        }else{
            txtLevel.setText("Level   : Student");
        }
        listViewNotification = (ListView) findViewById(R.id.lvNotification);
        listViewNotification.setOnItemSelectedListener(this);

        navigationView.setNavigationItemSelectedListener(this);

        notificationArrayList = new ArrayList<Notification>();
        notificationDetailArrayList = new ArrayList<String>();
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, notificationDetailArrayList);
        listViewNotification.setAdapter(adapter);
        listViewNotification.setOnItemClickListener(this);
        FirebaseDatabase
                .getInstance()
                .getReference()
                .child("root")
                .child("notifications")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
<<<<<<< HEAD
//                        Toast.makeText(getApplicationContext(), dataSnapshot.getValue().toString(), Toast.LENGTH_SHORT).show();
=======
                        notificationArrayList.clear();
                        notificationDetailArrayList.clear();

                        for(DataSnapshot ds : dataSnapshot.getChildren()){
                            for(Course course : Data.courseArrayList){
                                if(course.getId() == ds.getValue(Notification.class).getIdCourse()) {
                                    notificationArrayList.add(ds.getValue(Notification.class));
                                    notificationDetailArrayList.add(ds.getValue(Notification.class).getTitleOfNotification());
                                    break;
                                }
                            }
                        }
                        adapter.notifyDataSetChanged();
>>>>>>> notification
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

    }

    @Override
    public void onBackPressed() {

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_courses) {
            Intent newSharing = new Intent(LoginSuccess.this,CoursesActivity.class);
            startActivity(newSharing);

        } else if (id == R.id.nav_setting) {
            Intent newSharing = new Intent(LoginSuccess.this,SettingActivity.class);
            startActivity(newSharing);

        } else if (id == R.id.nav_logout) {
            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
            alertBuilder.setTitle("LOG OUT");
            alertBuilder.setMessage("Are you sure to log out?");
            alertBuilder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int id) {
                    Data.clearAllData();
                    finish();
                }
            });
            alertBuilder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            AlertDialog alertDialog = alertBuilder.create();
            alertDialog.show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Data.currentNotification = notificationArrayList.get(i);

        switch(Data.currentNotification.getTypeOfNotification()){
            case 0 :{
                FirebaseDatabase
                        .getInstance()
                        .getReference()
                        .child("root")
                        .child("syllabuses")
                        .child("syllabus-" + Data.currentCourse.getId())
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                Data.currentNotificationSyllabus = dataSnapshot.getValue(Syllabus.class);

                                Toast.makeText(getApplicationContext(), "Course : " + Data.currentCourse.getId() + " syllabus", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
            }
            break;
            case 1 :{
                FirebaseDatabase
                        .getInstance()
                        .getReference()
                        .child("root")
                        .child("deadlines")
                        .child("course-" + Data.currentNotification.getIdCourse())
                        .child("deadline-" + Data.currentNotification.getIdDeadline())
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                Data.currentNotificationDeadline = dataSnapshot.getValue(Deadline.class);

                                Toast.makeText(getApplicationContext(), "Course : " + Data.currentCourse.getId() + " Deadline : " + Data.currentNotificationDeadline.getId(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
            }
            break;
            case 2 :{
                FirebaseDatabase
                        .getInstance()
                        .getReference()
                        .child("root")
                        .child("announcements")
                        .child("course-" + Data.currentNotification.getIdCourse())
                        .child("announcements-" + Data.currentNotification.getIdAnnouncement())
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                Data.currentNotificationAnnouncement = dataSnapshot.getValue(Announcement.class);

                                Toast.makeText(getApplicationContext(), "Course : " + Data.currentCourse.getId() + " Deadline : " + Data.currentNotificationDeadline, Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
            }
            break;
            case 3 :{

            }
            break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Notification currentNotification = notificationArrayList.get(position);

        switch (currentNotification.getTypeOfNotification()){
            case 0:{
                // syllabus
            }
            break;
            case 1:{
                // deadline
            }
            break;
            case 2:{
                // announcement
            }
            break;
            case 3:{
                // news form VNUK
            }
            break;
        }
    }
}
