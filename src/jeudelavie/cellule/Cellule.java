package jeudelavie.cellule;

import java.util.*;
import jeudelavie.grille.*;


/**
 * Class Cellule qui permet de gerer chaque case d'un grille de la class abstraite Grilles
 */
public class Cellule extends AbstractModelEcoutable{

    private static Grilles grille;
    private Etat etat ;
    private Etat etatArchive;
    private int x;
    private int y;


    /**
     * Constructeur de la classe Cellule pour des cellules avec un etat aléatoire
     * @requires grille != null
     * @param x la coordonnée X
     * @param y la coordonnée Y
     * @param grille la grille concernée
     */
    public Cellule(int x ,int y,Grilles grille){
        this.x = x;
        this.y = y;
        Cellule.grille = grille;

        if(grille.isRandom()){
            Random rdm = new Random();
            if(rdm.nextInt(2)==1){
                this.etat = Etat.Vivant;
            }
            else{
                this.etat = Etat.Mort;
            }
        }
        
        this.etatArchive = this.etat;

    }

    /**
     * Cronstructeur de la classe Cellule pour des cellules avec un état prédefini
     * @param x la coordonnée X
     * @param y la coordonnée Y
     * @param grille la grille concernée
     * @param etatInitial l'état de la cellule lors de la création
     */
    public Cellule(int x, int y,Grilles grille,int etatInitial){
        this(x,y,grille);
        if(etatInitial ==0){
            this.etat = Etat.Mort;
        }
        else{
            this.etat = Etat.Vivant;
        }
    }

    /**
     * Retourne la grille à laquelle appartient la cellule 
     * @return this.grille
     */
    public Grilles getGrille(){
        return Cellule.grille;
    }

    /**
     * Retourne l'état archivé de la cellule
     * @return this.etatArchive
     */
    public Etat getArchiveEtat(){
        return this.etatArchive;
    }

    /**
     * Retourne l'etat de la cellule 
     * @return this.etat
     */
    public Etat getEtat(){
        return this.etat;
    }

    /**
     * Retourne la coordonnée x de la cellule
     * @return this.x
     */
    public int getX(){
        return this.x;
    }
    
    /**
     * Retourne la coordonnée x de la cellule
     * @return this.y
     */
    public int getY(){
        return this.y;
    }

    /**
     * Change l'état de la cellule en un nouvelEtat
     */
    public void setEtat(Etat nouvelEtat){
        this.etat = nouvelEtat;
    }

    /**
     * Change l'état de la cellule
     */
    public void changeEtat(){
        if(etat == Etat.Mort){
            etat = Etat.Vivant;
            grille.ajoutCelluleVivant(this);
        }else{
            etat = Etat.Mort;
            grille.retraitCelluleMorte(this);
        }
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères de la cellule
     * @return "⓱" pour vivant, " " pour mort
     */
    @Override
    public String toString(){
        if(this.etat == Etat.Vivant){
            return " ⓱ ";
        }
        return "   ";
    }


    /**
     * Archive l'Etat actuel de la cellule
     */
    public void archive(){
        this.etatArchive = this.etat;
    }

    

}




