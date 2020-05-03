package com.example.pfeapp.client_ui;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pfeapp.R;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class Recherche extends Fragment {

    Recom_adapter adapter;
    RecyclerView recview;
    EditText SearchBar;
    MaterialButton cherche;

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_recherche, container, false);

        recview = view.findViewById(R.id.RecomRecView);

        adapter = new Recom_adapter(this, getList());
        recview.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recview.setLayoutManager(layoutManager);
        SearchBar = (EditText) view.findViewById(R.id.SearchBar);
        cherche = (MaterialButton) view.findViewById(R.id.search);

        cherche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(SearchBar.getText().toString().isEmpty()) {
                    FragmentTransaction fr = getFragmentManager().beginTransaction();
                    fr.replace(R.id.Fragment_container, new ResRech(SearchBar.getText().toString())); //CHANGE THE FRAGMENT TO THE RESULTS OF THE SEARCH
                    fr.commit();
                }


            }
        });







        return view;
    }


    private ArrayList<Recom_card> getList() {
        ArrayList<Recom_card> cards = new ArrayList<>();


        Recom_card c = new Recom_card();
        c.setImage(R.drawable.rest);
        c.setNom("Restaurant Alger");
        cards.add(c);

        c = new Recom_card();
        c.setImage(R.drawable.rest);
        c.setNom("Restaurant Alger");
        cards.add(c);

        c = new Recom_card();
        c.setImage(R.drawable.rest);
        c.setNom("Restaurant Alger");
        cards.add(c);

        c = new Recom_card();
        c.setImage(R.drawable.rest);
        c.setNom("Restaurant Alger");
        cards.add(c);

        return cards;


    }

   public boolean InternetAvailable() {
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
        } else {
            connected = false;
        }

        return connected;
    }
}