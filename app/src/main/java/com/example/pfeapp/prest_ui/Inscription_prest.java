package com.example.pfeapp.prest_ui;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pfeapp.BD.Category;
import com.example.pfeapp.BD.Service;
import com.example.pfeapp.BD.Data_Base;
import com.example.pfeapp.BD.Prestataire;
import com.example.pfeapp.R;
import com.example.pfeapp.client_ui.Background;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;

import static com.example.pfeapp.R.layout.prestataire_inscription;
import static com.example.pfeapp.client_ui.Background.ip;

public class Inscription_prest extends AppCompatActivity implements AdapterView.OnItemSelectedListener, OnCardListener {

    String[] categories = {"Santé & Médecine", "Restauration", "Autres"};
    Spinner spinner;
    RecyclerView recview;
    cat_adapter adapter;
    ArrayList<Category> cards;
    Chip chip;
    ArrayList<Integer> selected_cat_ids=new ArrayList<>();
    ChipGroup chipGroup;
    MaterialButton terminer;


    EditText nomService,GPS;

    Service service;

    Data_Base db;

    Prestataire prest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(prestataire_inscription);

        db=new Data_Base(this);


        service = new Service();
        prest = new Prestataire();
        cards = new ArrayList<>();
        terminer = findViewById(R.id.terminer);
        getList();

        nomService=findViewById(R.id.name_service);
        GPS=findViewById(R.id.Gps);

        chip = (Chip) findViewById(R.id.chip);
        chipGroup = (ChipGroup) findViewById(R.id.chipGroup);


        spinner = findViewById(R.id.spinner1);
        spinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, categories));
        spinner.setOnItemSelectedListener(this);

        recview = findViewById(R.id.recCAT);
        adapter = new cat_adapter(this, cards, this);
        recview.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recview.setLayoutManager(layoutManager);


        terminer.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Register_GoMenu();
            }
        });

    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        Toast.makeText(this, "Selected Category Does not Exist!", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    void getList() {
        cards=db.getCategory();
    }

    int id_chip(String s){

        for(int i=0;i<cards.size();i++){

            if(cards.get(i).getCategorie().equals(s)){
                return cards.get(i).getID();
            }


        }
        return 0;
    }

    @Override
    public void onCardClick(int position) {

        Category c;

        c = cards.get(position);

        selected_cat_ids.add(c.getID());

        LayoutInflater inflater = LayoutInflater.from(this);

        final Chip chip = (Chip) inflater.inflate(R.layout.chip, null, false);

        chip.setText(c.getCategorie());

        chip.setOnCloseIconClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                int d=id_chip(chip.getText().toString());
                selected_cat_ids.remove(d);
                chipGroup.removeView(v);

            }
        });

        chipGroup.addView(chip);


    }


    public class cat_adapter extends RecyclerView.Adapter<Cat_holder> {

        Inscription_prest c;
        ArrayList<Category> cards;
        OnCardListener onCardListener;


        public cat_adapter(Inscription_prest c, ArrayList<Category> cards, OnCardListener onCardListener) {
            this.c = c;
            this.cards = cards;
            this.onCardListener = onCardListener;
        }


        ///////////////////////////////////////////////////////////////////////////

        @NonNull
        @Override
        public Cat_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cat_card, null);

            return new Cat_holder(view, onCardListener);
        }

        @Override
        public void onBindViewHolder(@NonNull Cat_holder holder, int position) {

            holder.categorie.setText(cards.get(position).getCategorie());


        }

        @Override
        public int getItemCount() {
            return cards.size();
        }

        @Override
        public int getItemViewType(int position) {


            return position;
        }


    }


    void Register_GoMenu() {
        service.setNom(nomService.getText().toString());
        service.setLongitude(GPS.getText().toString());



        connect connect = new connect(this);
        connect.execute();

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

            String r1="";
            String  r2="";

            Prestataire prest;
            prest=db.getPrest().get(0);

            String ids=arrayInt_to_String(selected_cat_ids); //convert ids of categories to strings

            Background b = new Background();
            r1 = b.request("insert_service", ip, "id_prest",prest.getId_prestataire(),"nom",service.getNom(),"longitude",service.getLongitude());
            //r1 returns service ID

            String buf;
            buf=r1.substring(0,3);

            if (buf.equals("ID=")) {

                String id_service = r1.substring(3);
                r2 = b.request("affect_category", ip, "id_service",id_service,"cat_ids",ids);

            }







            result=r1+"#"+r2;
            return result;
        }

        @Override
        protected void onPostExecute(String result) {

            String [] r1_r2=result.split("#");
            String id = r1_r2[0];
            id = id.substring(0, 3);

            if (id.equals("ID=")) {

                id = result.substring(3);

                    db.insertService(id,service.getNom(),service.getLongitude(),"");
                    db.affect_category(id,selected_cat_ids);

                Intent intent = new Intent(context, Prestaire_Menu.class);
                startActivity(intent);


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




        String arrayInt_to_String(ArrayList<Integer> S){

            String ids=""; //convert ids of categories to strings

            for(Integer j:S){
                ids=ids+" "+j.toString();
            }
            return ids;
        }
    }

}
