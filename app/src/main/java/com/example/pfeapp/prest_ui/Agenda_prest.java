package com.example.pfeapp.prest_ui;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pfeapp.R;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.Calendar;

public class Agenda_prest extends Fragment implements OnCardListener {


    RecyclerView recview_tasks;
    Task_adapter_prest adapter;

    Dialog rdv;
    Dialog pop_time;
    MaterialButton done;

    final String[] heure = new String[1];




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.agenda_prest, container, false);

        recview_tasks = view.findViewById(R.id.RecViewTasks);

        adapter= new Task_adapter_prest(this,getList(),this  );

        recview_tasks.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recview_tasks.setLayoutManager(layoutManager);

        rdv=new Dialog(getActivity());
        return view;
    }


    private ArrayList<Task_card_prest> getList(){
        ArrayList<Task_card_prest> cards = new ArrayList<>();

        Task_card_prest c;

        String matin=" AM";
        String soir=" PM";


        String i_str, min_str;
        for (int i=6;i<13;i++){


            i_str=Integer.toString(i);

            for(int min=0;min<60;min=min+15){

                String hour=i_str;

                min_str=Integer.toString(min);


                hour=hour+":"+min_str+matin;




                c= new Task_card_prest();
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



                c= new Task_card_prest();
                c.setTime(hour);
                c.setDescription("");
                cards.add(c);

            }




        }


        return cards;
    }


    @Override
    public void onCardClick(int position) {

        final MaterialButton close,time,done; //done button needs to be global because of the ontime set
        EditText titre, desctiption;

        final int a=1;

        rdv.setContentView(R.layout.rdv_popup);

        final MaterialButton donem=(MaterialButton)rdv.findViewById(R.id.done);

        time=(MaterialButton)rdv.findViewById(R.id.timep);



        close=(MaterialButton)rdv.findViewById(R.id.cancel);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rdv.dismiss();
            }
        });

        time.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                final int b=2;
                Calendar calendar=Calendar.getInstance();
                int hour= calendar.get(Calendar.HOUR);
                int minute=calendar.get(Calendar.MINUTE);


                CustomTimePickerDialog timePickerDialog =new CustomTimePickerDialog(getActivity(),
                        android.R.style.Theme_Holo_Light_Dialog_NoActionBar,
                        new TimePickerDialog.OnTimeSetListener(){

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                                String amORpm=" AM";
                                String min=Integer.toString(minute);

                                if (minute==0){
                                    min=min+"0";
                                }

                                if(hourOfDay>12){
                                    hourOfDay=hourOfDay-12;
                                    amORpm=" PM";
                                }

                                String buf=Integer.toString(hourOfDay)+":"+ min +amORpm;
                                time.setText(buf);
                            }

                        } ,hour,minute, false);





                timePickerDialog.show();
                timePickerDialog.getButton(timePickerDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.Navy));
                timePickerDialog.getButton(timePickerDialog.BUTTON_NEGATIVE).setBackgroundColor(Color.WHITE);
                timePickerDialog.getButton(timePickerDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.Navy));
                timePickerDialog.getButton(timePickerDialog.BUTTON_POSITIVE).setBackgroundColor(Color.WHITE);


            }
        } );

        rdv.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        rdv.show();

    }




    public class Task_adapter_prest extends RecyclerView.Adapter<Task_holder_prest>{


        Agenda_prest c;
        ArrayList <Task_card_prest> cards;
        OnCardListener onCardListener;

        public Task_adapter_prest(Agenda_prest c, ArrayList<Task_card_prest> cards,OnCardListener onCardListener) {
            this.c = c;
            this.cards = cards;
            this.onCardListener=onCardListener;
        }

        @NonNull
        @Override
        public Task_holder_prest onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view;


            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.white_task_card, null);



            return new Task_holder_prest(view,onCardListener);
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
