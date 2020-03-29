package com.example.pfeapp.prest_ui;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pfeapp.R;

public class Task_holder_prest extends RecyclerView.ViewHolder {

    TextView description;
    TextView time;


    public Task_holder_prest(@NonNull View itemView) {
        super(itemView);
        this.description=itemView.findViewById(R.id.eventDescription);
        this.time=itemView.findViewById(R.id.taskTime);
    }
}
