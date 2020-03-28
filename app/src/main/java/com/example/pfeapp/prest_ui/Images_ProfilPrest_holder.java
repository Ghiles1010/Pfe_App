package com.example.pfeapp.prest_ui;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pfeapp.R;

public class Images_ProfilPrest_holder extends RecyclerView.ViewHolder {

    ImageView image;


    public Images_ProfilPrest_holder(@NonNull View itemView) {
        super(itemView);

        this.image=itemView.findViewById(R.id.imageRestprofil);
    }
}
