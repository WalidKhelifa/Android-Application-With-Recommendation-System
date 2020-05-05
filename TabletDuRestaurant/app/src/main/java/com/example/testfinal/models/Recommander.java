package com.example.testfinal.models;

import android.content.Context;

import java.util.ArrayList;

public class Recommander {

    private ArrayList<Plat> plats_commandes;
    private ArrayList<Plat> plats_filtres;
    private  int a,b,c;
    private MatriceMSP matrice;
    private ArrayList<Ingredient> communs,dansXnonY,dansYnonX;
    private ArrayList<Plat> plats_recommande;
    private Context context;


    //CONSTRUCTOR
    public Recommander(Context ctx,ArrayList<Commande> commandes, ArrayList<Plat> plats) {

        plats_filtres = plats;
        context = ctx;

        if(commandes!=null && !commandes.isEmpty()) {

            transform(commandes);

            //INITIALISATION

            a = 0;
            b = 0;
            c = 0;

            communs = new ArrayList<>();
            dansXnonY = new ArrayList<>();
            dansYnonX = new ArrayList<>();
            plats_recommande = new ArrayList<>();

            ArrayList<Plat> plat_remove = new ArrayList<>();

            //JE RAJOUTE LES PLATS FAVORIS directement a la recommandation

            for (Plat p : plats_filtres) {

                if (p.getFavoris().equals("true")) {

                    plats_recommande.add(new Plat(p));

                    plat_remove.add(p);
                }

            }
            //enlever les favoris
            plats_filtres.removeAll(plat_remove);

            plat_remove.clear();

            //J ENLEVE LES PLATS DEJA COMMANDEE POUR NE PAS APPLIQUER LA RECOMMANDATION SUR EUX

            for (Plat p : plats_filtres) {

                if (p.exists(plats_commandes)) {

                    plat_remove.add(p);
                }

            }
            plats_filtres.removeAll(plat_remove);

            matrice = new MatriceMSP(plats_commandes.size(), plats_filtres.size());
        }
        else
        {
            plats_recommande = new ArrayList<>();

            ArrayList<Plat> plat_remove = new ArrayList<>();

            //JE RAJOUTE LES PLATS FAVORIS directement a la recommandation

            for (Plat p : plats_filtres) {

                if (p.getFavoris().equals("true")) {

                    plats_recommande.add(new Plat(p));

                    plat_remove.add(p);
                }

            }
            //enlever les favoris
            plats_filtres.removeAll(plat_remove);

            plat_remove.clear();
        }

    }



    public ArrayList<Plat> execute() {

        if (!plats_filtres.isEmpty() && plats_commandes!=null ) {

            calculPoidsHistorique();

            calculPoidsMenu();

            return calculAllMSP();

        }else

        {
            return  plats_recommande;
        }
    }

    //la je viens de transformer la liste des commandes comme dans l exemple de mon memoire
    private void transform(ArrayList<Commande> commandes) {

        plats_commandes=new ArrayList<>();

        for (Commande c : commandes) {

            for (Plat p : c.getPlats_commandes()) {

                //dans les deux cas on calcule le nbr de fois que le plat a été commandé
                //si le plat n existe pas

                if (!exist(plats_commandes, p)) {

                    p.setNbAchat(Float.valueOf(p.getSelected_quantity()));

                    plats_commandes.add(new Plat(p));

                } else {

                    Plat selected_plat = plats_commandes.get(getposition(plats_commandes, p));

                    selected_plat.setNbAchat(selected_plat.getNbAchat() + Float.valueOf(p.getSelected_quantity()));

                }

            }

        }

    }

    //Verifie si un plat p existe dans la liste plats
    private boolean exist(ArrayList<Plat> plats, Plat p) {

        for (Plat plat : plats) {

            if (p.getId_plat().equals(plat.getId_plat())) return true;
        }

        return false;
    }


    //get la position d'un plat
    private int getposition(ArrayList<Plat> plats, Plat p) {

        for (Plat plat : plats) {

            if (p.getId_plat().equals(plat.getId_plat())) return plats.indexOf(plat);
        }

        return -1;
    }

    private void calculPoidsHistorique() {

        //on met dans le poids de chaque ingredient le nbr de ses apparitions dans la liste
        apparitionTousIngredients();
        // pour chaque plat on calcule le nombre d apparition total
        // puis on divise le poids de chaque ingredient du plat par le resultat trouvé
        nbApparitionsTotal();
    }

