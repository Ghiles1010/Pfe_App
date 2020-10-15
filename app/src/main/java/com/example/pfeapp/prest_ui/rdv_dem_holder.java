package com.example.pfeapp.prest_ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pfeapp.R;

public class rdv_dem_holder extends RecyclerView.ViewHolder {

    TextView name;
    TextView description;
    ImageView image;

    public rdv_dem_holder(@NonNull View itemView) {
        super(itemView);

        this.image=itemView.findViewById(R.id.profil_pic);
        this.name=itemView.findViewById(R.id.nom);
        this.description=itemView.findViewById(R.id.descr);
    }
}
