package com.example.pfeapp.prest_ui;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pfeapp.BD.User;
import com.example.pfeapp.R;
import com.example.pfeapp.client_ui.Background;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class Discussion_prest extends AppCompatActivity {

    private EditText message;
    private ImageButton send;
    private RecyclerView recview;
    Chat_adapter_prest adapter;
    User user;

    String last_message_time;
    String current_last_message_time;

    int number_of_messages;
    int current_number_of_messages;
    ArrayList<Chat_card> cards = new ArrayList<>();

    private thread looperThread = new thread();
    private Handler mainHandler=new Handler();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.discussion);

        Intent i = getIntent();
        user = (User)i.getSerializableExtra("User");



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






            Chat_card c= new Chat_card();
            c.setText(message.getText().toString());
            cards.add(c);
            recview.smoothScrollToPosition(cards.size());
            adapter.notifyDataSetChanged();

            String mes=message.getText().toString();
            message.getText().clear();

            String conv="conv";
            String type = "send_message";
            String res;
            res=background.execute(type, mes,conv).toString();



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
        new getData().execute("get_message","conv",current_last_message_time,us);

    }

    private class getData extends AsyncTask<String, Void, String> {
        String name;
        String sData;

        String result;

        Context con;



        @Override
        protected String doInBackground(String... voids) {
            try {

                String result = "";
                String type = voids[0];
                String time=voids[2];
                String user=voids[3];
                String c = type;
                String login_url = "http:/192.168.1.7/" + type + ".php";//go to commend prompt to know your local ip adress


                Chat_card cg;


                String conv = voids[1];

                URL url = new URL(login_url);
                HttpURLConnection URLconn = (HttpURLConnection) url.openConnection();
                URLconn.setRequestMethod("POST");//request to write on the server
                URLconn.setDoInput(true);
                URLconn.setDoOutput(true);

                OutputStream ops = URLconn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(ops, "UTF8"));
                String data = URLEncoder.encode("conv", "UTF8") + "=" + URLEncoder.encode(conv, "UTF8")+ "&"
                        + URLEncoder.encode("time", "UTF8") + "=" + URLEncoder.encode(time, "UTF8")+ "&"
                        + URLEncoder.encode("user", "UTF8") + "=" + URLEncoder.encode(user, "UTF8");

                writer.write(data);//write on the buffer
                writer.flush();
                writer.close();//close the buffer

                ops.close();

                InputStream ips = URLconn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(ips, "ISO-8859-1"));
                String line = "";
                while ((line = reader.readLine()) != null) {
                    result += line;




                }




                current_last_message_time=result.substring(0, 19);

                result=result.replaceFirst("\\d+(-\\d+){2} (\\d+:){2}\\d+", "");




                try {



                JSONArray JA = new JSONArray(result);

                for(int j=0; j<JA.length();j++){
                    JSONObject JO = (JSONObject) JA.get(j);

                    cg= new Chat_card();
                    cg.setText(JO.get("text").toString());
                    cards.add(cg);
                }
                }catch (org.json.JSONException e){
                    e.printStackTrace();
                }






                reader.close();
                ips.close();
                URLconn.disconnect();



            } catch (MalformedURLException e) {
                result = e.getMessage();
            } catch (java.io.IOException e) {
                result = e.getMessage();
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

                try {


                    String type = "number_messages";
                    String c = type;
                    String login_url = "http:/192.168.1.7/" + type + ".php";//go to commend prompt to know your local ip adress


                    Chat_card cg;


                    String conv = "conv";

                    URL url = new URL(login_url);
                    HttpURLConnection URLconn = (HttpURLConnection) url.openConnection();
                    URLconn.setRequestMethod("POST");//request to write on the server
                    URLconn.setDoInput(true);
                    URLconn.setDoOutput(true);

                    OutputStream ops = URLconn.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(ops, "UTF8"));
                    String data = URLEncoder.encode("conv", "UTF8") + "=" + URLEncoder.encode(conv, "UTF8");

                    writer.write(data);//write on the buffer
                    writer.flush();
                    writer.close();//close the buffer

                    ops.close();

                    InputStream ips = URLconn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(ips, "ISO-8859-1"));
                    String line = "";
                    while ((line = reader.readLine()) != null) {
                        result += line;


                    }


                    last_message_time=result.substring(0, 19);

                    result=result.replaceFirst("\\d+(-\\d+){2} (\\d+:){2}\\d+", "");


                    reader.close();
                    ips.close();
                    URLconn.disconnect();


                } catch (MalformedURLException e) {
                    result = e.getMessage();
                } catch (java.io.IOException e) {
                    result = e.getMessage();
                }//fin try-catch


                String current;
                String last;

                current=current_last_message_time.replaceAll("(-|:)"," ");
                last=last_message_time.replaceAll("(-|:)"," ");


                if(isAfter(last,current,4)){ // ne change pas le numero 4, il represente le nombre de chiifre de l'annee





                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {


                            getList(user.getId());
                            current_last_message_time=  last_message_time;
                            adapter.notifyDataSetChanged();
                            recview.smoothScrollToPosition(cards.size());

                        }
                    });

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




















}
