package vn.edu.vnuk.vnuk_sharing.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import vn.edu.vnuk.vnuk_sharing.DataTemp.Data;
import vn.edu.vnuk.vnuk_sharing.DataStructure.Course;
import vn.edu.vnuk.vnuk_sharing.DataStructure.Student;
import vn.edu.vnuk.vnuk_sharing.DataStructure.Teacher;
import vn.edu.vnuk.vnuk_sharing.DataStructure.User;
import vn.edu.vnuk.vnuk_sharing.Methods.SHA256;
import vn.edu.vnuk.vnuk_sharing.R;
import vn.edu.vnuk.vnuk_sharing.Test.GeneratingDummyData;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_login ;
    EditText edt_username, edt_password;
    ArrayList<User> userArrayList = new ArrayList<User>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_login= (Button)findViewById(R.id.btn_login);
        edt_username = (EditText) findViewById(R.id.std_id);
        edt_password = (EditText) findViewById(R.id.std_pass);

        boolean check = false;

        btn_login.setOnClickListener(this);

        final GeneratingDummyData generatingDummyData = new GeneratingDummyData();
        //generatingDummyData.createData(70, 1000, 20, 1000);

    }

    ArrayList<Teacher> teacherArrayList = new ArrayList<Teacher>();
    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_login : {

                FirebaseDatabase.getInstance().getReference().child("root").child("users").child("user" + "-" + edt_username.getText().toString() + "-" + SHA256.getSHA256Hash(edt_password.getText().toString())).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){
                            User user = new User();
                            user = dataSnapshot.getValue(User.class);
                            Data.currentUser = user;

                            if(user.getAccess() == 1) {

                                FirebaseDatabase
                                        .getInstance()
                                        .getReference()
                                        .child("root")
                                        .child("students")
                                        .child("student" + "-" + user.getId())
                                        .addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                if(dataSnapshot.exists()) {

                                                    Toast.makeText(getApplicationContext(), "Đăng nhập student thành công", Toast.LENGTH_LONG).show();

                                                    Data.currentStudent = dataSnapshot.getValue(Student.class);

                                                    FirebaseDatabase
                                                            .getInstance()
                                                            .getReference()
                                                            .child("root")
                                                            .child("courses")
                                                            .addListenerForSingleValueEvent(new ValueEventListener() {
                                                                @Override
                                                                public void onDataChange(DataSnapshot dataSnapshot) {
                                                                    for(Integer integer : Data.currentStudent.getIdCoursesArrayList()){
                                                                        Data.courseArrayList.add(dataSnapshot.child("course" + "-" + integer).getValue(Course.class));
                                                                    }

                                                                    Intent intent = new Intent(MainActivity.this, LoginSuccess.class);
                                                                    startActivity(intent);

                                                                }

                                                                @Override
                                                                public void onCancelled(DatabaseError databaseError) {

                                                                }
                                                            });
                                                }
                                            }

                                            @Override
                                            public void onCancelled(DatabaseError databaseError) {

                                            }
                                        });
                            }else{

                                FirebaseDatabase
                                        .getInstance()
                                        .getReference()
                                        .child("root")
                                        .child("teachers")
                                        .child("teacher" + "-" + user.getId())
                                        .addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {

                                                if(dataSnapshot.exists()) {

                                                    Toast.makeText(getApplicationContext(), "Đăng nhập teacher thành công", Toast.LENGTH_LONG).show();

                                                    Data.currentTeacher = dataSnapshot.getValue(Teacher.class);

                                                    FirebaseDatabase
                                                            .getInstance()
                                                            .getReference()
                                                            .child("root")
                                                            .child("courses")
                                                            .addListenerForSingleValueEvent(new ValueEventListener() {
                                                                @Override
                                                                public void onDataChange(DataSnapshot dataSnapshot) {
                                                                    for (Integer integer : Data.currentTeacher.getIdCoursesArrayList()) {
                                                                        Data.courseArrayList.add(dataSnapshot.child("course" + "-" + integer).getValue(Course.class));
                                                                    }

                                                                    Intent intent = new Intent(MainActivity.this, LoginSuccess.class);
                                                                    startActivity(intent);
                                                                }

                                                                @Override
                                                                public void onCancelled(DatabaseError databaseError) {

                                                                }
                                                            });
                                                }
                                            }

                                            @Override
                                            public void onCancelled(DatabaseError databaseError) {

                                            }
                                        });
                            }
                        }else{
                            Toast.makeText(getApplicationContext(), "Thất bại", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
            break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        edt_username.setText("");
        edt_password.setText("");
    }
}