package jeudelavie.grille;

import jeudelavie.regles.*;
import java.lang.*;
import jeudelavie.cellule.*;

/**
 * Initialise un thread qui permet d'effectuer la fonction futur de la class Grilles
 */

public class Threadfutur extends Thread {

    private int debut_i,fin_i,debut_j,fin_j;
    private Grilles grille;
    private Regles regle;
    /**
     * Constructeur de la class
     * @param debut_i début de la boucle for sur la longueur
     * @param fin_i fin de la boucle for sur la longueur 
     * @param debut_j début de la boucle for sur la hauteur
     * @param fin_j fin de la boucle for sur la hauteur
     * @param grille pour pouvoir modifier les valeur des cellule de la grille
     * @param regle regle a effectuer sur les cellule de la grille
    */
    public Threadfutur(int debut_i,int fin_i,int debut_j,int fin_j , Grilles grille,Regles regle){
        this.debut_i = debut_i;
        this.debut_j = debut_j;
        this.fin_i = fin_i;
        this.fin_j = fin_j;
        this.grille = grille;
        this.regle= regle;
        //System.out.println("debut x =>"+debut_i+" fin x =>"+fin_i+"\ndebut y =>"+debut_j+" fin y=>"+fin_j);
    }

    /**
     * Méthode qui effectue le calcul du futur de chaque cellule de la grille
    */
    @Override
    public void run(){
        for(int i = debut_i;i<fin_i;i++){
            for(int j = debut_j;j<fin_j;j++){
                Cellule cel = grille.getGrille()[i][j];
                int nbVoisinsVivants = regle.voisins(cel);
                Etat ancienEtat = cel.getArchiveEtat();
                Etat nouvelEtat = regle.etatFutur(ancienEtat,nbVoisinsVivants);
                cel.setEtat( nouvelEtat);
                
            }
        }
    }

}