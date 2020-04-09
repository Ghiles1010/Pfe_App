package com.example.pfeapp.prest_ui;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pfeapp.R;

public class Cat_holder extends RecyclerView.ViewHolder implements View.OnClickListener{


    TextView categorie;
    OnCardListener onCardListener;



    public Cat_holder (@NonNull View itemView,OnCardListener onCardListener) {
        super(itemView);

        this.categorie=itemView.findViewById(R.id.categ);
        this.onCardListener=onCardListener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        onCardListener.onCardClick(getAdapterPosition());
    }
}
