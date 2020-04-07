package com.example.pfeapp.BD;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class Data_Base extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Base_donnees";
    private static final int Version = 1;

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
                + " CONSTRAINT PK_appreciation PRIMARY KEY (client,service)"
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
                + " CONSTRAINT Fk_prest FOREIGN KEY (id_prest) references prestataire(id_prestataire)"
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
                + " CONSTRAINT Fk_conversation FOREIGN KEY (conversation) references conversation(id_conversation),"
                + " CONSTRAINT PK_message PRIMARY KEY (conversation,heure)"
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

        Log.i("DATABASE", "onCreate invoked");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }




}
