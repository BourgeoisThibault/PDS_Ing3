package com.esipe.esibankm.esibankm;

import android.content.Context;
import android.content.Intent;
import android.support.test.*;


import com.esipe.esibankm.esibankm.services.LocalService;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;


import static org.hamcrest.core.IsNull.notNullValue;
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
    public void testWitchTargetNotificationAccounts(){

        final LocalService localService = mock(LocalService.class);
        String target = "accounts";
        Intent intent= mock(Intent.class);

        Context context = mock(Context.class);
        when(localService.switchTargetNotification(target)).thenReturn(new Intent(context, AccountsActivity.class));

        Intent expected = new Intent(context, AccountsActivity.class);
        Assert.assertThat(expected,notNullValue());

    }


    @Test
    public void testWitchTargetNotificationTransaction(){

        final LocalService localService = mock(LocalService.class);
        String target = "transfer";
        Intent intent= mock(Intent.class);

        Context context = mock(Context.class);
        when(localService.switchTargetNotification(target)).thenReturn(new Intent(context, AccountsActivity.class));

        Intent expected = new Intent(context, AccountsActivity.class);
        Assert.assertThat(expected,notNullValue());

    }

    @Test
    public void testNotificationDisplay(){

    }

}
