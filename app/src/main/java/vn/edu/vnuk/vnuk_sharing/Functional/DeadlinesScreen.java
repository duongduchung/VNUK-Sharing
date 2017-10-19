package vn.edu.vnuk.vnuk_sharing.Functional;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import vn.edu.vnuk.vnuk_sharing.MainActivity;
import vn.edu.vnuk.vnuk_sharing.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog.OnTimeSetListener;
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


public class DeadlinesScreen extends AppCompatActivity {

    TextView txtDate,txtTime;
    EditText editCv,editNd;
    Button btnDate,btnTime,btnAdd;
    ArrayList<DeadlineInWeek>arrJob=new ArrayList<DeadlineInWeek>();
    ArrayAdapter<DeadlineInWeek>adapter=null;
    ListView lv;
    Calendar cal;
    Date dateFinish;
    Date hourFinish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deadlines_screen);

        getFormWidgets();
        getDefaultInfor();
        addEventFormWidgets();
        Intent callFunctionalScreen = new Intent();
    }

    public void getFormWidgets()
    {
        txtDate=(TextView) findViewById(R.id.txtdate);
        txtTime=(TextView) findViewById(R.id.txttime);
        editCv=(EditText) findViewById(R.id.editdealine);
        editNd=(EditText) findViewById(R.id.editcontent);
        btnDate=(Button) findViewById(R.id.btndate);
        btnTime=(Button) findViewById(R.id.btntime);
        btnAdd=(Button) findViewById(R.id.btndeadline);
        lv=(ListView) findViewById(R.id.lvdeadline);
        adapter=new ArrayAdapter<DeadlineInWeek>
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

        editCv.requestFocus();

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
                case R.id.btndeadline:
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
        int ngay=Integer.parseInt(strArrtmp[0]);
        int thang=Integer.parseInt(strArrtmp[1])-1;
        int nam=Integer.parseInt(strArrtmp[2]);
        DatePickerDialog pic=new DatePickerDialog(
                DeadlinesScreen.this,
                callback, nam, thang, ngay);
        pic.setTitle("Chọn ngày hoàn thành");
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
        int gio=Integer.parseInt(strArr[0]);
        int phut=Integer.parseInt(strArr[1]);
        TimePickerDialog time=new TimePickerDialog(
                DeadlinesScreen.this,
                callback, gio, phut, true);
        time.setTitle("Chọn giờ hoàn thành");
        time.show();
    }

    public void processAddJob()
    {
        String title=editCv.getText()+"";
        String description=editNd.getText()+"";
        DeadlineInWeek job=new DeadlineInWeek(title, description, dateFinish, hourFinish);
        arrJob.add(job);
        adapter.notifyDataSetChanged();
        editCv.setText("");
        editNd.setText("");
        editCv.requestFocus();
    }
}
