package com.example.testfinal.fragments;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.testfinal.R;
import com.example.testfinal.models.Commande;
import com.example.testfinal.models.Plat;
import com.travijuu.numberpicker.library.NumberPicker;

public class AddToChart extends DialogFragment {

    private static String TAG="add_to_chart";
    private static Plat plat;
    private TextView text_petit,text_moyen,text_grand,prix_petit,prix_moyen,prix_grand;
    private ImageView img_petit,img_moyen,img_grand;
    private Button annuler,ajouter;
    private ImageButton close;
    private NumberPicker quantity;
    private static Commande commande;


    public AddToChart() {
        // Required empty public constructor
    }

    public static void display(FragmentManager fragmentManager, Plat p, Commande c) {
        AddToChart dialog= new AddToChart();
        plat=p;
        commande=c;
        dialog.show(fragmentManager,TAG);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_add_to_chart, container, false);

        //GET WIDGETS
        text_petit=v.findViewById(R.id.text_petit);
        text_moyen=v.findViewById(R.id.text_moyen);
        text_grand=v.findViewById(R.id.text_grand);
        img_petit=v.findViewById(R.id.image_petit);
        img_moyen=v.findViewById(R.id.image_moyen);
        img_grand=v.findViewById(R.id.image_grand);
        quantity=v.findViewById(R.id.quantity);
        annuler=v.findViewById(R.id.annuler);
        ajouter=v.findViewById(R.id.ajouter);
        close= v.findViewById(R.id.close_add);
        prix_petit=v.findViewById(R.id.prix_petit);
        prix_moyen=v.findViewById(R.id.prix_moyen);
        prix_grand=v.findViewById(R.id.prix_grand);


        //METHODS

        setDefaultPrice();

        //SET LISTENERS

        close.setOnClickListener(closeListener);
        annuler.setOnClickListener(closeListener);
        ajouter.setOnClickListener(addListener);

        text_petit.setOnClickListener(portionListener);
        text_moyen.setOnClickListener(portionListener);
        text_grand.setOnClickListener(portionListener);
        img_petit.setOnClickListener(portionListener);
        img_moyen.setOnClickListener(portionListener);
        img_grand.setOnClickListener(portionListener);
        quantity.setOnClickListener(portionListener);
        prix_petit.setOnClickListener(portionListener);
        prix_moyen.setOnClickListener(portionListener);
        prix_grand.setOnClickListener(portionListener);
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

    View.OnClickListener portionListener= new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch(v.getId())
            {
                case R.id.text_petit:
                case R.id.prix_petit:
                case R.id.image_petit:{

                    setText(text_petit,text_moyen,text_grand);
                    setPrice(prix_petit,prix_moyen,prix_grand);
                    setImage(img_petit,img_moyen,img_grand);

                }break;

                case R.id.text_moyen:
                case R.id.prix_moyen:
                case R.id.image_moyen:{

                    setText(text_moyen,text_petit,text_grand);
                    setPrice(prix_moyen,prix_petit,prix_grand);
                    setImage(img_moyen,img_petit,img_grand);

                }break;

                case R.id.text_grand:
                case R.id.prix_grand:
                case R.id.image_grand:{

                    setText(text_grand,text_petit,text_moyen);
                    setPrice(prix_grand,prix_petit,prix_moyen);
                    setImage(img_grand,img_petit,img_moyen);

                }break;

            }

        }
    };


    View.OnClickListener addListener= new View.OnClickListener() {
        @Override
        public void onClick(View v) {

         plat.setSelected_portion(setPortion());


         plat.setSelected_quantity(String.valueOf(quantity.getValue()));


         commande.getPlats_commandes().add(new Plat(plat));

            dismiss();

        }
    };

    private void setDefaultPrice()
    {
        if(plat.getSmallPrix()!=null && !plat.getSmallPrix().isEmpty()){
            prix_petit.setText(plat.getSmallPrix());
            prix_petit.append("Da");
        }

        if(plat.getMediumPrix()!=null && !plat.getMediumPrix().isEmpty()){
            prix_moyen.setText(plat.getMediumPrix());
            prix_moyen.append("Da");
        }

        if(plat.getLargePrix()!=null && !plat.getLargePrix().isEmpty()){
            prix_grand.setText(plat.getLargePrix());
            prix_grand.append("Da");
        }

    }


    private void setText(TextView selected,TextView other1,TextView other2){

        selected.setTextColor(getResources().getColor(R.color.blue_selection));
        other1.setTextColor(getResources().getColor(R.color.white));
        other2.setTextColor(getResources().getColor(R.color.white));
    }

    private void setImage(ImageView selected,ImageView other1,ImageView other2){

        selected.setBackgroundResource(R.drawable.ic_portion_selected);
        other1.setBackgroundResource(R.drawable.ic_portion);
        other2.setBackgroundResource(R.drawable.ic_portion);


    }

    private void setPrice(TextView selected,TextView other1,TextView other2){

        selected.setTextColor(getResources().getColor(R.color.blue_selection));
        other1.setTextColor(getResources().getColor(R.color.white));
        other2.setTextColor(getResources().getColor(R.color.white));
    }

    private  String setPortion()
    {

        if(text_petit.getCurrentTextColor()==getResources().getColor(R.color.blue_selection))
        {
            return "small";
        }
        if(text_moyen.getCurrentTextColor()==getResources().getColor(R.color.blue_selection))
        {
            return "medium";
        }
        if(text_grand.getCurrentTextColor()==getResources().getColor(R.color.blue_selection))
        {
            return "large";
        }


        return "nothing";

    }
}
