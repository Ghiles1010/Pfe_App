package com.example.pfeapp.client_ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pfeapp.R;


public class Recom_holder extends RecyclerView.ViewHolder {

    ImageView image;
    TextView text;




    public Recom_holder(@NonNull View itemView) {
        super(itemView);

        this.image=itemView.findViewById(R.id.imageRest);
        this.text=itemView.findViewById(R.id.titre_rec);
    }
}
