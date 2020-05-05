package com.example.testfinal.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.testfinal.R;
import com.example.testfinal.models.Plat;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;


public class FragmentMenuAdapter extends RecyclerView.Adapter<FragmentMenuAdapter.ViewHolder> implements Filterable {

    private  ArrayList<Plat> plats;
    private ArrayList<Plat> platsResearch;
    private Context context;
    private OnItemCLickListener listener;

    public FragmentMenuAdapter(Context cxt, ArrayList<Plat> list)
     {
         context=cxt;
         plats=new ArrayList<>(list);
         //cette liste est pour la recherche des plats
         platsResearch=new ArrayList<>(list);

     }

    @NonNull
    @Override
    public FragmentMenuAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.menu_card_layout, viewGroup, false);

        ViewHolder viewHolder = new ViewHolder(v,this.listener,plats);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final FragmentMenuAdapter.ViewHolder viewHolder, int i) {

        //SETTING THE CARD VIEW ELEMENTS

        viewHolder.name.setText(plats.get(i).getDesignation());

        viewHolder.price.setText(plats.get(i).getMediumPrix());

        viewHolder.price.append(" Da");

        Picasso.get().load(plats.get(i).getPicture()).into(viewHolder.image);


        //CA C EST POUR QUAND ON SORT DE L ACTIVITE ,ON DOIT GARDER LES COEURS
        if(plats.get(i).getFavoris().equals("true")){

            viewHolder.favorite.setImageResource(R.drawable.ic_favorite);
        }



    };

    @Override
    public int getItemCount() {
        return plats.size();
    }

    //ALL THOSE METHODS ARE FOR DISH RESEARCH
    @Override
    public Filter getFilter() {
        return filter;
    }

    private  Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            ArrayList<Plat> filtredList= new ArrayList<>();

            //SI LE MOT TAPPE EST NULL OU VIDE ON RETOURNE TOUTE LA LISTE
            if(constraint==null || constraint.length()==0)
            {
                filtredList.addAll(platsResearch);
            }
            else
            {

                String nameResearch= constraint.toString().toLowerCase().trim();

                //SINON ON CHERCHE SI CE QU ON A TAPPE EST CONTENU DANS L UN DES PLATS
                for (Plat plat : platsResearch)
                {
                    if(plat.getDesignation().toLowerCase().contains(nameResearch))
                    {
                        filtredList.add(plat);
                    }
                }
            }

            //RETOURNER LA LISTE FILTREE
            FilterResults results=new FilterResults();
            results.values=filtredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            plats.clear();
            plats.addAll((ArrayList)results.values);
            notifyDataSetChanged();
        }
    };


    //THIS TO HANDLE CLICK ON THE CARD VIEW ITEMS
    public interface OnItemCLickListener{

        void onItemClick(int position, ArrayList<Plat> p, ImageButton favorite);
        void onDetailsClick(int position, ArrayList<Plat> p);
    }

    public  void setOnItemClickListener(OnItemCLickListener listener){

        this.listener=listener;
    }

    public  static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView image;
        private TextView  name,price;
        private ImageButton favorite;
        private ImageButton details;

        public ViewHolder(@NonNull View itemView, final OnItemCLickListener listener, final ArrayList<Plat> p) {

                super(itemView);
                image = itemView.findViewById(R.id.img_plat);
                name =  itemView.findViewById(R.id.name);
                price= itemView.findViewById(R.id.price);
                favorite=itemView.findViewById(R.id.fav_button);
                details=itemView.findViewById(R.id.item_details);

                favorite.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (listener!=null)
                        {
                            int position = getAdapterPosition();

                            if(position!= RecyclerView.NO_POSITION)
                            {
                                listener.onItemClick(position,p,favorite);
                            }
                        }

                    }
                });

                details.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (listener!=null)
                        {
                            int position = getAdapterPosition();

                            if(position!= RecyclerView.NO_POSITION)
                            {
                                listener.onDetailsClick(position,p);
                            }
                        }
                    }
                });
        }



    }
}

