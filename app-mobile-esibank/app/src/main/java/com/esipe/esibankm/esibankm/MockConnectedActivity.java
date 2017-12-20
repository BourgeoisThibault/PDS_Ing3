package com.esipe.esibankm.esibankm;

import android.app.Activity;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.esipe.esibankm.esibankm.models.MobileToken;
import com.esipe.esibankm.esibankm.services.ConnectSocket;
import com.esipe.esibankm.esibankm.utils.JsonUtils;

public class MockConnectedActivity extends Activity {

    private static final String TAG = "MockConnectedActivity";
    private static final String FILE_NAME = "data.json";
    private MobileToken mobileTokenMock;
    private TextView connect_label;
    private Snackbar mySnack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mock_connected);
        connect_label = findViewById(R.id.connectedName);
        connect_label.setText(String.valueOf(getIntent().getExtras().getString("name")));

        mobileTokenMock = new MobileToken();
        mobileTokenMock = JsonUtils.MobileTokenFromJson(JsonUtils.getData(getApplicationContext(),FILE_NAME));

        Log.i(TAG, "Token in connected activity  : "+mobileTokenMock.getToken()+" UID : "+mobileTokenMock.getUid());

//        mySnack = Snackbar.make(findViewById(R.id.myCoordinatorLayout), "Vous êtes connecté en tant que  :"+String.valueOf(getIntent().getExtras().getString("name")), 2000);
//        mySnack.show();

    }
}
