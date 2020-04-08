package com.example.pfeapp.user_ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pfeapp.R;

import static com.example.pfeapp.R.layout.choix_type_session;

public class Choix_session_inscr extends AppCompatActivity {


    Button prest, client;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(choix_type_session);

        prest = findViewById(R.id.prest);
        client=findViewById(R.id.client);



        prest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });


    }












}
