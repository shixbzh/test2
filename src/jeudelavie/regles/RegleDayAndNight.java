package jeudelavie.regles;

import jeudelavie.cellule.*;
import jeudelavie.grille.*;

import java.util.*;

public class RegleDayAndNight implements Regles {

        private int[] conditions;
    
        public RegleDayAndNight(int[] conditions){
            this.conditions = conditions;
        }
    
     /************************************************
     * Methode qui renvoie les voisins d'une cellule *
     * @param cell la cellule  
     * @return voisins la liste des voisins          *
     ************************************************/
        @Override
        public int voisins(Cellule cell){
            Cellule[] voisins = new Cellule[8];
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
    
            if((nbrCellV == 3 || nbrCellV == 4 || nbrCellV == 6  || nbrCellV == 7  || nbrCellV == 8) && ancienEtat == Etat.Vivant){
                return Etat.Vivant;
            }
    
            if((nbrCellV == 3 || nbrCellV == 6 || nbrCellV == 7 || nbrCellV == 8) && ancienEtat == Etat.Mort){
                return Etat.Vivant;
            }
            return Etat.Mort;
    
        }
    }

