package vn.edu.vnuk.vnuk_sharing.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import vn.edu.vnuk.vnuk_sharing.DataStructure.CheckIntentIsCalled;
import vn.edu.vnuk.vnuk_sharing.DataTemp.Data;
import vn.edu.vnuk.vnuk_sharing.R;

/**
 * Created by quangngoc430 on 23/11/2017.
 */

public class NewsActivity extends AppCompatActivity{

    TextView tvDateOfNewsNotification, tvTitleOfNewsNotification, tvContentOfNewsNotification;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showing_detailed_a_news);

        tvDateOfNewsNotification = (TextView) findViewById(R.id.tvDateOfNewsNotification);
        tvTitleOfNewsNotification = (TextView) findViewById(R.id.tvTitleOfNewsNotification);
        tvContentOfNewsNotification = (TextView) findViewById(R.id.tvContentOfNewsNotification);

        tvDateOfNewsNotification.setText(Data.currentNews.getDate().toString());
        tvTitleOfNewsNotification.setText(Data.currentNews.getTitle());
        tvContentOfNewsNotification.setText(Data.currentNews.getContent());

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        CheckIntentIsCalled.isIntentNewsDetails = false;
    }
}
