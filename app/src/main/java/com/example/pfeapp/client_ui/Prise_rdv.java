package com.example.pfeapp.client_ui;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pfeapp.BD.Data_Base;
import com.example.pfeapp.BD.RDV;
import com.example.pfeapp.BD.Service;
import com.example.pfeapp.R;
import com.example.pfeapp.prest_ui.OnCardListener;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Prise_rdv extends AppCompatActivity implements OnCardListener {


    RecyclerView recview;
    Rdv_adapter adapter;
    ArrayList<RDV> cards;
    Dialog rdv;
    Data_Base db = new Data_Base(this);
    Service service;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.agenda);

        Intent i = getIntent();

        service = (Service) i.getSerializableExtra("service");


        recview = findViewById(R.id.RecViewTasks);
        cards = getList();
        adapter = new Rdv_adapter(this, cards, this);
        recview.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recview.setLayoutManager(layoutManager);

        rdv = new Dialog(this);
        rdv.setContentView(R.layout.prise_rdv_pop);
    }

    ArrayList<RDV> getList() {
        ArrayList<RDV> cards = new ArrayList<>();

        RDV c;

        String matin = " AM";
        String soir = " PM";


        String i_str, min_str;
        for (int i = 6; i < 13; i++) {


            i_str = Integer.toString(i);

            for (int min = 0; min < 60; min = min + 15) {

                String hour = i_str;

                min_str = Integer.toString(min);


                hour = hour + ":" + min_str + matin;


                c = new RDV();
                c.setTime(hour);
                c.setDescription("");
                cards.add(c);

            }


        }

        for (int i = 1; i < 12; i++) {


            i_str = Integer.toString(i);
            for (int min = 0; min < 60; min = min + 15) {

                String hour = i_str;

                min_str = Integer.toString(min);

                hour = hour + ":" + min_str + soir;


                c = new RDV();
                c.setTime(hour);
                c.setDescription("");
                cards.add(c);

            }


        }


        return cards;
    }

    @Override
    public void onCardClick(final int position) {

        final Background b = new Background(this);
        MaterialButton cancel, confirm;
        final EditText Description = rdv.findViewById(R.id.description);

        cancel = rdv.findViewById(R.id.cancel);
        confirm = rdv.findViewById(R.id.confirme_rdv);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rdv.dismiss();
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String res = "";
                try {
                    res = b.execute("ask_rdv", Description.getText().toString(),
                            cards.get(position).getTime(), db.getClient().get(0).getId_client(),
                            service.getIDservice()).get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (res.equals("rdv_asked")) {
                    cards.get(position).setAccepted(RDV.ASKED);
                    adapter.notifyDataSetChanged();
                }

                rdv.dismiss();
            }

        });

        rdv.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        rdv.show();


    }


    public class Rdv_adapter extends RecyclerView.Adapter<Rdv_holder> {


        Prise_rdv c;
        ArrayList<RDV> cards;
        OnCardListener onCardListener;

        public Rdv_adapter(Prise_rdv c, ArrayList<RDV> cards, OnCardListener onCardListener) {
            this.c = c;
            this.cards = cards;
            this.onCardListener = onCardListener;
        }

        @NonNull
        @Override
        public Rdv_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view;
            switch (cards.get(viewType).getAccepted()) {

                case RDV.ACCEPTED:

                    view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_card, null);
                    break;

                case RDV.ASKED:
                    view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_card, null);

                    break;
                case RDV.AVAILABLE:
                    view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_card, null);
                    break;

                case RDV.NOT_AVAILABLE:
                    view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_card, null);
                    break;

                case RDV.REFUSED:
                    view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_card, null);
                    break;
                default:
                    view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_card, null);

            }


            return new Rdv_holder(view, onCardListener);
        }

        @Override
        public void onBindViewHolder(@NonNull Rdv_holder holder, int position) {
            if (cards.get(position).getAccepted() == RDV.ASKED) {
                holder.cardView.setCardBackgroundColor(getResources().getColor(R.color.Yellow));
                holder.description.setText(cards.get(position).getDescription());
            } else {
                holder.description.setText(cards.get(position).getDescription());
                holder.time.setText(cards.get(position).getTime());
            }
        }

        @Override
        public int getItemCount() {
            return cards.size();
        }


        @Override
        public int getItemViewType(int position) {

            return position;


        }
    }


}