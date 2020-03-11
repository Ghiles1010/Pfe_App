package com.example.pfeapp.client_ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pfeapp.R;

import java.util.ArrayList;


public class ResRech extends Fragment {

    private Button liste;
    private Button map;
    RecyclerView recview;
    Res_resch_adapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view=inflater.inflate(R.layout.resultats_recherche, container, false);
        /////////////////////////////////////////////////////////////////////////////////////////////

        recview=view.findViewById(R.id.res_rech_recview);

        adapter=new Res_resch_adapter(this,getList());

        recview.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recview.setLayoutManager(layoutManager);








        /////////////////////////////////////////////////////////////////////////////////////////////
        liste=(Button)view.findViewById(R.id.liste_Button);
        map=(Button)view.findViewById(R.id.mapButton);



        liste.setBackground(getResources().getDrawable(R.drawable.liste_button));
        liste.setTextColor(getResources().getColor(R.color.White));
        map.setBackground(getResources().getDrawable(R.drawable.map_button));
        map.setTextColor(getResources().getColor(R.color.Navy));






        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                liste.setBackground(getResources().getDrawable(R.drawable.map_button_left));
                liste.setTextColor(getResources().getColor(R.color.Navy));
                map.setBackground(getResources().getDrawable(R.drawable.liste_button_left));
                map.setTextColor(getResources().getColor(R.color.White));


                FragmentTransaction fr= getFragmentManager().beginTransaction();
                fr.replace(R.id.Fragment_container,new Map());
                fr.commit();





            }
        });





        return view;
    }




    private ArrayList<Res_resch_card> getList(){
        ArrayList<Res_resch_card> cards = new ArrayList<>();

        Res_resch_card c= new Res_resch_card();
        c.setTitre("Restaurant Alger");
        c.setDescription("Plombier Ammawfwef wfewf");
        c.setImage(R.drawable.rest);
        cards.add(c);

        c= new Res_resch_card();
        c.setTitre("Restaurant Alger");
        c.setDescription("Plombier Ammar wf wfewf");
        c.setImage(R.drawable.rest);
        cards.add(c);

        c= new Res_resch_card();
        c.setTitre("Restaurant Alger");
        c.setDescription("Plombier Ammar wefef wfewf");
        c.setImage(R.drawable.rest);
        cards.add(c);

        c= new Res_resch_card();
        c.setTitre("Restaurant Alger");
        c.setDescription("Plombier Ammaf wfewf");
        c.setImage(R.drawable.rest);
        cards.add(c);

        c= new Res_resch_card();
        c.setTitre("Restaurant Alger");
        c.setDescription("Plombier Ammaf wfewf");
        c.setImage(R.drawable.rest);
        cards.add(c);


        c= new Res_resch_card();
        c.setTitre("Restaurant Alger");
        c.setDescription("Plombier Ammaf wfewf");
        c.setImage(R.drawable.rest);
        cards.add(c);


        c= new Res_resch_card();
        c.setTitre("Restaurant Alger");
        c.setDescription("Plombier Ammaf wfewf");
        c.setImage(R.drawable.rest);
        cards.add(c);


        c= new Res_resch_card();
        c.setTitre("Restaurant Alger");
        c.setDescription("Plombier Ammaf wfewf");
        c.setImage(R.drawable.rest);
        cards.add(c);


        c= new Res_resch_card();
        c.setTitre("Restaurant Alger");
        c.setDescription("Plombier Ammaf wfewf");
        c.setImage(R.drawable.rest);
        cards.add(c);


        c= new Res_resch_card();
        c.setTitre("Restaurant Alger");
        c.setDescription("Plombier Ammaf wfewf");
        c.setImage(R.drawable.rest);
        cards.add(c);

        c= new Res_resch_card();
        c.setTitre("Restaurant Alger");
        c.setDescription("Plombier Ammaf wfewf");
        c.setImage(R.drawable.rest);
        cards.add(c);

        c= new Res_resch_card();
        c.setTitre("Restaurant Alger");
        c.setDescription("Plombier Ammaf wfewf");
        c.setImage(R.drawable.rest);
        cards.add(c);


        c= new Res_resch_card();
        c.setTitre("Restaurant Alger");
        c.setDescription("Plombier Ammaf wfewf");
        c.setImage(R.drawable.rest);
        cards.add(c);


        return cards;
    }



}
