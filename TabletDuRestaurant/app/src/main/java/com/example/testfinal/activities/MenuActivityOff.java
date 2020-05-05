package com.example.testfinal.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.example.testfinal.R;
import com.example.testfinal.adapters.FragmentSwipeAdapter;
import com.example.testfinal.models.Plat;
import java.util.ArrayList;
import java.util.Collections;

public class MenuActivityOff extends AppCompatActivity  {

    private Toolbar toolbar;
    private ViewPager pager;
    private FragmentPagerAdapter adapter;
    private TabLayout tab;
    public static ArrayList<Plat>  plats =new ArrayList<>();
    public static ArrayList<Plat> desserts=new ArrayList<>();
    public static ArrayList<Plat> entrees=new ArrayList<>();
    public static ArrayList<Plat> boissons=new ArrayList<>();
    private   ArrayList<Plat> p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);



        // Referencer les elements ...
        pager = findViewById(R.id.view_pager);
        tab = findViewById(R.id.tab);
        toolbar = findViewById(R.id.toolbar);

        // Configurer la toolbar ...
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(clickBackListener); //button de retour

        p = MainActivity.plats;

        getMenu(); // ajouter les plats dans les listes correspandantes ...

        // Configurer le view Pager ...
        adapter = new FragmentSwipeAdapter(getSupportFragmentManager(),1);
        pager.setAdapter(adapter);
        tab.setupWithViewPager(pager);

    }




    // retour
    View.OnClickListener clickBackListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();

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