package com.example.pfeapp.client_ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pfeapp.R;

import static com.example.pfeapp.R.layout.connexion;

public class Connexion extends AppCompatActivity {
    private EditText emailET,pswET;
    private Button conn;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(connexion);

        emailET= (EditText)findViewById(R.id.email);
        pswET= (EditText)findViewById(R.id.psw);

        conn=(Button) findViewById(R.id.connexion);

        conn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenConn();
            }
        });


    }

    public void OpenConn(){
        Connexion_Background background=new Connexion_Background(this);

        if(InternetAvailable()) {
            String email = emailET.getText().toString();
            String psw = pswET.getText().toString();
            String type = "login";


            background.execute(type, email, psw);
        }
        else{

            Toast.makeText(Connexion.this, "aucune connexion Internet", Toast.LENGTH_SHORT).show();



            
           }



    }
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
