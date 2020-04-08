package com.example.pfeapp.user_ui;

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
import com.example.pfeapp.client_ui.Background;

import static com.example.pfeapp.R.layout.inscription;

public class Inscription extends AppCompatActivity {

    private EditText nomET ,prenomET ,usernameET,emailET,pswET,confpsw;
    private Button inscrire;

    Background background;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(inscription);

        nomET= (EditText)findViewById(R.id.nom);
        prenomET= (EditText)findViewById(R.id.prenom);
        usernameET= (EditText)findViewById(R.id.username);
        emailET=(EditText)findViewById(R.id.mail);
        pswET=(EditText)findViewById(R.id.psw);
        confpsw=(EditText)findViewById(R.id.Confpsw);



        inscrire=(Button) findViewById(R.id.inscrire);
        inscrire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Go();
            }
        });


    }

    private void Go() {
        Background background=new Background(this);


        if(InternetAvailable()) {
            String email = emailET.getText().toString();
            String psw = pswET.getText().toString();
            String userName=usernameET.getText().toString();
            String name=nomET.getText().toString();
            String surname=prenomET.getText().toString();

            String type = "sign-in";
            String res;

            res=background.execute(type, name, surname, userName, email, psw).toString();


            Toast.makeText(this,res, Toast.LENGTH_LONG).show();
        }
        else{

            Toast.makeText(this, "aucune connexion Internet", Toast.LENGTH_LONG).show();

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