package com.esipe.esibankm.esibankm;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.esipe.esibankm.esibankm.models.Card;
import com.esipe.esibankm.esibankm.models.MobileToken;
import com.esipe.esibankm.esibankm.utils.CardDBOpenHelper;
import com.esipe.esibankm.esibankm.utils.JsonUtils;
import com.esipe.esibankm.esibankm.utils.NFCManager;

import java.util.regex.Pattern;


public class NfcCardActivity extends Activity {
    private static final String FILE_NAME = "data.json";

    private NFCManager nfcMger;

    private View v;
    private Ndef ndef = null;
    private ProgressDialog dialog;
    Tag currentTag;
    private boolean response;
    private Card card;
    private Cursor rs;
    private CardDBOpenHelper mydb;
    private EditText card_num_edit;
    private EditText card_pin_edit;
    private TextView card_label;
    private MobileToken mobileToken;
    private TextView card_name;
    private TextView card_name_edit;

    protected void onCreate(Bundle savedInstanceState) {
        mydb = new CardDBOpenHelper(this);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc_card);
        card_label = findViewById(R.id.card_number);
        card_name = findViewById(R.id.card_name);

        card_num_edit = findViewById(R.id.edit_card_num);
        card_pin_edit = findViewById(R.id.edit_pin);
        card_name_edit = findViewById(R.id.edit_name);


        mobileToken = JsonUtils.MobileTokenFromJson(JsonUtils.getData(getApplicationContext(),FILE_NAME));

        rs = mydb.getData(Integer.valueOf(mobileToken.getUid()));
        if(rs.moveToFirst()){
            findViewById(R.id.paiement_layout).setVisibility(View.VISIBLE);
            System.out.println("BDD resultats : "+rs.getString(rs.getColumnIndex(CardDBOpenHelper.pin)));
            card_label.setText(rs.getString(rs.getColumnIndex(CardDBOpenHelper.card_num)).toString());
            card_name.setText(rs.getString(rs.getColumnIndex(CardDBOpenHelper.name)).toString());

            findViewById(R.id.register_card0).setVisibility(View.GONE);

//            card_name.setText(rs.getString(rs.getColumnIndex(CardDBOpenHelper.name)).toString());
        }
        else{
            findViewById(R.id.paiement_layout).setVisibility(LinearLayout.GONE);
            findViewById(R.id.register_card0).setVisibility(View.VISIBLE);
            System.out.println("Aucune carte correspondante  ! : ");

        }

    }


    public void OnValidClick(View view) {
        if ((Pattern.matches("[a-zA-Z]+", card_num_edit.toString()) == true  || card_num_edit.length() != 10)) {
            Toast.makeText(getApplicationContext(), "Le numéro de carte doit contenir 10 chiffres !"+ card_num_edit.length(), Toast.LENGTH_SHORT).show();
        }
        else if(card_pin_edit.length() != 6){
            Toast.makeText(getApplicationContext(), "Le code pin doit contenir 6 caractère !"+ card_pin_edit.length(), Toast.LENGTH_SHORT).show();
        }    else if(card_name_edit.length() == 0){
            Toast.makeText(getApplicationContext(), "Merci de renseigner un nom valide !", Toast.LENGTH_SHORT).show();
        }
        else{
            String card = card_num_edit.getText().toString();
            String pin = card_pin_edit.getText().toString();

            Log.i("NFC","Ok, Info !! + NUM : "+ card_num_edit.getText().toString() + " PIN : "+ card_pin_edit.getText().toString());

            mydb.insertCard(Integer.parseInt(mobileToken.getUid()),card,pin,card_name_edit.getText().toString());
            Toast.makeText(getApplicationContext(), "Carte enregistrée avec succès !", Toast.LENGTH_SHORT).show();
            card_label.setText(card_num_edit.getText().toString());
            card_name.setText(card_name_edit.getText().toString());
            card_num_edit.setText(card_num_edit.getText().toString());
            findViewById(R.id.paiement_layout).setVisibility(View.VISIBLE);
            findViewById(R.id.register_card0).setVisibility(View.GONE);

        }
    }

    public void onDeleteClick(View view) {
        mydb.deleteCard(Integer.valueOf(mobileToken.getUid()));
        Toast.makeText(getApplicationContext(), "Carte supprimée avec succès !", Toast.LENGTH_SHORT).show();
        findViewById(R.id.register_card0).setVisibility(View.VISIBLE);
        findViewById(R.id.paiement_layout).setVisibility(View.GONE);

    }


}
