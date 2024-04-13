package jeudelavie.grille;

import jeudelavie.cellule.*;
import jeudelavie.interfacegraphique.EcouteurModele;
import jeudelavie.regles.*;

import java.util.*;


public abstract class AbstractModelEcoutable{

    private List<EcouteurModele> listEcouteur = new ArrayList<EcouteurModele>();

    /**
     * Ajoute l'écouteur spécifié à la liste listEcouteur
     * @param e l'écouteur de modèle 
     */
    public void ajoutEcouteur(EcouteurModele e){
        listEcouteur.add(e);
    }

    /**
     * Retire l'écouteur spécifié de la liste listEcouteur 
     * @param e l'écouteur de modèle 
     */
    public void retraitEcouteur(EcouteurModele e){
        listEcouteur.remove(e);
    }

    /**
     * Notifie tous les écouteurs de modèle pour indiquer un changement dans le modèle
     */
    public void firechangement(){
        for(EcouteurModele e : this.listEcouteur){
            e.modeleMisAJour(this);
        }
    }


}