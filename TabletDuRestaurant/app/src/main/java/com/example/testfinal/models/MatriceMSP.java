package com.example.testfinal.models;

public class MatriceMSP {


    private float  [][] matrice;
    private int n_lignes,n_colonnes;

    public MatriceMSP(int plat_commande,int plat_filtre) {

        matrice= new float[plat_filtre+1][plat_commande+1];

        for (int i = 1; i <= plat_commande; i++) {

            matrice[0][i] = i;
        }
        for (int i = 1; i <= plat_filtre; i++) {

            matrice[i][0] = i;

        }

        n_colonnes=plat_commande;
        n_lignes=plat_filtre;
    }

    public  int [] positionsMaxColonnes() {

        int t[]=new int[100];
        float max;


        for(int j=1;j<=n_colonnes;j++){

            max = matrice[1][j];
            t[j-1]= 0;

            for (int i=2;i<=n_lignes;i++){

                if (max<matrice[i][j] ){

                    max=matrice[i][j];

                    t[j-1]=i-1;
                }

            }

            for (int p=1;p<=n_colonnes;p++){

                matrice[t[j-1]+1][p]=-1;
            }
        }
        return t;
    }

    public float[][] getMatrice() {
        return matrice;
    }

    public void setMatrice(float[][] matrice) {
        this.matrice = matrice;
    }

    public int getN_lignes() {
        return n_lignes;
    }

    public void setN_lignes(int n_lignes) {
        this.n_lignes = n_lignes;
    }

    public int getN_colonnes() {
        return n_colonnes;
    }

    public void setN_colonnes(int n_colonnes) {
        this.n_colonnes = n_colonnes;
    }
}
