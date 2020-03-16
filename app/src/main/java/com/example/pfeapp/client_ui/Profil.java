package com.example.pfeapp.client_ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pfeapp.R;

import java.util.ArrayList;

public class Profil extends Fragment {

    Comment_adapter adapter_comment;
    RecyclerView recview_comment;

    Images_Couv_profil_adapter adapter_images;
    RecyclerView recview_images;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.profile_prest, container, false);

        recview_comment=view.findViewById(R.id.Comment_sec_prest);
        adapter_comment=new Comment_adapter(this,getList());
        recview_comment.setAdapter(adapter_comment);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recview_comment.setLayoutManager(layoutManager);



        recview_images=view.findViewById(R.id.recImagesServices);
        adapter_images=new Images_Couv_profil_adapter( this,getListImages());
        recview_images.setAdapter(adapter_images);
        RecyclerView.LayoutManager layoutManagerImages = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        recview_images.setLayoutManager(layoutManagerImages);


        return view;
    }



    private ArrayList<Comment_card> getList(){
        ArrayList<Comment_card> cards = new ArrayList<>();

        Comment_card c= new Comment_card();
        c.setTitre("Trés bon service!");
        c.setText("du bla blabllbalba aded ohs  sdfih idhf si fw fsoiduh dsv  csyf skygc sdfh sug csagnxsd ygcniss syg csiuc bsd ciu sdic siucsuc sidys ciydsfn  h.");
        c.setImage(R.drawable.body);
        cards.add(c);

        c= new Comment_card();
        c.setTitre("Trés bon service!");
        c.setText("du bla blabllbalba aded ohs  sdfih idhf si fw fsoiduh dsv  csyf skygc sdfh sug csagnxsd ygcniss syg csiuc bsd ciu sdic siucsuc sidys ciydsfn  h.");
        c.setImage(R.drawable.body);
        cards.add(c);

        c= new Comment_card();
        c.setTitre("Trés bon service!");
        c.setText("du bla blabllbalba aded ohs  sdfih idhf si fw fsoiduh dsv  csyf skygc sdfh sug csagnxsd ygcniss syg csiuc bsd ciu sdic siucsuc sidys ciydsfn  h.");
        c.setImage(R.drawable.body);
        cards.add(c);

        c= new Comment_card();
        c.setTitre("Trés bon service!");
        c.setText("du bla blabllbalba aded ohs  sdfih idhf si fw fsoiduh dsv  csyf skygc sdfh sug csagnxsd ygcniss syg csiuc bsd ciu sdic siucsuc sidys ciydsfn  h.");
        c.setImage(R.drawable.body);
        cards.add(c);

        c= new Comment_card();
        c.setTitre("Trés bon service!");
        c.setText("du bla blabllbalba aded ohs  sdfih idhf si fw fsoiduh dsv  csyf skygc sdfh sug csagnxsd ygcniss syg csiuc bsd ciu sdic siucsuc sidys ciydsfn  h.");
        c.setImage(R.drawable.body);
        cards.add(c);

        c= new Comment_card();
        c.setTitre("Trés bon service!");
        c.setText("du bla blabllbalba aded ohs  sdfih idhf si fw fsoiduh dsv  csyf skygc sdfh sug csagnxsd ygcniss syg csiuc bsd ciu sdic siucsuc sidys ciydsfn  h.");
        c.setImage(R.drawable.body);
        cards.add(c);


        c= new Comment_card();
        c.setTitre("Trés bon service!");
        c.setText("du bla blabllbalba aded ohs  sdfih idhf si fw fsoiduh dsv  csyf skygc sdfh sug csagnxsd ygcniss syg csiuc bsd ciu sdic siucsuc sidys ciydsfn  h.");
        c.setImage(R.drawable.body);
        cards.add(c);


        c= new Comment_card();
        c.setTitre("Trés bon service!");
        c.setText("du bla blabllbalba aded ohs  sdfih idhf si fw fsoiduh dsv  csyf skygc sdfh sug csagnxsd ygcniss syg csiuc bsd ciu sdic siucsuc sidys ciydsfn  h.");
        c.setImage(R.drawable.body);
        cards.add(c);

        c= new Comment_card();
        c.setTitre("Trés bon service!");
        c.setText("du bla blabllbalba aded ohs  sdfih idhf si fw fsoiduh dsv  csyf skygc sdfh sug csagnxsd ygcniss syg csiuc bsd ciu sdic siucsuc sidys ciydsfn  h.");
        c.setImage(R.drawable.body);
        cards.add(c);


        return cards;
    }





    private ArrayList<Images_Couv_profil_card> getListImages(){
        ArrayList<Images_Couv_profil_card> cards = new ArrayList<>();

        Images_Couv_profil_card c= new Images_Couv_profil_card();
        c.setImage(R.drawable.rest);
        cards.add(c);

        c= new Images_Couv_profil_card();
        c.setImage(R.drawable.rest);
        cards.add(c);

        c= new Images_Couv_profil_card();
        c.setImage(R.drawable.rest);
        cards.add(c);

        c= new Images_Couv_profil_card();
        c.setImage(R.drawable.rest);
        cards.add(c);

        c= new Images_Couv_profil_card();
        c.setImage(R.drawable.rest);
        cards.add(c);

        c= new Images_Couv_profil_card();
        c.setImage(R.drawable.rest);
        cards.add(c);

        c= new Images_Couv_profil_card();
        c.setImage(R.drawable.rest);
        cards.add(c);
        return cards;
    }



}
