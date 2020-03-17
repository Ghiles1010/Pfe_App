package com.example.pfeapp.client_ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

        emailET= (EditText)findViewById(R.id.mail);
        pswET= (EditText)findViewById(R.id.psww);

        conn=(Button) findViewById(R.id.connexion);

        conn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenConn();
            }
        });


    }

    public void OpenConn(){
        Background background=new Background(this);

        if(InternetAvailable()) {
            String email = emailET.getText().toString();
            String psw = pswET.getText().toString();
            String type = "login";
            String res;

            res=background.execute(type, email, psw).toString();

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
