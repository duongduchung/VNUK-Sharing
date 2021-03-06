package vn.edu.vnuk.vnuk_sharing.Activities;

import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

import vn.edu.vnuk.vnuk_sharing.Activities.FunctionalActivity.Announcement.Student.showing_detailed_one_announcement;
import vn.edu.vnuk.vnuk_sharing.Activities.FunctionalActivity.Deadline.Student.showing_detailed_one_deadline;
import vn.edu.vnuk.vnuk_sharing.Activities.FunctionalActivity.Syllabus.Student.SyllabusActivity;
import vn.edu.vnuk.vnuk_sharing.DataStructure.Announcement;
import vn.edu.vnuk.vnuk_sharing.DataStructure.CheckIntentIsCalled;
import vn.edu.vnuk.vnuk_sharing.DataStructure.CustomListView;
import vn.edu.vnuk.vnuk_sharing.DataStructure.Deadline;
import vn.edu.vnuk.vnuk_sharing.DataStructure.News;
import vn.edu.vnuk.vnuk_sharing.DataStructure.Notification;
import vn.edu.vnuk.vnuk_sharing.DataStructure.Setting;
import vn.edu.vnuk.vnuk_sharing.DataStructure.Syllabus;
import vn.edu.vnuk.vnuk_sharing.DataTemp.Data;
import vn.edu.vnuk.vnuk_sharing.Database.FileHelper;
import vn.edu.vnuk.vnuk_sharing.R;

public class LoginSuccess extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemClickListener {

    TextView txtUsername, txtLevel;
    ListView listViewNotification;
    public static ArrayList<Notification> notificationViewArrayList;
    public static ArrayList<Notification> notificationArrayList;
    public static ArrayList<String> notificationDetailArrayList;
    public static ArrayList<Integer> notificationIconArrayList;
    public static CustomListView adapter;

    @RequiresApi(api = Build.VERSION_CODES.N)
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
        listViewNotification.setOnItemClickListener(this);
        navigationView.setNavigationItemSelectedListener(this);

        notificationViewArrayList = new ArrayList<Notification>();
        notificationArrayList = new ArrayList<Notification>();
        notificationDetailArrayList = new ArrayList<String>();
        notificationIconArrayList = new ArrayList<Integer>();



        adapter = new CustomListView(this, notificationDetailArrayList, notificationIconArrayList);
        listViewNotification.setAdapter(adapter);

        //final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, notificationDetailArrayList);
        //listViewNotification.setAdapter(adapter);

        FirebaseDatabase
                .getInstance()
                .getReference()
                .child("root")
                .child("numberOfNotification")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Data.currentNumberOfNotifications = dataSnapshot.getValue(Integer.class);

                        notificationArrayList.clear();
                        notificationDetailArrayList.clear();

                        FirebaseDatabase
                                .getInstance()
                                .getReference()
                                .child("root")
                                .child("notifications")
                                .addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        Notification newNotification;
                                        Boolean receive;
                                        for(DataSnapshot ds : dataSnapshot.getChildren()){
                                            newNotification = ds.getValue(Notification.class);

                                            receive = false;

                                            switch (newNotification.getTypeOfNotification()){
                                                case 0 : {
                                                    if(Data.currentUser.getSetting().isReceiveSyllabus() == true)
                                                        receive = true;
                                                }
                                                break;

                                                case 1 : {
                                                    if(Data.currentUser.getSetting().isReceiveDeadline() == true)
                                                        receive = true;
                                                }
                                                break;

                                                case 2 : {
                                                    if(Data.currentUser.getSetting().isReceiveAnnouncement() == true)
                                                        receive = true;
                                                }
                                                break;

                                                case 3 : {
                                                    if(Data.currentUser.getSetting().isReceiveNews() == true)
                                                        receive = true;
                                                }
                                            }

                                            if(receive == true) {
                                                notificationArrayList.add(newNotification);
                                                notificationViewArrayList.add(newNotification);
                                                notificationDetailArrayList.add(newNotification.getTitleOfNotification());
                                                notificationIconArrayList.add(newNotification.getTypeOfNotification());
                                            }
                                        }

                                        boolean isCourseExist;
                                        int index = 0;
                                        if(Data.currentUser.getAccess() == 1) { // student
                                            while(index < notificationArrayList.size()) {
                                                isCourseExist = false;
                                                for (Integer integer : Data.currentStudent.getIdCoursesArrayList()) {
                                                    if (notificationArrayList.get(index).getIdCourse() == integer) {
                                                        isCourseExist = true;
                                                        break;
                                                    }
                                                }

                                                if(isCourseExist == false){
                                                    notificationArrayList.remove(index);
                                                    notificationViewArrayList.remove(index);
                                                    notificationDetailArrayList.remove(index);
                                                    notificationIconArrayList.remove(index);
                                                }
                                                else{
                                                    index++;
                                                }
                                            }
                                        }

                                        index = 0;
                                        if(Data.currentUser.getAccess() == 0) { // teacher
                                            while(index < notificationArrayList.size()) {
                                                isCourseExist = false;
                                                for (Integer integer : Data.currentTeacher.getIdCoursesArrayList()) {
                                                    if (notificationArrayList.get(index).getIdCourse() == integer) {
                                                        isCourseExist = true;
                                                        break;
                                                    }
                                                }

                                                if(isCourseExist == false){
                                                    notificationArrayList.remove(index);
                                                    notificationViewArrayList.remove(index);
                                                    notificationDetailArrayList.remove(index);
                                                    notificationIconArrayList.remove(index);
                                                }
                                                else{
                                                    index++;
                                                }
                                            }
                                        }

