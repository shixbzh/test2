package jeudelavie.grille;

import jeudelavie.cellule.*;
import jeudelavie.interfacegraphique.*;
import jeudelavie.regles.*;

import javax.swing.*;
import java.util.*;
import java.lang.*;
import java.io.*;

/**
 * Sous class de Grilles qui initialise une grille vide
 */
public class GrilleVide extends Grilles{


    /**
     * Initialise une nouvelle instance de GrilleVide en appelant le constructeur de la classe mère (Grilles)
     */
    public GrilleVide(int lignes,int colonnes) {
        super();
        setColonnes(colonnes);
        setLignes(lignes);
        initialisation();
    }

    /**
     * Réinitialise la grille en créant une grille où toutes les cellules sont en état mort
     */
    @Override
    protected void initialisation(){
        Cellule[][] grille = new Cellule[getLignes()][getColonnes()];  
        for(int i=0;i<getLignes();i++){ 
            for(int j=0;j<getColonnes();j++){
                grille[i][j] = new Cellule(i,j,this,0);
            }
        }
        setGrille(grille);
    }

    /**
     * Methode qui retourne 2 si la class est GrilleVide 
     */
    @Override
    public int getMode(){
        return 2;
    }

    /**
     * Appelle la méthode initialisation() pour réinitialiser la grille toute vide
     */
    @Override 
    public void reset(){
        super.reset();
        initialisation();
    }

    /**
     * Méthode pour initialiser l'état des cellules.
     * @return false
     */
    @Override
    public boolean isRandom(){
        return false;
    }    



}