package com.example.pfeapp.prest_ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pfeapp.R;
import com.example.pfeapp.client_ui.OnConvListener;

import java.util.ArrayList;

public class Conv_adapter_prest extends RecyclerView.Adapter<Conv_Holder_prest> {

    Messagerie_prest cp;

    private OnConvListener onConvListener;

    ArrayList<Conv_card_prest> cards;

    public Conv_adapter_prest(Messagerie_prest cp, ArrayList<Conv_card_prest> cards, OnConvListener onConvListener) {
        this.cp = cp;
        this.cards = cards;
        this.onConvListener=onConvListener;
    }

    @NonNull
    @Override
    public Conv_Holder_prest onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.conversation,null);

        return new Conv_Holder_prest(view,onConvListener);
    }

    @Override
    public void onBindViewHolder(@NonNull Conv_Holder_prest holder, int position) {
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