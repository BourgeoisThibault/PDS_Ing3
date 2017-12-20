package com.esipe.esibankm.esibankm.services;

import com.esipe.esibankm.esibankm.models.MobileToken;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by Usman ABID BUTT on 21/11/2017.
 */

public interface EsibankService {
    public static final String ENDPOINT = "http://notification.esibank.inside.esiag.info";

    @GET("/token")
    String findAnonymToken();

    @POST("/token/")
    MobileToken postToken(@Body MobileToken mobileToken);

}
