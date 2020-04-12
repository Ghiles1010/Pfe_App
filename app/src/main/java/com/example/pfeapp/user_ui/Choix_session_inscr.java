package com.example.pfeapp.user_ui;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pfeapp.BD.Data_Base;
import com.example.pfeapp.BD.User;
import com.example.pfeapp.R;
import com.example.pfeapp.client_ui.Background;
import com.example.pfeapp.prest_ui.Inscription_prest;

import static com.example.pfeapp.R.layout.choix_type_session;
import static com.example.pfeapp.client_ui.Background.ip;

public class Choix_session_inscr extends AppCompatActivity {


    Button prest, client;

    Data_Base db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(choix_type_session);

        db=new Data_Base(this);

        prest = findViewById(R.id.prest);
        client=findViewById(R.id.client);



        prest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Go_Prest();


            }
        });




    }






    public void Go_Prest(){

        User user;

        user=db.getUser().get(0);

        connect connect = new connect(this);
        connect.execute(user.getId());
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




            Background b = new Background();
            result=b.request("add_prest", ip, "id_user",voids[0]);




            return result;
        }

        @Override
        protected void onPostExecute(String result) {

            String id = result.substring(0, 3);

            if (id.equals("ID=")) {

                String [] values;
                values = result.substring(3).split(" ");


                db.insertPrest(values[1],values[0]);

                Intent intent = new Intent(context, Inscription_prest.class);
                startActivity(intent);


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
