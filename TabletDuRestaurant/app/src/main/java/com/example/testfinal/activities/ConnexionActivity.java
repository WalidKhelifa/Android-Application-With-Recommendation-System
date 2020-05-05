package com.example.testfinal.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import com.example.testfinal.R;
import com.example.testfinal.background.BackgroundTaskLogin;
import com.example.testfinal.models.Client;


public class ConnexionActivity extends AppCompatActivity implements BackgroundTaskLogin.AsyncResponse{

    private TextInputEditText edituser, editpassword; //declare user name and password...
    private TextInputLayout layout_username,layout_password;
    private Button btnconnect;
    private Toolbar toolbar;
    private SharedPreferences preferences; //il permet de redefinir les parametre deja initialiser (memoire tempo)..
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion); //referencer le fichier XML

        ///////////////////////////////////////////// referancer les elements...

        btnconnect = findViewById(R.id.oval);
        edituser = findViewById(R.id.edit_username);
        editpassword =  findViewById(R.id.edit_password);
        toolbar = findViewById(R.id.goto_login);
        layout_username=findViewById(R.id.layout_username);
        layout_password=findViewById(R.id.layout_password);

        ////////////////// configurer la toolbar ...

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(clickBackListener); //button de retour

        ////// initialisation

        preferences= PreferenceManager.getDefaultSharedPreferences(this);
        editor=preferences.edit();

        //////////////////////////

        btnconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = edituser.getText().toString(); //recuperer l'infos introduite..
                String password = editpassword.getText().toString();

                if(errors()){

                    BackgroundTaskLogin background = new BackgroundTaskLogin(ConnexionActivity.this, ConnexionActivity.this);
                    background.execute("login",user,password);

                }

            }
        });

    }
    ///////////// le retour ...
    View.OnClickListener clickBackListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();

        }
    };

    @Override
    public void onBackPressed() {

        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }

    private  boolean errors()
    {
        boolean value=true;

        layout_username.setError(null);
        layout_password.setError(null);


        String user = edituser.getText().toString(); //recuperer l'infos introduite..
        String password = editpassword.getText().toString();


        if(password.length()<=3 && !password.isEmpty() )
        {
            layout_password.setError("Mot de passe incorrect");
            value= false;
        }

        if(password.isEmpty())
        {
            layout_password.setError("Saisissez un mot de passe");
            value= false;
        }

        if(user.isEmpty())
        {
            layout_username.setError("Saisissez un nom d'utilisateur");
            value= false;
        }

        if(user.length()<3 && !user.isEmpty())
        {
            layout_username.setError("Nom d'utilisateur incorrect");
            value=false;
        }


        return  value;
    }



    @Override
    public void processFinish(Object valeur,String code) {

        layout_username.setError(null);
        layout_password.setError(null);

            switch (code)
            {

                case "L1": {

                    Client client =(Client) valeur;

                    editor.putString("id_client",client.getIdentifier());
                    editor.putString("username",client.getUsername());
                    editor.putString("photo",client.getPhoto());
                    editor.apply();
                    // ouvrir une activite avec les donnees recuperee
                    startActivity(new Intent(getApplicationContext(), MainActivityOn.class));
                    finish();
                } break;

                case "L0": {


                    layout_password.setError("Mot de passe incorrect");

                } break;

                case "L00": {


                    layout_username.setError("Nom d'utilisateur incorrect");

                } break;

                case "L000": {


                    layout_password.setError("Mot de passe incorrect");
                    layout_username.setError("Nom d'utilisateur incorrect");


                } break;

            }

    }



}


