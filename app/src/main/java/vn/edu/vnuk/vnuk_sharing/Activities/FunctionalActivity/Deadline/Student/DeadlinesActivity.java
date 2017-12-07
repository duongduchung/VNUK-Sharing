package vn.edu.vnuk.vnuk_sharing.Activities.FunctionalActivity.Deadline.Student;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import vn.edu.vnuk.vnuk_sharing.Activities.FunctionalActivity.Deadline.DeadlinesInWeek;
import vn.edu.vnuk.vnuk_sharing.DataStructure.CheckIntentIsCalled;
import vn.edu.vnuk.vnuk_sharing.DataStructure.Deadline;
import vn.edu.vnuk.vnuk_sharing.DataTemp.Data;
import vn.edu.vnuk.vnuk_sharing.R;

/**
 * Created by HP on 11/13/2017.
 */

public class DeadlinesActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ArrayList<DeadlinesInWeek>arrJob=new ArrayList<DeadlinesInWeek>();
    ArrayAdapter<DeadlinesInWeek>adapter=null;
    ListView lv;
    Calendar cal;
    Date dateFinish;
    Date hourFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deadlines_screen_student);

        getFormWidgets();
        getDefaultInfor();
        addEventFormWidgets();

        for (Deadline deadline : Data.deadlineArrayList) {

            DeadlinesInWeek job = new DeadlinesInWeek(deadline.getTitle() , deadline.getDescription(), deadline.getDate(), hourFinish);
            arrJob.add(job);
        }

        adapter.notifyDataSetChanged();
    }

    public void getFormWidgets()
    {
        lv=(ListView) findViewById(R.id.lvdeadline);
        adapter=new ArrayAdapter<DeadlinesInWeek>(this,
                android.R.layout.simple_list_item_1,
                arrJob);
        lv.setAdapter(adapter);
    }

    public void getDefaultInfor()
    {

        cal=Calendar.getInstance();
        SimpleDateFormat dft=null;

        dft=new SimpleDateFormat("dd/MM/yyyy",Locale.getDefault());
        String strDate=dft.format(cal.getTime());

        dft=new SimpleDateFormat("hh:mm a",Locale.getDefault());
        String strTime=dft.format(cal.getTime());

        dft=new SimpleDateFormat("HH:mm",Locale.getDefault());

        dateFinish=cal.getTime();
        hourFinish=cal.getTime();
    }

    public void addEventFormWidgets()
    {
        lv.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Data.currentDeadline = Data.deadlineArrayList.get(position);
        Intent showing = new Intent(DeadlinesActivity.this, showing_detailed_one_deadline.class);
        startActivity(showing);
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        CheckIntentIsCalled.isIntentFunctionalDeadlineStudent = false;
        CheckIntentIsCalled.isIntentFunctionalStudent = false;
    }
}