package com.esipe.esibankm.esibankm;

import android.nfc.NdefMessage;

import com.esipe.esibankm.esibankm.services.LocalService;
import com.esipe.esibankm.esibankm.utils.NFCManager;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by usman on 13/05/2018.
 */

public class NfcManagerTest {
    @Mock
    NFCManager nfcManager;

    @Test
    public void testCreateMessage(){
        boolean isNDefMessage = false;

        final NFCManager nfcManager = mock(NFCManager.class);
        NdefMessage ndefMessage = nfcManager.createMessage("test");
        when(nfcManager.createMessage("test")).thenReturn(ndefMessage);
        if(nfcManager.createMessage("test") == ndefMessage)
            isNDefMessage = true;

        Assert.assertTrue(isNDefMessage);

    }

}
