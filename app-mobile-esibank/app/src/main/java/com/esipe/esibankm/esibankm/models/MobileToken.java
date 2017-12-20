package com.esipe.esibankm.esibankm.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Usman ABID BUTT on 21/11/2017.
 */

public class MobileToken implements Serializable {
    @SerializedName("token")
    private String token;
    @SerializedName("uid")
    private String uid;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUid() {
        return uid;
    }

    @Override
    public String toString() {
        return "MobileToken{" +
                "token='" + token + '\'' +
                ", uid='" + uid + '\'' +
                '}';
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

}
