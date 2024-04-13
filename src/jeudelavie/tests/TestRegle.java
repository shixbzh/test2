package jeudelavie.tests;

import jeudelavie.cellule.*;
import jeudelavie.grille.*;
import jeudelavie.regles.*;

public class TestRegle{

    public static void testRegle(){
        
        Grilles grille = new GrilleVide(10,10);
       
        
        grille.getGrille()[2][3].setEtat(Etat.Vivant);
        grille.getGrille()[3][4].setEtat(Etat.Vivant);
        grille.getGrille()[4][3].setEtat(Etat.Vivant);

        

        int[][] conditions = {{0},{3}};
        Regles regle = new Regle(conditions);

        // Pour que la cellule devient vivante il faut 3 voisins vivants
        // On a initié les 3 voisins de la cellule de co (3,3) a vivant pour le test
        
        int nbVoisinsVivants = regle.voisins(grille.getGrille()[3][3]);
        
        System.out.println(nbVoisinsVivants);
        

        Etat etatPasse = grille.getGrille()[3][3].getEtat();
        grille.futur(regle);
        Etat etatFuture = grille.getGrille()[3][3].getEtat();
        

        assert  conditions[1][0] == nbVoisinsVivants && etatFuture == Etat.Vivant; 
        System.out.println("Test passé !");
       
    } 

    public static void main(String[] args){
        testRegle();
    }
}