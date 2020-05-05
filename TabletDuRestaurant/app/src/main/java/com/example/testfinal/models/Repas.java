package com.example.testfinal.models;

import java.util.ArrayList;

public class Repas {

    private String identifiant,nom;
    private ArrayList<Plat>plats;

    public Repas()
    {

        identifiant="";
        nom="";
        plats=new ArrayList<>();
    }

    public Repas(String id, String n)
    {

        identifiant=id;
        nom=n;
        plats=new ArrayList<>();
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public ArrayList<Plat> getPlats() {
        return plats;
    }

    public void setPlats(ArrayList<Plat> plats) {
        this.plats = plats;
    }

    public static boolean notExist(ArrayList<Repas> repas,String s)
    {
        for (Repas r:repas) {

            if(r.getNom().equals(s)){
                return false;
            }

        }
        return true;

    }


}