    private void apparitionTousIngredients() {

        for (Plat p : plats_commandes) {

            for (Ingredient ing : p.getIngredients()) {

                ing.nbApparition(new ArrayList<Plat>(plats_commandes));
            }

        }

    }


    //calcule la somme des nbr d'apparitions
    private void nbApparitionsTotal() {

        float somme = 0;

        for (Plat p : plats_commandes) {

            //  on calcule la somme d apparition de tous les ingredients de la liste des plas recommandés

            for (Ingredient ing : p.getIngredients()) {

                somme = somme + ing.getPoids();

            }
        }
            //puis on divise ces ingredients par la somme

            for (Plat p : plats_commandes) {


            for (Ingredient ing : p.getIngredients()) {

                ing.setPoids(ing.getPoids() / somme);

            }
        }
    }


// et mtn calcul du poids de la liste filtrée

    private void calculPoidsMenu() {


        for (Plat p : plats_filtres) {


            for (Ingredient ing : p.getIngredients()) {

                ing.setPoids(getPoidsHistorique(ing));
            }

        }
    }



        // reecupere de la liste des plats commandés , le nbr d apparition de chaque ingredient
        private float getPoidsHistorique (Ingredient ing) {

            for (Plat p : plats_commandes) {

                for (Ingredient ingredient : p.getIngredients() ) {

                    if(ing.getId_ing().equals(ingredient.getId_ing())){

                        return  ingredient.getPoids();
                    }
                }
            }

        return 0;

        }

        //GETTERS AND SETTERS

        public ArrayList<Plat> getPlats_commandes () {
            return plats_commandes;
        }

        public void setPlats_commandes (ArrayList < Plat > plats_commandes) {
            this.plats_commandes = plats_commandes;
        }

        public ArrayList<Plat> getPlats_filtres () {
            return plats_filtres;
        }

        public void setPlats_filtres (ArrayList < Plat > plats_filtres) {
            this.plats_filtres = plats_filtres;
        }



        //MAINTENANT LE CALCUL DE MSP


    //On calcule a
    private void ingredientsCommuns(Plat p,Plat d){

        communs.clear();

        for (Ingredient ing: p.getIngredients()) {

           if(ing.exists(d)){

                communs.add(ing);
           }
        }
        a=communs.size();


    }
    //On calcule b

    private void ingredientsDansX(Plat p,Plat d){

        dansYnonX.clear();

        for (Ingredient ing: p.getIngredients()) {

            if(!ing.exists(d)){

                dansXnonY.add(ing);
            }
        }
        b=dansXnonY.size();


    }
    //On calcule c

    private void ingredientsDansY(Plat p,Plat d){

        dansYnonX.clear();

        for (Ingredient ing: d.getIngredients()) {

            if(!ing.exists(p)){

                dansYnonX.add(ing);
            }
        }
        c=dansYnonX.size();


    }


    private ArrayList<Plat> calculAllMSP(){


        for (Plat commande: plats_commandes) {

            for (Plat filtre: plats_filtres) {

                ingredientsCommuns(commande,filtre);
                ingredientsDansX(commande,filtre);
                ingredientsDansY(commande,filtre);
                calculMSP(filtre,commande);


            }

        }

       int t[]= matrice.positionsMaxColonnes();

        for (int i = 0 ; i<matrice.getN_colonnes();i++){

            if(t[i]!=-1){
                plats_recommande.add(plats_filtres.get(t[i]));
            }

        }

        return plats_recommande;
    }

    //calcule de la mesure de similarité en deux plats p1 = ligne p2 =colonne
    private void calculMSP(Plat p1,Plat p2){


        float value1=0;
        float value2=0;
        float value3=0;

        for (Ingredient g: communs) {

            value1=value1+ a* g.getPoids();

        }

        for (Ingredient g: dansXnonY) {

            value2=value2+ b* g.getPoids();

        }
        for (Ingredient g: dansYnonX) {

            value3=value3+ c* g.getPoids();
        }


            matrice.getMatrice()[plats_filtres.indexOf(p1)+1][plats_commandes.indexOf(p2)+1]= value1/(value1+value2+value3);



    }
}


