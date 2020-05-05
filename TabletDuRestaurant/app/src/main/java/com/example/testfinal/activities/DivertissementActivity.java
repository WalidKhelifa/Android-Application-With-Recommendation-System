package com.example.testfinal.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.testfinal.R;


public class DivertissementActivity extends AppCompatActivity {

    Button btnjouer,btnquitter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_divertissement);

        btnjouer = (Button) findViewById(R.id.buttonjouer);

        btnquitter = (Button) findViewById(R.id.buttonquitter);

        btnjouer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DivertissementActivity.this, JouerActivity.class);
                startActivity(i);
            }
        });
        btnquitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences preferences;

                preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

                if(preferences.contains("id_client")) {

                    Intent i = new Intent(DivertissementActivity.this, MainActivityOn.class);
                    startActivity(i);
                }
                else
                {
                    Intent i = new Intent(DivertissementActivity.this, MainActivity.class);
                    startActivity(i);
                }

            }
        });




    }
}