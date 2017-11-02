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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

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



public class DeadlinesScreen extends AppCompatActivity {

    TextView txtDate,txtTime;
    EditText editDl,editCt;
    Button btnDate,btnTime,btnAdd;
    ArrayList<DeadlinesInWeek>arrJob=new ArrayList<DeadlinesInWeek>();
    ArrayAdapter<DeadlinesInWeek>adapter=null;
    ListView lv;
    Calendar cal;
    Date dateFinish;
    Date hourFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deadlines_screen);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Deadlines Notification");

        getFormWidgets();
        getDefaultInfor();
        addEventFormWidgets();
        Intent callFunctionalScreen = new Intent();
    }

    public void getFormWidgets()
    {
        txtDate=(TextView) findViewById(R.id.txtdate);
        txtTime=(TextView) findViewById(R.id.txttime);
        editDl=(EditText) findViewById(R.id.editdeadline);
        editCt=(EditText) findViewById(R.id.editcontent);
        btnDate=(Button) findViewById(R.id.btndate);
        btnTime=(Button) findViewById(R.id.btntime);
        btnAdd=(Button) findViewById(R.id.btndeadline);
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

        txtDate.setText(strDate);

        dft=new SimpleDateFormat("hh:mm a",Locale.getDefault());
        String strTime=dft.format(cal.getTime());

        txtTime.setText(strTime);

        dft=new SimpleDateFormat("HH:mm",Locale.getDefault());
        txtTime.setTag(dft.format(cal.getTime()));

        editDl.requestFocus();

        dateFinish=cal.getTime();
        hourFinish=cal.getTime();
    }

    public void addEventFormWidgets()
    {
        btnDate.setOnClickListener(new MyButtonEvent());
        btnTime.setOnClickListener(new MyButtonEvent());
        btnAdd.setOnClickListener(new MyButtonEvent());
        lv.setOnItemClickListener(new MyListViewEvent());
        //lv.setOnItemLongClickListener(new MyListViewEvent());
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
                case R.id.btndeadline:
                    processAddJob();
                    break;
            }
        }

    }

    private class MyListViewEvent implements
            OnItemClickListener
           // OnItemLongClickListener
    {

       /* @Override
        public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
            arrJob.remove(arg2);
            adapter.notifyDataSetChanged();
            return false;
        }*/

        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                long arg3) {
            Toast.makeText(DeadlinesScreen.this,
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
                DeadlinesScreen.this,
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
                DeadlinesScreen.this,
                callback, hour, minute, true);
        time.setTitle("Pick time");
        time.show();
    }

    public void processAddJob()
    {
        Deadline deadline = new Deadline();
        deadline.setId(Data.currentCourse.getDeadlinesCount());
        deadline.setIdCourse(Data.currentCourse.getId());
        deadline.setDescription(editCt.getText().toString());
        deadline.setDate(dateFinish);
        deadline.setTitle(editDl.getText().toString());

        FirebaseDatabase.getInstance().getReference()
                .child("root")
                .child("deadlines")
                .child("course" + "-" + Data.currentCourse.getId())
                .child("deadline" + "-" + Data.currentCourse.getDeadlinesCount())
                .setValue(deadline, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        if(databaseError != null){
                            //TODO
                            // loi ko update duoc du lieu;

                        }else{

                            FirebaseDatabase.getInstance().getReference()
                                    .child("root")
                                    .child("courses")
                                    .child("course" + "-" + Data.currentCourse.getId())
                                    .child("deadlinesCount")
                                    .setValue(Data.currentCourse.getDeadlinesCount() + 1 , new DatabaseReference.CompletionListener() {
                                        @Override
                                        public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                                            if(databaseError == null){
                                                String title = editDl.getText() + "";
                                                String description=editCt.getText()+"";
                                                DeadlinesInWeek job=new DeadlinesInWeek(title, description, dateFinish, hourFinish);
                                                if(editDl.getText().toString().equals("") || editCt.getText().toString().equals("")){
                                                    return;
                                                }
                                                arrJob.add(0, job);
                                                adapter.notifyDataSetChanged();
                                                editDl.setText("");
                                                editCt.setText("");
                                                editDl.requestFocus();

                                                Data.currentCourse.setDeadlinesCount(Data.currentCourse.getDeadlinesCount() + 1);

                                                Toast.makeText(getApplicationContext(), "Lưu thành công", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });
                        }
                    }
                });



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
