package com.example.pfeapp.user_ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pfeapp.R;
import com.example.pfeapp.prest_ui.OnCardListener;


public class session_holder extends RecyclerView.ViewHolder implements View.OnClickListener{


    TextView session;
    ImageView image;
    OnCardListener onCardListener;

    public session_holder(@NonNull View itemView, OnCardListener onCardListener) {
        super(itemView);

        this.session=itemView.findViewById(R.id.nom_session);
        this.onCardListener=onCardListener;
        this.image=itemView.findViewById(R.id.image_pro);

        itemView.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        onCardListener.onCardClick(getAdapterPosition());
    }
}
