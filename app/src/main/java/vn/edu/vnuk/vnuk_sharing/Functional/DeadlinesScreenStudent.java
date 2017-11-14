package vn.edu.vnuk.vnuk_sharing.Functional;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import vn.edu.vnuk.vnuk_sharing.Data;
import vn.edu.vnuk.vnuk_sharing.DataStructure.Deadline;
import vn.edu.vnuk.vnuk_sharing.R;

/**
 * Created by HP on 11/13/2017.
 */

public class DeadlinesScreenStudent extends AppCompatActivity {

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

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Deadlines Notification");

        getFormWidgets();
        getDefaultInfor();
        addEventFormWidgets();
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
        lv.setOnItemClickListener(new DeadlinesScreenStudent.MyListViewEvent());
    }


    private class MyListViewEvent implements
            AdapterView.OnItemClickListener
    {
        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                long arg3) {
            Toast.makeText(DeadlinesScreenStudent.this,
                    arrJob.get(arg2).getDesciption(),
                    Toast.LENGTH_LONG).show();
        }
    }


    // ngoc
    @Override
    public void onStart(){
        super.onStart();

        for (Deadline deadline : Data.deadlineArrayList) {

            DeadlinesInWeek job = new DeadlinesInWeek(deadline.getTitle() , deadline.getDescription(), deadline.getDate(), hourFinish);
            arrJob.add(job);
        }
    }
}