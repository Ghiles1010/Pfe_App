package com.example.pfeapp.prest_ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pfeapp.R;
import com.example.pfeapp.client_ui.Menu;

public class Temporaire extends AppCompatActivity {


    private Button client;
    private Button prestataire;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.temporaire);

        client=findViewById(R.id.profil_client);
        prestataire=findViewById(R.id.profil_prest);


        client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goClient();
            }
        });



        prestataire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goPrest();
            }
        });

    }


    void goClient(){

        Intent intent =new Intent(this, Menu.class);
        startActivity(intent);
    }

    void goPrest(){

        Intent intent =new Intent(this, Prestaire_Menu.class);
        startActivity(intent);
    }


}
