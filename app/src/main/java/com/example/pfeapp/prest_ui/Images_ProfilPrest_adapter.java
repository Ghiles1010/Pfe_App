package com.example.pfeapp.prest_ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pfeapp.R;

import java.util.ArrayList;

public class Images_ProfilPrest_adapter  extends RecyclerView.Adapter<Images_ProfilPrest_holder > {

        Profil_prest c;
        ArrayList<Images_ProfilPrest_card> cards;


public Images_ProfilPrest_adapter(Profil_prest c, ArrayList<Images_ProfilPrest_card> cards) {
        this.c = c;
        this.cards = cards;
        }

@NonNull
@Override
public Images_ProfilPrest_holder  onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.img_profil,null);

        return new Images_ProfilPrest_holder (view);
        }

@Override
public void onBindViewHolder(@NonNull Images_ProfilPrest_holder  holder, int position) {

        holder.image.setImageResource(cards.get(position).getImage());


        }

@Override
public int getItemCount() {
        return cards.size();
        }
        }
