package jeudelavie.grille;

import jeudelavie.cellule.*;
import jeudelavie.regles.*;

import java.util.*;


/**
 * Classe abstraite pour chaque Grilles
 */
public abstract class Grilles extends AbstractModelEcoutable{       
    
    private  Cellule[][] grille = {};
    private int lignes =0;
    private int colonnes =0;
    private boolean running = false;
    private int nbTemps = 0;
    private ArrayList<Cellule> listCelluleVivante = new ArrayList<Cellule>();


    /**
     * Méthode abstraite qui doit être implémentée par les sous-classes 
       pour initialiser correctement la grille spécifique avec des cellules.
     */
    protected abstract void initialisation();

    /**
     * Méthode qui renvoie le mode de la class
     */
    public abstract int getMode();


    /**
     * @return listCelluleVivante la liste des cellules vivantes
     */
    public ArrayList<Cellule> getCelluleVivante(){
        return listCelluleVivante;
    }

    /**
     * Ajoute la cellule spécifiée à la liste listCelluleVivante.
     * @param cel la cellule à ajouter à la liste des cellules vivantes
     */
    public void ajoutCelluleVivant(Cellule cel){
        if(cel.getEtat() == Etat.Vivant && !listCelluleVivante.contains(cel)){
            listCelluleVivante.add(cel);
        }
    }

    public void retraitCelluleMorte(Cellule cel){
        if(cel.getEtat() == Etat.Mort && listCelluleVivante.contains(cel)){
            listCelluleVivante.remove(cel);
        }
    }

    /**
     * Méthode qui renvoie un booléen en fonction de si la grille est en marche ou en pause
     * @return running
     */
    public boolean running(){
        return this.running;
    }
    
    /**
     * Méthode vide qui peut être redéfinie dans les sous-classes pour réinitialiser la grille.
     */
    public void reset(){
        nbTemps =0;
        listCelluleVivante.clear();
        running = false;
    }

    /**
     * Met l'état de fonctionnement 'running' à false, indiquant l'arrêt du jeu
     */
    public void pause(){
        this.running = false;
    }

    /**
     * Met l'état de fonctionnement 'running' à true, indiquant le début ou la reprise de la simulation 
     */
    public void start(){
        this.running = true;
    }

    /**
     * Met à jour le nombre de lignes de la grille à la valeur spécifiée
     * @param lin le nombre de lignes désirées
     */
    public void setLignes(int lin){
        this.lignes = lin;
    }

    /**
     * Met à jour le nombre de colonnes de la grille à la valeur spécifiée
     * @param col le nombre de colonnes désirées
     */
    public void setColonnes(int col){
        this.colonnes = col;
    }

    /**
     * Définit la grille de cellules à définir
     * @param grid la grille de cellules à définir pour cette grille
     */
    public void setGrille(Cellule[][] grid){
        this.grille = grid;
    }

    /**
     * Méthode qui récupère une cellule
     * @param indexLigne l'indice de la ligne de la cellule
     * @param indexColonne l'indice de la colonne de la cellule
     * @return celulle la cellule
     */
    public Cellule getCellule(int indexLigne,int indexColonne){
        int lin, col;
        lin = indexLigne;
        col = indexColonne;
        if (indexColonne<0){
            col = colonnes -1;
        }
        if(indexColonne>=colonnes){
            col = 0;
        }
        if(indexLigne<0){
            lin = lignes -1;
        }
        if(indexLigne>=lignes){
            lin =0;
        }
        return this.grille[lin][col];
    }

    /**
     * Méthode abstraite qui renvoie un booléen en fonction de si la grille est en mode random ou non
     */
    public abstract boolean isRandom();
    /**
     * Méthode pur obtenir la grille
     * @return cellule la case voulue
     */
    public Cellule[][] getGrille(){
        return this.grille;
    }

    /**
     * Méthode pour archiver toutes les cellules
     */
    public void archiveAll(){
        listCelluleVivante.clear();
        for(int i=0;i<lignes;i++){
            for(int j=0;j<colonnes;j++){
                this.grille[i][j].archive();
                if(this.grille[i][j].getArchiveEtat() == Etat.Vivant ){
                    listCelluleVivante.add(grille[i][j]);
                }
            }
        }
    }


    /**
     * Méthode qui donne la grille futur en fonction de la regle donnée en paramètre
     * @param r la règle que le jeu va implémenter
     */
    public void futur(Regles r ){
        archiveAll();
        
        int milieulignes = lignes /2;
        int milieucolonnes = colonnes /2;
        Threadfutur thread_nw = new Threadfutur(0,milieulignes,0,milieucolonnes,this,r);
        Threadfutur thread_ne = new Threadfutur(milieulignes,lignes,0,milieucolonnes,this,r);
        Threadfutur thread_sw = new Threadfutur(0,milieulignes,milieucolonnes,colonnes,this,r);
        Threadfutur thread_se = new Threadfutur(milieulignes,lignes,milieucolonnes,colonnes,this,r);

        thread_nw.start();
        thread_ne.start();
        thread_sw.start();
        thread_se.start();

        nbTemps ++;
    }

    /**
     * Compteur du nombre de générations
     * @return nbTemps la génération
     */
    public int getnbTemps(){
        return nbTemps;
    }

    /**
     * Méthode pour obtenir le nombre de lignes
     * @return this.lignes la ligne
     */
    public int getLignes(){
        return this.lignes;
    }

    /**
     * Méthode pour obtenir le nombre de colonnes
     * @return this.colonnes la colonnes
     */
    public int getColonnes(){
        return this.colonnes;
    }

}