package vn.edu.vnuk.vnuk_sharing.Functional;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import vn.edu.vnuk.vnuk_sharing.R;

public class FunctionalScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_functional_screen);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Activity");

        Intent callSharingIntent = new Intent();

        String[] functions = {"Syllabus", "Announcements", "Deadlines"};

        final ListView listView = (ListView) findViewById(R.id.list_view_function);

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                FunctionalScreen.this,
                android.R.layout.simple_list_item_1,
                functions
        );

        listView.setAdapter(listViewAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {


                    if(position == 0) {
                        Intent intent = new Intent(FunctionalScreen.this, SyllabusScreen.class);
                        startActivity(intent);
                    }

                    if(position == 1) {
                        Intent intent = new Intent(FunctionalScreen.this, Announcements.class);
                            startActivity(intent);
                       }

                    if(position == 2) {
                        Intent intent = new Intent(FunctionalScreen.this, DeadlinesScreen.class);
                        startActivity(intent);
                    }
                }
            });


    }
}
