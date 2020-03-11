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

public class Conv_card {


    private String UserName,Message,Time;
    private int image;



    ////////////////////////////     getters and setters             /////////////////////////

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }


    public static class Recherche extends Fragment {

        Recom_adapter adapter;
        RecyclerView recview;

        Button cherche;


        @Override
        public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                                 final Bundle savedInstanceState) {


            View view=inflater.inflate(R.layout.fragment_recherche, container, false);

            recview=view.findViewById(R.id.RecomRecView);
            adapter=new Recom_adapter(this,getList());
            recview.setAdapter(adapter);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
            recview.setLayoutManager(layoutManager);

            cherche= (Button) view.findViewById(R.id.ajouterFiltre);

            cherche.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentTransaction fr= getFragmentManager().beginTransaction();
                    fr.replace(R.id.Fragment_container,new ResRech());
                    fr.commit();
                }
            });





            return view;
        }





        private ArrayList<Recom_card> getList() {
            ArrayList<Recom_card> cards = new ArrayList<>();


            Recom_card c= new Recom_card();
            c.setImage(R.drawable.rest);
            c.setNom("Restaurant Alger");
            cards.add(c);

            c= new Recom_card();
            c.setImage(R.drawable.rest);
            c.setNom("Restaurant Alger");
            cards.add(c);

            c= new Recom_card();
            c.setImage(R.drawable.rest);
            c.setNom("Restaurant Alger");
            cards.add(c);

            c= new Recom_card();
            c.setImage(R.drawable.rest);
            c.setNom("Restaurant Alger");
            cards.add(c);

            return cards;


        }




    }
}
