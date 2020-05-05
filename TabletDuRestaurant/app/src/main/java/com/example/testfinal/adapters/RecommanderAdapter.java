package com.example.testfinal.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.testfinal.R;
import com.example.testfinal.models.Plat;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class RecommanderAdapter extends RecyclerView.Adapter<RecommanderAdapter.ViewHolder> {

    private ArrayList<Plat> plats;
    private Context context;
    private  OnItemCLickListener listener;

    public RecommanderAdapter(Context cxt, ArrayList<Plat>list){

        context=cxt;
        plats=new ArrayList<>(list);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.commande_menu_card_layout, viewGroup, false);

        ViewHolder viewHolder = new ViewHolder(v,this.listener,plats);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.name.setText(plats.get(i).getDesignation());

        viewHolder.price.setText(plats.get(i).getMediumPrix());

        viewHolder.price.append(" Da");

        Picasso.get().load(plats.get(i).getPicture()).into(viewHolder.image);

    }

    @Override
    public int getItemCount() {
        return plats.size();
    }

    //THIS TO HANDLE CLICK ON THE CARD VIEW ITEMS
    public interface OnItemCLickListener{

        void onItemClick(int position, ArrayList<Plat> p, ImageButton add);
        void onDetailsClick(int position, ArrayList<Plat> p);
    }

    public  void setOnItemClickListener(RecommanderAdapter.OnItemCLickListener listener){

        this.listener=listener;
    }



    public  static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView image;
        private TextView name,price;
        private ImageButton add;
        private ImageButton details;


        public ViewHolder(@NonNull View itemView, final OnItemCLickListener listener, final ArrayList<Plat> p) {

            super(itemView);

            image = itemView.findViewById(R.id.img_plat_commande);
            name =  itemView.findViewById(R.id.name_commande);
            price= itemView.findViewById(R.id.price_commande);
            add=itemView.findViewById(R.id.add_button);
            details=itemView.findViewById(R.id.item_details_commande);
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (listener!=null)
                    {
                        int position = getAdapterPosition();

                        if(position!= RecyclerView.NO_POSITION)
                        {
                            listener.onItemClick(position,p,add);
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
