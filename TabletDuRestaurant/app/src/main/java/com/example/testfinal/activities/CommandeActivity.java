package com.example.testfinal.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import com.example.testfinal.adapters.RecommanderAdapter;
import com.example.testfinal.background.BackgroundRecommander;
import com.example.testfinal.adapters.FragmentCommandeSwipeAdapter;
import com.example.testfinal.R;
import com.example.testfinal.fragments.AddToChart;
import com.example.testfinal.fragments.MenuDetailsFragment;
import com.example.testfinal.fragments.ValiderCommande;
import com.example.testfinal.models.Commande;
import com.example.testfinal.models.DialogOk;
import com.example.testfinal.models.Plat;
import com.example.testfinal.models.Recommander;
import java.util.ArrayList;
import java.util.Collections;

public class CommandeActivity extends AppCompatActivity implements BackgroundRecommander.AsyncResponse {
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
    public  static FloatingActionButton valider;
    private Boolean info1=false,info2=false;
    private ArrayList<Commande> commandes_client;
    private ArrayList<Plat> menu_filtre;
    private RecyclerView recycler;
    private RecommanderAdapter adapter2;
    private ImageButton left,right;
    private LinearLayoutManager layoutManager;
    private DialogOk dialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //GET LISTES POUR RECOMMANDATION

        SharedPreferences preferences;
        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        BackgroundRecommander recommander =new BackgroundRecommander(this,this);
        recommander.execute("commandes_recommander", preferences.getString("id_client","no value"));

        BackgroundRecommander recommander2 =new BackgroundRecommander(this,this);
        recommander2.execute("menu_recommander", preferences.getString("id_client","no value"));

       dialog = new DialogOk(this,this,2);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commande);

        //Initialiser la commande
        commande=new Commande();

        // Referencer les elements ...
        pager = findViewById(R.id.view_pager_commande);
        tab = findViewById(R.id.tab_commande);
        toolbar = findViewById(R.id.toolbar_commande);
        valider=findViewById(R.id.valider_commande);
        recycler=findViewById(R.id.recycler_recommander);
        left=findViewById(R.id.left_arrow);
        right=findViewById(R.id.right_arrow);


        /// Configurer la toolbar ...
        setSupportActionBar(toolbar);
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // Configurer le view Pager ...
        adapter = new FragmentCommandeSwipeAdapter(getSupportFragmentManager(),0);
        pager.setAdapter(adapter);
        tab.setupWithViewPager(pager);


        //Configure the recycler of the recommander sys


        toolbar.setNavigationOnClickListener(clickBackListener);
        valider.setOnClickListener(validerListener);


        p = MainActivityOn.plats;

        getMenu();// ajouter les plats dans les listes correspandantes ...

    }


    View.OnClickListener clickBackListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            startActivity(new Intent(getApplicationContext(), MainActivityOn.class));
            finish();

        }
    };

    View.OnClickListener validerListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if(!commande.getPlats_commandes().isEmpty()){

                ValiderCommande.display(getSupportFragmentManager(),commande,1);

            }
            else{

                dialog.execute("Selectionnez d'abord un plat...!");

                dialog.closeListener();
            }

        }
    };


    View.OnClickListener rightListener= new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            recycler.smoothScrollToPosition(layoutManager.findLastVisibleItemPosition() + 1);
        }
    };

    View.OnClickListener leftListener= new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (layoutManager.findFirstVisibleItemPosition() > 0) {

              recycler.smoothScrollToPosition(layoutManager.findFirstVisibleItemPosition() - 1);

            } else {

                recycler.smoothScrollToPosition(0);
            }
        }
    };


    //TRIE LE MENU RAMENE DE LA BD
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

    @Override
    public void processFinish(Object result, String value) {

        if (value.equals("menu")){

            info1=true;
            menu_filtre= new ArrayList<>((ArrayList<Plat>)result);

        }

        if(value.equals("commandes")){

            info2=true;
            commandes_client= new ArrayList<>((ArrayList<Commande>)result);
        }

        if(info1 && info2){

            if( menu_filtre!=null && !menu_filtre.isEmpty()) {

                Recommander recommander = new Recommander(this,commandes_client,menu_filtre);
                ArrayList<Plat> platsRecommande = recommander.execute();

                adapter2 = new RecommanderAdapter(this,platsRecommande);

                //SETTING THE RECYCler
                recycler.setHasFixedSize(true);
                //SETTING THE NUMBER OF COLUMNS OF THE GRID

                layoutManager=new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

                recycler.setLayoutManager(layoutManager);

                recycler.setAdapter(adapter2);


                left.setOnClickListener(leftListener);
                right.setOnClickListener(rightListener);

                adapter2.setOnItemClickListener(new RecommanderAdapter.OnItemCLickListener() {
                    @Override
                    public void onItemClick(int position, ArrayList<Plat> p, ImageButton add) {

                        AddToChart.display(getSupportFragmentManager(),p.get(position),commande);

                    }

                    @Override
                    public void onDetailsClick(int position, ArrayList<Plat> p) {

                        MenuDetailsFragment.display(getSupportFragmentManager(),p.get(position));
                    }
                });
            }

        }

    }
}
