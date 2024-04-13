package jeudelavie.interfacegraphique ;

import jeudelavie.cellule.*;
import jeudelavie.grille.*;
import jeudelavie.regles.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * class qui permet d'acceder a chaque VueCellule de la grille donné en utilisant les boutons
 */
public class VueGrille extends JPanel implements EcouteurModele{


    private Grilles grille;
    private VueCellule[][] grilleVue;
    private GrilleGUI gui ;

    /**
     * Initialise la vue de la grille 
     * Ajoute 'this' en tant qu'écouteur à la grille 
     * Crée une grille de vues de cellules basée sur les dimensions de la grille 
     * Ajoute les vues individuelles de cellules à la vue de la grille
     * @param grille la grille à afficher dans la vue
     * @param gui l'instance de grilleGUI  
     */
    public VueGrille(Grilles grille,GrilleGUI gui){
        super();
        this.grille = grille;
        grille.ajoutEcouteur(this);

        this.gui = gui;

        setPreferredSize(new Dimension(gui.getWidth(), (int) gui.getHeight() * 9 /10 ));

        grilleVue =  new VueCellule[this.grille.getLignes()][this.grille.getColonnes()];

        setLayout(new GridLayout(this.grille.getLignes(), this.grille.getColonnes() ));

        for(int i=0;i<this.grille.getLignes();i++){
            for(int j=0;j<this.grille.getColonnes();j++){
                VueCellule vueCellule =  new VueCellule(grille.getGrille()[i][j]);
                grilleVue[i][j] = vueCellule;
                add(vueCellule);
            }
        }
        modeleMisAJour(this);

    }

    /**
     * Méthode qui permet de changer le type de grille que la vue écoute
     */
    public void setGrille(Grilles nouvelleGrille){
        this.grille.retraitEcouteur(this);
        this.grille = nouvelleGrille;
        this.grille.ajoutEcouteur(this);
        repaint();
    }


    /* *
     * Réinitialise la grille associée en appelant reset()
     * Met à jour les vues individuelles de cellules 
     * Appelle modeleMisAJour(this) pour mettre à jour la grille
     */
    public void restartVue(){
        this.grille.reset();
        this.grille.pause();
        for(int i=0;i<this.grille.getLignes();i++){
            for(int j=0;j<this.grille.getColonnes();j++){
                grilleVue[i][j].setCellule(this.grille.getGrille()[i][j]);
            }
        }
        modeleMisAJour(this);        
    }

    
    /**
     * Méthode qui peint la vue de la grille 
     * Met à jour et affiche les vues individuelles de cellules (grilleVue)
       en appelant modeleMisAJour(this) pour chaque cellule.
     * Affiche un counteur, du nombre de mises à jour
     * @param g l'instance de Graphics utilisée pour peindre la vue
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        setPreferredSize(new Dimension(getWidth(), (int) gui.getHeight() * 9 /10 ));    

        for(int i=0;i<this.grille.getLignes();i++){
            for(int j=0;j<this.grille.getColonnes();j++){
                grilleVue[i][j].modeleMisAJour(this);
            }
        }

        g.drawString("Nombre de cout : "+grille.getnbTemps() ,5, (  (int) gui.getHeight())*9/10 -5);      
    }

    /**
     * Méthode qui Appelle repaint() pour mettre à jour visuellement la vue de la grille 
     * @param source l'objet source pour la mise à jour
     */
    @Override
    public void modeleMisAJour(Object source){
        repaint();
    }


}