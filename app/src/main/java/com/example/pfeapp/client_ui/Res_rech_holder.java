package com.example.pfeapp.client_ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pfeapp.R;


public class Res_rech_holder extends RecyclerView.ViewHolder {


    ImageView image_rech;
    TextView titre, description;



    public Res_rech_holder(@NonNull View itemView) {
        super(itemView);

        this.image_rech=itemView.findViewById(R.id.imageRestrech);
        this.titre=itemView.findViewById(R.id.tireresrech);
        this.description=itemView.findViewById(R.id.decrresrech);
    }
}
