package jeudelavie.regles;

import java.util.*;
import jeudelavie.cellule.*;
import jeudelavie.grille.*;

public interface Regles{

     /*******************************************************************************
     * Methode qui défini le prochain état d'une cellule 
     * @param ancienEtat l'état actuel de la cellule
     * @param nbrCellV le nombre de cellules vivantes   
     * @return Etat le nouvel état
     ********************************************************************************/
    public Etat etatFutur(Etat ancienEtat, int nbrCellV);

    /************************************************
     * Methode qui renvoie les voisins d'une cellule *
     * @param cell la cellule  
     * @return voisins la liste des voisins          *
     ************************************************/
    public int voisins(Cellule cell);
}