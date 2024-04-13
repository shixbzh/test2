package jeudelavie.tests;


import jeudelavie.cellule.*;
import jeudelavie.grille.*;
import jeudelavie.regles.*;



public class TestGrille{

  
        
    public static void testGrille(){
        
        Grilles g = new GrilleRandom(2,2);
            
        for(int i = 0; i < 2; i++){

            for(int j = 0; j < 2; j++){
                assert g.getGrille()[i][j].getX() >= 0 && g.getGrille()[i][j].getY() >= 0;
            }
        }

        System.out.println("Test passé !");
        
    } 

    public static void main(String[] args){
        testGrille();
    }

}