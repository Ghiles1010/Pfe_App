package com.example.pfeapp.client_ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.example.pfeapp.prest_ui.Temporaire;

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

public class Background extends AsyncTask<String, Void, String> {

    AlertDialog dialog;
    Context context;

    public Background(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        dialog = new AlertDialog.Builder(context).create();
        dialog.setTitle("");

    }

    @Override
    protected void onPostExecute(String s) {


        switch (s) {

            case ("success"): {
                Intent intent = new Intent(context, Temporaire.class);
                context.startActivity(intent);
                break;
            }

            default: {
                dialog.setMessage(s);
                dialog.show();
            }
        }

    }


    @Override
    protected String doInBackground(String... voids) {


        String result = "";
        String type = voids[0];
        String c = type;

        String login_url = "http:/192.168.1.6/" + type + ".php";//go to commend prompt to know your local ip adress

        switch (c) {


            case "login":

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
                    return result;


                } catch (MalformedURLException e) {
                    result = e.getMessage();
                } catch (java.io.IOException e) {
                    result = e.getMessage();
                }


                break;

            case "sign-in":

                try {

                    String name = voids[1];
                    String surname = voids[2];
                    String username=voids[3];
                    String email=voids[4];
                    String psw=voids[5];

                    URL url = new URL(login_url);
                    HttpURLConnection URLconn = (HttpURLConnection) url.openConnection();
                    URLconn.setRequestMethod("POST");//request to write on the server
                    URLconn.setDoInput(true);
                    URLconn.setDoOutput(true);

                    OutputStream ops = URLconn.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(ops, "UTF8"));
                    String data = URLEncoder.encode("name", "UTF8") + "=" + URLEncoder.encode(name, "UTF8") + "&"
                            + URLEncoder.encode("surname", "UTF8") + "=" + URLEncoder.encode(surname, "UTF8")+ "&"
                            + URLEncoder.encode("username", "UTF8") + "=" + URLEncoder.encode(username, "UTF8")+ "&"
                            + URLEncoder.encode("email", "UTF8") + "=" + URLEncoder.encode(email, "UTF8")+ "&"
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
                    return result;


                } catch (MalformedURLException e) {
                    result = e.getMessage();
                } catch (java.io.IOException e) {
                    result = e.getMessage();
                }


                Log.d("ress",result);

                break;




        }



        return result;
    }

}





