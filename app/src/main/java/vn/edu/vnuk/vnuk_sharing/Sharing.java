package vn.edu.vnuk.vnuk_sharing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import vn.edu.vnuk.vnuk_sharing.DataStructure.Course;
import vn.edu.vnuk.vnuk_sharing.Functional.FunctionalScreen;


public class Sharing extends AppCompatActivity {


    //    public Sharing() {
//        // Required empty public constructor
//    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {

//        public View onCreateView (LayoutInflater inflater, ViewGroup container,
//                Bundle savedInstanceState){
//            View view = inflater.inflate(R.layout.fragment_sharing, container, false)

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
                    Intent intent = new Intent(Sharing.this, FunctionalScreen.class);
                    startActivity(intent);
                }
            });

           // return listView;
        }

   // }
}
