package com.example.pfeapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Conv_adapter extends RecyclerView.Adapter<Conv_Holder> {

    Messagerie c;

    ArrayList<Conv_card> cards;

    public Conv_adapter(Messagerie c, ArrayList<Conv_card> cards) {
        this.c = c;
        this.cards = cards;
    }

    @NonNull
    @Override
    public Conv_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.conversation,null);

        return new Conv_Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Conv_Holder holder, int position) {
        holder.mUserName.setText(cards.get(position).getUserName());
        holder.mMessage.setText(cards.get(position).getMessage());
        holder.mTime.setText(cards.get(position).getTime());
        holder.mImageView.setImageResource(cards.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }
}
