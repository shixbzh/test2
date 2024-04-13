package jeudelavie.interfacegraphique ;

import jeudelavie.cellule.*;
import jeudelavie.grille.*;
import jeudelavie.regles.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Class qui permet de generer la fenêtre de l'interface graphique
 */
public class GrilleGUI extends JFrame implements ActionListener{

    private Grilles grille;
    private VueGrille2 vueGrille;
    private VueBoutons vueBoutons;
    static final Dimension DIM = new Dimension(500,500);

    /**************************************************************** 
    * La fenêtre de l'interface graphique GrilleGUI est initialisée 
    * avec une grille vide par défaut de taille 50x50 
    *****************************************************************/
    public GrilleGUI(){
        this(new GrilleVide(100,100));
    }

    /**
     * Méthode qui initialise la grille en interface graphique
     */
    public GrilleGUI(Grilles grille){
        super("Jeu de la vie");

        this.grille = grille;
        vueGrille = new VueGrille2(this.grille,this);

        vueBoutons = new VueBoutons(this.grille,vueGrille,this);

        
        setMinimumSize(DIM);

        Container cp = this.getContentPane();
        setLayout(new BorderLayout());
        
        int hauteurFrame = Toolkit.getDefaultToolkit().getScreenSize().height;
        int largeurFrame = Toolkit.getDefaultToolkit().getScreenSize().width;

        hauteurFrame = 800;
        largeurFrame = 700;

        vueGrille.setPreferredSize(new Dimension( largeurFrame , (int) hauteurFrame * 19 /20 ));
        vueBoutons.setPreferredSize(new Dimension(largeurFrame ,(int) hauteurFrame /10 ));


        cp.add(vueGrille.getScrollPane(),BorderLayout.NORTH);
        cp.add(vueBoutons,BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);

    }

    /**
     * Méthode qui appelle la méthode firechangement() 
     * sur l'instance de la grille
     * @param e l'objet ActionEvent pour l'action déclanchée
     */
    @Override
    public void actionPerformed(ActionEvent e){
        this.grille.firechangement();
    }

    /**
     * Met à jour la vue de la grille en appelant 
     * modeleMisAJour(this) sur l'instance de vueGrille.
     */
    public void update(Regles r){
        grille.futur(r);
        vueGrille.modeleMisAJour(this);
    }

    /**
     * Change la grille en la nouvelle grille 
     * @param nouvelleGrille
     */
    public void setGrille(Grilles nouvelleGrille){
        this.grille = nouvelleGrille;
    }

    /**
     * Methode qui renvoie la Grilles de la class écoute
     */
    public Grilles getGrille(){
        return this.grille;
    }

}