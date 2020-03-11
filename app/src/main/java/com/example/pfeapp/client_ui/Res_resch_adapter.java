package com.example.pfeapp.client_ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pfeapp.R;

import java.util.ArrayList;

public class Res_resch_adapter extends RecyclerView.Adapter<Res_rech_holder> {


    ResRech c;

    ArrayList<Res_resch_card> cards;

    public Res_resch_adapter(ResRech c, ArrayList<Res_resch_card> cards) {
        this.c = c;
        this.cards = cards;
    }





    @NonNull
    @Override
    public Res_rech_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rech_card,null);

        return new Res_rech_holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Res_rech_holder holder, int position) {
        holder.titre.setText(cards.get(position).getTitre());
        holder.description.setText(cards.get(position).getDescription());
        holder.image_rech.setImageResource(cards.get(position).getImage());

    }

    @Override
    public int getItemCount() {
        return cards.size();
    }
}
