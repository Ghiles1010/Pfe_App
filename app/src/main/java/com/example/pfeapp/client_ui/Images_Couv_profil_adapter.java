package com.example.pfeapp.client_ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pfeapp.R;

import java.util.ArrayList;

public class Images_Couv_profil_adapter extends RecyclerView.Adapter<Images_Couv_profil_holder> {

    Profil c;
    ArrayList<Images_Couv_profil_card> cards;


    public Images_Couv_profil_adapter(Profil c, ArrayList<Images_Couv_profil_card> cards) {
        this.c = c;
        this.cards = cards;
    }

    @NonNull
    @Override
    public Images_Couv_profil_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.img_profil,null);

        return new Images_Couv_profil_holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Images_Couv_profil_holder holder, int position) {

        holder.image.setImageResource(cards.get(position).getImage());


    }

    @Override
    public int getItemCount() {
        return cards.size();
    }
}
