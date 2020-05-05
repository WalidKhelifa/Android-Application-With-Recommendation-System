package com.example.testfinal.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.testfinal.adapters.ListeCommandesAdapter;
import com.example.testfinal.R;
import com.example.testfinal.activities.HistoriqueActivity;
import com.example.testfinal.models.Commande;
import java.util.ArrayList;

public class HistoriqueFragment extends Fragment {

    private RecyclerView recyclerView;
    public static ListeCommandesAdapter adapter;
    private RecyclerView.LayoutManager manager;
    private ArrayList<Commande>commandes;
    private TextView pas_commande;
    private RelativeLayout entete;

    public HistoriqueFragment() {

        commandes= HistoriqueActivity.commandes;


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_historique, container, false);

        //GET WIDGETS

        recyclerView=v.findViewById(R.id.recycler_liste_commandes);
        pas_commande= v.findViewById(R.id.pas_commande);
        entete=v.findViewById(R.id.entete);

        if(commandes.isEmpty()){

            pas_commande.setVisibility(View.VISIBLE);
            entete.setVisibility(View.GONE);

        }else{

            pas_commande.setVisibility(View.GONE);
            entete.setVisibility(View.VISIBLE);

        }




        //set recycler view

        manager=new LinearLayoutManager(getContext());
        adapter= new ListeCommandesAdapter(commandes);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        //item click listener
        adapter.setOnItemClickListener(itemCLickListener);

        return v;
    }

    ListeCommandesAdapter.OnItemCLickListener itemCLickListener = new ListeCommandesAdapter.OnItemCLickListener() {
        @Override
        public void onItemClick(int position) {

            ValiderCommande.display(getFragmentManager(),commandes.get(position),1);

        }
    };


}
