package com.example.pfeapp.prest_ui;



import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pfeapp.R;

import java.util.ArrayList;

public class Profil_prest extends Fragment {

    Comment_adapter_prest adapter_comment;
    RecyclerView recview_comment;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.profile_prest_prest, container, false);


        recview_comment = view.findViewById(R.id.Comment_sec_prest);
        adapter_comment = new Comment_adapter_prest(this, getList());
        recview_comment.setAdapter(adapter_comment);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recview_comment.setLayoutManager(layoutManager);

        return view;
    }






    private ArrayList<Comment_card_prest> getList(){
        ArrayList<Comment_card_prest> cards = new ArrayList<>();

        Comment_card_prest c= new Comment_card_prest();
        c.setTitre("Trés bon service!");
        c.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin volutpat sit amet leo in viverra. Suspendisse at purus lectus. Aliquam ut sagittis mauris. Cras pharetra sapien eu sem maximus suscipit.");
        c.setImage(R.drawable.person);
        cards.add(c);

        c= new Comment_card_prest();
        c.setTitre("Trés bon service!");
        c.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin volutpat sit amet leo in viverra. Suspendisse at purus lectus. Aliquam ut sagittis mauris. Cras pharetra sapien eu sem maximus suscipit.");
        c.setImage(R.drawable.person);
        cards.add(c);

        c= new Comment_card_prest();
        c.setTitre("Trés bon service!");
        c.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin volutpat sit amet leo in viverra. Suspendisse at purus lectus. Aliquam ut sagittis mauris. Cras pharetra sapien eu sem maximus suscipit.");
        c.setImage(R.drawable.person);
        cards.add(c);

        c= new Comment_card_prest();
        c.setTitre("Trés bon service!");
        c.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin volutpat sit amet leo in viverra. Suspendisse at purus lectus. Aliquam ut sagittis mauris. Cras pharetra sapien eu sem maximus suscipit.");
        c.setImage(R.drawable.person);
        cards.add(c);

        c= new Comment_card_prest();
        c.setTitre("Trés bon service!");
        c.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin volutpat sit amet leo in viverra. Suspendisse at purus lectus. Aliquam ut sagittis mauris. Cras pharetra sapien eu sem maximus suscipit.");
        c.setImage(R.drawable.person);
        cards.add(c);

        c= new Comment_card_prest();
        c.setTitre("Trés bon service!");
        c.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin volutpat sit amet leo in viverra. Suspendisse at purus lectus. Aliquam ut sagittis mauris. Cras pharetra sapien eu sem maximus suscipit.");
        c.setImage(R.drawable.person);
        cards.add(c);

        c= new Comment_card_prest();
        c.setTitre("Trés bon service!");
        c.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin volutpat sit amet leo in viverra. Suspendisse at purus lectus. Aliquam ut sagittis mauris. Cras pharetra sapien eu sem maximus suscipit.");
        c.setImage(R.drawable.person);
        cards.add(c);





        return cards;
    }









}
