package com.example.pfeapp.client_ui;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pfeapp.R;
import com.example.pfeapp.prest_ui.OnCardListener;
import com.google.android.material.card.MaterialCardView;

public class Rdv_holder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView description;
    TextView time;
    OnCardListener onCardListener;
    MaterialCardView cardView;

    public Rdv_holder(@NonNull View itemView, OnCardListener onCardListener) {
        super(itemView);
        this.cardView=itemView.findViewById(R.id.dd);
        this.description=itemView.findViewById(R.id.eventDescription);
        this.time=itemView.findViewById(R.id.taskTime);
        this.onCardListener=onCardListener;

        itemView.setOnClickListener(this);



    }




    @Override
    public void onClick(View v) {
        onCardListener.onCardClick(getAdapterPosition());
    }
}
