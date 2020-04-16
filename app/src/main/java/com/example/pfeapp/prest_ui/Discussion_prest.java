package com.example.pfeapp.prest_ui;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.pfeapp.BD.Data_Base;
import com.example.pfeapp.BD.Message;
import com.example.pfeapp.R;
import com.example.pfeapp.client_ui.Background;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.pfeapp.client_ui.Background.ip;

public class Discussion_prest extends AppCompatActivity {

    private EditText message;
    private ImageButton send;
    private RecyclerView recview;
    Chat_adapter_prest adapter;


    String id_client;
    String id_service;



    String last_message_time;
    String current_last_message_time;

    Data_Base db = new Data_Base(this);

    ArrayList<Message> cards = new ArrayList<>();

    private thread looperThread = new thread();
    private Handler mainHandler=new Handler();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.discussion);

        Intent i = getIntent();
        id_client = (String) i.getSerializableExtra("id_client");

        id_service=db.get_current_service().getIDservice();



        last_message_time="2019-03-24 22:27:28";
        current_last_message_time="2019-03-24 22:27:28";

        message=(EditText)findViewById(R.id.Message_To_send);
        send=(ImageButton)findViewById(R.id.send);

        recview=findViewById(R.id.recConvm);

        getList("");


        adapter=new Chat_adapter_prest(this,cards);



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

        looperThread.start();


    }






    void sendMessage() {

        Background background=new Background(this);

        if(InternetAvailable()) {






            Message c= new Message();
            c.setText(message.getText().toString());
            c.setId_sender(id_service);
            cards.add(c);
            recview.smoothScrollToPosition(cards.size());
            adapter.notifyDataSetChanged();

            String mes=message.getText().toString();
            message.getText().clear();

            String conv="conv";
            String type = "send_message";
            String res;
            res=background.execute(type, mes,id_client,id_service,id_service).toString();



        }
        else{

            Toast.makeText(Discussion_prest.this, "aucune connexion Internet", Toast.LENGTH_SHORT).show();

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



    private void getList(String us){
        //entrez le chaine vide dans us pour afficher tout les user
        //sinon specifier le ID du user
        new getData().execute("get_message",id_service,current_last_message_time,id_client,us);

    }



    private class getData extends AsyncTask<String, Void, String> {

        String result;

        @Override
        protected String doInBackground(String... voids) {


            Background b=new Background();

            result=b.request(voids[0],ip,"id_service",voids[1],"time",voids[2],"id_client",voids[3],"id_sender",voids[4]);


                Message cg;




                if(!result.equals("no messages")) {
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
                            cards.add(cg);
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
        protected void onPreExecute() {}

        @Override
        protected void onProgressUpdate(Void... values) {}
    }



    public class thread extends Thread {


        @Override
        public void run() {

            while(true){



                SystemClock.sleep(500);
                String result = "";

                Background b=new Background();
                result=b.request("last_message",ip,"id_service",id_service,"id_client",id_client);



                    if(!result.equals("")) {
                        last_message_time = result.substring(0, 19);

                        result = result.replaceFirst("\\d+(-\\d+){2} (\\d+:){2}\\d+", "");


                        String current;
                        String last;

                        current = current_last_message_time.replaceAll("(-|:)", " ");
                        last = last_message_time.replaceAll("(-|:)", " ");


                        if (isAfter(last, current, 4)) { // ne change pas le numero 4, il represente le nombre de chiifre de l'annee


                            mainHandler.post(new Runnable() {
                                @Override
                                public void run() {


                                    getList(id_service);
                                    current_last_message_time = last_message_time;
                                    adapter.notifyDataSetChanged();
                                    recview.smoothScrollToPosition(cards.size());

                                }
                            });

                        }
                    }





            }//fin while



        }



        public  boolean isAfter(String d1,String d2, int n){

            int num1, num2;
            String str1, str2;


            str1=d1.substring(0, n);


            str2=d2.substring(0, n);


            num1=Integer.parseInt(str1);
            num2=Integer.parseInt(str2);

            if(num1>num2)
                return true;
            if(num1<num2)
                return false;
            else{

                d1=d1.replaceFirst("\\d+ ?", "");
                d2=d2.replaceFirst("\\d+ ?", "");

                if(d1.isEmpty())
                    return false;
                else
                    return isAfter(d1,d2,2);

            }
        }
    }




    public class Chat_adapter_prest extends RecyclerView.Adapter<Chat_Holder_prest> {

        Discussion_prest c;
        ArrayList<Message> cards;

        public static final int MSG_RIGHT=1;
        public static final int MSG_LEFT=0;


        public Chat_adapter_prest(Discussion_prest c, ArrayList<Message> cards) {
            this.c = c;
            this.cards = cards;
        }


///////////////////////////////////////////////////////////////////////////

        @NonNull
        @Override
        public Chat_Holder_prest onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view;
            if(viewType==MSG_RIGHT) {
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item_right, null);
            }
            else{
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item_left, null);
            }
            return new Chat_Holder_prest(view);
        }

        @Override
        public void onBindViewHolder(@NonNull Chat_Holder_prest holder, int position) {

            holder.text.setText(cards.get(position).getText());

        }

        @Override
        public int getItemCount() {
            return cards.size();
        }


        @Override
        public int getItemViewType(int position) {

            String f=cards.get(position).getId_sender();

            if(f.equals(id_service)){
                return MSG_RIGHT;
            }

            else{
                return  MSG_LEFT;
            }
        }
    }







}