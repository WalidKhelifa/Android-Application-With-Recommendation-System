package com.example.testfinal.models;




import java.util.ArrayList;

public class Ingredient {

    private String id_ing,nom_ing;
    private float poids,nbApparition;


    public Ingredient(String id , String nom)
    {
        id_ing=id;
        nom_ing=nom;
        poids=0;
        nbApparition=0;

    }


    //il calcule le nbr d apparition d'un ingredient dans une liste de plats et le met dans le plat
    public void nbApparition(ArrayList<Plat> plats_commandes)
    {
        float somme=0;

        for (Plat p : plats_commandes ) {


            for (Ingredient ing :p.getIngredients()) {

                if(this.getId_ing().equals(ing.getId_ing())){

                    somme=somme +p.getNbAchat();
                }
            }


        }

        this.setNbApparition(somme);
        this.setPoids(somme);


    }






  public boolean exists(Plat p){

      for (Ingredient ing : p.getIngredients()) {

          if (this.getId_ing().equals(ing.getId_ing())) return true;
      }

      return false;
  }




    public String getId_ing() {
        return id_ing;
    }

    public void setId_ing(String id_ing) {
        this.id_ing = id_ing;
    }


    public String getNom_ing() {
        return nom_ing;
    }

    public void setNom_ing(String nom_ing) {
        this.nom_ing = nom_ing;
    }

    public float getPoids() {
        return poids;
    }

    public void setPoids(float poids) {
        this.poids = poids;
    }

    public float getNbApparition() {
        return nbApparition;
    }

    public void setNbApparition(float nbApparition) {
        this.nbApparition = nbApparition;
    }
}
