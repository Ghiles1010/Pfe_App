package com.example.pfeapp.client_ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pfeapp.R;

public class MainActivity extends AppCompatActivity  {
    private Button Acc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        Acc=(Button)findViewById(R.id.button);

        Acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenConn();
            }
        });
    }

    public void OpenConn(){
        Intent intent =new Intent(this, Connexion.class);
        startActivity(intent);
    }






}
