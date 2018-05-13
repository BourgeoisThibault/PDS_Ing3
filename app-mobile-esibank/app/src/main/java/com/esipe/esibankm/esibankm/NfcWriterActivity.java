package com.esipe.esibankm.esibankm;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.os.Build;
import android.os.Parcelable;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.esipe.esibankm.esibankm.models.MobileToken;
import com.esipe.esibankm.esibankm.utils.CardDBOpenHelper;
import com.esipe.esibankm.esibankm.utils.JsonUtils;
import com.esipe.esibankm.esibankm.utils.NFCManager;

public class NfcWriterActivity extends Activity {
    private static final String FILE_NAME = "data.json";
    private boolean response;
    private CardDBOpenHelper mydb;
    private Cursor rs;
    private MobileToken mobileToken;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc_writer);
        mobileToken = JsonUtils.MobileTokenFromJson(JsonUtils.getData(getApplicationContext(),FILE_NAME));


        mydb = new CardDBOpenHelper(this);

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

            rs = mydb.getData(Integer.valueOf(mobileToken.getUid()));
            if(rs.moveToFirst()){
                String card_num = rs.getString(rs.getColumnIndex(CardDBOpenHelper.card_num));
                String pin = rs.getString(rs.getColumnIndex(CardDBOpenHelper.pin));
                Log.i("NFC","BDD resultats : "+rs.getString(rs.getColumnIndex(CardDBOpenHelper.card_num)));

                Log.i("NFC","Ok, contact !!!");
                response = NFCManager.getInstance().writeToDevice(card_num+";"+pin+";",getIntent(),NFCManager.getInstance().getNFCAdapter(getApplicationContext()));
                if(response){
                    Toast.makeText(getApplicationContext(), "Envoi des informations réussi !", Toast.LENGTH_SHORT).show();
                    Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    // Vibrate for 500 milliseconds
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        v.vibrate(VibrationEffect.createOneShot(500,VibrationEffect.DEFAULT_AMPLITUDE));
                    }else{
                        //deprecated in API 26
                        v.vibrate(500);
                    }
                }
                else
                    Toast.makeText(getApplicationContext(), "Echec de l'envoi des informations !", Toast.LENGTH_SHORT).show();

            }
            else{
                Log.i("NFC","Aucune carte enregistrée correspondante  ! : ");
            }
        }


    }

}
