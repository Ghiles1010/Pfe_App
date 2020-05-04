package com.example.pfeapp.client_ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pfeapp.BD.Conversation;
import com.example.pfeapp.BD.Data_Base;
import com.example.pfeapp.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;
import static com.example.pfeapp.client_ui.Background.ip;
import static com.example.pfeapp.user_ui.Connexion.PREFERENCES;

public class Messagerie extends Fragment implements OnConvListener {


    public final static String CONV_LOADED = "conv_loaded";

    Conv_adapter adapter;
    RecyclerView recview;
    ArrayList<Conversation> cards = new ArrayList<>();
    Data_Base db;
    String id_client;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_messagerie, container, false);

        recview = view.findViewById(R.id.MesRecView);

        db = new Data_Base(getActivity());

        id_client = db.getClient().get(0).getId_client();



        adapter = new Conv_adapter(this, cards, this);
        recview.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recview.setLayoutManager(layoutManager);


        getList();

        return view;
    }

    private void getList() {

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(PREFERENCES, MODE_PRIVATE);
        boolean loaded = sharedPreferences.getBoolean(CONV_LOADED, false);

        if (!loaded) {
            GetConv getConv = new GetConv();
            getConv.execute();
        } else {

            ArrayList<Conversation>c=db.getConversation();

            for(Conversation i:c){

                cards.add(i);
            }

            adapter.notifyDataSetChanged();


        }
    }

    @Override
    public void onConvClick(int position) {

        String id = cards.get(position).getId_service();

        Intent intent = new Intent(getActivity(), Discussion.class);
        intent.putExtra("id_service", id);
        startActivity(intent);
    }


    private class GetConv extends AsyncTask<String, Void, String> {

        String result;
        Conversation conv;

        @Override
        protected String doInBackground(String... voids) {


            Background b = new Background();



            result = b.request("get_conv_client", ip, "id_client", id_client);


            if (!result.equals("no_conversations")) {

                try {


                    JSONArray JA = new JSONArray(result);

                    for (int j = 0; j < JA.length(); j++) {
                        JSONObject JO = (JSONObject) JA.get(j);

                        conv = new Conversation();
                        conv.setId_client(JO.get("client").toString());
                        conv.setId_service(JO.get("service").toString());
                        conv.setLast_message(JO.get("last_message").toString());
                        conv.setTime(JO.get("time").toString());
                        conv.setNom_client(JO.get("nom_client").toString());
                        conv.setNom_service(JO.get("nom_service").toString());


                        db.insertConversation(conv.getId_client(), conv.getId_service()
                                , conv.getTime(), conv.getLast_message(), conv.getNom_client(), conv.getNom_service(),conv.getMessages_loaded(),conv.getConversation_loaded());
                    }

                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences(PREFERENCES, MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean(CONV_LOADED, true);
                    editor.apply();

                } catch (org.json.JSONException e) {
                    e.printStackTrace();
                }


            }


            return result;
        }

        @Override
        protected void onPostExecute(String result) {

            ArrayList<Conversation> c;
            c = db.getConversation();

            for (Conversation i : c) {

                cards.add(i);
            }

            adapter.notifyDataSetChanged();

        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }


}