package com.example.testfinal.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.example.testfinal.adapters.FragmentCommandeSwipeAdapter;
import com.example.testfinal.R;
import com.example.testfinal.fragments.ValiderCommande;
import com.example.testfinal.models.Commande;
import com.example.testfinal.models.DialogOk;
import com.example.testfinal.models.Plat;
import java.util.ArrayList;
import java.util.Collections;

public class CommandeActivityOff extends AppCompatActivity {
    private Toolbar toolbar;
    private ViewPager pager;
    private FragmentPagerAdapter adapter;
    private TabLayout tab;
    public static ArrayList<Plat> plats =new ArrayList<>();
    public static ArrayList<Plat> desserts=new ArrayList<>();
    public static ArrayList<Plat> entrees=new ArrayList<>();
    public static ArrayList<Plat> boissons=new ArrayList<>();
    private   ArrayList<Plat> p;
    public static Commande commande;
    public static FloatingActionButton valider;
    private DialogOk dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commande_off);

        //Initialiser la commande
        commande=new Commande();

        // Referencer les elements ...
        pager = findViewById(R.id.view_pager_commande);
        tab = findViewById(R.id.tab_commande);
        toolbar = findViewById(R.id.toolbar_commande);
        valider=findViewById(R.id.valider_commande);


        dialog = new DialogOk(this,this,2);


        /// Configurer la toolbar ...
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // Configurer le view Pager ...
        adapter = new FragmentCommandeSwipeAdapter(getSupportFragmentManager(),1);
        pager.setAdapter(adapter);
        tab.setupWithViewPager(pager);



        toolbar.setNavigationOnClickListener(clickBackListener);
        valider.setOnClickListener(validerListener);

        p = MainActivity.plats;
        getMenu();// ajouter les plats dans les listes correspandantes ...
    }


    View.OnClickListener clickBackListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();

        }
    };

    View.OnClickListener validerListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if(!commande.getPlats_commandes().isEmpty()){

                ValiderCommande.display(getSupportFragmentManager(),commande,0);

            }
            else{

                dialog.execute("Selectionnez d'abord un plat...!");

                dialog.closeListener();
            }

        }
    };



    private void getMenu() {

        entrees.clear();
        desserts.clear();
        plats.clear();
        boissons.clear();
        for (Plat plat : p) {

            switch (plat.getCategorie())
            {
                case "entree": entrees.add(plat); break;
                case "plat": plats.add(plat); break;
                case "dessert": desserts.add(plat); break;
                case "boisson":   boissons.add(plat); break;
            }

        }

        Collections.sort(entrees);
        Collections.sort(plats);
        Collections.sort(desserts);
        Collections.sort(boissons);
    }

}
