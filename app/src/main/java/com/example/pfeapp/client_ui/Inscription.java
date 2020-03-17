package com.example.pfeapp.client_ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import androidx.appcompat.app.AppCompatActivity;

import com.example.pfeapp.R;

import static com.example.pfeapp.R.layout.inscription;

public class Inscription extends AppCompatActivity {

    private EditText nomET ,prenomET ,usernameET,emailET,pswET;
    private Button inscrire;

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


        inscrire=(Button) findViewById(R.id.inscrire);
        inscrire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Go();
            }
        });


    }

    private void Go() {

        Intent intent =new Intent(this,Menu.class);
        startActivity(intent);

    }


}