package com.esipe.esibankm.esibankm.models;

/**
 * Created by usman on 23/11/2017.
 */

public class NotificationModel {

    private String title;
    private String target;
    private String message;
    private String date;

    public String getNotif_id() {
        return notif_id;
    }

    public void setNotif_id(String notif_id) {
        this.notif_id = notif_id;
    }

    private String notif_id;

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }
    public String getTarget() {
        return target;
    }
    public void setTarget(String target) {
        this.target = target;
    }

    @Override
    public String toString() {
        return "NotificationModel{" +
                "title='" + title + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
