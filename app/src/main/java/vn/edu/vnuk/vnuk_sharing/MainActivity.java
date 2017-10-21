package vn.edu.vnuk.vnuk_sharing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import vn.edu.vnuk.vnuk_sharing.Api.LoginApi;
import vn.edu.vnuk.vnuk_sharing.DataActions.ReadData;
import vn.edu.vnuk.vnuk_sharing.DataStructure.Course;
import vn.edu.vnuk.vnuk_sharing.DataStructure.Teacher;
import vn.edu.vnuk.vnuk_sharing.DataStructure.User;
import vn.edu.vnuk.vnuk_sharing.Test.GeneratingDummyData;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_login ;
    EditText edt_username, edt_password;
    LoginApi loginApi = new LoginApi();
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
        //generatingDummyData.createData(40, 10, 7);

    }

    @Override
    public void onStart(){
        super.onStart();

        ReadData readData = new ReadData();
        readData.getAllUser(userArrayList);
    }

    ArrayList<Teacher> teacherArrayList = new ArrayList<Teacher>();
    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_login : {

                int index = loginApi.checkLogin(userArrayList, edt_username.getText().toString(), edt_password.getText().toString());

                if (index == -1) {
                    Toast.makeText(getApplicationContext(), "Đăng nhập thất bai", Toast.LENGTH_SHORT).show();
                } else {
                    Data.currentUser = userArrayList.get(index);

                    if (Data.currentUser.getAccess() == 1) {

                        ReadData readData = new ReadData();
                        readData.getAllTeacher(teacherArrayList);

                        FirebaseDatabase.getInstance().getReference().child("root").child("teachers").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                    if (ds.getValue(Teacher.class).getIdUser() == Data.currentUser.getId()) {
                                        Data.currentTeacher = ds.getValue(Teacher.class);



                                        break;
                                    }
                                }

                                FirebaseDatabase.getInstance().getReference().child("root").child("courses").addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        for(DataSnapshot ds : dataSnapshot.getChildren()){
                                            if(Data.currentTeacher.getId() == ds.getValue(Course.class).getIdTeacher()){
                                                Data.courseArrayList.add(ds.getValue(Course.class));

                                            }
                                        }

                                        Toast.makeText(getApplicationContext(), "Đăng nhập thành công teacher", Toast.LENGTH_SHORT).show();

                                        Intent intent = new Intent(MainActivity.this, AfterLogin.class);
                                        startActivity(intent);
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }

                        });


                    } else {
                        Toast.makeText(getApplicationContext(), "Đăng nhập thành công student", Toast.LENGTH_LONG).show();
                    }
                }
            }
            break;
        }
    }
}

