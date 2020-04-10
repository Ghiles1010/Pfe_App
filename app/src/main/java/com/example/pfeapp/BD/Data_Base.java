package com.example.pfeapp.BD;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;


public class Data_Base extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Base_donnees";
    private static final int Version = 1;

    Category c;

    int rec=0;

    public Data_Base(Context context) {

        super(context, DATABASE_NAME, null, Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        String creer_user = "CREATE TABLE user("
                + " id_user VARCHAR(255) PRIMARY KEY NOT NULL,"
                + " mail VARCHAR(50),"
                + " psw VARCHAR(255),"
                + " username VACHAR(40),"
                + " name VARCHAR(20),"
                + " surname VARCHAR(20)"
                + " );";

       String creer_prestataire = "CREATE TABLE prestataire("
                + " id_prestataire VARCHAR(40) PRIMARY KEY NOT NULL,"
                + " id_user VARCHAR(255),"
                + "CONSTRAINT Fk_userP FOREIGN KEY(id_user) REFERENCES user(id_user)"
                + " );";



       String creer_client = "CREATE TABLE client("
                + " id_client VARCHAR(40) PRIMARY KEY NOT NULL,"
                + " id_user VARCHAR(255),"
                + "CONSTRAINT Fk_userC FOREIGN KEY(id_user) REFERENCES user(id_user)"
                + " );";

        String creer_service = "CREATE TABLE service("
                + " id_service VARCHAR(40) PRIMARY KEY NOT NULL,"
                + " id_prestataire VARCHAR(40),"
                + " nom VARCHAR(40),"
                + " description VARCHAR(255),"
                + " disponibilite int(1),"
                + " categorie VARCHAR(255),"
                + " nb_reviews int(255),"
                + " avrg_stars float,"
                + " addresse VARCHAR(255),"
                + " longitude VARCHAR(255),"
                + " latitude VARCHAR(255),"
                + " ville VARCHAR(255),"
                + " CONSTRAINT Fk_prestS FOREIGN KEY (id_prestataire) references prestataire(id_prestataire)"
                + " );";

        String creer_appreciation = "CREATE TABLE appreciation("
                + " id_client VARCHAR(40) ,"
                + " id_service VARCHAR(40) ,"
                + " note INT,"
                + " CONSTRAINT Fk_serviceA FOREIGN KEY (id_service) references service(id_service),"
                + " CONSTRAINT Fk_clientA FOREIGN KEY (id_client) references client(id_client),"
                + " CONSTRAINT PK_appreciation PRIMARY KEY (id_client,id_service)"
                + " );";


        String creer_commentaire = "CREATE TABLE commentaire("
                + "id_comment VARCHAR(40) ,"
                + " id_client VARCHAR(40) ,"
                + " id_service VARCHAR(40) ,"
                + " text VARCHAR(255),"
                + " CONSTRAINT Fk_serviceC FOREIGN KEY (id_service) references service(id_service),"
                + " CONSTRAINT Fk_clientC FOREIGN KEY (id_client) references client(id_client)"
                + " );";


        String creer_rdv = "CREATE TABLE rdv("
                + " id_rdv VARCHAR(40) ,"
                + " id_client VARCHAR(40) ,"
                + " id_service VARCHAR(40),"
                + " time DATETIME,"
                + " lieu VARCHAR (50),"
                + " CONSTRAINT Fk_client FOREIGN KEY (id_client) references client(id_client),"
                + " CONSTRAINT Fk_service FOREIGN KEY (id_service) references prestataire(id_service)"
                + " );";

        String creer_conversation = "CREATE TABLE conversation("
                + " id_conversation VARCHAR(40) PRIMARY KEY NOT NULL,"
                + " id_client VARCHAR(40),"
                + " id_service VARCHAR(40),"
                + " jour DATE DEFAULT CURRENT_DATE,"
                + " CONSTRAINT Fk_client FOREIGN KEY (id_client) references client(id_client),"
                + " CONSTRAINT Fk_service FOREIGN KEY (id_service) references service(id_service)"
                + " );";

        String creer_message = "CREATE TABLE message("
                + " id_conversation VARCHAR(40),"
                + " heure TIMESTAMP DEFAULT CURRENT_TIMESTAMP ,"
                + "texte VARCHAR(255),"
                + " CONSTRAINT Fk_conversation FOREIGN KEY (id_conversation) references conversation(id_conversation),"
                + " CONSTRAINT PK_message PRIMARY KEY (id_conversation,heure)"
                + " );";

        String creer_Category= "CREATE TABLE category("
                + " id_category INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,"
                + " name VARCHAR(255)"
                + " );";

        String cat_service="CREATE TABLE category_service("
                + " id_category INT ,"
                + " id_service VARCHAR(255),"
                + " CONSTRAINT Fk_category FOREIGN KEY (id_category) references category(id_category),"
                + " CONSTRAINT Fk_service FOREIGN KEY (id_service) references service(id_service),"
                + " CONSTRAINT PK_message PRIMARY KEY (id_category,id_service)"
                + " );";


        db.execSQL(creer_user);
        db.execSQL(creer_prestataire);
        db.execSQL(creer_client);
        db.execSQL(creer_service);
        db.execSQL(creer_appreciation);
        db.execSQL(creer_commentaire);
        db.execSQL(creer_rdv);
        db.execSQL(creer_conversation);
        db.execSQL(creer_message);
        db.execSQL(creer_Category);
        db.execSQL(cat_service);



        Log.i("DATABASEppp", "onCreate invoked");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertUser(String ID, String email, String psw, String name, String surname, String username){

        String insert_User="INSERT INTO user (id_user,mail,psw,username,name,surname) VALUES ( '"+ID+"','"+email+"','"+psw+"','"+username+"','"+name+"','"+surname+"');";
        this.getWritableDatabase().execSQL(insert_User);
    }


    public void affect_category(String id_service,ArrayList<Integer>cat_ids){

        for (int i=0;i<cat_ids.size();i++){

            String insert_Service="INSERT INTO cat_service (id_service,id_category) VALUES ( '"+id_service+"','"+cat_ids.get(i)+"');";
            this.getWritableDatabase().execSQL(insert_Service);

        }



    }


    public void insertService(String id_service, String nom,String longitude, String latitude){

        String insert_Service="INSERT INTO service (id_service,nom,longitude,latitude) VALUES ( '"+id_service+"','"+nom+"','"+longitude+"','"+latitude+"');";
        this.getWritableDatabase().execSQL(insert_Service);
    }


    public void insertPrest(String id_user, String id_prest ){

        String insert_User="INSERT INTO prestataire (id_prestataire,id_user) VALUES ( '"+id_user+"','"+id_prest+"');";
        this.getWritableDatabase().execSQL(insert_User);
    }

    public ArrayList<User> getUser(){

        ArrayList<User> u = new ArrayList<>();
        String strSql = "select * from user";
        Cursor cursor = this.getReadableDatabase().rawQuery( strSql, null );
        cursor.moveToFirst();
        while( ! cursor.isAfterLast() ) {
            User user = new User( cursor.getString( 0 ),cursor.getString( 01 ),cursor.getString( 2 ),
                    cursor.getString( 3 ), cursor.getString( 4 ),cursor.getString( 5 ) );
            u.add( user );
            cursor.moveToNext();
        }
        cursor.close();

        return u;
    }


    public void insertCat(){


        rec=rec+1;
        String insert_cat="";
        ArrayList<String> cat =new ArrayList<>();

        cat.add("bla");
        cat.add("bla");
        cat.add("bla");
        cat.add("bla");
        cat.add("bla");
        cat.add("bla");

        int i=0;

        do{
            insert_cat ="INSERT INTO category (name) VALUES ( '"+ cat.get(i)+"');";
            this.getWritableDatabase().execSQL(insert_cat);

            i++;
        }while(i<cat.size());


    }


    public ArrayList<Category> getCategory(){

        String d;
        ArrayList<Category> c = new ArrayList<>();
        String strSql = "select * from category";
        Cursor cursor = this.getReadableDatabase().rawQuery( strSql, null );
        cursor.moveToFirst();
        while( ! cursor.isAfterLast() ) {
            Category catego = new Category( cursor.getInt( 0 ),cursor.getString( 1 ) );
            c.add( catego );
            d=catego.getCategorie();
            cursor.moveToNext();
        }
        cursor.close();



        return c;
    }


    public ArrayList<Prestataire> getPrest(){

        ArrayList<Prestataire> u = new ArrayList<>();
        String strSql = "select * from prestataire";
        Cursor cursor = this.getReadableDatabase().rawQuery( strSql, null );
        cursor.moveToFirst();
        while( ! cursor.isAfterLast() ) {
            Prestataire prest = new Prestataire( cursor.getString( 0 ),cursor.getString( 1 ) );
            u.add( prest );
            cursor.moveToNext();
        }
        cursor.close();

        return u;
    }


}
