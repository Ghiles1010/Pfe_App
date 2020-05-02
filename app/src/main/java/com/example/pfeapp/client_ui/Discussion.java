package com.example.pfeapp.client_ui;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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

import com.example.pfeapp.BD.Data_Base;
import com.example.pfeapp.BD.Message;
import com.example.pfeapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

import static com.example.pfeapp.client_ui.Background.ip;

public class Discussion extends AppCompatActivity {

    private EditText messageBox;
    private ImageButton send;

    private RecyclerView recview;
    Chat_adapter adapter;
    ArrayList<Message> cards = new ArrayList<>();
    Data_Base db=new Data_Base(this);
    private WebSocket webSocket;

    private String id_client;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.discussion);

        id_client=db.getClient().get(0).getId_client();

        messageBox=(EditText)findViewById(R.id.Message_To_send);
        send=(ImageButton)findViewById(R.id.send);

        Message m=new Message();
        m.setText("Hi");
        m.setId_sender("kk");
        cards.add(m);
        recview = findViewById(R.id.recConvm);

        adapter = new Chat_adapter(this, cards);


        recview.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recview.setLayoutManager(layoutManager);

        recview.smoothScrollToPosition(cards.size());

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();

            }


        });

        initWebSocket();

    }



    private void initWebSocket(){
        OkHttpClient client = new OkHttpClient();


        //replace x.x.x.x with your machine's IP Address
        Request request = new Request.Builder().url("ws://"+ip+":8080/?userID="+id_client+"").build();


        SocketListener socketListener = new SocketListener(this);


        webSocket = client.newWebSocket(request, socketListener);
    }


    void sendMessage() {

        String message = messageBox.getText().toString();

        if (!message.isEmpty()) {

            JSONObject jsonObject = new JSONObject();

            try {


                jsonObject.put("message", message);
                jsonObject.put("sender", id_client);

            } catch (JSONException e) {
                e.printStackTrace();
            }


            webSocket.send(jsonObject.toString());
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
        }  else{
            connected = false;}

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


                    Message message=new Message();

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

}
