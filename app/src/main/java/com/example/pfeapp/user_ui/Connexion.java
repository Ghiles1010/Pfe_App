package com.example.pfeapp.user_ui;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.pfeapp.client_ui.Background;
import com.example.pfeapp.prest_ui.Temporaire;

import static com.example.pfeapp.R.layout.connexion;
import static com.example.pfeapp.client_ui.Background.ip;

public class Connexion extends AppCompatActivity {
    private EditText emailET, pswET;
    private Button conn;
    private TextView error;
    public User user = new User();
    public static final String PREFERENCES="preferences";
    public static final String LOGGED="logged";



    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


            setContentView(connexion);

            error = findViewById(R.id.Error);
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




    public class connect extends AsyncTask<String, Void, String> {

        AlertDialog dialog;
        String result = "";
        Context context;

        public connect(Context context) {
            this.context = context;
        }

        @Override
        protected String doInBackground(String... voids) {


            Background b=new Background();
            result=b.request("login",ip,"email",voids[1],"psw",voids[2]);

            String temp="";


            if (result.length()> 7) {
                temp = result.substring(0, 7);
            }

            if (temp.equals("success")) {



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

                String id=result.substring(8, length);




                SharedPreferences prefs = getSharedPreferences(PREFERENCES, MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString(LOGGED,id );
                editor.apply();

                user.setId(id);
                user.setEmail(emailET.getText().toString());

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
