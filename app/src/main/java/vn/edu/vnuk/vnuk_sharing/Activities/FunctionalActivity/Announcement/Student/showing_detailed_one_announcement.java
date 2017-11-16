package vn.edu.vnuk.vnuk_sharing.Activities.FunctionalActivity.Announcement.Student;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import vn.edu.vnuk.vnuk_sharing.R;

public class showing_detailed_one_announcement extends AppCompatActivity {

    TextView tvSubject,tvContent,tvDate,tvCourse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showing_detailed_one_announcement);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Day-off Notification");

        tvSubject = (TextView) findViewById(R.id.tv_subject2);
        tvContent = (TextView) findViewById(R.id.tv_content2);
        tvDate = (TextView) findViewById(R.id.tv_date2);
        tvCourse =(TextView) findViewById(R.id.tv_course2);



    }
}
