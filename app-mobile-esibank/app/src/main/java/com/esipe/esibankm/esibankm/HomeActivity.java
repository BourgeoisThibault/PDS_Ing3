package com.esipe.esibankm.esibankm;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.esipe.esibankm.esibankm.models.MobileToken;
import com.esipe.esibankm.esibankm.services.ConnectSocket;
import com.esipe.esibankm.esibankm.services.LocalService;
import com.esipe.esibankm.esibankm.utils.CardDBOpenHelper;
import com.esipe.esibankm.esibankm.utils.JsonUtils;
import com.esipe.esibankm.esibankm.utils.LoadProp;
import com.esipe.esibankm.esibankm.utils.NFCManager;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.w3c.dom.Text;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import static android.content.ContentValues.TAG;


@RequiresApi(api = Build.VERSION_CODES.M)
public class HomeActivity extends MainActivity {
    private static final String TAG = "HomeActivity";
    private static final String FILE_NAME = "data.json";
    public static String NAME = "";
    private DrawerLayout mDrawer;

    private TelephonyManager telephonyManager;
    private Spinner mySpinner;
    private String name;
    private String uid;
    private Toolbar myToolbar;
    private Snackbar mySnack;

    private String[] mPermission = {Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.RECEIVE_BOOT_COMPLETED, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};

    private static final int REQUEST_CODE_PERMISSION = 2;
    private CardDBOpenHelper mydb;
    private Button connect_button;
    private TextView login_connected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_home);
        mydb = new CardDBOpenHelper(this);


        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_home, null, false);
        mDrawer.addView(contentView, 0);
        login_connected = findViewById(R.id.login_connected);


        telephonyManager = (TelephonyManager)getSystemService(this.TELEPHONY_SERVICE);

       // myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        //setSupportActionBar(myToolbar);

        Spinner mySpinner = (Spinner)findViewById(R.id.users_spinner);
        ArrayAdapter<String> spinnerCountShoesArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.users_list));
        mySpinner.setAdapter(spinnerCountShoesArrayAdapter);

        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Object item = parent.getItemAtPosition(pos);
                name = parent.getSelectedItem().toString();
                uid = String.valueOf(parent.getSelectedItemId());
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        try {
            if (ActivityCompat.checkSelfPermission(this, mPermission[0])
                    != PackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.checkSelfPermission(this, mPermission[1])
                            != PackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.checkSelfPermission(this, mPermission[2])
                            != PackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.checkSelfPermission(this, mPermission[3])
                            != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this,
                        mPermission, REQUEST_CODE_PERMISSION);
                // If any permission aboe not allowed by user, this condition will execute every tim, else your else part will work
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        if(ConnectSocket.isRunning==true){
            findViewById(R.id.connect_button).setVisibility(View.GONE);
        }


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.aboutus, menu);
        return true;
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.e("Req Code", "" + requestCode);
        if (requestCode == REQUEST_CODE_PERMISSION) {
            if (grantResults.length == 4 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[1] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[2] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[3] == PackageManager.PERMISSION_GRANTED) {

                //check if token exists or not and set a json file
                this.checkToken();
                if (!ConnectSocket.isRunning) {
                    this.restartService();
                }
            }
            else
            {
                Toast.makeText(getApplicationContext(),LoadProp.getProperty("popup_thanks_rights",getApplicationContext(),"messages"), Toast.LENGTH_LONG).show();

            }
        }

    }



    public void onStopButtonClick(View view) {
        this.stopService(new Intent(HomeActivity.this,
                LocalService.class));

        if(ConnectSocket.isRunning==false) {
            mySnack = Snackbar.make(view, LoadProp.getProperty("popup_connected",getApplicationContext(),"messages"), 2000);
        }else
            mySnack = Snackbar.make(view, LoadProp.getProperty("popup_disconnected",getApplicationContext(),"messages"), 2000);

        mySnack.show();
        findViewById(R.id.connected_label).setVisibility(View.GONE);
        findViewById(R.id.connect_button).setVisibility(View.VISIBLE);
        findViewById(R.id.name_connected).setVisibility(View.VISIBLE);
        mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);


//        if(rs.isFirst()){
//            System.out.println("BDD resultats : "+mydb.getData(111045));
//        }
//        else
//            mydb.insertCard(111045,1245,1234);



    }


    public void onConnectionButtonClick(View view) {
        Log.i(TAG, "Spinner selected : "+name+" UID : "+uid);
        MobileToken mobileTokenMock = JsonUtils.MobileTokenFromJson(JsonUtils.getData(getApplicationContext(),FILE_NAME));
        mobileTokenMock.setUid(uid);
        try {
            JsonUtils.saveData(getApplicationContext(),JsonUtils.objectToJson(mobileTokenMock),FILE_NAME,MODE_PRIVATE);
            Log.i(TAG, "Spinner saved : "+name+" UID : "+mobileTokenMock.getUid());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Intent appel = new Intent(HomeActivity.this, MockConnectedActivity.class);
        appel.putExtra("name",name);
        if(ConnectSocket.isRunning==true){
            mySnack = Snackbar.make(view, "Vous êtes déjà connecté !", 2000);
            mySnack.show();
        }else
            if(ConnectSocket.isOnline()){
                this.restartService();
                login_connected.setText(name);
                NAME = name;
                startActivity(appel);
            }
            else{
                mySnack = Snackbar.make(view, "Aucune connexion ou VPN non connecté !", 2000);
                mySnack.show();
            }

    }


    public void restartService(){
        //recheck (to be sure) if service is started or not to avoir conflicts
        if(ConnectSocket.isRunning==true){
            this.stopService(new Intent(HomeActivity.this,
                    LocalService.class));
        }

        Intent intent = new Intent(this,com.esipe.esibankm.esibankm.services.LocalService.class);
        this.startService(intent);
    }


    public void checkToken(){
        File file = new File(getApplicationContext().getFilesDir().getPath()+"/"+FILE_NAME);
        if(!file.exists()) {
            try {
                MobileToken mobileToken = new MobileToken();
                mobileToken.setUid("0");
                mobileToken.setToken("null");
                JsonUtils.saveData(getApplicationContext(),JsonUtils.objectToJson(mobileToken),FILE_NAME, Context.MODE_PRIVATE);
            } catch (JsonProcessingException e) {
                Log.i(TAG, "ERROR WRITING JSON FILE !" +e);
            }
        }
        String contenu = JsonUtils.getData(getApplicationContext(),FILE_NAME);
        Log.i(TAG, "Token from file : "+contenu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.aboutus_action){
            Log.i(TAG, "Action about us...");
            Intent intent = new Intent(getApplicationContext(),AboutUsActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onResume() {
        super.onResume();
        if(ConnectSocket.isRunning==true){
            findViewById(R.id.connect_button).setVisibility(View.GONE);
            findViewById(R.id.connected_label).setVisibility(View.VISIBLE);
            findViewById(R.id.name_connected).setVisibility(View.GONE);
            mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN);
            mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);


        }
        else{
            findViewById(R.id.connected_label).setVisibility(View.GONE);
            findViewById(R.id.connect_button).setVisibility(View.VISIBLE);
            findViewById(R.id.name_connected).setVisibility(View.VISIBLE);
            mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        }
    }
}
