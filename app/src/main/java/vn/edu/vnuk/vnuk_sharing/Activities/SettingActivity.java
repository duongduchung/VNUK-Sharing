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

import vn.edu.vnuk.vnuk_sharing.DataTemp.Data;
import vn.edu.vnuk.vnuk_sharing.R;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_aboutus;
    Switch switchNews, switchSyllabus, switchDeadline, switchAnnouncement;

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
    }


    @Override
    public void onClick(View view) {
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
            }
            break;
        }
    }
}
