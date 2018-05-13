package com.esipe.esibankm.esibankm;

import android.app.Notification;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.esipe.esibankm.esibankm.models.NotificationModel;

import java.util.List;

/**
 * Created by usman on 13/05/2018.
 */

public class NotificationAdapter extends ArrayAdapter<NotificationModel> {

    public NotificationAdapter(Context context, List<NotificationModel> clients) {
        super(context,0,clients);
    }

    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_notifications, parent, false);
        }

        NotificationModel c = getItem(position);

        TextView title= (TextView) view.findViewById(R.id.title_notif);
        title.setText(c.getTitle());

        TextView mess= (TextView) view.findViewById(R.id.message_notif);
        mess.setText(c.getMessage());

        TextView date= (TextView) view.findViewById(R.id.date_notif);
        date.setText(c.getDate());

        return view;

    }
}
