package vn.edu.vnuk.vnuk_sharing.Activities.FunctionalActivity.Deadline.Student;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import vn.edu.vnuk.vnuk_sharing.R;

public class showing_detailed_one_deadline extends AppCompatActivity {

    TextView tvDate,tvSubject,tvContent,tvCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showing_detailed_one_deadline);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Deadlines notification");

        tvSubject = (TextView) findViewById(R.id.tv_subject2);
        tvContent = (TextView) findViewById(R.id.tv_content2);
        tvDate = (TextView) findViewById(R.id.tv_date2);
        tvCourse =(TextView) findViewById(R.id.tv_course2);




    }
}
