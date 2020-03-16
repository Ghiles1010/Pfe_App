package com.example.pfeapp.client_ui;

<<<<<<< Updated upstream
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import androidx.appcompat.app.AppCompatActivity;

import com.example.pfeapp.R;

=======
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

>>>>>>> Stashed changes
import static com.example.pfeapp.R.layout.inscription;

public class Inscription extends AppCompatActivity {

<<<<<<< Updated upstream
    private EditText nomET ,prenomET ,usernameET;
    private Button continuer;

    @SuppressLint("ClickableViewAccessibility")
    @Override
=======

>>>>>>> Stashed changes
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(inscription);

<<<<<<< Updated upstream
        nomET= (EditText)findViewById(R.id.nom);
        prenomET= (EditText)findViewById(R.id.prenom);
        usernameET= (EditText)findViewById(R.id.username);

        continuer=(Button) findViewById(R.id.continuer);

        continuer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Go();
            }
        });


    }

    private void Go() {

        Intent intent =new Intent(this, Inscription2.class);
        startActivity(intent);

    }


=======
    }
>>>>>>> Stashed changes
}
