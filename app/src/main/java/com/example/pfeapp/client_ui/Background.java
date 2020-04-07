package com.example.pfeapp.client_ui;

import android.app.AlertDialog;
import android.content.Context;
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

public class Background extends AsyncTask<String, Void, String> {

    AlertDialog dialog;
    Context context;
    public static final String ip ="192.168.1.7";

    public Background(Context context) {
        this.context = context;
    }

    public Background() {
    }

    @Override
    protected void onPreExecute() {
        dialog = new AlertDialog.Builder(context).create();
        dialog.setTitle("");

    }

    @Override
    protected void onPostExecute(String s) {


        switch (s) {

            case ("success"):

                break;


            case ("message_sent"):


                break;

            case ("get_message"):

                dialog.setMessage(s);
                dialog.show();


                break;


            default:
                dialog.setMessage(s);
                dialog.show();

        }

    }


    @Override
    protected String doInBackground(String... voids) {


        String result = "";
        String type = voids[0];
        String c = type;


        switch (c) {




            case "sign-in":


                result=request(c,ip,"name",voids[1],"surname",voids[2],"username",voids[3],"email",voids[4],"psw",voids[5]);



                break;

            case "send_message":



                result=request(c,ip,"message",voids[1],"conv",voids[2],"user",voids[3]);



                break;

        }


        return result;
    }


    /*
    Le premier Argument est le nom du fichier php sans l'extension
    le deuxieme est le est l'adresse ip
    puis "nom du $_POST puis sa valeur..."
     */
    public String request(String... voids){


        String result = "";
        String type = voids[0];
        String address=voids[1];


        String login_url = "http:/"+address+"/" + type + ".php";//go to commend prompt to know your local ip adress


        try {


            URL url = new URL(login_url);
            HttpURLConnection URLconn = (HttpURLConnection) url.openConnection();
            URLconn.setRequestMethod("POST");//request to write on the server
            URLconn.setDoInput(true);
            URLconn.setDoOutput(true);

            OutputStream ops = URLconn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(ops, "UTF8"));

            int i=2;
            String data="";
            while(i<voids.length){

                if(i==2) {
                    data = data + URLEncoder.encode(voids[i], "UTF8") + "=" + URLEncoder.encode(voids[i + 1], "UTF8");
                }
                else{
                    data = data + "&"+URLEncoder.encode(voids[i], "UTF8") + "=" + URLEncoder.encode(voids[i + 1], "UTF8");
                }

                i=i+2;

            }

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






        return result;
    }

}





