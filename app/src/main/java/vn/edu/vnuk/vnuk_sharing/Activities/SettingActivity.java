package vn.edu.vnuk.vnuk_sharing.Activities;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

import vn.edu.vnuk.vnuk_sharing.DataStructure.Notification;
import vn.edu.vnuk.vnuk_sharing.DataStructure.Setting;
import vn.edu.vnuk.vnuk_sharing.DataTemp.Data;
import vn.edu.vnuk.vnuk_sharing.R;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_aboutus;
    Switch switchNews, switchSyllabus, switchDeadline, switchAnnouncement;
    public static boolean checkTypeOfNotification[];

    @Override
    protected void onCreate(final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_setting);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Setting");

        btn_aboutus = (Button) findViewById(R.id.btn_aboutus);
        btn_aboutus.setOnClickListener(this);

        switchNews = (Switch) findViewById(R.id.btn_notiNews);
        switchNews.setOnClickListener(this);

        switchSyllabus = (Switch) findViewById(R.id.btn_notiSyllabus);
        switchSyllabus.setOnClickListener(this);

        switchDeadline = (Switch) findViewById(R.id.btn_notiDeadlines);
        switchDeadline.setOnClickListener(this);

        switchAnnouncement = (Switch) findViewById(R.id.btn_notiAnnoucement);
        switchAnnouncement.setOnClickListener(this);

        switchNews.setChecked(Data.currentUser.getSetting().isReceiveNews());
        switchSyllabus.setChecked(Data.currentUser.getSetting().isReceiveSyllabus());
        switchDeadline.setChecked(Data.currentUser.getSetting().isReceiveDeadline());
        switchAnnouncement.setChecked(Data.currentUser.getSetting().isReceiveAnnouncement());

        checkTypeOfNotification = new boolean[4];
        checkTypeOfNotification[0] = Data.currentUser.getSetting().isReceiveSyllabus();
        checkTypeOfNotification[1] = Data.currentUser.getSetting().isReceiveDeadline();
        checkTypeOfNotification[2] = Data.currentUser.getSetting().isReceiveAnnouncement();
        checkTypeOfNotification[3] = Data.currentUser.getSetting().isReceiveNews();
    }

    void loadNotifications(){
        LoginSuccess.notificationArrayList.clear();
        LoginSuccess.notificationDetailArrayList.clear();
        LoginSuccess.notificationIconArrayList.clear();
        for(Notification notification : LoginSuccess.notificationViewArrayList){
            if(checkTypeOfNotification[notification.getTypeOfNotification()] == true){
                LoginSuccess.notificationArrayList.add(notification);
                LoginSuccess.notificationDetailArrayList.add(notification.getTitleOfNotification());
                LoginSuccess.notificationIconArrayList.add(notification.getTypeOfNotification());
            }
        }
        LoginSuccess.adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        Setting currentSetting = Data.currentUser.getSetting();

        switch (view.getId()) {
            case R.id.btn_aboutus: {
                Intent intent = new Intent(SettingActivity.this, AboutUs.class);
                startActivity(intent);
            }
            break;

            case R.id.btn_notiNews: {
                boolean receive;

                if(switchNews.isChecked()){
                    receive = true;
                }
                else{
                    receive = false;

                    int index = 0;


                }

                FirebaseDatabase
                        .getInstance()
                        .getReference()
                        .child("root")
                        .child("users")
                        .child("user-" + Data.currentUser.getUsername() + "-" + Data.currentUser.getPassword())
                        .child("setting")
                        .child("receiveNews")
                        .setValue(receive);

                currentSetting.setReceiveNews(receive);
                Data.currentUser.setSetting(currentSetting);

                checkTypeOfNotification[3] = receive;

                loadNotifications();
            }
            break;

            case R.id.btn_notiSyllabus: {
                boolean receive;

                if(switchSyllabus.isChecked()){
                    receive = true;
                }
                else{
                    receive = false;
                }

                FirebaseDatabase
                        .getInstance()
                        .getReference()
                        .child("root")
                        .child("users")
                        .child("user-" + Data.currentUser.getUsername() + "-" + Data.currentUser.getPassword())
                        .child("setting")
                        .child("receiveSyllabus")
                        .setValue(receive);

                currentSetting.setReceiveSyllabus(receive);
                Data.currentUser.setSetting(currentSetting);

                checkTypeOfNotification[0] = receive;

                loadNotifications();
            }
            break;

            case R.id.btn_notiDeadlines: {
                boolean receive;

                if(switchDeadline.isChecked()){
                    receive = true;
                }
                else{
                    receive = false;
                }

                FirebaseDatabase
                        .getInstance()
                        .getReference()
                        .child("root")
                        .child("users")
                        .child("user-" + Data.currentUser.getUsername() + "-" + Data.currentUser.getPassword())
                        .child("setting")
                        .child("receiveDeadline")
                        .setValue(receive);

                currentSetting.setReceiveDeadline(receive);
                Data.currentUser.setSetting(currentSetting);

                checkTypeOfNotification[1] = receive;

                loadNotifications();
            }
            break;

            case R.id.btn_notiAnnoucement : {
                boolean receive;

                if(switchAnnouncement.isChecked()){
                    receive = true;
                }
                else{
                    receive = false;
                }

                FirebaseDatabase
                        .getInstance()
                        .getReference()
                        .child("root")
                        .child("users")
                        .child("user-" + Data.currentUser.getUsername() + "-" + Data.currentUser.getPassword())
                        .child("setting")
                        .child("receiveAnnouncement")
                        .setValue(receive);

                currentSetting.setReceiveAnnouncement(receive);
                Data.currentUser.setSetting(currentSetting);

                checkTypeOfNotification[2] = receive;

                loadNotifications();
            }
            break;
        }
    }
}
