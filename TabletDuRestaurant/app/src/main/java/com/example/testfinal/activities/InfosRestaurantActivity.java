package com.example.testfinal.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.testfinal.R;
import com.example.testfinal.background.BackgroundMenuData;
import com.example.testfinal.models.Avis;
import com.example.testfinal.models.DialogOk;
import com.example.testfinal.models.Restaurant;
import com.squareup.picasso.Picasso;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class InfosRestaurantActivity extends AppCompatActivity implements BackgroundMenuData.AsyncResponse {


    private Toolbar toolbar;
    private TextView horraire,email,tel,opened_closed,desc,noter_text;
    private EditText comment;
    private ImageView photo_resto;
    public static Avis avis;
    private Button noter;
    private ImageButton star1,star2,star3,star4,star5;
    private SharedPreferences preferences;
    private  String number="1";

    static {

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);


        BackgroundMenuData background= new BackgroundMenuData(this,this);
        background.execute("info_resto");


        //Referancer les elements ...

        toolbar=findViewById(R.id.toolbar_resto);
        horraire=findViewById(R.id.horaire_bd);
        email=findViewById(R.id.email_resto_bd);
        tel=findViewById(R.id.num_tel_resto_bd);
        opened_closed=findViewById(R.id.closed_opened);
        photo_resto=findViewById(R.id.image_resto);
        desc=findViewById(R.id.description_resto);
        noter=findViewById(R.id.noter);
        star1=findViewById(R.id.star1);
        star2=findViewById(R.id.star2);
        star3=findViewById(R.id.star3);
        star4=findViewById(R.id.star4);
        star5=findViewById(R.id.star5);
        comment= findViewById(R.id.comment);
        noter_text= findViewById(R.id.textNoter);



        // Configurer la Toolbar ...

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(clickBackListener);
        noter.setOnClickListener(validerListener);

        preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());


        //if noter
        if(!preferences.getString("id_client","nothing").equals("nothing")){


            star1.setVisibility(View.VISIBLE);
            star2.setVisibility(View.VISIBLE);
            star3.setVisibility(View.VISIBLE);
            star4.setVisibility(View.VISIBLE);
            star5.setVisibility(View.VISIBLE);
            noter.setVisibility(View.VISIBLE);
            comment.setVisibility(View.VISIBLE);
            noter_text.setVisibility(View.VISIBLE);
            //LISTENERS

            star1.setOnClickListener(selectStarListener);
            star2.setOnClickListener(selectStarListener);
            star3.setOnClickListener(selectStarListener);
            star4.setOnClickListener(selectStarListener);
            star5.setOnClickListener(selectStarListener);

        }
        else{

            star1.setVisibility(View.GONE);
            star2.setVisibility(View.GONE);
            star3.setVisibility(View.GONE);
            star4.setVisibility(View.GONE);
            star5.setVisibility(View.GONE);
            noter.setVisibility(View.GONE);
            comment.setVisibility(View.INVISIBLE);
            noter_text.setVisibility(View.GONE);
        }



    }

    @Override
    public void onBackPressed() {

        if(preferences.contains("id_client"))
        {
            startActivity(new Intent(getApplicationContext(),MainActivityOn.class));
        }
        else{

            startActivity(new Intent(getApplicationContext(),MainActivity.class));

        }
        finish();
    }


    View.OnClickListener clickBackListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

           if(preferences.contains("id_client"))
           {
               startActivity(new Intent(getApplicationContext(),MainActivityOn.class));
           }
           else{

               startActivity(new Intent(getApplicationContext(),MainActivity.class));

           }
            finish();

        }
    };


    View.OnClickListener selectStarListener= new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()){

                case R.id.star1 :{

                    star1.setImageResource(R.drawable.ic_star);
                    star2.setImageResource(R.drawable.ic_star_border);
                    star3.setImageResource(R.drawable.ic_star_border);
                    star4.setImageResource(R.drawable.ic_star_border);
                    star5.setImageResource(R.drawable.ic_star_border);
                    number="1";

                } break;

                case R.id.star2 :{

                    star1.setImageResource(R.drawable.ic_star);
                    star2.setImageResource(R.drawable.ic_star);
                    star3.setImageResource(R.drawable.ic_star_border);
                    star4.setImageResource(R.drawable.ic_star_border);
                    star5.setImageResource(R.drawable.ic_star_border);
                    number="2";
                } break;

                case R.id.star3 :{

                    star1.setImageResource(R.drawable.ic_star);
                    star2.setImageResource(R.drawable.ic_star);
                    star3.setImageResource(R.drawable.ic_star);
                    star4.setImageResource(R.drawable.ic_star_border);
                    star5.setImageResource(R.drawable.ic_star_border);
                    number="3";
                } break;

                case R.id.star4 :{

                    star1.setImageResource(R.drawable.ic_star);
                    star2.setImageResource(R.drawable.ic_star);
                    star3.setImageResource(R.drawable.ic_star);
                    star4.setImageResource(R.drawable.ic_star);
                    star5.setImageResource(R.drawable.ic_star_border);
                    number="4";

                } break;

                case R.id.star5 :{

                    star1.setImageResource(R.drawable.ic_star);
                    star2.setImageResource(R.drawable.ic_star);
                    star3.setImageResource(R.drawable.ic_star);
                    star4.setImageResource(R.drawable.ic_star);
                    star5.setImageResource(R.drawable.ic_star);
                    number="5";

                } break;

            }
        }
    };
    View.OnClickListener validerListener= new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            avis = new Avis();

            Calendar calendar = Calendar.getInstance();

            SimpleDateFormat dformat = new SimpleDateFormat("yyyy/MM/dd");
            SimpleDateFormat tformat = new SimpleDateFormat("HH:mm");

            avis.setDate_avis(dformat.format(calendar.getTime()));
            avis.setHeure_avis(tformat.format(calendar.getTime()));
            if(!comment.getText().toString().isEmpty())avis.setComment(comment.getText().toString());
            avis.setNb_etoile(number);


            BackgroundMenuData background = new BackgroundMenuData(InfosRestaurantActivity.this,getBaseContext());


            if(preferences.contains("id_client")){

                background.execute("add_avis",preferences.getString("id_client","no value"), avis);
            }
            else{

                background.execute("add_avis","null",avis);

            }




        }

    };

    @Override
    public void processFinish(Object result,String value) {

        if (value.equals("infos")) {

            Restaurant restaurant = (Restaurant) result;

            String heure = "ouvert de : " + restaurant.getOuverture() + "h à " + restaurant.getFermeture() + "h";

            Calendar calendar = Calendar.getInstance();

            Date h1=null,h2=null,h3=null ;

            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            String now = sdf.format(calendar.getTime());

            try {


                 h1 = sdf.parse(restaurant.getOuverture());
                 h2 = sdf.parse("23:59");
                 h3 = sdf.parse(now);

            } catch (ParseException ex) {


                Log.v("Exception", ex.getLocalizedMessage());
            }

            horraire.setText(heure);

           if(h3.before(h2) && h3.after(h1) ){

                horraire.setTextColor(getResources().getColor(R.color.colorPrimary));
                opened_closed.setText("le restaurant est ouvert");
                opened_closed.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
            else{

                horraire.setTextColor(getResources().getColor(R.color.red_win));
               opened_closed.setText("le restaurant est fermé");
                opened_closed.setTextColor(getResources().getColor(R.color.red_win));

            }

            desc.setText(restaurant.getDescription());
            email.setText(restaurant.getEmail());
            tel.setText(restaurant.getTel());

            if (!restaurant.getPhoto().isEmpty())
                Picasso.get().load(restaurant.getPhoto()).into(photo_resto);

        }

        if (value.equals("avis"))
        {
            String answer = (String)result;

            DialogOk dialog = new DialogOk(this,this,1);
            dialog.execute(answer);
            dialog.closeListener();

        }

    }

}
