package com.example.pfeapp.prest_ui;

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

import com.example.pfeapp.R;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;

import static com.example.pfeapp.R.layout.prestataire_inscription;

public class Inscription_prest extends AppCompatActivity implements AdapterView.OnItemSelectedListener,OnCardListener {

    String[] categories = {"Santé & Médecine", "Restauration", "Autres"};
    Spinner spinner;
    RecyclerView recview;
    cat_adapter adapter;
    ArrayList<Cat_card> cards;
    Chip chip;
    ChipGroup chipGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(prestataire_inscription);

        cards = new ArrayList<>();

        getList();

        chip=(Chip)findViewById(R.id.chip);
        chipGroup=(ChipGroup)findViewById(R.id.chipGroup);



        spinner = findViewById(R.id.spinner1);
        spinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, categories));
        spinner.setOnItemSelectedListener(this);

        recview = findViewById(R.id.recCAT);
        adapter = new cat_adapter(this, cards,this);
        recview.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recview.setLayoutManager(layoutManager);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        Toast.makeText(this, "Selected Category Does not Exist!", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    void getList() {

        Cat_card c = new Cat_card();
        c.setCategorie("Bla bla");
        cards.add(c);

        c = new Cat_card();
        c.setCategorie("Bewfwe bla");
        cards.add(c);

        c = new Cat_card();
        c.setCategorie("Blfea");
        cards.add(c);

        c = new Cat_card();
        c.setCategorie("Blwefewfla");
        cards.add(c);

        c = new Cat_card();
        c.setCategorie("Blwesssfla");
        cards.add(c);


    }

    @Override
    public void onCardClick(int position) {

        Cat_card c= new Cat_card();
        c=cards.get(position);

        LayoutInflater inflater = LayoutInflater.from(this);

        Chip chip = (Chip)inflater.inflate(R.layout.chip,null,false);
        chip.setText(c.getCategorie());

        chip.setOnCloseIconClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                chipGroup.removeView(v);
            }
        });

        chipGroup.addView(chip);


    }


    public class cat_adapter extends RecyclerView.Adapter<Cat_holder> {

        Inscription_prest c;
        ArrayList<Cat_card> cards;
        OnCardListener onCardListener;


        public cat_adapter(Inscription_prest c, ArrayList<Cat_card> cards,OnCardListener onCardListener) {
            this.c = c;
            this.cards = cards;
            this.onCardListener=onCardListener;
        }


        ///////////////////////////////////////////////////////////////////////////

        @NonNull
        @Override
        public Cat_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cat_card, null);

            return new Cat_holder(view,onCardListener);
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


}
