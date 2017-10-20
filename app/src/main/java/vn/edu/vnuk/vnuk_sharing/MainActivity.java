package vn.edu.vnuk.vnuk_sharing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import vn.edu.vnuk.vnuk_sharing.DataStructure.Annoucement;
import vn.edu.vnuk.vnuk_sharing.DataStructure.Deadline;
import vn.edu.vnuk.vnuk_sharing.DataStructure.Syllabus;
import vn.edu.vnuk.vnuk_sharing.Test.GeneratingDummyData;


public class MainActivity extends AppCompatActivity {
    Button btn_login ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_login= (Button)findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TRONG NÀY SO SÁNH ĐIỀU KIỆN NẾU ĐÚNG VỚI Id + PASS THÌ CHUYỂN QUA NEW INTENT DƯỚI ĐÂY

                Intent intent = new Intent(MainActivity.this, AfterLogin.class);
                startActivity(intent);

            }
        });

        GeneratingDummyData generatingDummyData = new GeneratingDummyData();

        DatabaseReference database = FirebaseDatabase.getInstance().getReference();

        generatingDummyData.generateCourseOnFirebaseDatabase(database);
        generatingDummyData.generateListClassOfCoursesOnFirebaseDatabase(database);
        generatingDummyData.generateUsersOnFirebaseDatabase(database);
    }
}
