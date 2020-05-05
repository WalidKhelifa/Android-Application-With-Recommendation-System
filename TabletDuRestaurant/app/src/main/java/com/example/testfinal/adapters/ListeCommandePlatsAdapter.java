package com.example.testfinal.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import com.example.testfinal.R;
import com.example.testfinal.models.Plat;
import java.util.ArrayList;

public class ListeCommandePlatsAdapter extends RecyclerView.Adapter<ListeCommandePlatsAdapter.ViewHolder> {

    private ArrayList<Plat> plats;
    private ListeCommandePlatsAdapter.OnItemCLickListener listener;

    public ListeCommandePlatsAdapter(ArrayList<Plat> p)
    {
      plats=p;
    }



    @NonNull
    @Override
    public ListeCommandePlatsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.commande_chart_card_layout, viewGroup, false);

        ViewHolder viewHolder = new ViewHolder(v,this.listener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListeCommandePlatsAdapter.ViewHolder viewHolder, int i) {

        int qte= Integer.valueOf(plats.get(i).getSelected_quantity());


        viewHolder.designation.setText(plats.get(i).getDesignation());
        viewHolder.qte.setText(plats.get(i).getSelected_quantity());

        if(plats.get(i).getSelected_portion().equals("small")){

            int price =  Integer.valueOf(plats.get(i).getSmallPrix());

            viewHolder.price.setText(String.valueOf(price*qte));
            viewHolder.portion.setText("Petit");
        }


        if(plats.get(i).getSelected_portion().equals("medium")){

            int price =  Integer.valueOf(plats.get(i).getMediumPrix());

            viewHolder.price.setText(String.valueOf(price*qte));
            viewHolder.portion.setText("Moyen");
        }
        if(plats.get(i).getSelected_portion().equals("large")){

            int price =  Integer.valueOf(plats.get(i).getLargePrix());

            viewHolder.price.setText(String.valueOf(price*qte));
            viewHolder.portion.setText("Grand");
        }

        viewHolder.price.append("Da");
    }

    @Override
    public int getItemCount() {
        return plats.size();
    }


    //THIS TO HANDLE CLICK ON THE CARD VIEW ITEMS
    public interface OnItemCLickListener{

        void onItemClick(int position);

    }

    public  void setOnItemClickListener(OnItemCLickListener listener){

        this.listener=listener;
    }




    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView designation,qte,price,portion;
        ImageButton suppress;

        public ViewHolder(@NonNull View itemView, final OnItemCLickListener listener) {
            super(itemView);


            designation=itemView.findViewById(R.id.nom_plat_liste);
            qte=itemView.findViewById(R.id.qte_selected);
            price=itemView.findViewById(R.id.price_selected);
            portion=itemView.findViewById(R.id.portion_selected);
            suppress=itemView.findViewById(R.id.supprimer_plat);



            suppress.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (listener!=null)
                    {
                        int position = getAdapterPosition();

                        if(position!= RecyclerView.NO_POSITION)
                        {
                            listener.onItemClick(position);
                        }
                    }

                }
            });
        }
    }
}
