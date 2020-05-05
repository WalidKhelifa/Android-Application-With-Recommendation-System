package com.example.testfinal.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import com.example.testfinal.R;
import com.example.testfinal.models.Commande;
import java.util.ArrayList;


public class ListeCommandesAdapter extends RecyclerView.Adapter<ListeCommandesAdapter.ViewHolder> {
    private ArrayList<Commande> commandes;
    private ListeCommandesAdapter.OnItemCLickListener listener;


    public ListeCommandesAdapter(ArrayList<Commande> c)
    {
        commandes=c;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.historique_card_layout, viewGroup, false);

       ViewHolder viewHolder = new ViewHolder(v,this.listener);

       return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        //La je reecupere les données

        String value="Commande "+(i+1);
        viewHolder.nom.setText(value);
        viewHolder.heure.setText(commandes.get(i).getHeure());
        viewHolder.date.setText(commandes.get(i).getDate());
    }

    @Override
    public int getItemCount() {

        return commandes.size();
    }

    public interface OnItemCLickListener{

        void onItemClick(int position);

    }

    public  void setOnItemClickListener(OnItemCLickListener listener){

        this.listener=listener;
    }











    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView nom,date,heure;

        ImageButton details;

        public ViewHolder(@NonNull View itemView, final OnItemCLickListener listener) {
            super(itemView);



             nom=itemView.findViewById(R.id.nom_commande);
             date=itemView.findViewById(R.id.date_commande);
             heure=itemView.findViewById(R.id.heure_commande);
             details=itemView.findViewById(R.id.voir_commande);

            details.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int position = getAdapterPosition();

                    if(position!= RecyclerView.NO_POSITION)
                    {
                        listener.onItemClick(position);
                    }
                }
            });

        }
    }





}
