package com.example.pfeapp.client_ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pfeapp.R;

import java.util.ArrayList;

public class Recom_adapter extends RecyclerView.Adapter<Recom_holder>{

    Recherche c;

    ArrayList<Recom_card> cards;

    public Recom_adapter(Recherche c, ArrayList<Recom_card> cards ) {

        this.c=c;
        this.cards=cards;
    }




    @NonNull
    @Override
    public Recom_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recomended_card,null);

        return new Recom_holder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull Recom_holder holder, int position) {

        holder.text.setText(cards.get(position).getNom());
        holder.image.setImageResource(cards.get(position).getImage());

    }

    @Override
    public int getItemCount() {
        return cards.size();
    }
}