                                        for(int i = 0; i < notificationArrayList.size() - 1; i++){
                                            for(int j = i + 1; j < notificationArrayList.size(); j++){
                                                if(notificationArrayList.get(i).getIdNotification() < notificationArrayList.get(j).getIdNotification()){
                                                    Collections.swap(notificationArrayList, i, j);
                                                    Collections.swap(notificationViewArrayList, i, j);
                                                    Collections.swap(notificationDetailArrayList, i, j);
                                                    Collections.swap(notificationIconArrayList, i, j);
                                                }
                                            }
                                        }

                                        adapter.notifyDataSetChanged();
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }

    public void sendNotification(View v, String title, String content) {

        // Gets an instance of the NotificationManager service//

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        android.app.Notification.Builder notifyBuilder = new android.app.Notification.Builder(this);
        notifyBuilder.setContentTitle(title);
        notifyBuilder.setContentText(content);
        notifyBuilder.setSmallIcon(R.mipmap.ic_logo);

        mNotificationManager.notify(1, notifyBuilder.build());
    }

    @Override
    public void onBackPressed() {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_notifications, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_all:
                SettingActivity.checkTypeOfNotification[0] = true;
                SettingActivity.checkTypeOfNotification[1] = true;
                SettingActivity.checkTypeOfNotification[2] = true;
                SettingActivity.checkTypeOfNotification[3] = true;
                SettingActivity.loadNotifications();
                return true;
            case R.id.action_news:
                SettingActivity.checkTypeOfNotification[0] = false;
                SettingActivity.checkTypeOfNotification[1] = false;
                SettingActivity.checkTypeOfNotification[2] = false;
                SettingActivity.checkTypeOfNotification[3] = true;
                SettingActivity.loadNotifications();
                return true;
            case R.id.action_dayoff:
                SettingActivity.checkTypeOfNotification[0] = false;
                SettingActivity.checkTypeOfNotification[1] = false;
                SettingActivity.checkTypeOfNotification[2] = true;
                SettingActivity.checkTypeOfNotification[3] = false;
                SettingActivity.loadNotifications();
                return true;
            case R.id.action_deadline:
                SettingActivity.checkTypeOfNotification[0] = false;
                SettingActivity.checkTypeOfNotification[1] = true;
                SettingActivity.checkTypeOfNotification[2] = false;
                SettingActivity.checkTypeOfNotification[3] = false;
                SettingActivity.loadNotifications();
                return true;
            case R.id.action_syllabus:
                SettingActivity.checkTypeOfNotification[0] = true;
                SettingActivity.checkTypeOfNotification[1] = false;
                SettingActivity.checkTypeOfNotification[2] = false;
                SettingActivity.checkTypeOfNotification[3] = false;
                SettingActivity.loadNotifications();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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

                    FileHelper.writeToFile("", getApplicationContext());

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
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Data.currentNotification = notificationArrayList.get(position);

        switch (Data.currentNotification.getTypeOfNotification()){
            case 0:{
                // syllabus
                FirebaseDatabase
                        .getInstance()
                        .getReference()
                        .child("root")
                        .child("syllabuses")
                        .child("syllabus-" + Data.currentNotification.getIdCourse())
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                Data.currentSyllabus = dataSnapshot.getValue(Syllabus.class);
                                Data.currentCourse.setId(Data.currentNotification.getIdCourse());
                                Data.currentCourse.setName(Data.currentNotification.getNameCourse());

                                if(CheckIntentIsCalled.isIntentSyllabusDetail == false) {
                                    Intent intent = new Intent(LoginSuccess.this, SyllabusActivity.class);
                                    startActivity(intent);
                                    CheckIntentIsCalled.isIntentSyllabusDetail = true;
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
            }
            break;
            case 1:{
                // deadline
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
                                Data.currentDeadline = dataSnapshot.getValue(Deadline.class);
                                Data.currentCourse.setId(Data.currentNotification.getIdCourse());
                                Data.currentCourse.setName(Data.currentNotification.getNameCourse());

                                if(CheckIntentIsCalled.isIntentDealineDetail == false) {
                                    Intent intent = new Intent(LoginSuccess.this, showing_detailed_one_deadline.class);
                                    startActivity(intent);
                                    CheckIntentIsCalled.isIntentDealineDetail = true;
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
            }
            break;
            case 2:{
                // announcement
                FirebaseDatabase
                        .getInstance()
                        .getReference()
                        .child("root")
                        .child("announcements")
                        .child("course-" + Data.currentNotification.getIdCourse())
                        .child("announcement-" + Data.currentNotification.getIdAnnouncement())
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                Data.currentAnnouncement = dataSnapshot.getValue(Announcement.class);
                                Data.currentCourse.setId(Data.currentNotification.getIdCourse());
                                Data.currentCourse.setName(Data.currentNotification.getNameCourse());

                                if(CheckIntentIsCalled.isIntentAnnouncementDetail == false) {
                                    Intent intent = new Intent(LoginSuccess.this, showing_detailed_one_announcement.class);
                                    startActivity(intent);
                                    CheckIntentIsCalled.isIntentAnnouncementDetail = true;
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
            }
            break;
            case 3:{
                // news form VNUK
                FirebaseDatabase
                        .getInstance()
                        .getReference()
                        .child("root")
                        .child("news")
                        .child("news-" + Data.currentNotification.getIdNews())
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                Data.currentNews = dataSnapshot.getValue(News.class);

                                if(CheckIntentIsCalled.isIntentNewsDetails == false) {
                                    Intent intent = new Intent(LoginSuccess.this, NewsActivity.class);
                                    startActivity(intent);
                                    CheckIntentIsCalled.isIntentNewsDetails = true;
                                }

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
            }
            break;
        }
    }
}
