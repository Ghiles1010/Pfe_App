package com.example.pfeapp.user_ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pfeapp.BD.Client;
import com.example.pfeapp.BD.Data_Base;
import com.example.pfeapp.BD.Prestataire;
import com.example.pfeapp.BD.Service;
import com.example.pfeapp.BD.User;
import com.example.pfeapp.R;
import com.example.pfeapp.client_ui.Background;
import com.example.pfeapp.prest_ui.OnCardListener;
import com.example.pfeapp.prest_ui.Prestaire_Menu;
import com.google.android.material.button.MaterialButton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.pfeapp.R.layout.choix_session_connexion;
import static com.example.pfeapp.client_ui.Background.ip;

public class Choix_session_connexion extends AppCompatActivity implements OnCardListener{


    MaterialButton terminer;
    RecyclerView recview;
    session_adapter adapter;
    ArrayList<session_card>cards=new ArrayList<>();
    Data_Base db=new Data_Base(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(choix_session_connexion);

        getListcards();

        terminer=findViewById(R.id.terminer);
        recview=findViewById(R.id.sessions);

        adapter=new session_adapter(this,cards,this);
        recview.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recview.setLayoutManager(layoutManager);




    }

    void getListcards(){


        connect c = new connect(this);
        c.execute();



    }

    @Override
    public void onCardClick(int position) {
        Intent intent =new Intent(this, Prestaire_Menu.class);
        startActivity(intent);
    }


    public class session_adapter extends RecyclerView.Adapter<session_holder>{


        Choix_session_connexion cp;

        OnCardListener onCardListener;

        ArrayList<session_card> cards;

        public session_adapter(Choix_session_connexion cp, ArrayList<session_card> cards, OnCardListener onCardListener) {
            this.cp = cp;
            this.cards = cards;
            this.onCardListener=onCardListener;
        }

        @NonNull
        @Override
        public session_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.session_card,null);

            return new session_holder(view,onCardListener);
        }

        @Override
        public void onBindViewHolder(@NonNull session_holder holder, int position) {

            holder.session.setText(cards.get(position).getNom_session());

        }

        @Override
        public int getItemCount() {
            return cards.size();
        }


    }




    public class connect extends AsyncTask<String, Void, String> {

        AlertDialog dialog;
        String result = "";
        Context context;


        public connect(Context context) {
            this.context = context;
        }

        @Override
        protected String doInBackground(String... voids) {

            Prestataire prestataire = new Prestataire();
            Client client = new Client();

            Background b=new Background();
            User user=db.getUser().get(0);

            String id_client=b.request("get_client",ip,"id_user",user.getId());

            if( ! id_client.equals("no_client") ) {

                result="client&";

                try {


                    JSONArray JA = new JSONArray(id_client);
                    JSONObject JO = (JSONObject) JA.get(0);
                    client.setId_client(JO.get("id_client").toString());
                    client.setId_user(user.getId());


                } catch (org.json.JSONException e) {
                    e.printStackTrace();
                }



                db.insertClient(client.getId_user(),client.getId_client());


            }
            else{
                result="no_client&";
            }




            String id_prest=b.request("get_prest",ip,"id_user",user.getId());

            if( ! id_prest.equals("no_prestataire") ) {

                result=result+"prest";

                try {


                    JSONArray JA = new JSONArray(id_prest);
                    JSONObject JO = (JSONObject) JA.get(0);
                    prestataire.setId_prestataire(JO.get("id_prestataire").toString());
                    prestataire.setId_user(user.getId());


                } catch (org.json.JSONException e) {
                    e.printStackTrace();
                }


                db.insertPrest(prestataire.getId_user(), prestataire.getId_prestataire());

                String services_json=b.request("get_services",ip,"id_prest",prestataire.getId_prestataire());

                try {

                    Service serv;

                    JSONArray JA = new JSONArray(services_json);

                    for (int j = 0; j < JA.length(); j++) {
                        JSONObject JO = (JSONObject) JA.get(j);

                        serv= new Service();

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

                        db.insertService(serv.getIDservice(),serv.getIDprestataire(),serv.getNom(),serv.getLongitude(),serv.getLatitude());

                    }
                } catch (org.json.JSONException e) {
                    e.printStackTrace();
                }

            }
            else{
                result=result+"no_prest";
            }


            return result; //return "client& or prest or client &prestataire to let us know which type of accounts user has"
        }

        @Override
        protected void onPostExecute(String result) {

            String [] types=result.split("&");

            if(types[0].equals("client")){


                session_card sesion=new session_card();

                String u=db.getUser().get(0).getName();

                sesion.setNom_session(u);

                cards.add(sesion);
                adapter.notifyDataSetChanged();

            }

            if(types[1].equals("prest")){

                ArrayList <Service> services =db.get_services(db.getPrest().get(0).getId_prestataire());

                for(Service s: services) {

                    session_card session = new session_card();
                    session.setNom_session(s.getNom());
                    cards.add(session);
                }


                adapter.notifyDataSetChanged();

            }





        }


        @Override
        protected void onPreExecute() {

            dialog = new AlertDialog.Builder(context).create();
            dialog.setTitle("");
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }
}
