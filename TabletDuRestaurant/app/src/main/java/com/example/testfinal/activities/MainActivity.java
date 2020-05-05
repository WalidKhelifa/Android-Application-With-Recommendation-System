package com.example.testfinal.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import com.example.testfinal.R;
import com.example.testfinal.background.BackgroundMenuData;
import com.example.testfinal.models.Plat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BackgroundMenuData.AsyncResponse{
    private ImageButton btnconnexion,btninfo,btndivertissement,btnmenu,btncommande;
    private SharedPreferences preference;
    public static ArrayList<Plat> plats;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnconnexion =  findViewById(R.id.circle_button1);
        btninfo =findViewById(R.id.circle_button3);
        btndivertissement = findViewById(R.id.circle_button4);
        btnmenu =  findViewById(R.id.circle_button5);
        btncommande = findViewById(R.id.circle_button2);
        ///////////

        //recuperer le menu....
        SharedPreferences preferences;
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        BackgroundMenuData background = new BackgroundMenuData(MainActivity.this,getBaseContext());
        background.execute("menu_online", preferences.getString("id_client","no value"));


        preference = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        if(preference.contains("id_client"))
        {
            Intent i = new Intent(MainActivity.this, MainActivityOn.class);
            startActivity(i);
            finish();
        }

        btnconnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this,ConnexionActivity.class);
                startActivity(i);
                finish();
            }
        });


        btnmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,MenuActivityOff.class);
                startActivity(i);
                finish();
            }
        });
        //////////
        btninfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, InfosRestaurantActivity.class);
                startActivity(i);
                finish();
            }
        });
        ///////////
        btndivertissement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(MainActivity.this, DivertissementActivity.class);
                startActivity(i);
                finish();
            }
        });
        ////////
        btncommande.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(MainActivity.this, CommandeActivityOff.class);
                startActivity(i);
                finish();
            }
        });
    }

    @Override
    public void processFinish(Object result, String value) {

        plats = (ArrayList<Plat>) result;
    }
}
