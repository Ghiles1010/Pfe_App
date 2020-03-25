package com.example.pfeapp.prest_ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pfeapp.BD.User;
import com.example.pfeapp.R;
import com.example.pfeapp.client_ui.OnConvListener;

import java.util.ArrayList;

public class Messagerie_prest extends Fragment implements OnConvListener {


    Conv_adapter_prest adapter;
    RecyclerView recview;
    User user;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {




        View view=inflater.inflate(R.layout.fragment_messagerie, container, false);

        recview=view.findViewById(R.id.MesRecView);

        adapter= new Conv_adapter_prest(this,getList(),this);

        recview.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recview.setLayoutManager(layoutManager);




        return view;
    }

    private ArrayList<Conv_card_prest> getList(){
        ArrayList<Conv_card_prest> cards = new ArrayList<>();

        Conv_card_prest c= new Conv_card_prest();
        c.setMessage("Rdv Samedi prochain à 13h !");
        c.setUserName("Plombier Ammar");
        c.setTime("6:21 pm");
        c.setImage(R.drawable.body);
        cards.add(c);

        c= new Conv_card_prest();
        c.setMessage("Rdv Samedi prochain à 13h !");
        c.setUserName("Plombier Ammar");
        c.setTime("6:21 pm");
        c.setImage(R.drawable.body);
        cards.add(c);

        c= new Conv_card_prest();
        c.setMessage("Rdv Samedi prochain à 13h !");
        c.setUserName("Plombier Ammar");
        c.setTime("6:21 pm");
        c.setImage(R.drawable.body);
        cards.add(c);

        c= new Conv_card_prest();
        c.setMessage("Rdv Samedi prochain à 13h !");
        c.setUserName("Plombier Ammar");
        c.setTime("6:21 pm");
        c.setImage(R.drawable.body);
        cards.add(c);

        return cards;
    }

    @Override
    public void onConvClick(int position) {

        Intent i = getActivity().getIntent();
        user = (User) i.getSerializableExtra("User");

        Intent intent =new Intent(getActivity(), Discussion_prest.class);
        intent.putExtra("User",  user);
        startActivity(intent);
    }
}