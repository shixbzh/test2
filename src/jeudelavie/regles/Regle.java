package jeudelavie.regles;

import jeudelavie.cellule.*;
import jeudelavie.grille.*;


import java.util.*;


public class Regle implements Regles{

    private int[][] conditions;

    public Regle(int[][] conditions){
        this.conditions = conditions;
    }

    /*******************************************************************************
     * Methode qui obtient les voisins d'une cellule 
     * @param cell la cellule concernée
     * @return voisins la liste des voisins
     ********************************************************************************/
    @Override
    public int voisins(Cellule cell){
        Cellule[] voisins = new Cellule[8];
        //System.out.println(cell.getX()+" "+cell.getY()+" "+cell.getEtat()+" "+cell.getArchiveEtat());
        voisins[0] = cell.getGrille().getCellule(cell.getX() -1,cell.getY() +1);
        voisins[1] = cell.getGrille().getCellule(cell.getX(),cell.getY()+1);
        voisins[2] = cell.getGrille().getCellule(cell.getX()+1,cell.getY()+1);
        voisins[3] = cell.getGrille().getCellule(cell.getX()+1,cell.getY());
        voisins[4] = cell.getGrille().getCellule(cell.getX()+1,cell.getY()-1);
        voisins[5] = cell.getGrille().getCellule(cell.getX(),cell.getY()-1);
        voisins[6] = cell.getGrille().getCellule(cell.getX()-1,cell.getY()-1);
        voisins[7] = cell.getGrille().getCellule(cell.getX()-1,cell.getY());
        int cpt = 0;
        for(Cellule c : voisins){
            //System.out.println(c.getX()+" "+c.getY());
            if(c.getArchiveEtat() == Etat.Vivant){
                cpt++;
            }
        }
        
        return cpt;
    }

     /*******************************************************************************
     * Methode qui défini le prochain état d'une cellule 
     * @param ancienEtat l'état actuel de la cellule
     * @param nbrCellV le nombre de cellules vivantes   
     * @return Etat le nouvel état
     ********************************************************************************/
    @Override
    public Etat etatFutur(Etat ancienEtat , int nbrCellV){

        if(ancienEtat == Etat.Vivant){
            return survie(this.conditions, nbrCellV);
        }

        if(ancienEtat == Etat.Mort){
            return naissance(this.conditions, nbrCellV);
        }

        return Etat.Mort;
    }
     /*******************************************************************************
     * Methode qui détermine si une cellule survie à la prochaine génération 
     * @param nbNecessaire le nombre de voisins nécessaire pour que la cellule survive
     * @param nbrCellV le nombre de cellules vivantes   
     * @return Etat le nouvel état
     ********************************************************************************/
    public Etat survie(int[][] nbNecessaire, int nbrCellV){
        for(int i = 0; i < nbNecessaire[0].length; i ++){
            if(nbNecessaire[0][i] == nbrCellV){
                return Etat.Vivant;
            }
        }
        return Etat.Mort;
    }
     /*******************************************************************************
     * Methode qui détermine si une cellule naît à la prochaine génération 
     * @param nbNecessaire le nombre de voisins nécessaire pour que la cellule survive
     * @param nbrCellV le nombre de cellules vivantes   
     * @return Etat le nouvel état
     ********************************************************************************/
    public Etat naissance(int[][] nbNecessaire, int nbrCellV){
        for(int i = 0; i < nbNecessaire[1].length; i ++){
            if(nbNecessaire[1][i] == nbrCellV){
                return Etat.Vivant;
            }
        }
        return Etat.Mort;
    }
}

