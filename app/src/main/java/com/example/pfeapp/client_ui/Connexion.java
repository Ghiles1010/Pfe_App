package com.example.pfeapp.client_ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

        String email= emailET.getText().toString();
        String psw= pswET.getText().toString();
        String type="login";

        Connexion_Background background=new Connexion_Background(this);
        background.execute(type,email,psw);




    }


}
