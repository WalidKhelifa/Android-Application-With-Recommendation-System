package com.example.testfinal.models;

public class Avis
{
   public int id_avis,id_client;
   public String date_avis,nb_etoile,heure_avis,comment;

    public Avis()
    {

        this.date_avis = "";
        this.nb_etoile = "";
        this.heure_avis = "";
        comment="null";


    }


    public Avis(int id_avis, int id_client, String date_avis, String nb_etoile, String heure_avis,String com) {

        this.id_avis = id_avis;
        this.id_client = id_client;
        this.date_avis = date_avis;
        this.nb_etoile = nb_etoile;
        this.heure_avis = heure_avis;
        comment= com;

    }

    public int getId_avis() {
        return id_avis;
    }

    public void setId_avis(int id_avis) {
        this.id_avis = id_avis;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public String getDate_avis() {
        return date_avis;
    }

    public void setDate_avis(String date_avis) {
        this.date_avis = date_avis;
    }

    public String getNb_etoile() {
        return nb_etoile;
    }

    public void setNb_etoile(String nb_etoile) {
        this.nb_etoile = nb_etoile;
    }

    public String getHeure_avis() {
        return heure_avis;
    }

    public void setHeure_avis(String heure_avis) {
        this.heure_avis = heure_avis;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
