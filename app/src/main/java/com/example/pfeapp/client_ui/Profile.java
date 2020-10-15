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
    Service service;
    MaterialButton message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.profile_prest);

        service= (Service) getIntent().getSerializableExtra("service");

        initRecviews();

        message = findViewById(R.id.message);



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

    void goRdv(){
        Intent intent= new Intent(this, Prise_rdv.class);
        intent.putExtra("service",service);
        startActivity(intent);
    }

    private  void initRecviews(){
        recview_comment=findViewById(R.id.Comment_sec_prest);
        adapter_comment=new Comment_adapter(this,getList());
        recview_comment.setAdapter(adapter_comment);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recview_comment.setLayoutManager(layoutManager);

    }


    private ArrayList<Comment_card> getList(){
        ArrayList<Comment_card> cards = new ArrayList<>();

        Comment_card c= new Comment_card();
        c.setTitre("Trés bon service!");
        c.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin volutpat sit amet leo in viverra. Suspendisse at purus lectus. Aliquam ut sagittis mauris. Cras pharetra sapien eu sem maximus suscipit.");
        c.setImage(R.drawable.body);
        cards.add(c);

        c= new Comment_card();
        c.setTitre("Trés bon service!");
        c.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin volutpat sit amet leo in viverra. Suspendisse at purus lectus. Aliquam ut sagittis mauris. Cras pharetra sapien eu sem maximus suscipit.");
        c.setImage(R.drawable.body);
        cards.add(c);

        c= new Comment_card();
        c.setTitre("Trés bon service!");
        c.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin volutpat sit amet leo in viverra. Suspendisse at purus lectus. Aliquam ut sagittis mauris. Cras pharetra sapien eu sem maximus suscipit.");
        c.setImage(R.drawable.body);
        cards.add(c);

        c= new Comment_card();
        c.setTitre("Trés bon service!");
        c.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin volutpat sit amet leo in viverra. Suspendisse at purus lectus. Aliquam ut sagittis mauris. Cras pharetra sapien eu sem maximus suscipit.");
        c.setImage(R.drawable.body);
        cards.add(c);

        c= new Comment_card();
        c.setTitre("Trés bon service!");
        c.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin volutpat sit amet leo in viverra. Suspendisse at purus lectus. Aliquam ut sagittis mauris. Cras pharetra sapien eu sem maximus suscipit.");
        c.setImage(R.drawable.body);
        cards.add(c);

        c= new Comment_card();
        c.setTitre("Trés bon service!");
        c.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin volutpat sit amet leo in viverra. Suspendisse at purus lectus. Aliquam ut sagittis mauris. Cras pharetra sapien eu sem maximus suscipit.");
        c.setImage(R.drawable.body);
        cards.add(c);


        return cards;
    }



}
