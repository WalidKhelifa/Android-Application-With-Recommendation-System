package com.example.testfinal.models;

public class Restaurant {

    private String nom,photo,email,tel,ouverture,fermeture,description;


    public Restaurant(String n, String p, String desc, String e, String t, String o, String f)
    {
        nom=n;
        photo=p;
        description=desc;
        email=e;
        tel=t;
        ouverture=o;
        fermeture=f;
    }


    //GETTERS AND SETTERS

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getOuverture() {
        return ouverture;
    }

    public void setOuverture(String ouverture) {
        this.ouverture = ouverture;
    }

    public String getFermeture() {
        return fermeture;
    }

    public void setFermeture(String fermeture) {
        this.fermeture = fermeture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
