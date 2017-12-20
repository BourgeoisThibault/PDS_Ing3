package com.esipe.esibankm.esibankm;

import android.content.Intent;

import com.esipe.esibankm.esibankm.services.LocalService;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Usman ABID BUTT on 20/11/2017.
 */

public class LocalServiceTest {
    @Mock
    LocalService localService;
    @Mock
    Intent intent;
    @Mock
    int flags;
    @Mock
    int startId;
    @Test
    public void testStartService(){
        final LocalService localService = mock(LocalService.class);

        when(localService.onStartCommand(intent,flags,startId)).thenReturn(1);

        Assert.assertEquals("Message retourné",1,localService.onStartCommand(intent,flags,startId));

    }

    @Test
    public void testThreadStarted(){

    }
    @Test
    public void testUserPreferencesAccepted(){

    }

    @Test
    public void testNotificationDisplay(){

    }

}
