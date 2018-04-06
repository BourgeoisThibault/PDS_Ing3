package com.esipe.esibankm.esibankm.utils;

/**
 * Created by usman on 24/03/2018.
 */

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

import android.content.Context;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.os.Parcelable;
import android.widget.Toast;

public class NFCManager {

    private Context context;
    private NfcAdapter nfcAdpt;


    public void setContext(Context context) {
        this.context = context;
    }

    private static NFCManager instance;

    public NFCManager() {
        init();
    }

    private void init() {

    }

    public static NFCManager getInstance() {
        if (instance == null)
            instance = new NFCManager();

        return instance;
    }

//    public void verifyNFC() throws NFCNotSupported, NFCNotEnabled {
//
//        nfcAdpt = NfcAdapter.getDefaultAdapter(getInstance().context);
//
//        if (instance == null)
//            throw new NFCNotSupported();
//
//        if (!instance.isEnabled())
//            throw new NFCNotEnabled();
//
//    }

    public static class NFCNotSupported extends Exception {

        public NFCNotSupported() {
            super();
        }
    }

    public static class NFCNotEnabled extends Exception {

        public NFCNotEnabled() {
            super();
        }
    }

    public boolean writeToDevice(String msgToWrite, Intent intent, NfcAdapter nfcAdapter ) {
//        Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        Tag tag = intent.getParcelableExtra(nfcAdapter.EXTRA_TAG);

        if (tag == null) {
            Toast.makeText(context, "No Tag. Waiting...", Toast.LENGTH_LONG)
                    .show();
            return false;
        }

        Ndef ndefref = Ndef.get(tag);

        try {
            assert ndefref != null;
            ndefref.connect();

            try {

                ndefref.writeNdefMessage(createMessage(msgToWrite));
//
//                Toast.makeText(context, "Writing message successful",
//                        Toast.LENGTH_SHORT).show();
                    System.out.println("Writing message successful !");
                    return true;

            } catch (Exception e) {
//                Toast.makeText(context,
//                        "Writing message failed : " + e.getMessage(),
//                        Toast.LENGTH_SHORT).show();
                System.out.println("WRITING MESSAGE FAILED !");
                return false;

            }
        } catch (Exception e) {
//            Toast.makeText(context, "No Tag. Waiting...", Toast.LENGTH_LONG)
//                    .show();
            System.out.println("NO TAG DETECTED !");
             return false;
        }

//        try {
//            ndefref.close();
//        } catch (Exception e) {
//
//        }
    }

    private NdefMessage createMessage(String text) {

        NdefRecord[] record = new NdefRecord[1];

        String lang = "en";

        byte[] langBytes = lang.getBytes(Charset.forName("US-ASCII"));

        byte[] textBytes = text.getBytes(Charset.forName("UTF-8"));

        char status = (char) (langBytes.length);

        byte[] data = new byte[1 + langBytes.length + textBytes.length];

        data[0] = (byte) status;

        System.arraycopy(langBytes, 0, data, 1, langBytes.length);
        System.arraycopy(textBytes, 0, data, 1 + langBytes.length,
                textBytes.length);

        record[0] = new NdefRecord(NdefRecord.TNF_WELL_KNOWN,
                NdefRecord.RTD_TEXT, new byte[0], data);

        return new NdefMessage(record);
    }

//    public boolean checkDeviceHasNFC() {
//
//        return getNFCAdapter() != null && getNFCAdapter().isEnabled();
//
//    }

    public NfcAdapter getNFCAdapter(Context context1) {
        NfcManager manager = (NfcManager) context1
                .getSystemService(Context.NFC_SERVICE);

        return manager.getDefaultAdapter();

    }

}