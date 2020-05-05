package com.example.testfinal.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.example.testfinal.R;
import com.example.testfinal.adapters.FragmentSelectionRepasSwipeAdapter;
import com.example.testfinal.models.Commande;
import com.example.testfinal.models.DialogOk;
import com.example.testfinal.models.Plat;
import java.util.ArrayList;
import java.util.Collections;


public class SelectionRepasActivity extends AppCompatActivity {

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
    private DialogOk dialog;


        public SelectionRepasActivity()
        {
            commande=new Commande();

        }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_repas);


        pager = findViewById(R.id.view_pager_repas);
        tab = findViewById(R.id.tab_repas);
        toolbar=findViewById(R.id.toolbar_repas);

        //SETUP TOOLBAR
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //GETTING THE MENU FROM SERVER

        p =MainActivityOn.plats;

        getMenu();


        dialog = new DialogOk(this,this,2);
        //SETTING PAGER
        adapter = new FragmentSelectionRepasSwipeAdapter(getSupportFragmentManager());

        pager.setAdapter(adapter);

        //setting tab
        tab.setupWithViewPager(pager);

        //SET CALLBACK
        toolbar.setNavigationOnClickListener(clickBackListener);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.profile_activity_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==R.id.save) {


            if(commande!=null && !commande.getPlats_commandes().isEmpty()){


                Intent result = new Intent();
                setResult(RESULT_OK, result);
                finish();

            }
            else
            {
                dialog.execute("Selectionnez d'abord un plat... !");
                dialog.closeListener();
            }

        }
        return true;
    }


    View.OnClickListener clickBackListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(getApplicationContext(), HistoriqueActivity.class));
            finish();
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


}
