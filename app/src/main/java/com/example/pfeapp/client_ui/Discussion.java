package com.example.pfeapp.client_ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pfeapp.BD.Conversation;
import com.example.pfeapp.BD.Data_Base;
import com.example.pfeapp.BD.Message;
import com.example.pfeapp.BD.Service;
import com.example.pfeapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

import static com.example.pfeapp.client_ui.Background.ip;
import static com.example.pfeapp.user_ui.Connexion.PREFERENCES;

public class Discussion extends AppCompatActivity {

    private EditText messageBox;
    private ImageButton send;

    boolean asyncended = false;

    private RecyclerView recview;
    Chat_adapter adapter;
    ArrayList<Message> cards = new ArrayList<>();
    Data_Base db = new Data_Base(this);
    private WebSocket webSocket;

    private String id_service;
    private String id_client;

    Service service;

    ArrayList<Conversation> c;

    String last_message_time;
    String current_last_message_time;

    public final static String MSG_LOADED = "messages loaded";

    boolean firstContact = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.discussion);

        last_message_time = "2019-03-24 22:27:28";
        current_last_message_time = "2019-03-24 22:27:28";

        Intent i = getIntent();

        service=(Service) i.getSerializableExtra("service");


        id_service = service.getIDservice();
        id_client = db.getClient().get(0).getId_client();

        messageBox = (EditText) findViewById(R.id.Message_To_send);
        send = (ImageButton) findViewById(R.id.send);


        c = db.getConversationByID(id_client, id_service);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();

            }


        });


        if (c.isEmpty()) {//conversation non en local donc on vient de profile
            //le client est forcément en ligne car avant de venir ici on a testé dans le listener du boutom
            //créer une conversation en ligne si elle n'existe pas
            //si elle n'existe pas on la rajoute en local
            Check_conv check_conv = new Check_conv();

            check_conv.execute();




        }

        recview = findViewById(R.id.recConvm);

        SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCES, MODE_PRIVATE);
        boolean loaded = sharedPreferences.getBoolean(MSG_LOADED, false);




        adapter = new Chat_adapter(this, cards);

        if (!c.isEmpty()) {
            if (c.get(0).getMessages_loaded() == 0) {
                getList();


            } else {

                getLocalList();

            }
        }



        recview.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recview.setLayoutManager(layoutManager);

        recview.smoothScrollToPosition(cards.size());


        initWebSocket();

    }

    private void getLocalList() {


        ArrayList<Message> m = db.get_messages(id_client,id_service);
        current_last_message_time = db.getLastTimeMessage(id_client, id_service);
        last_message_time = current_last_message_time;

        for (Message i : m) {
            cards.add(i);
        }


    }

    private void getList() {


        SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(MSG_LOADED, true);
        editor.apply();

        //entrez le chaine vide dans us pour afficher tout les user
        //sinon specifier le ID du user


        new getData().execute("get_message", id_service, current_last_message_time, id_client, "");

    }

    private void initWebSocket() {
        OkHttpClient client = new OkHttpClient();


        //replace x.x.x.x with your machine's IP Address
        Request request = new Request.Builder().url("ws://" + ip + ":8080/?IDuser=" + id_client + "").build();


        SocketListener socketListener = new SocketListener(this);


        webSocket = client.newWebSocket(request, socketListener);
    }


    void sendMessage() {

        String message = messageBox.getText().toString();

        if (!message.isEmpty()) {

            Background background = new Background(this);
            JSONObject jsonObject = new JSONObject();

            try {


                jsonObject.put("message", message);
                jsonObject.put("sender", id_client);
                jsonObject.put("id_dest", "IDuser=" + id_service);


            } catch (JSONException e) {
                e.printStackTrace();
            }


            webSocket.send(jsonObject.toString());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            String currentDateandTime = sdf.format(new Date());
            background.execute("send_message", message, id_client, id_service, id_client, currentDateandTime);


            db.insertMessage(id_client, id_service, currentDateandTime, message, id_client);
            messageBox.setText("");

            Message m = new Message();

            m.setId_sender(id_client);
            m.setText(message);
            cards.add(m);

            adapter.notifyDataSetChanged();
        }

    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////

    public boolean InternetAvailable() {
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
        } else {
            connected = false;
        }

        return connected;
    }


    public class SocketListener extends WebSocketListener {


        public Discussion activity;


        public SocketListener(Discussion activity) {
            this.activity = activity;
        }


        @Override
        public void onOpen(WebSocket webSocket, Response response) {


            activity.runOnUiThread(new Runnable() {

                @Override
                public void run() {

                    Toast.makeText(activity, "Connection Established!", Toast.LENGTH_LONG).show();

                }

            });

        }


        @Override
        public void onMessage(WebSocket webSocket, final String text) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {


                    Message message = new Message();

                    try {

                        JSONObject jsonObject = new JSONObject(text);

                        message.setText(jsonObject.get("message").toString());
                        message.setId_sender(jsonObject.get("sender").toString());


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    cards.add(message);
                    adapter.notifyDataSetChanged();


                }
            });
        }


        @Override
        public void onMessage(WebSocket webSocket, ByteString bytes) {
            super.onMessage(webSocket, bytes);
        }


        @Override
        public void onClosing(WebSocket webSocket, int code, String reason) {
            super.onClosing(webSocket, code, reason);
        }


        @Override
        public void onClosed(WebSocket webSocket, int code, String reason) {
            super.onClosed(webSocket, code, reason);
        }


        @Override
        public void onFailure(WebSocket webSocket, final Throwable t, @Nullable final Response response) {
            super.onFailure(webSocket, t, response);
        }
    }


    public class Chat_adapter extends RecyclerView.Adapter<Chat_holder> {

        Discussion c;
        ArrayList<Message> cards;

        public static final int MSG_RIGHT = 1;
        public static final int MSG_LEFT = 0;


        public Chat_adapter(Discussion c, ArrayList<Message> cards) {
            this.c = c;
            this.cards = cards;
        }


///////////////////////////////////////////////////////////////////////////

        @NonNull
        @Override
        public Chat_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view;
            if (viewType == MSG_RIGHT) {
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item_right, null);
            } else {
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item_left, null);
            }
            return new Chat_holder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull Chat_holder holder, int position) {

            holder.text.setText(cards.get(position).getText());

        }

        @Override
        public int getItemCount() {
            return cards.size();
        }


        @Override
        public int getItemViewType(int position) {


            if (cards.get(position).getId_sender().equals(id_client)) {
                return MSG_RIGHT;
            } else {
                return MSG_LEFT;
            }
        }
    }

    private class getData extends AsyncTask<String, Void, String> {

        String result;

        @Override
        protected String doInBackground(String... voids) {


            Background b = new Background();

            result = b.request(voids[0], ip, "id_service", voids[1], "time", voids[2], "id_client", voids[3], "id_sender", voids[4]);


            Message cg;


            if (!result.equals("no messages")) {
                current_last_message_time = result.substring(0, 19);

                result = result.replaceFirst("\\d+(-\\d+){2} (\\d+:){2}\\d+", "");


                try {


                    JSONArray JA = new JSONArray(result);

                    for (int j = 0; j < JA.length(); j++) {
                        JSONObject JO = (JSONObject) JA.get(j);

                        cg = new Message();
                        cg.setText(JO.get("text").toString());
                        cg.setId_sender(JO.get("id_sender").toString());
                        cg.setId_client(JO.get("client").toString());
                        cg.setId_service(JO.get("service").toString());
                        cg.setTime(JO.get("hour").toString());
                        cards.add(cg);


                        db.insertMessage(cg.getId_client(), cg.getId_service(), cg.getTime(), cg.getText(), cg.getId_sender());
                        db.setMessageLoadedStatus(id_client,id_service,1);
                    }
                } catch (org.json.JSONException e) {
                    e.printStackTrace();
                }

            }

            return result;
        }

        @Override
        protected void onPostExecute(String result) {

            adapter.notifyDataSetChanged();
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }

    void sendFirstMessage() {

        Background background = new Background(this);
        background.execute("insert_conv", id_client, id_service,db.getUser().get(0).getName(),service.getNom(),messageBox.getText().toString());


        sendMessage();

        restoreSendListener();

    }

    void restoreSendListener() {
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });
    }


    void checkConv(){
        String result = "";
        Background b = new Background();

        result = b.request("check_exist_conv", ip, "id_client", id_client, "id_service", id_service);

        switch (result) {


            case "not_exist":

                firstContact = true;

                send.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sendFirstMessage();

                    }


                });


                break;

            default:

                firstContact = false;

                try {

                    JSONObject JO = new JSONObject(result);
                    Conversation conv;

                    conv = new Conversation();
                    conv.setId_client(JO.get("client").toString());
                    conv.setId_service(JO.get("service").toString());
                    conv.setLast_message(JO.get("last_message").toString());
                    conv.setTime(JO.get("time").toString());
                    conv.setNom_client(JO.get("nom_client").toString());
                    conv.setNom_service(JO.get("nom_service").toString());

                    if(!db.ConversationLoaded(id_client,id_service)) {


                        db.insertConversation(conv.getId_client(), conv.getId_service()
                                , conv.getTime(), conv.getLast_message(), conv.getNom_client()
                                , conv.getNom_service(), conv.getMessages_loaded(), conv.getConversation_loaded());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                break;


        }

        asyncended = true;


    }


    private class Check_conv extends AsyncTask<String, Void, String> {


        String result = "";

        @Override
        protected String doInBackground(String... voids) {

            Background b = new Background();

            result = b.request("check_exist_conv", ip, "id_client", id_client, "id_service", id_service);


            return result;
        }

        @Override
        protected void onPostExecute(String result) {

            switch (result) {


                case "not_exist":

                    firstContact = true;

                    send.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            sendFirstMessage();

                        }


                    });


                    break;

                default:

                    firstContact = false;
                    Conversation conv;
                    try {
                        JSONArray JA = new JSONArray(result);
                        JSONObject JO = (JSONObject) JA.get(0);

                        conv = new Conversation();
                        conv.setId_client(JO.get("client").toString());
                        conv.setId_service(JO.get("service").toString());
                        conv.setLast_message(JO.get("last_message").toString());
                        conv.setTime(JO.get("time").toString());
                        conv.setNom_client(JO.get("nom_client").toString());
                        conv.setNom_service(JO.get("nom_service").toString());
                        if(!db.ConversationLoaded(id_client,id_service)) {
                            db.insertConversation(conv.getId_client(), conv.getId_service()
                                    , conv.getTime(), conv.getLast_message(), conv.getNom_client()
                                    , conv.getNom_service(), conv.getMessages_loaded(),
                                    conv.getConversation_loaded());


                        }
                        c.add(conv);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    break;


            }

            if (firstContact == false) {
                if (c.get(0).getMessages_loaded() == 0) {
                    getList();


                } else {

                    getLocalList();

                }
            }

        }
    }

}
