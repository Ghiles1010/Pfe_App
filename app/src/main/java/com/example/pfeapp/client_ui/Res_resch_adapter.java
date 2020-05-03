package com.example.pfeapp.client_ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pfeapp.BD.Service;
import com.example.pfeapp.R;
import com.example.pfeapp.prest_ui.OnCardListener;

import java.util.ArrayList;

public class Res_resch_adapter extends RecyclerView.Adapter<Res_rech_holder>  {


    ResRech c;

    ArrayList<Service> cards;
    private OnCardListener onCardListener;

    public Res_resch_adapter(ResRech c, ArrayList<Service>cards,OnCardListener onCardListener) {
        this.c = c;
        this.cards = cards;
        this.onCardListener=onCardListener;
    }


    @NonNull
    @Override
    public Res_rech_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rech_card,null);

        return new Res_rech_holder(view,onCardListener);
    }

    @Override
    public void onBindViewHolder(@NonNull Res_rech_holder holder, int position) {
        holder.titre.setText(cards.get(position).getNom());
        holder.description.setText(cards.get(position).getDescription());
        holder.image_rech.setImageResource(R.drawable.rest);

    }

    @Override
    public int getItemCount() {
      try{
        return cards.size();}
       catch(Exception e)
        {return 0;}
    }


}
