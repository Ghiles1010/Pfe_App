package com.example.pfeapp.prest_ui;



import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pfeapp.R;

import java.util.ArrayList;

public class Profil_prest extends Fragment {

    Comment_adapter_prest adapter_comment;
    RecyclerView recview_comment;

    Images_ProfilPrest_adapter adapter_images;
    RecyclerView recview_images;

    info_prest_adapter adapter_section;
    RecyclerView recview_section;

    Button add_section;

    ArrayList<Info_prest_card> cards_section;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View view=inflater.inflate(R.layout.profile_prest_prest, container, false);

        cards_section = new ArrayList<>();
        getListSections(cards_section);



        recview_comment=view.findViewById(R.id.Comment_sec_prest);
        adapter_comment=new Comment_adapter_prest(this,getList());
        recview_comment.setAdapter(adapter_comment);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recview_comment.setLayoutManager(layoutManager);


        recview_section=view.findViewById(R.id.SuppInfosRecView);
        adapter_section=new info_prest_adapter( this,cards_section);
        recview_section.setAdapter(adapter_section);
        RecyclerView.LayoutManager layoutManagerSection = new LinearLayoutManager(getActivity()){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recview_section.setLayoutManager(layoutManagerSection);



        recview_images=view.findViewById(R.id.recImagesServices);
        adapter_images=new Images_ProfilPrest_adapter( this,getListImages());
        recview_images.setAdapter(adapter_images);
        RecyclerView.LayoutManager layoutManagerImages = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        recview_images.setLayoutManager(layoutManagerImages);

        add_section=(Button)view.findViewById(R.id.add_section_button);


        add_section.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Info_prest_card c= new Info_prest_card();
                c.setNom_section("Spécialités cuisine");
                c.setDescription("Alors dddddd \nDu blabla \ndu blabla \net bien sur du blabla");
                cards_section.add(c);

                adapter_section.notifyDataSetChanged();
                recview_section.smoothScrollToPosition(cards_section.size());




            }
        });




        return view;
    }


    private void getListSections(ArrayList<Info_prest_card> d){

        Info_prest_card c= new Info_prest_card();
        c.setNom_section("Spécialités cuisine");
        c.setDescription("Alors on adu blabla \nDu blabla \ndu blabla \net bien sur du blabla");
        d.add(c);

        c= new Info_prest_card();
        c.setNom_section("Spécialités cuisine");
        c.setDescription("Alors on adu blabla \nDu blabla \ndu blabla \net bien sur du blabla");
        d.add(c);

        c= new Info_prest_card();
        c.setNom_section("Spécialités cuisine");
        c.setDescription("Alors on adu blabla \nDu blabla \ndu blabla \net bien sur du blabla");
        d.add(c);

        c= new Info_prest_card();
        c.setNom_section("Spécialités cuisine");
        c.setDescription("Alors on adu blabla \nDu blabla \ndu blabla \net bien sur du blabla");
        d.add(c);

        c= new Info_prest_card();
        c.setNom_section("Spécialités cuisine");
        c.setDescription("Alors on adu blabla \nDu blabla \ndu blabla \net bien sur du blabla");
        d.add(c);

        c= new Info_prest_card();
        c.setNom_section("Spécialités cuisine");
        c.setDescription("Alors on adu blabla \nDu blabla \ndu blabla \net bien sur du blabla");
        d.add(c);


    }



    private ArrayList<Comment_card_prest> getList(){
        ArrayList<Comment_card_prest> cards = new ArrayList<>();

        Comment_card_prest c= new Comment_card_prest();
        c.setTitre("Trés bon service!");
        c.setText("du bla blabllbalba aded ohs  sdfih idhf si fw fsoiduh dsv  csyf skygc sdfh sug csagnxsd ygcniss syg csiuc bsd ciu sdic siucsuc sidys ciydsfn  h.");
        c.setImage(R.drawable.body);
        cards.add(c);

        c= new Comment_card_prest();
        c.setTitre("Trés bon service!");
        c.setText("du bla blabllbalba aded ohs  sdfih idhf si fw fsoiduh dsv  csyf skygc sdfh sug csagnxsd ygcniss syg csiuc bsd ciu sdic siucsuc sidys ciydsfn  h.");
        c.setImage(R.drawable.body);
        cards.add(c);

        c= new Comment_card_prest();
        c.setTitre("Trés bon service!");
        c.setText("du bla blabllbalba aded ohs  sdfih idhf si fw fsoiduh dsv  csyf skygc sdfh sug csagnxsd ygcniss syg csiuc bsd ciu sdic siucsuc sidys ciydsfn  h.");
        c.setImage(R.drawable.body);
        cards.add(c);

        c= new Comment_card_prest();
        c.setTitre("Trés bon service!");
        c.setText("du bla blabllbalba aded ohs  sdfih idhf si fw fsoiduh dsv  csyf skygc sdfh sug csagnxsd ygcniss syg csiuc bsd ciu sdic siucsuc sidys ciydsfn  h.");
        c.setImage(R.drawable.body);
        cards.add(c);

        c= new Comment_card_prest();
        c.setTitre("Trés bon service!");
        c.setText("du bla blabllbalba aded ohs  sdfih idhf si fw fsoiduh dsv  csyf skygc sdfh sug csagnxsd ygcniss syg csiuc bsd ciu sdic siucsuc sidys ciydsfn  h.");
        c.setImage(R.drawable.body);
        cards.add(c);




        return cards;
    }





    private ArrayList<Images_ProfilPrest_card> getListImages(){
        ArrayList<Images_ProfilPrest_card> cards = new ArrayList<>();

        Images_ProfilPrest_card c= new Images_ProfilPrest_card();
        c.setImage(R.drawable.rest);
        cards.add(c);

        c= new Images_ProfilPrest_card();
        c.setImage(R.drawable.rest);
        cards.add(c);


        c= new Images_ProfilPrest_card();
        c.setImage(R.drawable.rest);
        cards.add(c);

        c= new Images_ProfilPrest_card();
        c.setImage(R.drawable.rest);
        cards.add(c);

        c= new Images_ProfilPrest_card();
        c.setImage(R.drawable.rest);
        cards.add(c);

        return cards;
    }


    public class info_prest_adapter extends RecyclerView.Adapter<info_prest_holder> {

        Profil_prest c;
        ArrayList<Info_prest_card> cards;

        public info_prest_adapter(Profil_prest c, ArrayList<Info_prest_card> cards) {
            this.c = c;
            this.cards = cards;
        }
///////////////////////////////////////////////////////////////////////////

        @NonNull
        @Override
        public info_prest_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.info_prest,null);

            return new info_prest_holder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull info_prest_holder holder, int position) {

            holder.section.setText(cards.get(position).getNom_section());
            holder.description.setText(cards.get(position).getDescription());



        }

        @Override
        public int getItemCount() {
            return cards.size();
        }
    }



}
