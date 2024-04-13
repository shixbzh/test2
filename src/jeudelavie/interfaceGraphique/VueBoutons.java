package jeudelavie.interfacegraphique ;

import jeudelavie.cellule.*;
import jeudelavie.grille.*;
import jeudelavie.regles.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 * Class qui permet de controler le modèle de class Grilles 
 */
public class VueBoutons extends JPanel implements ActionListener{

    private JButton bStartPause,bReset,bMode;
    private Grilles grille;
    private VueGrille2 vueGrille;
    private ArrayList<JButton> boutonTab = new ArrayList<JButton>();
    private GrilleGUI gui;
    private final String[] listeMode = {"Random","Fichier","Vide"};
    public final int RANDOM = 0;
    public final int FICHIER = 1;
    public final int VIDE = 2;
    private int mode;   

    /**
     * Constructeur: Initialise les boutons "Start" et "Reset" dans la vue des boutons.
     * Les boutons sont configurés pour écouter les événements d'action (clics de bouton)
     * @param grille la grille concernée
     * @param vueGrille la vue de la grille
     * @param gui une instance de la classe GrilleGUI
     */
    public VueBoutons(Grilles grille,VueGrille2 vueGrille,GrilleGUI gui){
        super();
        this.grille = grille;
        this.vueGrille = vueGrille;
        this.gui = gui;

        setLayout(new GridLayout(0,3));

        bStartPause = new JButton("Start");
        bStartPause.addActionListener(this);
        boutonTab.add(bStartPause);

        bReset = new JButton("Reset");
        bReset.addActionListener(this);
        boutonTab.add(bReset);

        this.mode = this.grille.getMode();

        bMode = new JButton("Changer le mode ("+listeMode[getModeSuivant()]+")");
        bMode.addActionListener(this);
        boutonTab.add(bMode);
        add(bStartPause);
        add(bReset);
        add(bMode);
        repaint();

    }  

    /**
     * Méthode qui définit la taille des boutons en fonction de la fenêtre
     * Ajuste la taille des boutons dans boutonTab
     * @param g l'instance de Graphics utilisée pour peindre la vue
     */
    @Override
    public void paintComponent(Graphics g){
        int hauteur = (int) gui.getHeight() /30;
        setPreferredSize(new Dimension(gui.getWidth(), hauteur ));
        for(JButton button : boutonTab){
            button.setPreferredSize(new Dimension(gui.getWidth() / boutonTab.size(), hauteur ));
        }
    }

    /**
     * Méthode qui renvoie le prochain mode de grille disponible
     */
    public int getModeSuivant(){
        int newMode = mode ;
        newMode ++;
        if(newMode >VIDE){
            newMode = RANDOM;
        }
        return newMode;    
    }

    /**
     * Méthode qui répond aux événements d'action avec "Start" , "Reset" et "Changer le mode".
     * Si le bouton "Start" est cliqué , la méthode start() de la grille est appelée
     * Si la grille est en cours d'exécution, la méthode "Start" laisse place à pause()
     * Si le bouton "Reset" est cliqué, la méthode restartVue() est appelée
     * Si le bouton "Changer le mode" est cliqué, la grille change de sous-class et passe à
     * la suivante sous-class de l'interface grilles
     * @param e l'objet ActionEvent pour l'action déclanchée
     */
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == bStartPause && !grille.running()){
            grille.start();
            bStartPause.setText("Pause");
        }
        else if (e.getSource() == bStartPause){
            grille.pause();
            bStartPause.setText("Start");
        }
        if(e.getSource() == bReset){
            vueGrille.restartVue();
            bStartPause.setText("Start");
        }
        if(e.getSource() == bMode){
            mode = getModeSuivant();
            bMode.setText("Changer le mode ("+listeMode[getModeSuivant()]+")");

            if(mode == VIDE){
                gui.setGrille(new GrilleVide(100,100) ) ;
            }
            if(mode == RANDOM){
                gui.setGrille( new GrilleRandom(600,600) );
            }
            if(mode == FICHIER){
                String path = System.getProperty("user.dir")+"/src/jeudelavie/grille/grilleDefini.txt";
                gui.setGrille( new GrilleFromFile(path) ) ;                
            }
            bStartPause.setText("Start");
            this.grille = gui.getGrille();
            vueGrille.setGrille(gui.getGrille());
        }

    }


}