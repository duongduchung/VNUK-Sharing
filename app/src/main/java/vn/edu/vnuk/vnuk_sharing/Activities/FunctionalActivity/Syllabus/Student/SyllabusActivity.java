package vn.edu.vnuk.vnuk_sharing.Activities.FunctionalActivity.Syllabus.Student;

import android.app.Notification;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import vn.edu.vnuk.vnuk_sharing.DataTemp.Data;
import vn.edu.vnuk.vnuk_sharing.R;

/**
 * Created by HP on 11/17/2017.
 */

public class SyllabusActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvFileName;
    Button btnDownloadFile;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showing_detailed_syllabus);

        tvFileName = (TextView) findViewById(R.id.lb_name_file);
        tvFileName.setText(Data.currentSyllabus.getName());

        btnDownloadFile = (Button) findViewById(R.id.btnDownloadFile);
        btnDownloadFile.setOnClickListener(this);

        if(Data.currentSyllabus.getExists() == true){
            btnDownloadFile.setEnabled(true);
        }
        else{
            btnDownloadFile.setEnabled(false);
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnDownloadFile:{
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(Data.currentSyllabus.getLink()));
                startActivity(intent);
            }
            break;
        }
    }
}
