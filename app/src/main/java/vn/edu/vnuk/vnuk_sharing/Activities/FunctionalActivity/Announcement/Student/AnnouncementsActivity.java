package vn.edu.vnuk.vnuk_sharing.Activities.FunctionalActivity.Announcement.Student;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import vn.edu.vnuk.vnuk_sharing.Activities.FunctionalActivity.Announcement.AnnouncementsInWeek;
import vn.edu.vnuk.vnuk_sharing.DataStructure.Announcement;
import vn.edu.vnuk.vnuk_sharing.DataStructure.CheckIntentIsCalled;
import vn.edu.vnuk.vnuk_sharing.DataTemp.Data;
import vn.edu.vnuk.vnuk_sharing.R;

/**
 * Created by HP on 11/13/2017.
 */

public class AnnouncementsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

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

        getFormWidgets();
        getDefaultInfor();
        addEventFormWidgets();

        for(Announcement announcement : Data.announcementArrayList) {
            AnnouncementsInWeek job = new AnnouncementsInWeek(announcement.getTitle(), announcement.getDescription(), announcement.getDate() , hourFinish);
            arrJob.add(job);
        }

        adapter.notifyDataSetChanged();
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
        lv.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Data.currentAnnouncement = Data.announcementArrayList.get(position);
        Intent showing = new Intent(AnnouncementsActivity.this, showing_detailed_one_announcement.class);
        startActivity(showing);
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        CheckIntentIsCalled.isIntentFunctionalAnnouncementStudent = false;
        CheckIntentIsCalled.isIntentFunctionalStudent = false;
    }
}
