package com.example.pfeapp.prest_ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pfeapp.R;

import java.util.ArrayList;

public class Agenda_prest extends Fragment {


    RecyclerView recview_tasks;
    Task_adapter_prest adapter;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.agenda, container, false);

        recview_tasks = view.findViewById(R.id.RecViewTasks);

        adapter= new Task_adapter_prest(this,getList());

        recview_tasks.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recview_tasks.setLayoutManager(layoutManager);


        return view;
    }


    private ArrayList<Task_card_prest> getList(){
        ArrayList<Task_card_prest> cards = new ArrayList<>();

        Task_card_prest c;

        String matin=":00 AM";
        String soir=":00 PM";



        c= new Task_card_prest();
        c.setTime("0:00 AM");
        c.setDescription("");
        cards.add(c);

        for (int i=1;i<13;i++){

            String i_str;
            i_str=Integer.toString(i);

            i_str=i_str+matin;

            c= new Task_card_prest();
            c.setTime(i_str);
            c.setDescription("");
            cards.add(c);
        }

        for (int i=1;i<12;i++){

            String i_str;
            i_str=Integer.toString(i);

            i_str=i_str+soir;

            c= new Task_card_prest();
            c.setTime(i_str);
            c.setDescription("");
            cards.add(c);
        }



        return cards;
    }



    public class Task_adapter_prest extends RecyclerView.Adapter<Task_holder_prest>{


        Agenda_prest c;
        ArrayList <Task_card_prest> cards;

        public Task_adapter_prest(Agenda_prest c, ArrayList<Task_card_prest> cards) {
            this.c = c;
            this.cards = cards;
        }

        @NonNull
        @Override
        public Task_holder_prest onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view;

            if((viewType%2)==0) {

                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_card, null);
            }

            else{
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.white_task_card, null);
            }

            return new Task_holder_prest(view);
        }

        @Override
        public void onBindViewHolder(@NonNull Task_holder_prest holder, int position) {
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
