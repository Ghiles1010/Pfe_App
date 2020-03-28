package com.example.pfeapp.prest_ui;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pfeapp.R;

public class info_prest_holder extends RecyclerView.ViewHolder {


    TextView section;
    TextView description;


    public info_prest_holder(@NonNull View itemView) {
        super(itemView);

        this.description = itemView.findViewById(R.id.description);
        this.section = itemView.findViewById(R.id.nom_section);

    }
}
