package com.example.pfeapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class Messagerie extends Fragment {


    Conv_adapter adapter;
    RecyclerView recview;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                               Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_messagerie, container, false);

        recview=view.findViewById(R.id.MesRecView);

        adapter=new Conv_adapter(this,getList());

        recview.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recview.setLayoutManager(layoutManager);




        return view;
    }

    private ArrayList<Conv_card> getList(){
         ArrayList<Conv_card> cards = new ArrayList<>();

         Conv_card c= new Conv_card();
         c.setMessage("Rdv Samedi prochain à 13h !");
         c.setUserName("Plombier Ammar");
         c.setTime("6:21 pm");
         c.setImage(R.drawable.body);
         cards.add(c);

        c= new Conv_card();
        c.setMessage("Rdv Samedi prochain à 13h !");
        c.setUserName("Plombier Ammar");
        c.setTime("6:21 pm");
        c.setImage(R.drawable.body);
        cards.add(c);

        c= new Conv_card();
        c.setMessage("Rdv Samedi prochain à 13h !");
        c.setUserName("Plombier Ammar");
        c.setTime("6:21 pm");
        c.setImage(R.drawable.body);
        cards.add(c);

        c= new Conv_card();
        c.setMessage("Rdv Samedi prochain à 13h !");
        c.setUserName("Plombier Ammar");
        c.setTime("6:21 pm");
        c.setImage(R.drawable.body);
        cards.add(c);

        c= new Conv_card();
        c.setMessage("Rdv Samedi prochain à 13h !");
        c.setUserName("Plombier Ammar");
        c.setTime("6:21 pm");
        c.setImage(R.drawable.body);
        cards.add(c);


        c= new Conv_card();
        c.setMessage("Rdv Samedi prochain à 13h !");
        c.setUserName("Plombier Ammar");
        c.setTime("6:21 pm");
        c.setImage(R.drawable.body);
        cards.add(c);


        c= new Conv_card();
        c.setMessage("Rdv Samedi prochain à 13h !");
        c.setUserName("Plombier Ammar");
        c.setTime("6:21 pm");
        c.setImage(R.drawable.body);
        cards.add(c);


        c= new Conv_card();
        c.setMessage("Rdv Samedi prochain à 13h !");
        c.setUserName("Plombier Ammar");
        c.setTime("6:21 pm");
        c.setImage(R.drawable.body);
        cards.add(c);


        c= new Conv_card();
        c.setMessage("Rdv Samedi prochain à 13h !");
        c.setUserName("Plombier Ammar");
        c.setTime("6:21 pm");
        c.setImage(R.drawable.body);
        cards.add(c);

        c= new Conv_card();
        c.setMessage("Rdv Samedi prochain à 13h !");
        c.setUserName("Plombier Ammar");
        c.setTime("6:21 pm");
        c.setImage(R.drawable.body);
        cards.add(c);


        c= new Conv_card();
        c.setMessage("Rdv Samedi prochain à 13h !");
        c.setUserName("Plombier Ammar");
        c.setTime("6:21 pm");
        c.setImage(R.drawable.body);
        cards.add(c);

        c= new Conv_card();
        c.setMessage("Rdv Samedi prochain à 13h !");
        c.setUserName("Plombier Ammar");
        c.setTime("6:21 pm");
        c.setImage(R.drawable.body);
        cards.add(c);

        c= new Conv_card();
        c.setMessage("Rdv Samedi prochain à 13h !");
        c.setUserName("Plombier Ammar");
        c.setTime("6:21 pm");
        c.setImage(R.drawable.body);
        cards.add(c);


        c= new Conv_card();
        c.setMessage("Rdv Samedi prochain à 13h !");
        c.setUserName("Plombier Ammar");
        c.setTime("6:21 pm");
        c.setImage(R.drawable.body);
        cards.add(c);

        c= new Conv_card();
        c.setMessage("Rdv Samedi prochain à 13h !");
        c.setUserName("Plombier Ammar");
        c.setTime("6:21 pm");
        c.setImage(R.drawable.body);
        cards.add(c);

        c= new Conv_card();
        c.setMessage("Rdv Samedi prochain à 13h !");
        c.setUserName("Plombier Ammar");
        c.setTime("6:21 pm");
        c.setImage(R.drawable.body);
        cards.add(c);

        return cards;
    }

}
