package com.example.testfinal.models;

import java.util.ArrayList;

public class Client {


    private String username,email,password,phone,adr,photo,taille,poids,date,genre,identifier;

    private ArrayList<Ingredient> aliments_pref;

    private ArrayList<Ingredient> aliments_detest;

    private ArrayList<Ingredient> aliments_allergies;

    private ArrayList<Ingredient> maladies;

    //CONSTRUCTORS

    public Client()
    {
        username="";
        email="";
        password="";
        phone="";
        adr="";
        identifier="";
        taille="";
        poids="";
        date="";
        genre="";

        aliments_pref=new ArrayList<>();
        aliments_detest=new ArrayList<>();
        aliments_allergies=new ArrayList<>();
        maladies=new ArrayList<>();

    }



    public Client(String u, String p, String e, String ph, String a)
    {
        username=u;
        email=e;
        password=p;
        phone=ph;
        adr=a;
    }


    public Client(String u, String p, String e, String ph, String a, String tof, String t, String po, String d, String gender)
    {
        username=u;
        email=e;
        password=p;
        phone=ph;
        adr=a;
        photo=tof;
        taille=t;
        poids=po;
        date=d;
        genre=gender;
    }


    public Client(String id, String u, String ph)
    {
        identifier=id;
        username=u;
        photo=ph;

    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAdr() {
        return adr;
    }

    public void setAdr(String adr) {
        this.adr = adr;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }


    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getTaille() {
        return taille;
    }

    public void setTaille(String taille) {
        this.taille = taille;
    }

    public String getPoids() {
        return poids;
    }

    public void setPoids(String poids) {
        this.poids = poids;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public ArrayList<Ingredient> getAliments_pref() {
        return aliments_pref;
    }

    public void setAliments_pref(ArrayList<Ingredient> aliments_pref) {
        this.aliments_pref = aliments_pref;
    }

    public ArrayList<Ingredient> getAliments_detest() {
        return aliments_detest;
    }

    public void setAliments_detest(ArrayList<Ingredient> aliments_detest) {
        this.aliments_detest = aliments_detest;
    }

    public ArrayList<Ingredient> getAliments_allergies() {
        return aliments_allergies;
    }

    public void setAliments_allergies(ArrayList<Ingredient> aliments_allergies) {
        this.aliments_allergies = aliments_allergies;
    }

    public ArrayList<Ingredient> getMaladies() {
        return maladies;
    }

    public void setMaladies(ArrayList<Ingredient> maladies) {
        this.maladies = maladies;
    }
}



