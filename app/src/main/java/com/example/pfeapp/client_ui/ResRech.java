package com.example.pfeapp.client_ui;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pfeapp.BD.Conversation;
import com.example.pfeapp.BD.Data_Base;
import com.example.pfeapp.BD.Service;
import com.example.pfeapp.R;
import com.example.pfeapp.prest_ui.OnCardListener;
import com.google.android.material.button.MaterialButton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

import static com.example.pfeapp.client_ui.Background.ip;


public class ResRech extends Fragment implements OnCardListener {

    private MaterialButton liste;
    private MaterialButton map;
    RecyclerView recview;
    Data_Base db=new Data_Base(getContext());
    Res_resch_adapter adapter;
    String tokenSearch;
    ArrayList<Service> cards= new ArrayList<>();

    public ResRech(String token)  {

        this.tokenSearch=token;
    }
    public ResRech(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view=inflater.inflate(R.layout.resultats_recherche, container, false);
        /////////////////////////////////////////////////////////////////////////////////////////////

        recview=view.findViewById(R.id.res_rech_recview);


       //need to add the progress bar
        adapter=new Res_resch_adapter(this,cards,this  );
        recview.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recview.setLayoutManager(layoutManager);

        /////////////////////////////////////////////////////////////////////////////////////////////
        liste=(MaterialButton) view.findViewById(R.id.liste_Button);
        map=(MaterialButton) view.findViewById(R.id.mapButton);



        liste.setBackgroundColor(getResources().getColor(R.color.Navy));
        liste.setTextColor(getResources().getColor(R.color.White));
        map.setBackgroundColor(getResources().getColor(R.color.White));
        map.setTextColor(getResources().getColor(R.color.Navy));


        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                liste.setBackgroundColor(getResources().getColor(R.color.White));
                liste.setTextColor(getResources().getColor(R.color.Navy));
                map.setBackgroundColor(getResources().getColor(R.color.Navy));
                map.setTextColor(getResources().getColor(R.color.White));
                FragmentTransaction fr= getFragmentManager().beginTransaction();
                fr.replace(R.id.Fragment_container,new Map());
                fr.commit();





            }
        });


        getList();



        return view;
    }




    private void getList(){

        GetServices c =new GetServices();
        c.execute();

    }

    private class GetServices extends AsyncTask<String, Void, String> {

        String result;
        Conversation conv;

        @Override
        protected String doInBackground(String... voids) {


            Background b = new Background();



            result = b.request("get_services_rech", ip);


            if (!result.equals("no_services")) {

                try {

                    Service serv;

                    JSONArray JA = new JSONArray(result);

                    for (int j = 0; j < JA.length(); j++) {
                        JSONObject JO = (JSONObject) JA.get(j);

                        serv = new Service();

                        serv.setIDprestataire(JO.get("prestataire").toString());
                        serv.setIDservice(JO.get("id_service").toString());
                        serv.setAddresse(JO.get("adresse").toString());
                        serv.setAvg_stars(Float.parseFloat(JO.get("avrg_stars").toString()));
                        serv.setDescription(JO.get("description").toString());
                        serv.setDispo(Integer.parseInt(JO.get("disponibilite").toString()));
                        serv.setIDprestataire(JO.get("prestataire").toString());
                        serv.setIDservice(JO.get("id_service").toString());
                        serv.setLatitude(JO.get("latitude").toString());
                        serv.setLongitude(JO.get("longitude").toString());
                        serv.setNb_views(Integer.parseInt(JO.get("nb_reviews").toString()));
                        serv.setNom(JO.get("nom").toString());
                        serv.setVille(JO.get("ville").toString());

                        cards.add(serv);

                    }
                } catch (org.json.JSONException e) {
                    e.printStackTrace();
                }


            }


            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            adapter.notifyDataSetChanged();

        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
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

    @Override
    public void onCardClick(int position) {
        Service service=cards.get(position);

        Intent intent = new Intent(getContext(),Profile.class);
        intent.putExtra("service", (Serializable) service);
        startActivity(intent);
    }
}
