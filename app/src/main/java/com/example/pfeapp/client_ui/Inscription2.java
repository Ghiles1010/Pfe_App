package com.example.pfeapp.client_ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pfeapp.R;

import static com.example.pfeapp.R.layout.inscription2;

public class Inscription2 extends AppCompatActivity {

    private EditText mailET ,passET ;
    private Button cont;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(inscription2);

        mailET= (EditText)findViewById(R.id.mail);
        passET= (EditText)findViewById(R.id.password);


        cont=(Button) findViewById(R.id.continuer);

        cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenConn();
            }
        });


    }

    private void OpenConn() {
        Intent intent =new Intent(this, Menu.class);
        startActivity(intent);
    }
}
