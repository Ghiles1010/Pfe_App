package com.example.pfeapp.client_ui;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pfeapp.R;

public class Chat_holder extends RecyclerView.ViewHolder {


    TextView text;


    public Chat_holder(@NonNull View itemView) {
        super(itemView);
        this.text=itemView.findViewById(R.id.message_user);

    }



}

