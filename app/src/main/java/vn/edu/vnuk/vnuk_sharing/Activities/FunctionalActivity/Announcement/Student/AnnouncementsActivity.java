package vn.edu.vnuk.vnuk_sharing.Activities.FunctionalActivity.Announcement.Student;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import vn.edu.vnuk.vnuk_sharing.Activities.FunctionalActivity.Announcement.AnnouncementsInWeek;
import vn.edu.vnuk.vnuk_sharing.DataTemp.Data;
import vn.edu.vnuk.vnuk_sharing.DataStructure.Announcement;
import vn.edu.vnuk.vnuk_sharing.R;

/**
 * Created by HP on 11/13/2017.
 */

public class AnnouncementsActivity extends AppCompatActivity {

    TextView txtDate;
    EditText editA,editCt;
    Button btnDate,btnAdd;
    ArrayList<AnnouncementsInWeek> arrJob=new ArrayList<AnnouncementsInWeek>();
    ArrayAdapter<AnnouncementsInWeek> adapter=null;
    ListView lv;
    Calendar cal;
    Date dateFinish;
    Date hourFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annoucements_screen_student);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Day off Notification");

        getFormWidgets();
        getDefaultInfor();
        addEventFormWidgets();
    }

    public void getFormWidgets()
    {
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

        dft=new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String strDate=dft.format(cal.getTime());

        dft=new SimpleDateFormat("hh:mm a",Locale.getDefault());
        String strTime=dft.format(cal.getTime());


        dateFinish=cal.getTime();
        hourFinish=cal.getTime();
    }

    public void addEventFormWidgets()
    {
        lv.setOnItemClickListener(new AnnouncementsActivity.MyListViewEvent());
    }


    private class MyListViewEvent implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                long arg3) {
            Toast.makeText(AnnouncementsActivity.this,
                    arrJob.get(arg2).getDesciption(),
                    Toast.LENGTH_LONG).show();
        }

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