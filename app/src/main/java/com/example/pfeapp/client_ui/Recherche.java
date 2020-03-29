package com.example.pfeapp.client_ui;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pfeapp.R;
import com.example.pfeapp.prest_ui.Temporaire;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import static androidx.core.content.ContextCompat.getSystemService;

public class Recherche extends Fragment {

    Recom_adapter adapter;
    RecyclerView recview;
    EditText SearchBar;
    Button cherche;

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
        cherche = (Button) view.findViewById(R.id.ajouterFiltre);

        cherche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr= getFragmentManager().beginTransaction();
                fr.replace(R.id.Fragment_container,new ResRech()); //CHANGE THE FRAGMENT TO THE RESULTS OF THE SEARCH
                fr.commit();


            }
        });




         SearchBar.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // TODO Auto-generated method stub
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    if (InternetAvailable()) {
                        //do the search here
                        String search = SearchBar.getText().toString();
                        FragmentTransaction fr= getFragmentManager().beginTransaction();
                        fr.replace(R.id.Fragment_container,new ResRech(search)); //CHANGE THE FRAGMENT TO THE RESULTS OF THE SEARCH
                        fr.commit();


                    } else {
                        Toast.makeText(getActivity(), "aucune connexion Internet", Toast.LENGTH_SHORT).show();
                    }

                }
                return false;
            }
        });

     /* ADD IT FOR THE FILTERS  SearchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });*/


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