package com.example.pfeapp.prest_ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pfeapp.R;
import com.example.pfeapp.client_ui.OnConvListener;

public class Conv_Holder_prest extends RecyclerView.ViewHolder implements View.OnClickListener {

    ImageView mImageView;
    TextView mUserName, mMessage,mTime;
    OnConvListener onConvListener;





    public Conv_Holder_prest(@NonNull View itemView, OnConvListener onConvListener) {
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
