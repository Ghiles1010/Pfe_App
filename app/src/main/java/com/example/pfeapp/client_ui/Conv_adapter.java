package com.example.pfeapp.client_ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pfeapp.R;
import com.example.pfeapp.prest_ui.Messagerie_prest;

import java.util.ArrayList;

public class Conv_adapter extends RecyclerView.Adapter<Conv_Holder> {

    Messagerie_prest cp;
    Messagerie c;
    private OnConvListener onConvListener;

    ArrayList<Conv_card> cards;

    public Conv_adapter(Messagerie c, ArrayList<Conv_card> cards,OnConvListener onConvListener) {
        this.c = c;
        this.cards = cards;
        this.onConvListener=onConvListener;
    }


    public Conv_adapter(Messagerie_prest cp, ArrayList<Conv_card> cards, OnConvListener onConvListener) {
        this.cp = cp;
        this.cards = cards;
        this.onConvListener=onConvListener;
    }

    @NonNull
    @Override
    public Conv_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.conversation,null);

        return new Conv_Holder(view,onConvListener);
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
