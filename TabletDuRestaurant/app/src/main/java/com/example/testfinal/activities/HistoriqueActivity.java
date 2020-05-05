package com.example.testfinal.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.example.testfinal.R;
import com.example.testfinal.adapters.FragmentHistoriqueSwipeAdapter;
import com.example.testfinal.background.BackgroundHistoriqueData;
import com.example.testfinal.models.Commande;
import com.example.testfinal.models.Repas;
import java.util.ArrayList;
import java.util.Collections;

public class HistoriqueActivity extends AppCompatActivity implements BackgroundHistoriqueData.AsyncResponse {

    private Toolbar toolbar;
    private ViewPager pager;
    private FragmentPagerAdapter adapter;
    private TabLayout tab;
    public static ArrayList<Commande>commandes;
    public static ArrayList<Repas> repas;
    private  boolean b1=false,b2=false;

    public HistoriqueActivity()
    {
        commandes=new ArrayList<>();
        repas=new ArrayList<>();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historique);


        //GET MY WIDGETS
        toolbar = findViewById(R.id.toolbar_historique);

        pager = findViewById(R.id.view_pager_histo);
        tab = findViewById(R.id.tab_histo);

        //SETUP THE TOOLBAR

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //SET CALLBACK
        toolbar.setNavigationOnClickListener(clickBackListener);


        SharedPreferences preferences;

        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        BackgroundHistoriqueData background2 = new BackgroundHistoriqueData(this,this);

        background2.execute("get_user_repas",preferences.getString("id_client","no value"));

        BackgroundHistoriqueData background = new BackgroundHistoriqueData(this,this);

        background.execute("get_user_commandes",preferences.getString("id_client","no value"));

    }

    View.OnClickListener clickBackListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            startActivity(new Intent(getApplicationContext(), MainActivityOn.class));
            finish();

        }
    };

    @Override
    public void processFinish(Object result,String type) {

        if(type.equals("historique")) {
            commandes = (ArrayList<Commande>) result;
            Collections.sort(commandes);
            b1=true;
        }

        if(type.equals("repas")) {

            repas = (ArrayList<Repas>) result;

            b2=true;

        }

        if(type.equals("nonHistorique")){

            b1=true;
        }

        if(type.equals("nonRepas")){

            b2=true;
        }
        //set else :

        if(b1 && b2) {

            adapter = new FragmentHistoriqueSwipeAdapter(getSupportFragmentManager());

            pager.setAdapter(adapter);
            //setting tab
            tab.setupWithViewPager(pager);
        }
    }
}
