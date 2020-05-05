package com.example.testfinal.fragments;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import com.example.testfinal.R;
import com.example.testfinal.activities.CommandeActivity;
import com.example.testfinal.activities.CommandeActivityOff;
import com.example.testfinal.activities.HistoriqueActivity;
import com.example.testfinal.adapters.ListeCommandePlatsAdapter;
import com.example.testfinal.background.BackgroundMenuData;
import com.example.testfinal.models.Commande;
import com.example.testfinal.models.DialogOk;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

/**
 * A simple {@link Fragment} subclass.
 */
public class ValiderCommande extends DialogFragment implements BackgroundMenuData.AsyncResponse {

    private static Commande commande;
    private ImageButton close;
    private Button annuler,valider;
    private RecyclerView recyclerView;
    private ListeCommandePlatsAdapter adapter;
    private RecyclerView.LayoutManager manager;
    private TextView prix_total;
    public static ArrayList<Commande>list_commandes;
    static int x;


    public ValiderCommande() {

        list_commandes= HistoriqueActivity.commandes;


    }

    public static void display(FragmentManager fragmentManager, Commande c,int y) {

        x=y;
        ValiderCommande dialog= new ValiderCommande();
        dialog.show(fragmentManager,"validerCommande");
        commande=new Commande(c);
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_valider_commande, container, false);

        // GET WIDGETS


        close=v.findViewById(R.id.close_valider);
        valider=v.findViewById(R.id.valider_commande);
        annuler=v.findViewById(R.id.annuler_commande);
        recyclerView=v.findViewById(R.id.liste_commande);
        prix_total=v.findViewById(R.id.prix_total);


        //Show le prix
        prix_total.setText(commande.calculerMontant());
        prix_total.append("Da");



        //set recycler view

        manager=new LinearLayoutManager(getContext());
        adapter= new ListeCommandePlatsAdapter(commande.getPlats_commandes());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        //set listeners

        close.setOnClickListener(closeListener);
        annuler.setOnClickListener(closeListener);
        valider.setOnClickListener(validerListener);

        adapter.setOnItemClickListener(new ListeCommandePlatsAdapter.OnItemCLickListener() {
            @Override
            public void onItemClick(int position) {

                commande.getPlats_commandes().remove(position);


                if (commande.getPlats_commandes().isEmpty()){

                    dismiss();

                }

                if(  (CommandeActivity.commande!=null && !CommandeActivity.commande.getPlats_commandes().isEmpty() )){

                    CommandeActivity.commande.getPlats_commandes().remove(position);
                }

                if(  (CommandeActivityOff.commande!=null && !CommandeActivityOff.commande.getPlats_commandes().isEmpty() )){

                    CommandeActivityOff.commande.getPlats_commandes().remove(position);
                }

                adapter.notifyItemRemoved(position);
                prix_total.setText(commande.calculerMontant());
                prix_total.append("Da");


            }
        });

        return v;
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





    View.OnClickListener validerListener= new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            if(!commande.getPlats_commandes().isEmpty()) {

                if (x!= 0) {

                    Calendar calendar = Calendar.getInstance();

                    SimpleDateFormat dformat = new SimpleDateFormat("yyyy/MM/dd");
                    SimpleDateFormat tformat = new SimpleDateFormat("HH:mm");
                    commande.setDate(dformat.format(calendar.getTime()));
                    commande.setHeure(tformat.format(calendar.getTime()));
                    commande.setMontant(commande.calculerMontant());

                    if (list_commandes != null && !list_commandes.isEmpty()) {

                        list_commandes.add(new Commande(commande));
                        Collections.sort(list_commandes);
                        HistoriqueFragment.adapter.notifyDataSetChanged();
                    }


                    SharedPreferences preferences;
                    preferences = PreferenceManager.getDefaultSharedPreferences(getContext());


                    BackgroundMenuData background = new BackgroundMenuData(ValiderCommande.this, getContext());

                    background.execute("add_commande", preferences.getString("id_client", "no value"), commande);

                }else
                {
                    Calendar calendar = Calendar.getInstance();

                    SimpleDateFormat dformat = new SimpleDateFormat("yyyy/MM/dd");
                    SimpleDateFormat tformat = new SimpleDateFormat("HH:mm");
                    commande.setDate(dformat.format(calendar.getTime()));
                    commande.setHeure(tformat.format(calendar.getTime()));
                    commande.setMontant(commande.calculerMontant());

                    if (list_commandes != null && !list_commandes.isEmpty()) {

                        list_commandes.add(new Commande(commande));
                        Collections.sort(list_commandes);
                        HistoriqueFragment.adapter.notifyDataSetChanged();
                    }



                    BackgroundMenuData background = new BackgroundMenuData(ValiderCommande.this, getContext());

                    background.execute("add_commande_off", commande);


                }


            }
            else {

                if(getActivity().getClass()== CommandeActivity.class || getActivity().getClass()== CommandeActivityOff.class){

                    DialogOk d3=new DialogOk(getActivity(),getActivity(),2);
                    d3.execute("Selectionnez d'abord un plat... !");
                    d3.closeListener();

                }

            }
        }
    };

    @Override
    public void processFinish(Object result,String value) {

        String answer = (String)result;




        if(getActivity().getClass()==HistoriqueActivity.class)
        {


            DialogOk d1 =new DialogOk(getActivity(),getActivity(),1);
            d1.execute(answer);
            d1.closeListener();


        }

        if(getActivity().getClass()== CommandeActivity.class || getActivity().getClass()== CommandeActivityOff.class){


            DialogOk d2 =new DialogOk(getActivity(),getActivity(),1);
            d2.execute(answer);
            d2.closeListener();

        }


        dismiss();
        if(getActivity().getClass()== CommandeActivity.class ) {
            CommandeActivity.commande.getPlats_commandes().clear();
        }
        if(getActivity().getClass()== CommandeActivityOff.class )
        { CommandeActivityOff.commande.getPlats_commandes().clear();}

    }

}
