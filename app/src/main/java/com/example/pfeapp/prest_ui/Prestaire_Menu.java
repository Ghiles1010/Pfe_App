package com.example.pfeapp.prest_ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.pfeapp.BD.User;
import com.example.pfeapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Prestaire_Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prestaire__menu);

        Intent i = getIntent();
        User user = (User)i.getSerializableExtra("User");

        getIntent().putExtra("User", user);

        Toolbar toolBar = findViewById(R.id.toolBarMenu);
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        BottomNavigationView BottomNav = findViewById(R.id.bottom_navigation);
        BottomNav.setOnNavigationItemSelectedListener(navListener);
    }



    private BottomNavigationView.OnNavigationItemSelectedListener navListener=
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment=null;
                    TextView titreBar=(TextView) findViewById(R.id.titreBar);
                    switch (item.getItemId()) {

                        case R.id.nav_profil_prest:


                            titreBar.setText("Profil");
                            selectedFragment = new Profil_prest();


                            break;

                        case R.id.nav_messagerie_prest:

                            titreBar.setText("Messagerie");
                            selectedFragment = new Messagerie_prest();


                            break;

                        case R.id.nav_agenda_prest:
                            titreBar.setText("Agenda");
                            selectedFragment = new Agenda_prest();

                            break;

                        case R.id.nav_stats_prest:
                            titreBar.setText("Statistiques");
                            selectedFragment = new stats();

                            break;

                        case R.id.nav_param_prest:
                            titreBar.setText("Parametres");

                            break;


                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.Fragment_container_prest, selectedFragment).commit();

                    return true;
                }
            };

}
