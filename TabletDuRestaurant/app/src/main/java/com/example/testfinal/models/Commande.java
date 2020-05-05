package com.example.testfinal.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Commande implements Comparable {

    private String identifiant,date,heure,montant,type;
    private ArrayList<Plat> plats_commandes;

    public Commande() {

        identifiant="";
        date="";
        heure="";
        montant="";
        type="";
        plats_commandes=new ArrayList<>();
    }
    public Commande(Commande commande){



        identifiant=commande.identifiant;
        date=commande.date;
        heure=commande.heure;
        montant=commande.montant;
        type=commande.type;
        plats_commandes=new ArrayList<>(commande.getPlats_commandes());

    }

    public Commande(String id , String d, String h, String m, String t) {


        identifiant=id;
        date=d;
        heure=h;
        montant=m;
        type=t;
        plats_commandes=new ArrayList<>();
    }


    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getMontant() {
        return montant;
    }

    public void setMontant(String montant) {
        this.montant = montant;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<Plat> getPlats_commandes() {
        return plats_commandes;
    }

    public void setPlats_commandes(ArrayList<Plat> plats_commandes) {
        this.plats_commandes = plats_commandes;
    }

    public String calculerMontant()
    {
        float somme=0,value=0;

        if(this.plats_commandes!=null && !plats_commandes.isEmpty())
        {


            for (Plat plat : plats_commandes)
            {

                if(plat.getSelected_portion().equals("small"))  value=Integer.valueOf(plat.getSmallPrix());
                if(plat.getSelected_portion().equals("medium")) value=Integer.valueOf(plat.getMediumPrix());
                if(plat.getSelected_portion().equals("large")) value=Integer.valueOf(plat.getLargePrix());

                somme=somme+(value*Integer.valueOf(plat.getSelected_quantity()));


            }


        }

        return String.valueOf(somme);
    }

    @Override
    public int compareTo(Object o) {

        Commande c=(Commande)o;
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

            Date date1 = format.parse(this.getDate());
            Date date2 = format.parse(c.getDate());

            if(this.getDate().equals(c.getDate()))
            {
                if(getHeure().compareTo(c.getHeure())==0) return 0;
                if(getHeure().compareTo(c.getHeure())<0)return 1;
                if(getHeure().compareTo(c.getHeure())>0)return -1;


            }
            if(date1.before(date2))return 1;

            if(date1.after(date2))return -1;





        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;

    }
}
