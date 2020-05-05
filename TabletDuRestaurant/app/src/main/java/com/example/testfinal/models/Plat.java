package com.example.testfinal.models;


import java.util.ArrayList;

public class Plat implements Comparable {

    private String id_plat;
    private String designation;
    private String picture;
    private String smallPrix,mediumPrix,largePrix;
    private String categorie;
    private String modeC,tempsC;
    private String favoris;
    private String selected_portion,selected_quantity;
    private ArrayList<Ingredient> ingredients;
    private float nbAchat;

    //CONSTRUCTOR
    public Plat(String id, String des, String portion, String prix, String qte)
    {
        id_plat=id;
        designation=des;
        selected_portion=portion;
        selected_quantity=qte;

        if(selected_portion.equals("medium"))
        {
            mediumPrix=prix;
        }
        if(selected_portion.equals("small"))
        {
            smallPrix=prix;
        }
        if(selected_portion.equals("large"))
        {
            largePrix=prix;
        }

        nbAchat=0;
        ingredients=new ArrayList<>();
    }
    public Plat(String id, String des, String pic, String cat, String mode, String temps, String smallp, String medP, String largeP, String fav) {

        id_plat=id;
        designation=des;
        picture=pic;
        categorie=cat;
        modeC=mode;
        tempsC=temps;
        smallPrix=smallp;
        mediumPrix=medP;
        largePrix=largeP;
        favoris=fav;
        ingredients=new ArrayList<>();
        nbAchat=0;
    }
    public Plat(Plat p) {

        id_plat=p.id_plat;
        designation=p.designation;
        picture=p.picture;
        categorie=p.categorie;
        modeC=p.modeC;
        tempsC=p.tempsC;
        smallPrix=p.smallPrix;
        mediumPrix=p.mediumPrix;
        largePrix=p.largePrix;
        favoris=p.favoris;
        selected_portion=p.selected_portion;
        selected_quantity=p.selected_quantity;
        ingredients=new ArrayList<>(p.ingredients);
        nbAchat=p.nbAchat;
    }

    //SETTERS AND GETTERS


    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getId_plat() {
        return id_plat;
    }

    public void setId_plat(String id_plat) {
        this.id_plat = id_plat;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getSmallPrix() {
        return smallPrix;
    }

    public void setSmallPrix(String smallPrix) {
        this.smallPrix = smallPrix;
    }

    public String getMediumPrix() {
        return mediumPrix;
    }

    public void setMediumPrix(String mediumPrix) {
        this.mediumPrix = mediumPrix;
    }

    public String getLargePrix() {
        return largePrix;
    }

    public void setLargePrix(String largePrix) {
        this.largePrix = largePrix;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getModeC() {
        return modeC;
    }

    public void setModeC(String modeC) {
        this.modeC = modeC;
    }

    public String getTempsC() {
        return tempsC;
    }

    public void setTempsC(String tempsC) {
        this.tempsC = tempsC;
    }

    public String getFavoris() {
        return favoris;
    }

    public void setFavoris(String favoris) {
        this.favoris = favoris;
    }

    public String getSelected_portion() {
        return selected_portion;
    }

    public void setSelected_portion(String selected_portion) {
        this.selected_portion = selected_portion;
    }

    public String getSelected_quantity() {
        return selected_quantity;
    }

    public void setSelected_quantity(String selected_quantity) {
        this.selected_quantity = selected_quantity;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public float getNbAchat() {
        return nbAchat;
    }

    public void setNbAchat(float nbAchat) {
        this.nbAchat = nbAchat;
    }

    @Override
    public int compareTo(Object o) {

        Plat p =(Plat)o;

        return this.designation.compareTo(p.designation);
    }

    public boolean exists(ArrayList<Plat> plats){

        for (Plat p:plats) {

            if(this.getId_plat().equals(p.getId_plat())){

                return true;
            }

        }
        return false;
    }


}
