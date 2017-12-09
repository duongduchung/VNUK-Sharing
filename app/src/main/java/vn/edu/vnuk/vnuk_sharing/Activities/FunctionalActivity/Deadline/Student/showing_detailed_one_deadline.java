package vn.edu.vnuk.vnuk_sharing.Activities.FunctionalActivity.Deadline.Student;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import vn.edu.vnuk.vnuk_sharing.DataStructure.CheckIntentIsCalled;
import vn.edu.vnuk.vnuk_sharing.DataTemp.Data;
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

        tvCourse =(TextView) findViewById(R.id.tv_course2);
        tvCourse.setText(Data.currentCourse.getName());
        tvSubject = (TextView) findViewById(R.id.tv_subject2);
        tvSubject.setText(Data.currentDeadline.getTitle());
        tvContent = (TextView) findViewById(R.id.tv_content2);
        tvContent.setText(Data.currentDeadline.getDescription());
        tvDate = (TextView) findViewById(R.id.tv_date2);
        tvDate.setText(Data.currentDeadline.getDate().toString());

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        CheckIntentIsCalled.isIntentDealineDetail = false;
    }
}
