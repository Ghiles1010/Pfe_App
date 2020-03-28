package com.example.pfeapp.client_ui;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pfeapp.BD.User;
import com.example.pfeapp.R;
import com.example.pfeapp.prest_ui.Temporaire;

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

import static com.example.pfeapp.R.layout.connexion;

public class Connexion extends AppCompatActivity {
    private EditText emailET, pswET;
    private Button conn;
    private TextView error;
    public User user = new User();
    String UserId;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(connexion);

        error=findViewById(R.id.Error);
        emailET = (EditText) findViewById(R.id.mail);
        pswET = (EditText) findViewById(R.id.psww);

        conn = (Button) findViewById(R.id.connexion);

        conn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenConn();
            }
        });


    }

    public void OpenConn() {
        connect connect = new connect(this);

        if (InternetAvailable()) {
            String email = emailET.getText().toString();
            String psw = pswET.getText().toString();
            String type = "login";
            String res;

            res = connect.execute(type, email, psw).toString();


        } else {

            Toast.makeText(Connexion.this, "aucune connexion Internet", Toast.LENGTH_SHORT).show();

        }


    }

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

    private String getID() {

        String id = "";
        try {


            String result = "";
            String type = "get_user_id";
            String login_url = "http:/192.168.1.7/" + type + ".php";//go to commend prompt to know your local ip adress
            String email = emailET.getText().toString();


            URL url = new URL(login_url);
            HttpURLConnection URLconn = (HttpURLConnection) url.openConnection();
            URLconn.setRequestMethod("POST");//request to write on the server
            URLconn.setDoInput(true);
            URLconn.setDoOutput(true);

            OutputStream ops = URLconn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(ops, "UTF8"));
            String data = URLEncoder.encode("mail", "UTF8") + "=" + URLEncoder.encode(email, "UTF8");

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


            try {

                JSONArray JA = new JSONArray(result);

                for (int j = 0; j < JA.length(); j++) {
                    JSONObject JO = (JSONObject) JA.get(j);

                    id = JO.get("mail").toString();

                }
            } catch (org.json.JSONException e) {
                e.printStackTrace();

            }


            reader.close();
            ips.close();
            URLconn.disconnect();


        } catch (MalformedURLException e) {
            e.getMessage();

        } catch (java.io.IOException e) {
            e.getMessage();

        }

        return id;
    }


    public class connect extends AsyncTask<String, Void, String> {
        String name;
        String sData;
        AlertDialog dialog;
        String result = "";
        Context context;
        String type = "login";
        String login_url = "http:/192.168.1.7/" + type + ".php";//go to commend prompt to know your local ip adress

        public connect(Context context) {
            this.context = context;
        }

        @Override
        protected String doInBackground(String... voids) {
            try {
                String email = voids[1];
                String psw = voids[2];
                URL url = new URL(login_url);
                HttpURLConnection URLconn = (HttpURLConnection) url.openConnection();
                URLconn.setRequestMethod("POST");//request to write on the server
                URLconn.setDoInput(true);
                URLconn.setDoOutput(true);

                OutputStream ops = URLconn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(ops, "UTF8"));
                String data = URLEncoder.encode("email", "UTF8") + "=" + URLEncoder.encode(email, "UTF8") + "&"
                        + URLEncoder.encode("psw", "UTF8") + "=" + URLEncoder.encode(psw, "UTF8");//encode the string into utf-8 (the recommended web encoding)

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

            String temp="";
            int length=result.length();


            if (length > 7) {
                temp = result.substring(0, 7);
            }

            if (temp.equals("success")) {

                user.setId(result.substring(8, length));

                Intent intent = new Intent(context, Temporaire.class);
                intent.putExtra("User",  user);
                startActivity(intent);

            } else {


                error.setText(result);

            }
        }


        @Override
        protected void onPreExecute() {

            dialog = new AlertDialog.Builder(context).create();
            dialog.setTitle("");
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }


}
