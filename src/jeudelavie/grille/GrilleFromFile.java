package jeudelavie.grille;

import jeudelavie.cellule.*;
import jeudelavie.interfacegraphique.*;
import jeudelavie.regles.*;

import javax.swing.*;
import java.util.*;
import java.lang.*;
import java.io.*;


/**
 * Sous class de Grilles qui initialise une grille via un fichier .txt qui se trouve dans le repertoire de la class
 */
public class GrilleFromFile extends Grilles{

    private String filePath;

    /**
     * Constructeur qui initialise une grille depuis un fichier
     * @param filePath le chemin d'accès au fichier
     */
    public GrilleFromFile(String filePath) {
        super();
        this.filePath = filePath;
        initialisation();
    }

    /**
     * Methode qui retourne 1 si la classe est GrilleFromFile
     */
    @Override
    public int getMode(){
        return 1;
    }


    /**
     * Méthode pour initialiser correctement la grille avec des cellules.
     */
    @Override
    protected void initialisation(){
        int i =0;
        String ligne;
        int lin =0;
        int col = 0;
        try {
            BufferedReader file = new BufferedReader(new FileReader(filePath));
            while((ligne = file.readLine()) != null){
                String[] arrayLigne = ligne.split("");
                col = arrayLigne.length;
                i ++;
            }
            lin = i;
            file.close();
            setLignes(lin);
            setColonnes(col);         
        }catch(Exception e){
            System.out.println("Erreur : "+e);
        }

        Cellule[][] grid = new Cellule[getLignes()][getColonnes()];

        try{
            BufferedReader file = new BufferedReader(new FileReader(filePath));

            i = 0;
        
            while((ligne = file.readLine()) != null){
                String[] arrayLigne = ligne.split("");
                for(int j =0;j<arrayLigne.length;j++){
                    if(arrayLigne[j].equals("1")){
                        grid[i][j] = new Cellule(i,j,this,1);
                        ajoutCelluleVivant(grid[i][j]);
                    }
                    else if(arrayLigne[j].equals("0")){
                        grid[i][j] = new Cellule(i,j,this,0);
                    }
                    col = j;
                }
                i ++;
            }
            lin = i;

            file.close();
            setColonnes(col);
            setLignes(lin);
            setGrille(grid);
        }catch(Exception e){
            System.out.println("Erreur : "+e);
        }
    }

    /**
     * Méthode pour réinitialiser la grille.
     */
    @Override 
    public void reset(){
        super.reset();
        initialisation();
    }
    
    /**
     * Méthode qui renvoie un booléen en fonction de si la grille est en mode random ou non
     * @return false
     */
    @Override
    public boolean isRandom(){
        return false;
    }    



}