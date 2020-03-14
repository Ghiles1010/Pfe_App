package com.example.pfeapp.client_ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

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

    public class Connexion_Background extends AsyncTask<String,Void,String> {

        AlertDialog dialog;
        Context context;
        public Connexion_Background(Context context){
            this.context = context ;
        }

        @Override
        protected void onPostExecute(String s) {
            dialog.setMessage(s);
            if(s =="success"){
                Intent intent =new Intent(this.context,Menu.class);
                this.context.startActivity(intent);
            }
            else {
                dialog.show();
            }
        }

        @Override
        protected String doInBackground(String... voids) {


            String result= ""  ;
            String type=voids[0];
            String login_url="http:/192.168.1.7/login.php";//go to commend prompt to know your local ip adress
            if(type.equals("login")) {

                try {
                    String email= voids[1];
                    String psw= voids[2];
                    URL url = new URL(login_url);
                    HttpURLConnection URLconn = (HttpURLConnection) url.openConnection();
                    URLconn.setRequestMethod("POST");//request to write on the server
                    URLconn.setDoInput(true);
                    URLconn.setDoOutput(true);

                    OutputStream ops = URLconn.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(ops, "UTF8"));
                    String data = URLEncoder.encode("email", "UTF8") + "=" + URLEncoder.encode(email, "UTF8")+"&"
                            + URLEncoder.encode("psw", "UTF8") + "=" + URLEncoder.encode(psw, "UTF8");//encode the string into utf-8 (the recommended web encoding)

                    writer.write(data);//write on the buffer
                    writer.flush();
                    writer.close();

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
                    return result;


                } catch (MalformedURLException e) {
                    result = e.getMessage();
                } catch (java.io.IOException e) {
                    result = e.getMessage();
                }
            }

            return result;
        }
    }


