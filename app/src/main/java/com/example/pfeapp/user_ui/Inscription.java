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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pfeapp.BD.Data_Base;
import com.example.pfeapp.BD.User;
import com.example.pfeapp.R;
import com.example.pfeapp.client_ui.Background;

import static com.example.pfeapp.R.layout.inscription;
import static com.example.pfeapp.client_ui.Background.ip;

public class Inscription extends AppCompatActivity {

    private EditText nomET ,prenomET ,usernameET,emailET,pswET,confpsw;
    private Button inscrire;
    User user;
    Data_Base data_base;
    Background background;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(inscription);

        user=new User();

        data_base=new Data_Base(this);

        nomET= (EditText)findViewById(R.id.nom);
        prenomET= (EditText)findViewById(R.id.prenom);
        usernameET= (EditText)findViewById(R.id.username);
        emailET=(EditText)findViewById(R.id.mail);
        pswET=(EditText)findViewById(R.id.psw);
        confpsw=(EditText)findViewById(R.id.Confpsw);



        inscrire=(Button) findViewById(R.id.inscrire);
        inscrire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Go();
            }
        });


    }

    private void Go() {
        Background background=new Background(this);


        if(InternetAvailable()) {
            String email = emailET.getText().toString();
            String psw = pswET.getText().toString();
            String userName=usernameET.getText().toString();
            String name=nomET.getText().toString();
            String surname=prenomET.getText().toString();


            user.setEmail(email);
            user.setPsw(psw);
            user.setName(name);
            user.setSurname(surname);
            user.setUserName(userName);

            String type = "sign-in";
            String res;

            connect connect = new connect(this);
            connect.execute(type, name, surname, userName, email, psw).toString();


        }
        else{

            Toast.makeText(this, "aucune connexion Internet", Toast.LENGTH_LONG).show();

        }

    }


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
            result=b.request("sign-in",ip,"name",voids[1],"surname",voids[2],"username",voids[3],"email",voids[4],"psw",voids[5]);

            return result;
        }

        @Override
        protected void onPostExecute(String result) {

            String id=result.substring(0,3);

            if(id.equals("ID=")){

                id=result.substring(3);


                data_base.insertUser(id,user.getEmail(),user.getPsw(),user.getName(),user.getSurname(),user.getUserName());

                Intent intent = new Intent(context, Choix_session_inscr.class);
                intent.putExtra("User",  user);
                startActivity(intent);


            }
            else{

                dialog.setMessage(result);
                dialog.show();

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