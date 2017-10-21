package vn.edu.vnuk.vnuk_sharing.Functional;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import vn.edu.vnuk.vnuk_sharing.Data;
import vn.edu.vnuk.vnuk_sharing.DataStructure.Announcement;
import vn.edu.vnuk.vnuk_sharing.R;


public class Announcements extends AppCompatActivity {

    TextView txtDate,txtTime;
    EditText editA,editCt;
    Button btnDate,btnTime,btnAdd;
    ArrayList<AnnouncementsInWeek>arrJob=new ArrayList<AnnouncementsInWeek>();
    ArrayAdapter<AnnouncementsInWeek>adapter=null;
    ListView lv;
    Calendar cal;
    Date dateFinish;
    Date hourFinish;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annoucements_screen);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Day off Notification");

        getFormWidgets();
        getDefaultInfor();
        addEventFormWidgets();
        Intent callFunctionalScreen = new Intent();
    }

    public void getFormWidgets()
    {
        txtDate=(TextView) findViewById(R.id.txtdate);
        txtTime=(TextView) findViewById(R.id.txttime);
        editA=(EditText) findViewById(R.id.editannouncement);
        editCt=(EditText) findViewById(R.id.editcontent);
        btnDate=(Button) findViewById(R.id.btndate);
        btnTime=(Button) findViewById(R.id.btntime);
        btnAdd=(Button) findViewById(R.id.btnannouncement);
        lv=(ListView) findViewById(R.id.lvannouncement);
        adapter=new ArrayAdapter<AnnouncementsInWeek>
                (this,
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

        txtDate.setText(strDate);

        dft=new SimpleDateFormat("hh:mm a",Locale.getDefault());
        String strTime=dft.format(cal.getTime());

        txtTime.setText(strTime);

        dft=new SimpleDateFormat("HH:mm",Locale.getDefault());
        txtTime.setTag(dft.format(cal.getTime()));

        editA.requestFocus();

        dateFinish=cal.getTime();
        hourFinish=cal.getTime();
    }

    public void addEventFormWidgets()
    {
        btnDate.setOnClickListener(new MyButtonEvent());
        btnTime.setOnClickListener(new MyButtonEvent());
        btnAdd.setOnClickListener(new MyButtonEvent());
        lv.setOnItemClickListener(new MyListViewEvent());
        lv.setOnItemLongClickListener(new MyListViewEvent());
    }

    private class MyButtonEvent implements OnClickListener
    {
        @Override
        public void onClick(View v) {
            switch(v.getId())
            {
                case R.id.btndate:
                    showDatePickerDialog();
                    break;
                case R.id.btntime:
                    showTimePickerDialog();
                    break;
                case R.id.btnannouncement:
                    processAddJob();
                    break;
            }
        }

    }

    private class MyListViewEvent implements
            OnItemClickListener,
            OnItemLongClickListener
    {

        @Override
        public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
            arrJob.remove(arg2);
            adapter.notifyDataSetChanged();
            return false;
        }

        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                long arg3) {
            Toast.makeText(Announcements.this,
                    arrJob.get(arg2).getDesciption(),
                    Toast.LENGTH_LONG).show();
        }

    }

    public void showDatePickerDialog()
    {
        OnDateSetListener callback=new OnDateSetListener() {
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear,
                                  int dayOfMonth) {
                txtDate.setText(
                        (dayOfMonth) +"/"+(monthOfYear+1)+"/"+year);
                cal.set(year, monthOfYear, dayOfMonth);
                dateFinish=cal.getTime();
            }
        };

        String s=txtDate.getText()+"";
        String strArrtmp[]=s.split("/");
        int date=Integer.parseInt(strArrtmp[0]);
        int month=Integer.parseInt(strArrtmp[1])-1;
        int year=Integer.parseInt(strArrtmp[2]);
        DatePickerDialog pic=new DatePickerDialog(
                Announcements.this,
                callback, year, month, date);
        pic.setTitle("Pick date ");
        pic.show();
    }


    public void showTimePickerDialog()
    {
        OnTimeSetListener callback=new OnTimeSetListener() {
            public void onTimeSet(TimePicker view,
                                  int hourOfDay, int minute) {

                String s=hourOfDay +":"+minute;
                int hourTam=hourOfDay;
                if(hourTam>12)
                    hourTam=hourTam-12;
                txtTime.setText
                        (hourTam +":"+minute +(hourOfDay>12?" PM":" AM"));

                txtTime.setTag(s);

                cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
                cal.set(Calendar.MINUTE, minute);
                hourFinish=cal.getTime();
            }
        };

        String s=txtTime.getTag()+"";
        String strArr[]=s.split(":");
        int hour=Integer.parseInt(strArr[0]);
        int minute=Integer.parseInt(strArr[1]);
        TimePickerDialog time=new TimePickerDialog(
                Announcements.this,
                callback, hour, minute, true);
        time.setTitle("Pick time");
        time.show();
    }

    public void processAddJob()
    {
        String title=editA.getText()+"";
        String description=editCt.getText()+"";
        AnnouncementsInWeek job=new AnnouncementsInWeek(title, description, dateFinish, hourFinish);
        arrJob.add(job);
        adapter.notifyDataSetChanged();
        editA.setText("");
        editCt.setText("");
        editA.requestFocus();
    }

    @Override
    public void onStart(){
        super.onStart();

        for(Announcement announcement : Data.announcementArrayList) {
            AnnouncementsInWeek job = new AnnouncementsInWeek(announcement.getTitle(), announcement.getDescription(), announcement.getDate() , hourFinish);
            arrJob.add(job);
        }
    }
}
