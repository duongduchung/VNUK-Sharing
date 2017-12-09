package vn.edu.vnuk.vnuk_sharing.Activities;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import vn.edu.vnuk.vnuk_sharing.DataTemp.Data;
import vn.edu.vnuk.vnuk_sharing.DataStructure.Course;
import vn.edu.vnuk.vnuk_sharing.DataStructure.Student;
import vn.edu.vnuk.vnuk_sharing.DataStructure.Teacher;
import vn.edu.vnuk.vnuk_sharing.DataStructure.User;
import vn.edu.vnuk.vnuk_sharing.Database.DatabaseHelper;
import vn.edu.vnuk.vnuk_sharing.Database.FileHelper;
import vn.edu.vnuk.vnuk_sharing.Database.User_Database;
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
        //generatingDummyData.createData(20, 100, 5);

        String text = FileHelper.readFromFile(getApplicationContext());

        if(text == ""){
            Toast.makeText(getApplicationContext(), "Ko co tai khoan", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(), "Loginning...", Toast.LENGTH_SHORT).show();
            String info[] = text.split("-");
            loadAccount(info[0], info[1]);
        }
    }

    ArrayList<Teacher> teacherArrayList = new ArrayList<Teacher>();
    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_login : {
                loadAccount(edt_username.getText().toString(), edt_password.getText().toString());
            }
            break;
        }
    }

    public void loadAccount(final String username, final String password){
        btn_login.setEnabled(false);
        FirebaseDatabase
                .getInstance()
                .getReference()
                .child("root")
                .child("users")
                .child("user" + "-" + username + "-" + SHA256.getSHA256Hash(password))
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){
                            User user = new User();
                            user = dataSnapshot.getValue(User.class);
                            Data.currentUser = user;

                            FileHelper.writeToFile(username + "-" + password, getApplicationContext());


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

                                                    Toast.makeText(getApplicationContext(), "Đăng nhập student thành công", Toast.LENGTH_SHORT).show();

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
                                                                    btn_login.setEnabled(true);

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

                                                    Toast.makeText(getApplicationContext(), "Đăng nhập teacher thành công", Toast.LENGTH_SHORT).show();

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
                                                                    btn_login.setEnabled(true);
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
                            btn_login.setEnabled(true);
                            Toast.makeText(getApplicationContext(), "Thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();

        Data.initialAllData();

        edt_username.setText("");
        edt_password.setText("");
    }
}