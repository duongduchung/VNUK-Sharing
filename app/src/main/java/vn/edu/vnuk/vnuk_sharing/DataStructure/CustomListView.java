package vn.edu.vnuk.vnuk_sharing.DataStructure;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import vn.edu.vnuk.vnuk_sharing.R;

/**
 * Created by quangngoc430 on 07/12/2017.
 */

public class CustomListView extends ArrayAdapter<String> {

    private static final Integer IMAGE_ID[] = {R.drawable.syllabus, R.drawable.deadline, R.drawable.noti, R.drawable.news};
    private ArrayList<Integer> imgID;
    private Activity context;
    private ArrayList<String> notificationNames;
    public CustomListView(Activity context, ArrayList<String> notificationNames, ArrayList<Integer> imgID) {
        super(context, R.layout.notification_item, notificationNames);

        this.context = context;
        this.notificationNames = notificationNames;
        this.imgID = imgID;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View r = convertView;
        ViewHolder viewHolder = null;

        if(r == null)
        {
            LayoutInflater layoutInflater = context.getLayoutInflater();
            r = layoutInflater.inflate(R.layout.notification_item, null, true);
            viewHolder = new ViewHolder(r);
            r.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) r.getTag();



        }

        viewHolder.notificationIcon.setImageResource(IMAGE_ID[imgID.get(position)]);

        if(notificationNames.get(position).length() > 34)
            viewHolder.notificationTitle.setText(notificationNames.get(position).substring(1, 34));
        else
            viewHolder.notificationTitle.setText(notificationNames.get(position));

        return r;

    }

    class ViewHolder{
        TextView notificationTitle;
        ImageView notificationIcon;

        ViewHolder(View v){
            notificationTitle =(TextView) v.findViewById(R.id.txtNotificationTitle);
            notificationIcon = (ImageView) v.findViewById(R.id.imgNotification);

        }
    }
}
