package com.example.pfeapp.prest_ui;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pfeapp.R;

public class Task_holder_prest extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView description;
    TextView time;
    OnCardListener onCardListener;


    public Task_holder_prest(@NonNull View itemView, OnCardListener onCardListener) {
        super(itemView);
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
