package com.example.pfeapp.user_ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pfeapp.BD.Data_Base;
import com.example.pfeapp.BD.User;
import com.example.pfeapp.R;
import com.example.pfeapp.prest_ui.Temporaire;

import static com.example.pfeapp.user_ui.Connexion.LOGGED;
import static com.example.pfeapp.user_ui.Connexion.PREFERENCES;

public class MainActivity extends AppCompatActivity  {
    private Button Acc,insc;
    User user=new User();
    Data_Base db=new Data_Base(this);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        aliment_data_base();
        SharedPreferences prefs = getSharedPreferences(PREFERENCES, MODE_PRIVATE);
        String isLogged = prefs.getString(LOGGED, "");

        if(isLogged.isEmpty()) {


        setContentView(R.layout.welcome);
        Acc=(Button)findViewById(R.id.connecter);
        insc=(Button)findViewById(R.id.inscrire);

       Acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenConn();
            }
        });




        insc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenInsc();
            }
        });

        }

        else{

            user.setId(isLogged);

            Intent intent = new Intent(this, Temporaire.class);
            intent.putExtra("User",  user);
            startActivity(intent);
        }

    }

    private void OpenInsc() {
        Intent intent =new Intent(this, Inscription.class);
        startActivity(intent);
    }

    public void OpenConn(){
        Intent intent =new Intent(this, Connexion.class);
        startActivity(intent);
    }



    void aliment_data_base(){
        db.insertCat();
    }
}
