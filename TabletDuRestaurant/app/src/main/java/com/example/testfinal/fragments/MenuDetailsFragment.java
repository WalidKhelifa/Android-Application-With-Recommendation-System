package com.example.testfinal.fragments;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.testfinal.models.CircleTransform;
import com.example.testfinal.R;
import com.example.testfinal.background.BackgroundMenuData;
import com.example.testfinal.models.Plat;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MenuDetailsFragment extends DialogFragment implements BackgroundMenuData.AsyncResponse {

    private static String TAG="exemple_dialog";
    private Button ing,cal,allerg;
    private TextView contenu,nom,tempsC,modeC;
    private ImageButton close;
    private static Plat plat;
    private ImageView imagePlat;
    private static String ingredients,allergies,calories;


    public static void display(FragmentManager fragmentManager, Plat p) {
        MenuDetailsFragment dialog= new MenuDetailsFragment();
        plat=p;

        allergies="";
        calories="";
        ingredients="";

        dialog.show(fragmentManager, TAG);

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //La je call pour avoir les données
        BackgroundMenuData backgroundMenuData = new BackgroundMenuData(this,getContext());
        backgroundMenuData.execute("details",plat.getId_plat());

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        View v=inflater.inflate(R.layout.fragment_menu_details,container,false);

        //GET MY WIDGETS

        close=v.findViewById(R.id.close_button);
        //INFOS SUR LE PLATS
        nom=v.findViewById(R.id.nom_plat);
        imagePlat=v.findViewById(R.id.image_plat);
        Picasso.get().load(plat.getPicture()).transform(new CircleTransform()).into(imagePlat);
        contenu=v.findViewById(R.id.contenu);
        modeC=v.findViewById(R.id.mode_cuisson_bd);
        tempsC=v.findViewById(R.id.temps_cuisson_bd);

        //LES BOUTONS
        ing=v.findViewById(R.id.button_ing);
        allerg=v.findViewById(R.id.button_allerg);
        cal=v.findViewById(R.id.button_cal);


        setData();


        //SET LISTENERS

        close.setOnClickListener(closeListener);


        ing.setOnClickListener(buttonClick);
        cal.setOnClickListener(buttonClick);
        allerg.setOnClickListener(buttonClick);

        return  v;
    }

    @Override
    public void onResume() {

        super.onResume();
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        getDialog().getWindow().setAttributes((WindowManager.LayoutParams) params);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

    }


    View.OnClickListener closeListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dismiss();
        }
    };

    private  void setData()
    {
        nom.setText(plat.getDesignation());
        modeC.setText(plat.getModeC());
        tempsC.setText(plat.getTempsC());
    }

    View.OnClickListener buttonClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId())
            {

                case R.id.button_allerg:{

                    allerg.setBackgroundResource(R.drawable.button_onglet_clicked);
                    allerg.setTextColor(getResources().getColor(R.color.white));

                    ing.setBackgroundResource(R.drawable.button_onglet_not_clicked);
                    ing.setTextColor(getResources().getColor(R.color.rose_saumon));

                    cal.setBackgroundResource(R.drawable.button_onglet_not_clicked);
                    cal.setTextColor(getResources().getColor(R.color.rose_saumon));

                    contenu.setText(allergies);

                    break;
                }

                case R.id.button_ing:{

                    ing.setBackgroundResource(R.drawable.button_onglet_clicked);
                    ing.setTextColor(getResources().getColor(R.color.white));

                    allerg.setBackgroundResource(R.drawable.button_onglet_not_clicked);
                    allerg.setTextColor(getResources().getColor(R.color.rose_saumon));

                    cal.setBackgroundResource(R.drawable.button_onglet_not_clicked);
                    cal.setTextColor(getResources().getColor(R.color.rose_saumon));

                    contenu.setText(ingredients);

                } break;

                case R.id.button_cal:{

                    cal.setBackgroundResource(R.drawable.button_onglet_clicked);
                    cal.setTextColor(getResources().getColor(R.color.white));

                    ing.setBackgroundResource(R.drawable.button_onglet_not_clicked);
                    ing.setTextColor(getResources().getColor(R.color.rose_saumon));

                    allerg.setBackgroundResource(R.drawable.button_onglet_not_clicked);
                    allerg.setTextColor(getResources().getColor(R.color.rose_saumon));
                    contenu.setText(calories);

                }break;

            }

        }
    };

    @Override
    public void processFinish(Object result,String value) {


        ArrayList<String> details = (ArrayList<String>) result;
        if(details!=null) {

            ingredients=details.get(0);

            if(details.get(1).isEmpty()){

                allergies="Aucun";
            }
            else{

                allergies=details.get(1);

            }

            calories=details.get(2);

            contenu.setText(ingredients);
        }

    }

}
