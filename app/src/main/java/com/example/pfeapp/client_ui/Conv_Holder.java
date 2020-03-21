package com.example.pfeapp.client_ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pfeapp.R;

public class Conv_Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

    ImageView mImageView;
    TextView mUserName, mMessage,mTime;
    OnConvListener onConvListener;





    public Conv_Holder(@NonNull View itemView, OnConvListener onConvListener) {
        super(itemView);

        this.mImageView=itemView.findViewById(R.id.ImageUser);
        this.mUserName=itemView.findViewById(R.id.UserName);
        this.mMessage=itemView.findViewById(R.id.Umessage);
        this.mTime=itemView.findViewById(R.id.uTime);

        this.onConvListener=onConvListener;

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        onConvListener.onConvClick(getAdapterPosition());

    }
}
