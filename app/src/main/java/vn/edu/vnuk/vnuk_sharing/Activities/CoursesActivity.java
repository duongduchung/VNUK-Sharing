package vn.edu.vnuk.vnuk_sharing.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import vn.edu.vnuk.vnuk_sharing.DataTemp.Data;
import vn.edu.vnuk.vnuk_sharing.DataStructure.Course;
import vn.edu.vnuk.vnuk_sharing.Activities.FunctionalActivity.FunctionalActivity;
import vn.edu.vnuk.vnuk_sharing.R;


public class CoursesActivity extends AppCompatActivity {
    @Override
    protected void onCreate(final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_sharing);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Courses");

            ArrayList<String> classes = new ArrayList<String>();
            for (Course course : Data.courseArrayList) {
                classes.add(course.getName());
            }

            ListView listView = (ListView) findViewById(R.id.list_view_classes);
            listView.setAdapter(null);

            ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                    this,
                    android.R.layout.simple_list_item_1,
                    classes
            );


            listView.setAdapter(listViewAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    Data.currentCourse = Data.courseArrayList.get(position);

                    Intent intent = new Intent(CoursesActivity.this, FunctionalActivity.class);
                    startActivity(intent);
                }
            });
        }
}
