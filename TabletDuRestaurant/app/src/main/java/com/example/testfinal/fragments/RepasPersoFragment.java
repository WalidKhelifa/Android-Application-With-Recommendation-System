package com.example.testfinal.fragments;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.example.testfinal.R;
import com.example.testfinal.activities.SelectionRepasActivity;
import com.example.testfinal.activities.HistoriqueActivity;
import com.example.testfinal.adapters.ListeRepasPersoAdapter;
import com.example.testfinal.background.BackgroundHistoriqueData;
import com.example.testfinal.models.Commande;
import com.example.testfinal.models.DialogOk;
import com.example.testfinal.models.Plat;
import com.example.testfinal.models.Repas;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class RepasPersoFragment extends Fragment implements BackgroundHistoriqueData.AsyncResponse {

    private FloatingActionButton ajouter;
    private Button cancel,ok;
    private TextInputEditText nom_repas_remplacer;
    private RecyclerView recyclerView;
    private static ListeRepasPersoAdapter adapter;
    private RecyclerView.LayoutManager manager;
    private ArrayList<Repas> repas;
    private static Commande commande;
    private AlertDialog dialog;
    private View dialogView;
    private Repas r;
    private TextView name,no_repas;
    private int p=0,cas;
    private TextInputLayout layout_name;

    public RepasPersoFragment() {

        repas= HistoriqueActivity.repas;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_repas_perso, container, false);

       //GET MY WIDGETS
        ajouter=v.findViewById(R.id.add_repas);
        recyclerView=v.findViewById(R.id.recycler_repas_perso);


        //initialize and set AlertDialog

        dialog=new AlertDialog.Builder(getContext()).create();
        LayoutInflater inf = this.getLayoutInflater();
        dialog.setCanceledOnTouchOutside(false);
        dialogView = inf.inflate(R.layout.name_repas_dialog_layout, null);

        cancel=dialogView.findViewById(R.id.annuler_nom_repas);
        ok=dialogView.findViewById(R.id.valider_nom_repas);
        nom_repas_remplacer=dialogView.findViewById(R.id.nom_repas_replace);
        layout_name=dialogView.findViewById(R.id.nom_repas_layout);
        no_repas=v.findViewById(R.id.no_repas);


        if (repas.isEmpty()){

            no_repas.setVisibility(View.VISIBLE);
        }

        //set recycler view

        manager = new GridLayoutManager(v.getContext(),5);
        adapter= new ListeRepasPersoAdapter(repas);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        //set listeners

        adapter.setOnItemClickListener(itemCLickListener);
        ajouter.setOnClickListener(addListener);
        cancel.setOnClickListener(cancelClickListener);
        ok.setOnClickListener(okClickListener);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {

                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0 ) {

                    ajouter.hide();

                } else if (dy < 0) {

                    ajouter.show();
                }
            }
        });

        return v;
    }

    ListeRepasPersoAdapter.OnItemCLickListener itemCLickListener= new ListeRepasPersoAdapter.OnItemCLickListener(){

        @Override
        public void onDetailsClick(int position) {

            Commande c=new Commande();
            c.setPlats_commandes(new ArrayList<Plat>(repas.get(position).getPlats()));

            if(!c.getPlats_commandes().isEmpty()){

                ValiderCommande.display(getFragmentManager(),c,1);
            }

        }

        @Override
        public void onSuppress(int position) {

            SharedPreferences preferences;

            preferences = PreferenceManager.getDefaultSharedPreferences(getContext());

            BackgroundHistoriqueData background= new BackgroundHistoriqueData(getContext(),RepasPersoFragment.this);

            background.execute("delete_user_repas",preferences.getString("id_client","nothing"),repas.get(position).getNom());

            repas.remove(position);
            adapter.notifyItemRemoved(position);

            if(repas.isEmpty()){

                no_repas.setVisibility(View.VISIBLE);

            }


        }

        @Override
        public void onModify(int position,TextView nom) {
            cas=1;
            p=position;
            dialog.setView(dialogView);
            layout_name.setError(null);
            name=nom;
            nom_repas_remplacer.setText(nom.getText().toString());
            dialog.show();
        }


    };


    View.OnClickListener addListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            cas=0;
            dialog.setView(dialogView);
            nom_repas_remplacer.setText("");
            layout_name.setError(null);
            dialog.show();



        }
    };

    View.OnClickListener okClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            no_repas.setVisibility(View.GONE);
            //SI LE NOM DU REPAS N EXISTE PAS DEJA

            if(!nom_repas_remplacer.getText().toString().isEmpty()) {

                String nom_remplacer = nom_repas_remplacer.getText().toString();

                if (Repas.notExist(repas,nom_remplacer)){

                    if (cas == 1) {

                        SharedPreferences preferences;

                        preferences = PreferenceManager.getDefaultSharedPreferences(getContext());

                        BackgroundHistoriqueData background= new BackgroundHistoriqueData(getContext(),RepasPersoFragment.this);

                        background.execute("modify_user_repas",preferences.getString("id_client","nothing"),repas.get(p).getNom(),nom_repas_remplacer.getText().toString());

                        repas.get(p).setNom(nom_repas_remplacer.getText().toString());
                        name.setText(nom_repas_remplacer.getText().toString());
                        dialog.dismiss();
                    }
                    if (cas == 0) {
                        commande = new Commande();
                        r = new Repas();
                        r.setNom(nom_repas_remplacer.getText().toString());

                        startActivityForResult(new Intent(getContext(), SelectionRepasActivity.class), 1);

                    }
                }
                else{

                    layout_name.setError("Nom deja existant !");
                }

            }else{

                layout_name.setError("Saisissez le nom du repas !");
            }
        }
    };
    View.OnClickListener cancelClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dialog.dismiss();
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode==1 && resultCode== SelectionRepasActivity.RESULT_OK)
        {
            commande=SelectionRepasActivity.commande;

            if(commande!=null && !commande.getPlats_commandes().isEmpty()) {

                r.setPlats(new ArrayList<Plat>(commande.getPlats_commandes()));
                repas.add(r);
                adapter.notifyDataSetChanged();

                SharedPreferences preferences;

                preferences = PreferenceManager.getDefaultSharedPreferences(getContext());

                BackgroundHistoriqueData background= new BackgroundHistoriqueData(getContext(),RepasPersoFragment.this);

                background.execute("add_user_repas",preferences.getString("id_client","nothing"),r);

            }
        }
        dialog.dismiss();
    }

    @Override
    public void processFinish(Object result, String type) {

       DialogOk dialogOk = new DialogOk(getActivity(),getContext(),1);
       dialogOk.execute(type);
       dialogOk.closeListener();

    }
}
