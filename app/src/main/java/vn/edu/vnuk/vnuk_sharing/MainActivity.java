package vn.edu.vnuk.vnuk_sharing;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.RequiresPermission;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import vn.edu.vnuk.vnuk_sharing.Api.LoginApi;
import vn.edu.vnuk.vnuk_sharing.DataActions.ReadData;
import vn.edu.vnuk.vnuk_sharing.DataStructure.Annoucement;
import vn.edu.vnuk.vnuk_sharing.DataStructure.Deadline;
import vn.edu.vnuk.vnuk_sharing.DataStructure.Syllabus;
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

        /*
        final GeneratingDummyData generatingDummyData = new GeneratingDummyData();

        generatingDummyData.generateCourseOnFirebaseDatabase(10, database);
        generatingDummyData.generateListClassOfCoursesOnFirebaseDatabase(database);
        generatingDummyData.generateUsersOnFirebaseDatabase(10, database);
        */
    }

    @Override
    public void onStart(){
        super.onStart();

        ReadData readData = new ReadData();
        readData.getAllUser(userArrayList);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_login :{
                if(loginApi.checkLogin(userArrayList, edt_username.getText().toString(), edt_password.getText().toString()) == -1){
                    Toast.makeText(getApplicationContext(), "Đăng nhập thất bai", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Đăng nhập thành công ", Toast.LENGTH_SHORT).show();
                }
            }
            break;
        }
    }
}
