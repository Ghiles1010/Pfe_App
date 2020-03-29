package com.example.pfeapp.client_ui;

import android.app.AlertDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pfeapp.R;

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
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;


public class ResRech extends Fragment {

    private Button liste;
    private Button map;
    RecyclerView recview;
    Res_resch_adapter adapter;
    String tokenSearch;
    ArrayList<Res_resch_card> cards= new ArrayList<>();

    public ResRech(String token)  {
       tokenSearch=token;
    }
    public ResRech(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view=inflater.inflate(R.layout.resultats_recherche, container, false);
        /////////////////////////////////////////////////////////////////////////////////////////////

        recview=view.findViewById(R.id.res_rech_recview);


       //need to add the progress bar
        adapter=new Res_resch_adapter(this,cards);
        recview.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recview.setLayoutManager(layoutManager);

        /////////////////////////////////////////////////////////////////////////////////////////////
        liste=(Button)view.findViewById(R.id.liste_Button);
        map=(Button)view.findViewById(R.id.mapButton);



        liste.setBackground(getResources().getDrawable(R.drawable.liste_button));
        liste.setTextColor(getResources().getColor(R.color.White));
        map.setBackground(getResources().getDrawable(R.drawable.map_button));
        map.setTextColor(getResources().getColor(R.color.Navy));






        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                liste.setBackground(getResources().getDrawable(R.drawable.map_button_left));
                liste.setTextColor(getResources().getColor(R.color.Navy));
                map.setBackground(getResources().getDrawable(R.drawable.liste_button_left));
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

        SearchBackground sb= new SearchBackground(getContext() );
        sb.execute(tokenSearch);
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





    public class SearchBackground extends AsyncTask<String,Void,String> {
        //declaration de quoi recuperer
        AlertDialog dialog;
        String result = "";
        String type = "search";
        Context context;
        Res_resch_card card;
        String search_url = "http:/192.168.1.8/" + type + ".php";//go to commend prompt to know your local ip adress
        public SearchBackground(Context context) { this.context = context; }





        @Override
        protected String doInBackground(String... voids){
            try {
                String token = voids[0];
                URL url = new URL(search_url);
                HttpURLConnection URLconn = (HttpURLConnection) url.openConnection();
                URLconn.setRequestMethod("POST");//request to write on the server
                URLconn.setDoInput(true);
                URLconn.setDoOutput(true);

                OutputStream ops = URLconn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(ops, "UTF8"));
                String data = URLEncoder.encode("token", "UTF8") + "=" + URLEncoder.encode(token, "UTF8");

                writer.write(data);//write on the buffer
                writer.flush();
                writer.close();//close the buffer

                ops.close();

                InputStream ips = URLconn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(ips, "ISO-8859-1"));


                String line = "";
                while ((line = reader.readLine()) != null)
                {
                    result += line;
                }




                try {
                        JSONArray service = new JSONArray(result);
                        if(service.length() != 0){
                        for (int i=0; i<service.length();i++) {

                            JSONObject serv =(JSONObject) service.get(i);
                            card = new Res_resch_card();
                            card.setTitre(serv.get("nom").toString());
                            card.setDescription(serv.get("description").toString());

                           try{ card.setImage(parseInt(serv.get("picture").toString()));}
                           catch(NumberFormatException e){card.setImage(R.drawable.rest);}
                            cards.add(card);
                        }}
                        else{
                            Toast.makeText(getActivity(), "aucun resultat", Toast.LENGTH_SHORT).show();
                        }


                }catch (org.json.JSONException e){
                        e.printStackTrace();
                }






                    reader.close();
                    ips.close();
                    URLconn.disconnect();



                } catch (MalformedURLException e) {
                    result = e.getMessage();
                } catch (java.io.IOException e) {
                    result = e.getMessage();
                }
                return result;
        }

        @Override
        protected void onPostExecute(String result) {




        }



        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }
}
