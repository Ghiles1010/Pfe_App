package com.example.pfeapp.prest_ui;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.pfeapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Prestaire_Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prestaire__menu);



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


                            break;

                        case R.id.nav_messagerie_prest:

                            titreBar.setText("Messagerie");
                            selectedFragment = new Messagerie_prest();


                            break;

                        case R.id.nav_agenda_prest:
                            titreBar.setText("Agenda");

                            break;

                        case R.id.nav_stats_prest:
                            titreBar.setText("Statistiques");

                            break;

                        case R.id.nav_param_prest:
                            titreBar.setText("Paramètres");

                            break;


                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.Fragment_container_prest, selectedFragment).commit();

                    return true;
                }
            };
}
