package vn.edu.vnuk.vnuk_sharing.Functional;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import vn.edu.vnuk.vnuk_sharing.Data;
import vn.edu.vnuk.vnuk_sharing.R;

public class SyllabusScreen extends AppCompatActivity {

    TextView lb_name_file_history, lb_size_file_history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syllabus_screen);

        Intent callFunctionalScreen = new Intent();

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Syllabus");

        lb_name_file_history = (TextView) findViewById(R.id.lb_name_file_history);
        lb_size_file_history = (TextView) findViewById(R.id.lb_size_file_history);
    }

    @Override
    public void onStart(){
        super.onStart();

        lb_name_file_history.setText(String.valueOf(Data.syllabus.getName()));
        lb_size_file_history.setText(String.valueOf(Data.syllabus.getSize()));

    }
}
