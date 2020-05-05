package com.example.pfeapp.client_ui;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pfeapp.BD.Service;
import com.example.pfeapp.R;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class Profile extends AppCompatActivity {

    Comment_adapter adapter_comment;
    RecyclerView recview_comment;

    Images_Couv_profil_adapter adapter_images;
    RecyclerView recview_images;
    Service service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.profile_prest);

        service= (Service) getIntent().getSerializableExtra("service");

        initRecviews();

        MaterialButton message = findViewById(R.id.message);




        message.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(InternetAvailable()) {
                    goDiscussion();
                }
                else{
                    Toast.makeText(Profile.this, "aucune connexion Internet", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    public boolean InternetAvailable() {
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
        }  else{
            connected = false;}

        return connected;
    }

    void goDiscussion(){
        Intent intent= new Intent(this,Discussion.class);
        intent.putExtra("service",service);
        startActivity(intent);
    }

    private  void initRecviews(){
        recview_comment=findViewById(R.id.Comment_sec_prest);
        adapter_comment=new Comment_adapter(this,getList());
        recview_comment.setAdapter(adapter_comment);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recview_comment.setLayoutManager(layoutManager);



        recview_images=findViewById(R.id.recImagesServices);
        adapter_images=new Images_Couv_profil_adapter( this,getListImages());
        recview_images.setAdapter(adapter_images);
        RecyclerView.LayoutManager layoutManagerImages = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recview_images.setLayoutManager(layoutManagerImages);
    }


    private ArrayList<Comment_card> getList(){
        ArrayList<Comment_card> cards = new ArrayList<>();

        Comment_card c= new Comment_card();
        c.setTitre("Trés bon service!");
        c.setText("du bla blabllbalba aded ohs  sdfih idhf si fw fsoiduh dsv  csyf skygc sdfh sug csagnxsd ygcniss syg csiuc bsd ciu sdic siucsuc sidys ciydsfn  h.");
        c.setImage(R.drawable.body);
        cards.add(c);

        c= new Comment_card();
        c.setTitre("Trés bon service!");
        c.setText("du bla blabllbalba aded ohs  sdfih idhf si fw fsoiduh dsv  csyf skygc sdfh sug csagnxsd ygcniss syg csiuc bsd ciu sdic siucsuc sidys ciydsfn  h.");
        c.setImage(R.drawable.body);
        cards.add(c);

        c= new Comment_card();
        c.setTitre("Trés bon service!");
        c.setText("du bla blabllbalba aded ohs  sdfih idhf si fw fsoiduh dsv  csyf skygc sdfh sug csagnxsd ygcniss syg csiuc bsd ciu sdic siucsuc sidys ciydsfn  h.");
        c.setImage(R.drawable.body);
        cards.add(c);

        c= new Comment_card();
        c.setTitre("Trés bon service!");
        c.setText("du bla blabllbalba aded ohs  sdfih idhf si fw fsoiduh dsv  csyf skygc sdfh sug csagnxsd ygcniss syg csiuc bsd ciu sdic siucsuc sidys ciydsfn  h.");
        c.setImage(R.drawable.body);
        cards.add(c);

        c= new Comment_card();
        c.setTitre("Trés bon service!");
        c.setText("du bla blabllbalba aded ohs  sdfih idhf si fw fsoiduh dsv  csyf skygc sdfh sug csagnxsd ygcniss syg csiuc bsd ciu sdic siucsuc sidys ciydsfn  h.");
        c.setImage(R.drawable.body);
        cards.add(c);

        c= new Comment_card();
        c.setTitre("Trés bon service!");
        c.setText("du bla blabllbalba aded ohs  sdfih idhf si fw fsoiduh dsv  csyf skygc sdfh sug csagnxsd ygcniss syg csiuc bsd ciu sdic siucsuc sidys ciydsfn  h.");
        c.setImage(R.drawable.body);
        cards.add(c);


        c= new Comment_card();
        c.setTitre("Trés bon service!");
        c.setText("du bla blabllbalba aded ohs  sdfih idhf si fw fsoiduh dsv  csyf skygc sdfh sug csagnxsd ygcniss syg csiuc bsd ciu sdic siucsuc sidys ciydsfn  h.");
        c.setImage(R.drawable.body);
        cards.add(c);


        c= new Comment_card();
        c.setTitre("Trés bon service!");
        c.setText("du bla blabllbalba aded ohs  sdfih idhf si fw fsoiduh dsv  csyf skygc sdfh sug csagnxsd ygcniss syg csiuc bsd ciu sdic siucsuc sidys ciydsfn  h.");
        c.setImage(R.drawable.body);
        cards.add(c);

        c= new Comment_card();
        c.setTitre("Trés bon service!");
        c.setText("du bla blabllbalba aded ohs  sdfih idhf si fw fsoiduh dsv  csyf skygc sdfh sug csagnxsd ygcniss syg csiuc bsd ciu sdic siucsuc sidys ciydsfn  h.");
        c.setImage(R.drawable.body);
        cards.add(c);


        return cards;
    }





    private ArrayList<Images_Couv_profil_card> getListImages(){
        ArrayList<Images_Couv_profil_card> cards = new ArrayList<>();

        Images_Couv_profil_card c= new Images_Couv_profil_card();
        c.setImage(R.drawable.rest);
        cards.add(c);

        c= new Images_Couv_profil_card();
        c.setImage(R.drawable.rest);
        cards.add(c);

        c= new Images_Couv_profil_card();
        c.setImage(R.drawable.rest);
        cards.add(c);

        c= new Images_Couv_profil_card();
        c.setImage(R.drawable.rest);
        cards.add(c);

        c= new Images_Couv_profil_card();
        c.setImage(R.drawable.rest);
        cards.add(c);

        c= new Images_Couv_profil_card();
        c.setImage(R.drawable.rest);
        cards.add(c);

        c= new Images_Couv_profil_card();
        c.setImage(R.drawable.rest);
        cards.add(c);
        return cards;
    }



}
