package com.esipe.esibankm.esibankm;


import android.app.Activity;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcEvent;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.esipe.esibankm.esibankm.utils.NFCManager;


public class NfcWriterActivity extends Activity {

    private NFCManager nfcMger;

    private View v;
    private Ndef ndef = null;
    private ProgressDialog dialog;
    Tag currentTag;
    private boolean response;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc_writer);
    }



    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent != null && NfcAdapter.ACTION_NDEF_DISCOVERED.equals(intent.getAction())) {
            Parcelable[] rawMessages =
                    intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
            if (rawMessages != null) {
                NdefMessage[] messages = new NdefMessage[rawMessages.length];
                for (int i = 0; i < rawMessages.length; i++) {
                    messages[i] = (NdefMessage) rawMessages[i];
                }
                // Process the messages array.
                Toast.makeText(getApplicationContext(),
                        "Writing message success : ",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (NFCManager.getInstance().getNFCAdapter(getApplicationContext()).ACTION_NDEF_DISCOVERED.equals(getIntent().getAction())) {
            System.out.println("Ok, contact !!!");
            response = NFCManager.getInstance().writeToDevice("1234567890123456;1234;",getIntent(),NFCManager.getInstance().getNFCAdapter(getApplicationContext()));
        }

        if(response){
            Toast.makeText(getApplicationContext(), "Envoi des informations réussi !", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(getApplicationContext(), "Echec de l'envoi des informations !", Toast.LENGTH_SHORT).show();

    }


    //    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        nfcMger = new NFCManager();
//
//        v = findViewById(R.id.mainLyt);
//
//
//        final EditText et = (EditText) findViewById(R.id.content);
//
//        FloatingActionButton btn = (FloatingActionButton) findViewById(R.id.fab);
//
//        if (NFCManager.getInstance().getNFCAdapter().ACTION_NDEF_DISCOVERED.equals(getIntent().getAction())) {
//
//
//            Intent nfcIntent = new Intent(this, getClass());
//            onNewIntent(nfcIntent);
//        }
//
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.nfc_writer, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }




//    @Override
//    protected void onResume() {
//        super.onResume();
//        Log.d("Nfc", "CONTACT !");
//
//        try {
//           // nfcMger.verifyNFC();
//            //nfcMger.enableDispatch();
//
//            Intent nfcIntent = new Intent(this, getClass());
//              onNewIntent(nfcIntent);
//            nfcIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, nfcIntent, 0);
//            IntentFilter[] intentFiltersArray = new IntentFilter[] {};
//            String[][] techList = new String[][] { { android.nfc.tech.Ndef.class.getName() }, { android.nfc.tech.NdefFormatable.class.getName() } };
//            NfcAdapter nfcAdpt = NfcAdapter.getDefaultAdapter(this);
//            nfcAdpt.enableForegroundDispatch(this, pendingIntent, intentFiltersArray, techList);
//        }catch (Exception e){
//            Log.d("Nfc", "Erreur nfc");

        //}
//        catch(NFCManager.NFCNotSupported nfcnsup) {
//            Snackbar.make(v, "NFC not supported", Snackbar.LENGTH_LONG).show();
//        }
//        catch(NFCManager.NFCNotEnabled nfcnEn) {
//            Snackbar.make(v, "NFC Not enabled", Snackbar.LENGTH_LONG).show();
//        }

//    }


//
//    @Override
//    public void onNewIntent(Intent intent) {
//        Log.d("Nfc", "New intent");
//        NFCManager.getInstance().writeToDevice("toto",intent);
//    }






}
