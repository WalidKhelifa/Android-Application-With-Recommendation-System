package com.example.testfinal.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.testfinal.R;
import com.example.testfinal.background.BackgroundMenuData;
import com.example.testfinal.models.CircleTransform;
import com.example.testfinal.models.DialogOk;
import com.example.testfinal.models.Plat;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class MainActivityOn extends AppCompatActivity implements BackgroundMenuData.AsyncResponse {

   private ImageButton btninfo,btnmenu,btncommander,btnjeux,btnmescommandes;
    private NavigationView navig;
    private DrawerLayout drawer;
    private Toolbar tool;
    private ImageView image_profil;
    private TextView nom_profil;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    public static ArrayList<Plat> plats;
    private DialogOk dialogOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_on);
        btnmenu =  findViewById(R.id.circle_button2);
        btninfo =  findViewById(R.id.circle_button3);
        btnjeux =  findViewById(R.id.circle_button4);
        btnmescommandes =  findViewById(R.id.circle_button1);
        btncommander = findViewById(R.id.circle_button5);
        navig=findViewById(R.id.navigation);

        //recuperer le menu....
        SharedPreferences preferences;
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        BackgroundMenuData background = new BackgroundMenuData(MainActivityOn.this,getBaseContext());
        background.execute("menu_online", preferences.getString("id_client","no value"));

        dialogOk=new DialogOk(this,this,1);

        //WIDGET DU NAVIGATION VIEW ...

        nom_profil=navig.getHeaderView(0).findViewById(R.id.nom_profil);
        image_profil=navig.getHeaderView(0).findViewById(R.id.photo_profile);

        setNavigationInfos();

        //APPLIQUER LES CONFIGS DE LA TOOLBAR + NAVIGATION VIEW ...

        this.configureToolBar();
        this.configureDrawerLayout();
        navig.setNavigationItemSelectedListener(navigate);

        btncommander.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivityOn.this, CommandeActivity.class);
                startActivity(i);
            }
        });

        btnmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivityOn.this, MenuActivity.class);
                startActivity(i);
            }
        });
        btninfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivityOn.this, InfosRestaurantActivity.class);
                startActivity(i);


            }
        });
        btnmescommandes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivityOn.this, HistoriqueActivity.class);
                startActivity(i);
            }
        });
        btnjeux.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivityOn.this, DivertissementActivity.class);
                startActivity(i);
            }
        });
    }

    private void setNavigationInfos()
    {

        SharedPreferences preferences;

        preferences = PreferenceManager.getDefaultSharedPreferences(this);


        nom_profil.setText(preferences.getString("username","nothing"));

        if(preferences.contains("photo"))
        Picasso.get().load(preferences.getString("photo","nothing")).transform(new CircleTransform()).into(image_profil);

    }

    //FERME LE NAVIGATIOR QUAND ON CLIQUE SUR UN ITEM ...
    @Override
    public void onBackPressed() {

        // 5 - Handle back click to close menu
        if (this.drawer.isDrawerOpen(GravityCompat.START)) {
            this.drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //CONFIGURER THE TOOLBAR ...
    private void configureToolBar(){

        this.tool =findViewById(R.id.goto_navigation);
        setSupportActionBar(tool);
    }

    // 2 - CONFIGURE THE DRAWER LAYOUT TO ADD THE HUMBERGER BUTTON IN TOOLBAR ...
    private void configureDrawerLayout(){

        this.drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle =new ActionBarDrawerToggle(this,drawer,tool, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        // cette classe permet de lier DrawerLayout et le cadre ActionBar
        drawer.addDrawerListener(toggle);
        toggle.syncState();

    }

    //CONFIGURER LA SELECTION DES ITEMS ...
    private NavigationView.OnNavigationItemSelectedListener navigate = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            menuItem.setChecked(true);
            //TO HANDLE THEM A SWITCH CASE
            switch (menuItem.getItemId())
            {

                case R.id.goto_historique:{

                    startActivity(new Intent(getApplicationContext(), HistoriqueActivity.class));
                    finish();

                }break;

                case R.id.goto_menu:{

                    startActivity(new Intent(getApplicationContext(), MenuActivity.class));
                    finish();

                }break;

                case R.id.goto_commande:{

                    startActivity(new Intent(getApplicationContext(), CommandeActivity.class));
                    finish();

                }break;

                case R.id.goto_restaurant_infos:{

                    startActivity(new Intent(getApplicationContext(), InfosRestaurantActivity.class));
                    finish();

                }break;

                case R.id.goto_jouer:{

                    startActivity(new Intent(getApplicationContext(), DivertissementActivity.class));
                    finish();

                }break;

                case R.id.goto_quitter:{

                    dialogOk.execute("Déconnexion Réussie");

                    removeSharedPreference();

                    dialogOk.getOk().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            dialogOk.close();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finish();
                        }
                    });

                }break;

            }
            drawer.closeDrawer(GravityCompat.START);
            return true ;

        }
    };


    // LOG OUT ...

    private void removeSharedPreference()
    {

        preferences= PreferenceManager.getDefaultSharedPreferences(this);
        editor=preferences.edit();

        if(preferences.contains("id_client"))
        {
            editor.remove("id_client");
            editor.remove("username");
            editor.remove("photo");
            editor.apply();

        }

    }

    @Override
    public void processFinish(Object result, String value) {

        plats = (ArrayList<Plat>) result;
    }
}
