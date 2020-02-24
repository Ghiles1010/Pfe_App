package com.example.pfeapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Conv_Holder extends RecyclerView.ViewHolder {

    ImageView mImageView;
    TextView mUserName, mMessage,mTime;





    public Conv_Holder(@NonNull View itemView) {
        super(itemView);

        this.mImageView=itemView.findViewById(R.id.ImageUser);
        this.mUserName=itemView.findViewById(R.id.UserName);
        this.mMessage=itemView.findViewById(R.id.Umessage);
        this.mTime=itemView.findViewById(R.id.uTime);
    }
}
