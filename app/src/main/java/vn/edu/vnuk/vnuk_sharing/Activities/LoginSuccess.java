package vn.edu.vnuk.vnuk_sharing.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import vn.edu.vnuk.vnuk_sharing.DataTemp.Data;
import vn.edu.vnuk.vnuk_sharing.R;

public class LoginSuccess extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView txtUsername, txtLevel;
    ListView listViewNotification;
    ArrayList<String> notificationArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View view = navigationView.getHeaderView(0);
        txtUsername = (TextView) view.findViewById(R.id.txtNameOfCurrentUser);
        txtUsername.setText("Username : " + Data.currentUser.getUsername());
        txtLevel = (TextView) view.findViewById(R.id.txtLevelOfCurrentUser);
        if(Data.currentUser.getAccess() == 0){
            txtLevel.setText("Level   : Teacher");
        }else{
            txtLevel.setText("Level   : Student");
        }
        listViewNotification = (ListView) findViewById(R.id.lvNotification);

        navigationView.setNavigationItemSelectedListener(this);

        notificationArrayList = new ArrayList<String>();
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, notificationArrayList);
        listViewNotification.setAdapter(adapter);

        FirebaseDatabase
                .getInstance()
                .getReference()
                .child("root")
                .child("notification")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        notificationArrayList.clear();
                        for(DataSnapshot ds : dataSnapshot.getChildren()){
                            notificationArrayList.add(ds.getValue().toString());
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });



    }

    @Override
    public void onBackPressed() {

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_courses) {
            Intent newSharing = new Intent(LoginSuccess.this,CoursesActivity.class);
            startActivity(newSharing);

        } else if (id == R.id.nav_setting) {
            Intent newSharing = new Intent(LoginSuccess.this,SettingActivity.class);
            startActivity(newSharing);

        } else if (id == R.id.nav_logout) {
            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
            alertBuilder.setTitle("LOG OUT");
            alertBuilder.setMessage("Are you sure to log out?");
            alertBuilder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int id) {
                    Data.clearAllData();
                    finish();
                }
            });
            alertBuilder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            AlertDialog alertDialog = alertBuilder.create();
            alertDialog.show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
