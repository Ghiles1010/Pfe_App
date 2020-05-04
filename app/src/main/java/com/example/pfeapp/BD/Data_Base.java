package com.example.pfeapp.BD;


import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;
import static com.example.pfeapp.prest_ui.Discussion_prest.MSG_LOADED;
import static com.example.pfeapp.prest_ui.Messagerie_prest.CONV_LOADED;
import static com.example.pfeapp.user_ui.Connexion.PREFERENCES;


public class Data_Base extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Base_donnees";
    private static final int Version = 1;


    int rec = 0;

    Context context;

    public Data_Base(Context context) {

        super(context, DATABASE_NAME, null, Version);
        this.context = context;
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
                + " current int(3),"
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
                + " id_client VARCHAR(40),"
                + " id_service VARCHAR(40),"
                + " time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ,"
                + " last_message VARCHAR(255),"
                + " nom_client VARCHAR(255),"
                + " nom_service VARCHAR(255),"
                + " messages_loaded int(2),"
                + " conversation_loaded int(2),"
                + " CONSTRAINT Fk_client FOREIGN KEY (id_client) references client(id_client),"
                + " CONSTRAINT Fk_service FOREIGN KEY (id_service) references service(id_service),"
                + " CONSTRAINT PK_conv PRIMARY KEY (id_client,id_service)"
                + " );";

        String creer_message = "CREATE TABLE message("
                + " id_client VARCHAR(40),"
                + " id_service VARCHAR(40),"
                + " time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ,"
                + " texte VARCHAR(255),"
                + " id_sender VARCHAR(255),"
                + " CONSTRAINT Fk_client FOREIGN KEY (id_client) references client(id_client),"
                + " CONSTRAINT Fk_service FOREIGN KEY (id_service) references service(id_service),"
                + " CONSTRAINT PK_message PRIMARY KEY (id_client,id_service,time)"
                + " );";

        String creer_Category = "CREATE TABLE category("
                + " id_category INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,"
                + " name VARCHAR(255)"
                + " );";

        String cat_service = "CREATE TABLE category_service("
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

    public void insertUser(String ID, String email, String psw, String name, String surname, String username) {

        clearTables("message", "conversation", "client", "category_service", "service", "prestataire", "user");

        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(CONV_LOADED, false);
        editor.putBoolean(MSG_LOADED, false);
        editor.apply();

        String insert_User = "INSERT INTO user (id_user,mail,psw,username,name,surname) VALUES ( '" + ID + "','" + email + "','" + psw + "','" + username + "','" + name + "','" + surname + "');";
        this.getWritableDatabase().execSQL(insert_User);
    }


    public void affect_category(String id_service, ArrayList<Integer> cat_ids) {

        for (int i = 0; i < cat_ids.size(); i++) {

            String insert_Service = "INSERT INTO category_service (id_service,id_category) VALUES ( '" + id_service + "','" + cat_ids.get(i) + "');";
            this.getWritableDatabase().execSQL(insert_Service);

        }


    }

    public void insertMessage(String id_client, String id_service, String time, String texte, String id_sender) {

        String insert_Service = "INSERT INTO message (id_client,id_service,time,texte,id_sender) VALUES ( '" + id_client + "','" + id_service + "','" + time + "','" + texte + "','" + id_sender + "');";
        this.getWritableDatabase().execSQL(insert_Service);
    }

    public ArrayList<Message> get_messages() {

        ArrayList<Message> u = new ArrayList<>();
        String strSql = "select * from message";
        Cursor cursor = this.getReadableDatabase().rawQuery(strSql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Message message = new Message(cursor.getString(0), cursor.getString(1), cursor.getString(2),
                    cursor.getString(3), cursor.getString(4));
            u.add(message);
            cursor.moveToNext();
        }
        cursor.close();

        return u;
    }


    public void insertService(String id_service, String id_prestataire, String nom, String longitude, String latitude, int current) {

        String insert_Service = "INSERT INTO service (id_service,id_prestataire,nom,longitude,latitude,current) VALUES ( '" + id_service + "','" + id_prestataire + "','" + nom + "','" + longitude + "','" + latitude + "','" + current + "');";
        this.getWritableDatabase().execSQL(insert_Service);
    }

    public String getLastTimeMessage(String id_client, String id_service) {

        String strSql = "select max(time) from message where id_client like '" + id_client + "' and id_service like '" + id_service + "';";
        Cursor cursor = this.getReadableDatabase().rawQuery(strSql, null);
        cursor.moveToFirst();

        String time = cursor.getString(0);

        cursor.close();

        return time;

    }


    public ArrayList<Service> get_services() {

        ArrayList<Service> u = new ArrayList<>();
        String strSql = "select * from service";
        Cursor cursor = this.getReadableDatabase().rawQuery(strSql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Service service = new Service(cursor.getString(0), cursor.getString(1), cursor.getString(2),
                    cursor.getString(3), cursor.getInt(4), cursor.getString(5), cursor.getInt(6),
                    cursor.getFloat(7), cursor.getString(8), cursor.getString(9), cursor.getString(10),
                    cursor.getString(11), cursor.getInt(12));
            u.add(service);
            cursor.moveToNext();
        }
        cursor.close();

        return u;
    }


    public void set_current_service(String id_service) {

        String insert_Service = "UPDATE service set current = 1 WHERE id_service = '" + id_service + "';";
        this.getWritableDatabase().execSQL(insert_Service);
    }


    public Service get_current_service() {


        String strSql = "select * from service WHERE current = 1 ";
        Cursor cursor = this.getReadableDatabase().rawQuery(strSql, null);
        cursor.moveToFirst();

        Service service = new Service(cursor.getString(0), cursor.getString(1), cursor.getString(2),
                cursor.getString(3), cursor.getInt(4), cursor.getString(5), cursor.getInt(6),
                cursor.getFloat(7), cursor.getString(8), cursor.getString(9), cursor.getString(10),
                cursor.getString(11), cursor.getInt(12));

        cursor.close();

        return service;

    }

    public void insertPrest(String id_user, String id_prest) {

        this.getWritableDatabase().execSQL("delete from prestataire");


        String insert_User = "INSERT INTO prestataire (id_prestataire,id_user) VALUES ( '" + id_prest + "','" + id_user + "');";
        this.getWritableDatabase().execSQL(insert_User);
    }

    public ArrayList<User> getUser() {

        ArrayList<User> u = new ArrayList<>();
        String strSql = "select * from user";
        Cursor cursor = this.getReadableDatabase().rawQuery(strSql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            User user = new User(cursor.getString(0), cursor.getString(01), cursor.getString(2),
                    cursor.getString(3), cursor.getString(4), cursor.getString(5));
            u.add(user);
            cursor.moveToNext();
        }
        cursor.close();

        return u;
    }

    public void insertClient(String id_user, String id_client) {

        this.getWritableDatabase().execSQL("delete from client");


        String insert_User = "INSERT INTO client (id_client,id_user) VALUES ( '" + id_client + "','" + id_user + "');";
        this.getWritableDatabase().execSQL(insert_User);
    }

    public void insertCat() {


        rec = rec + 1;
        String insert_cat = "";
        ArrayList<String> cat = new ArrayList<>();

        cat.add("bla");
        cat.add("blo");
        cat.add("blu");
        cat.add("bli");
        cat.add("ble");
        cat.add("bly");

        int i = 0;

        do {
            insert_cat = "INSERT INTO category (name) VALUES ( '" + cat.get(i) + "');";
            this.getWritableDatabase().execSQL(insert_cat);

            i++;
        } while (i < cat.size());


    }

    public ArrayList<Category> getCategory() {

        String d;
        ArrayList<Category> c = new ArrayList<>();
        String strSql = "select * from category";
        Cursor cursor = this.getReadableDatabase().rawQuery(strSql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Category catego = new Category(cursor.getInt(0), cursor.getString(1));
            c.add(catego);
            d = catego.getCategorie();
            cursor.moveToNext();
        }
        cursor.close();


        return c;
    }

    public ArrayList<Prestataire> getPrest() {

        ArrayList<Prestataire> u = new ArrayList<>();
        String strSql = "select * from prestataire";
        Cursor cursor = this.getReadableDatabase().rawQuery(strSql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Prestataire prest = new Prestataire(cursor.getString(0), cursor.getString(1));
            u.add(prest);
            cursor.moveToNext();
        }
        cursor.close();

        return u;
    }

    public ArrayList<Client> getClient() {

        ArrayList<Client> u = new ArrayList<>();
        String strSql = "select * from client";
        Cursor cursor = this.getReadableDatabase().rawQuery(strSql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Client client = new Client(cursor.getString(0), cursor.getString(1));
            u.add(client);
            cursor.moveToNext();
        }
        cursor.close();

        return u;
    }


    public void insertConversation(String id_client, String id_service, String time, String last_message, String nom_client, String nom_service, int message_loaded, int conversation_loaded) {

        String insert_conv = "INSERT INTO conversation (id_client,id_service,time,last_message,nom_client,nom_service,messages_loaded,conversation_loaded) VALUES ( '" + id_client + "','" + id_service + "','" + time + "','" + last_message + "','" + nom_client + "','" + nom_service + "','" + message_loaded + "','" + conversation_loaded + "');";
        this.getWritableDatabase().execSQL(insert_conv);
    }


    public boolean ConversationLoaded(String id_client, String id_service) {
        String strSql = "select * from conversation id_client = '" + id_client + "' and id_service='" + id_service + "'";
        Cursor cursor = this.getReadableDatabase().rawQuery(strSql, null);
        cursor.moveToFirst();

        Conversation conversation = new Conversation(cursor.getString(0), cursor.getString(1)
                , cursor.getString(2), cursor.getString(3), cursor.getString(4)
                , cursor.getString(5), cursor.getInt(6), cursor.getInt(7));

        cursor.moveToNext();
        cursor.close();
        if (conversation != null) {
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<Conversation> getConversation() {

        ArrayList<Conversation> u = new ArrayList<>();
        String strSql = "select * from conversation";
        Cursor cursor = this.getReadableDatabase().rawQuery(strSql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Conversation conversation = new Conversation(cursor.getString(0), cursor.getString(1)
                    , cursor.getString(2), cursor.getString(3), cursor.getString(4)
                    , cursor.getString(5), cursor.getInt(6), cursor.getInt(7));
            u.add(conversation);
            cursor.moveToNext();
        }
        cursor.close();

        return u;
    }

    public ArrayList<Conversation> getConversationByID(String id_client, String id_service) {

        ArrayList<Conversation> u = new ArrayList<>();
        String strSql = "select * from conversation where id_client='" + id_client + "'and id_service='" + id_service + "';";
        Cursor cursor = this.getReadableDatabase().rawQuery(strSql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Conversation conversation = new Conversation(cursor.getString(0), cursor.getString(1)
                    , cursor.getString(2), cursor.getString(3), cursor.getString(4)
                    , cursor.getString(5), cursor.getInt(6), cursor.getInt(7));
            u.add(conversation);
            cursor.moveToNext();
        }
        cursor.close();

        return u;
    }

    public void setMessageLoadedStatus(String id_client, String id_service, int status) {

        String stat = "UPDATE conversation set messages_loaded = " + status + " WHERE id_service = '" + id_service + "' and id_client = '" + id_client + "';";
        this.getWritableDatabase().execSQL(stat);

    }

    public void clearTables(String... voids) {

        for (int i = 0; i < voids.length; i++) {

            this.getWritableDatabase().execSQL("delete from " + voids[i] + "");
        }

    }

}
