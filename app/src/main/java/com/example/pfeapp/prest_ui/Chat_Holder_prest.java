package com.example.pfeapp.prest_ui;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pfeapp.R;

public class Chat_Holder_prest extends RecyclerView.ViewHolder {


    TextView text;


    public Chat_Holder_prest(@NonNull View itemView) {
        super(itemView);
        this.text=itemView.findViewById(R.id.message_user);

    }



}

