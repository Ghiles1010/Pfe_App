package com.example.pfeapp.user_ui;

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

import com.example.pfeapp.BD.Data_Base;
import com.example.pfeapp.BD.User;
import com.example.pfeapp.R;
import com.example.pfeapp.client_ui.Background;

import org.json.JSONArray;
import org.json.JSONObject;

import static com.example.pfeapp.R.layout.connexion;
import static com.example.pfeapp.client_ui.Background.ip;

public class Connexion extends AppCompatActivity {
    private EditText emailET, pswET;
    private Button conn;
    private TextView error;
    public User user = new User();
    Data_Base db= new Data_Base(this);
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
        User user=new User();

        public connect(Context context) {
            this.context = context;
        }

        @Override
        protected String doInBackground(String... voids) {


            Background b=new Background();
            result=b.request("login",ip,"email",voids[1],"psw",voids[2]);


            if (result.equals("success")) {


                String us=b.request("get_user",ip,"mail",voids[1]);


                try {


                    JSONArray JA = new JSONArray(us);
                    JSONObject JO = (JSONObject) JA.get(0);
                    user.setId(JO.get("id_user").toString());
                    user.setEmail(JO.get("mail").toString());
                    user.setPsw(JO.get("psw").toString());
                    user.setUserName(JO.get("username").toString());
                    user.setName(JO.get("name").toString());
                    user.setSurname(JO.get("surname").toString());

                    db.insertUser(user.getId(),user.getEmail(),user.getPsw(),user.getName(),user.getSurname(),user.getUserName());

                } catch (org.json.JSONException e) {
                    e.printStackTrace();
                }

            }



            return result;
        }

        @Override
        protected void onPostExecute(String result) {


            if (result.equals("success")) {



                Intent intent = new Intent(context, Choix_session_connexion.class);
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
