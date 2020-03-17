package com.example.pfeapp.client_ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pfeapp.R;

public class Comment_holder extends RecyclerView.ViewHolder {

     ImageView image;
     TextView titre;
     TextView text;



    public Comment_holder(@NonNull View itemView) {
        super(itemView);

        this.image=itemView.findViewById(R.id.procomm);
        this.text=itemView.findViewById(R.id.crit);
        this.titre=itemView.findViewById(R.id.titrecrit);

    }
}
