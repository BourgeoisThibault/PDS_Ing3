package com.esipe.esibankm.esibankm.models;

/**
 * Created by usman on 23/11/2017.
 */

public class NotificationModel {

    private String title;

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTitle() {
        return title;
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
