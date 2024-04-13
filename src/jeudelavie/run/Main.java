package jeudelavie.run ;

import jeudelavie.interfacegraphique.*;
import jeudelavie.cellule.*;
import jeudelavie.grille.*;
import jeudelavie.regles.*;

import java.util.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.io.*;

/**
 * Class qui permet d'executer une grille 
 */
public class Main{

    public static void main(String[] args) throws FileNotFoundException, IOException {

         
        GrilleGUI gui = new GrilleGUI(new GrilleRandom(200,500));
 
        int[][] conditions = {{2,3},{3}};
        Regles r = new Regle(conditions);

        Timer timer = new Timer();


        for(int a = 0; a < 2000; a++){
            final int iteration = a;
            timer.schedule(new TimerTask(){
            
            /**
             * La méthode qui lance la simulation du jeu de la vie
             */
            @Override
            public void run(){
                Grilles grille = gui.getGrille();
                if(gui.getGrille().running()){

                    gui.update(r);
                }

             }
             },200* (iteration + 1));
        }
 }

}
