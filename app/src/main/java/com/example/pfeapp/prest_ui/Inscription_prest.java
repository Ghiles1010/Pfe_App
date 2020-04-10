package com.example.pfeapp.prest_ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pfeapp.BD.Category;

import com.example.pfeapp.BD.Data_Base;
import com.example.pfeapp.BD.Prestataire;
import com.example.pfeapp.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;

import static com.example.pfeapp.R.layout.prestataire_inscription;

public class Inscription_prest extends AppCompatActivity implements AdapterView.OnItemSelectedListener, OnCardListener {

    String[] categories = {"Santé & Médecine", "Restauration", "Autres"};
    Spinner spinner;
    RecyclerView recview;
    cat_adapter adapter;
    ArrayList<Category> cards;
    Chip chip;
    ChipGroup chipGroup;
    MaterialButton terminer;

    Data_Base db;

    Prestataire prest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(prestataire_inscription);

        db=new Data_Base(this);

        prest = new Prestataire();
        cards = new ArrayList<>();
        terminer = findViewById(R.id.terminer);
        getList();

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

    void Register_GoMenu() {



        Intent intent = new Intent(this, Prestaire_Menu.class);
        startActivity(intent);



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
        Category f= new Category(26,"rwogwmv");
        cards.add(f);
    }

    @Override
    public void onCardClick(int position) {

        Category c;

        c = cards.get(position);

        LayoutInflater inflater = LayoutInflater.from(this);

        Chip chip = (Chip) inflater.inflate(R.layout.chip, null, false);

        chip.setText(c.getCategorie());

        chip.setOnCloseIconClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

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


  /*  public class connect extends AsyncTask<String, Void, String> {

        AlertDialog dialog;
        String result = "";
        Context context;

        public connect(Context context) {
            this.context = context;
        }

        @Override
        protected String doInBackground(String... voids) {


            Background b = new Background();
            result = b.request("sign-in", ip, "name", voids[1], "surname", voids[2], "username", voids[3], "email", voids[4], "psw", voids[5]);

            return result;
        }

        @Override
        protected void onPostExecute(String result) {

            String id = result.substring(0, 3);

            if (id.equals("ID=")) {

                id = result.substring(3);


                data_base.insertUser(id, "email", "psw", "surname", "username", "username");

                Intent intent = new Intent(context, Choix_session_inscr.class);
                intent.putExtra("User", user);
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
    }*/


}
