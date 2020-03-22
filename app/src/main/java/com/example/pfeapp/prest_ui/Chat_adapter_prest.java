package com.example.pfeapp.prest_ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pfeapp.R;

import java.util.ArrayList;

public class Chat_adapter_prest extends RecyclerView.Adapter<Chat_Holder_prest> {

    Discussion_prest c;
    ArrayList<Chat_card> cards;

    public static final int MSG_RIGHT=1;
    public static final int MSG_LEFT=0;


    public Chat_adapter_prest(Discussion_prest c, ArrayList<Chat_card> cards) {
        this.c = c;
        this.cards = cards;
    }


///////////////////////////////////////////////////////////////////////////

    @NonNull
    @Override
    public Chat_Holder_prest onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if(viewType==MSG_RIGHT) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item_right, null);
        }
        else{
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item_left, null);
        }
        return new Chat_Holder_prest(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Chat_Holder_prest holder, int position) {

        holder.text.setText(cards.get(position).getText());

    }

    @Override
    public int getItemCount() {
        return cards.size();
    }


    @Override
    public int getItemViewType(int position) {

        if(position>6){
            return MSG_RIGHT;
        }

        else{
            return  MSG_LEFT;
        }
    }
}