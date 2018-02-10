package com.esipe.esibankm.esibankm;

import com.esipe.esibankm.esibankm.models.MobileToken;
import com.esipe.esibankm.esibankm.models.NotificationModel;
import com.esipe.esibankm.esibankm.utils.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by usman on 10/02/2018.
 */

@RunWith(MockitoJUnitRunner.class)
public class JsonUtilsTest {
    @Mock
    NotificationModel notificationModel;

    @Before
    public void init(){

        notificationModel = new NotificationModel();
        notificationModel.setTarget("accounts");
        notificationModel.setMessage("test");
        notificationModel.setTitle("junit");
    }

    @Test
    public void checkObjectToJson() throws JsonProcessingException {

        String json = JsonUtils.objectToJson(notificationModel);
        String wrong = "toto";

        Assert.assertTrue(JsonUtils.objectFromJson(json,NotificationModel.class)!=null);

    }

//
//    @Test
//    public void checkMobileTokenFromJson() throws JsonProcessingException {
//        MobileToken mobileToken = mock(MobileToken.class);
//
//        String json = JsonUtils.objectToJson(notificationModel);
//        when(JsonUtils.MobileTokenFromJson(json)).thenReturn(mobileToken);
//
//        Assert.assertEquals(mobileToken,JsonUtils.MobileTokenFromJson(json));
//    }

}
