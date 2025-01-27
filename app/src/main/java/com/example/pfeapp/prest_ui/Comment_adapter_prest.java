package com.example.pfeapp.prest_ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pfeapp.R;

import java.util.ArrayList;

public class Comment_adapter_prest extends RecyclerView.Adapter<Comment_holder_prest> {

    Profil_prest c;
    ArrayList<Comment_card_prest> cards;


    public Comment_adapter_prest(Profil_prest c, ArrayList<Comment_card_prest> cards) {
        this.c = c;
        this.cards = cards;
    }


    ///////////////////////////////////////////////////////////////////////////

    @NonNull
    @Override
    public Comment_holder_prest onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.commentaire,null);

        return new Comment_holder_prest(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Comment_holder_prest holder, int position) {

        holder.titre.setText(cards.get(position).getTitre());
        holder.text.setText(cards.get(position).getText());
        holder.image.setImageResource(cards.get(position).getImage());


    }

    @Override
    public int getItemCount() {
        return cards.size();
    }
}