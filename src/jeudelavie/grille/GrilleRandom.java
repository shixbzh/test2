package jeudelavie.grille;

import jeudelavie.cellule.*;
import jeudelavie.interfacegraphique.*;
import jeudelavie.regles.*;

import javax.swing.*;
import java.util.*;
import java.lang.*;

/**
 * Sous class de Grilles qui initialise une grille aléatoirement
 */
public class GrilleRandom  extends Grilles{

    /**
     * Constructeur qui initialise une grille aléatoirement
     * @param lignes le nombre de lignes
     * @param colonnes le nombre de colonnes
     */
    public GrilleRandom(int lignes,int colonnes){
        super();
        setColonnes(colonnes);
        setLignes(lignes);
        initialisation();
    }

    /**
     * Méthode pour initialiser correctement la grille avec des cellules.
     */
    @Override
    protected void initialisation(){
        /*int milieulignes = getLignes() /2;
        int milieucolonnes = getColonnes() /2;
        ThreadRandom thread_nw = new ThreadRandom(0,milieulignes,0,milieucolonnes,this);
        ThreadRandom thread_ne = new ThreadRandom(milieulignes,getLignes(),0,milieucolonnes,this);
        ThreadRandom thread_sw = new ThreadRandom(0,milieulignes,milieucolonnes,getColonnes(),this);
        ThreadRandom thread_se = new ThreadRandom(milieulignes,getLignes(),milieucolonnes,getColonnes(),this);

        thread_nw.start();
        thread_ne.start();
        thread_sw.start();
        thread_se.start();*/
        Cellule[][] grille = new Cellule[getLignes()][getColonnes()];  
        for(int i=0;i<getLignes();i++){ 
            for(int j=0;j<getColonnes();j++){
                grille[i][j] = new Cellule(i,j,this);
                if(grille[i][j].getEtat() == Etat.Vivant){
                    ajoutCelluleVivant(grille[i][j]);
                }
            }
        }
        setGrille(grille);
    }

    /**
     * Methode qui retourne 0 si sa class est GrilleRandom
     */
    @Override
    public int getMode(){
        return 0;
    }

    /**
     * Méthode qui renvoie un booléen en fonction de si la grille est en mode random ou non
     * @return true
     */
    @Override
    public boolean isRandom(){
        return true;
    }

    /**
     * Méthode pour réinitialiser la grille.
     */
    @Override 
    public void reset(){
        super.reset();
        initialisation();
    }

}