package com.example.pfeapp.client_ui;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pfeapp.R;

public class Images_Couv_profil_holder extends RecyclerView.ViewHolder {

    ImageView image;


    public Images_Couv_profil_holder(@NonNull View itemView) {
        super(itemView);

        this.image=itemView.findViewById(R.id.imageRestprofil);
    }
}
