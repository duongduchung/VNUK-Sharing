package vn.edu.vnuk.vnuk_sharing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FunctionalScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_functional_screen);

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
                Intent intent = new Intent(FunctionalScreen.this, SyllabusScreen.class);
                String itemlistView = listView.getSelectedItem().toString();

                //dong goi bundle de dua vao Main2Activity
                Bundle bundle = new Bundle();
                bundle.putString("class",itemlistView); //dua du lieu vao bundle, class la key, itemSpinner la value

                //dua bundle vao Intent
                intent.putExtra("myPackage",bundle);
                startActivity(intent);
            }
        });
    }
}
