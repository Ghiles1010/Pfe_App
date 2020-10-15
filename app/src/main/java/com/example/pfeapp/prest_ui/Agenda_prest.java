package com.example.pfeapp.prest_ui;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pfeapp.BD.RDV;
import com.example.pfeapp.R;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class Agenda_prest extends Fragment implements OnCardListener {


    RecyclerView recview_tasks;
    RDV_adapter_prest adapter;

    Dialog rdv;
    Dialog pop_time;
    MaterialButton done;

    ArrayList<RDV> cards;

    final String[] heure = new String[1];




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.agenda_prest, container, false);

        recview_tasks = view.findViewById(R.id.RecViewTasks);


        cards=getList();
        adapter= new RDV_adapter_prest(this,cards,this  );

        recview_tasks.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recview_tasks.setLayoutManager(layoutManager);

        rdv = new Dialog(getActivity());
        return view;
    }


    private ArrayList<RDV> getList(){
        ArrayList<RDV> cards = new ArrayList<>();

        RDV c;

        String matin=" AM";
        String soir=" PM";


        String i_str, min_str;
        for (int i=6;i<13;i++){


            i_str=Integer.toString(i);

            for(int min=0;min<60;min=min+15){

                String hour=i_str;

                min_str=Integer.toString(min);


                hour=hour+":"+min_str+matin;




                c= new RDV();
                c.setTime(hour);
                c.setDescription("");
                cards.add(c);

            }



        }

        for (int i=1;i<12;i++){


            i_str=Integer.toString(i);
            for(int min=0;min<60;min=min+15){

                String hour=i_str;

                min_str=Integer.toString(min);

                hour=hour+":"+min_str+soir;



                c= new RDV();
                c.setTime(hour);
                c.setDescription("");
                cards.add(c);

            }




        }


        return cards;
    }




    @Override
    public void onCardClick(final int position) {

        final MaterialButton close, confirm; //done button needs to be global because of the ontime set
        final EditText text;
        rdv.setContentView(R.layout.prise_rdv_pop);
        rdv.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        rdv.show();


        confirm = rdv.findViewById(R.id.confirme_rdv);
        close = rdv.findViewById(R.id.cancel);
        text = rdv.findViewById(R.id.description);

        close.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rdv.dismiss();
            }
        });
        confirm.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View v){
                String desc = text.getText().toString();
                cards.get(position).setDescription(desc);
                adapter.notifyDataSetChanged();
                rdv.dismiss();
            }
        });


    }




    public class RDV_adapter_prest extends RecyclerView.Adapter<RDV_holder_prest>{


        Agenda_prest c;
        ArrayList <RDV> cards;
        OnCardListener onCardListener;

        public RDV_adapter_prest(Agenda_prest c, ArrayList<RDV> cards,OnCardListener onCardListener) {
            this.c = c;
            this.cards = cards;
            this.onCardListener=onCardListener;
        }

        @NonNull
        @Override
        public RDV_holder_prest onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view;


            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.white_task_card, null);



            return new RDV_holder_prest(view,onCardListener);
        }

        @Override
        public void onBindViewHolder(@NonNull RDV_holder_prest holder, int position) {
            holder.description.setText(cards.get(position).getDescription());
            holder.time.setText(cards.get(position).getTime());
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
