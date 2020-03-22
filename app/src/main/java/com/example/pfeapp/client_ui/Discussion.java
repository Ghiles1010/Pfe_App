package com.example.pfeapp.client_ui;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pfeapp.R;

public class Discussion extends AppCompatActivity {

    private EditText message;
    private ImageButton send;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.discussion);

        message=(EditText)findViewById(R.id.Message_To_send);
        send=(ImageButton)findViewById(R.id.send);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();

            }


        });

    }






    void sendMessage() {

        Background background=new Background(this);

        if(InternetAvailable()) {

            String mes=message.getText().toString();
            String conv="conv";

            String type = "send_message";
            String res;

            res=background.execute(type, mes,conv).toString();

        }
        else{

            Toast.makeText(Discussion.this, "aucune connexion Internet", Toast.LENGTH_SHORT).show();

        }

    }




    ///////////////////////////////////////////////////////////////////////////////////////////////////////////

    public boolean InternetAvailable() {
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
        }  else{
            connected = false;}

        return connected;
    }
}
