package com.example.pfeapp.prest_ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pfeapp.R;

public class Agenda_prest extends Fragment {


    RecyclerView recview;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {




        View view=inflater.inflate(R.layout.agenda, container, false);





        ListView listView = view.findViewById(R.id.listView);
        listView.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,
                new String[] {"Réunion", "réunion", "réunion", "réunion", "réunion", "réunion"}));



        return view;
    }

























}
