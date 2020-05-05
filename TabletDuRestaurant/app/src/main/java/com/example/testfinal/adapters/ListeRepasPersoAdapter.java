package com.example.testfinal.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import com.example.testfinal.R;
import com.example.testfinal.models.Repas;
import java.util.ArrayList;

public class ListeRepasPersoAdapter extends RecyclerView.Adapter<ListeRepasPersoAdapter.ViewHolder> {

    private ArrayList<Repas> repas;
    private ListeRepasPersoAdapter.OnItemCLickListener listener;


    public ListeRepasPersoAdapter(ArrayList<Repas> r)
    {
        repas=r;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.repas_perso_card_layout, viewGroup, false);

        ViewHolder viewHolder = new ViewHolder(v,this.listener);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.nom.setText(repas.get(i).getNom());

    }

    @Override
    public int getItemCount() {

        return repas.size();
    }
    public interface OnItemCLickListener {

        void onDetailsClick(int position);
        void onSuppress(int position);
        void onModify(int position, TextView nom);

    }

    public  void setOnItemClickListener(ListeRepasPersoAdapter.OnItemCLickListener listener){

        this.listener=listener;
    }





    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView nom;
        ImageButton details,modify,suppress;

        public ViewHolder(@NonNull View itemView,final OnItemCLickListener listener) {

            super(itemView);

            suppress=itemView.findViewById(R.id.suppress_button);
            modify=itemView.findViewById(R.id.modify_button);
            details=itemView.findViewById(R.id.item_details_repas);
            nom=itemView.findViewById(R.id.nom_repas);


            suppress.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int position = getAdapterPosition();

                    if(position!= RecyclerView.NO_POSITION)
                    {
                        listener.onSuppress(position);
                    }

                }
            });

            modify.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    if(position!= RecyclerView.NO_POSITION)
                    {
                        listener.onModify(position,nom);
                    }
                }
            });


            details.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    if(position!= RecyclerView.NO_POSITION)
                    {
                        listener.onDetailsClick(position);
                    }
                }
            });

        }
    }



}
