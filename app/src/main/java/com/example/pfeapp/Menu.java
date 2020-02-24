package com.example.pfeapp;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Menu extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_principal);
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

                        case R.id.nav_cherche:


                            titreBar.setText("Recherche");
                            selectedFragment= new Recherche();

                            break;

                        case R.id.nav_messagerie:
                            titreBar.setText("Messagerie");
                            selectedFragment= new Messagerie() ;

                            break;

                        case R.id.nav_param:
                            titreBar.setText("ResRecherhe");
                            selectedFragment= new ResRech() ;


                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.Fragment_container, selectedFragment).commit();

                    return true;
                }
            };

}
