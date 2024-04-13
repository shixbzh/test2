package jeudelavie.interfacegraphique ;

import jeudelavie.cellule.*;
import jeudelavie.grille.*;
import jeudelavie.regles.*;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * class qui affiche aussi chaque cellules de la grilles mets sans crée pour chaque cellule une class VueCellule
 */
public class VueGrille2 extends JPanel implements EcouteurModele,MouseListener{


    private Grilles grille;
    private int tailleCaseX ,tailleCaseY ;
    private GrilleGUI gui ;
    private JScrollPane scrollPane;

    /**
     * Initialise la vue de la grille avec la grille spécifiée (grille)
     * Ajoute 'this' en tant qu'écouteur à la grille (grille)
     * Active la réception des événements de souris en ajoutant 'this' en tant que MouseListener
     * @param grille la grille à afficher dans la vue
     * @param gui l'instance de grilleGUI  
     */
    public VueGrille2(Grilles grille,GrilleGUI gui){
        super();
        this.gui = gui;
        this.grille = grille;
        grille.ajoutEcouteur(this);

        addMouseListener(this);

        scrollPane = new JScrollPane(this,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        this.tailleCaseX = 0;
        this.tailleCaseY = 0 ;
        setPreferredSize(new Dimension(gui.getWidth(), (int) gui.getHeight() * 9 /10 ));
        repaint();

    }

    /**
     * Méthode qui renvoie le scrollPane 
     */
    public JScrollPane getScrollPane(){
        return scrollPane;
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

    /**
     * Méthode qui met à jour la grille en appelant la fonction reset de la grille et la fonction pause
     */
    public void restartVue(){
        this.grille.reset();
        this.grille.pause();
        repaint();  
    }


    /**
     * Methode qui renvoie un booléen en fonction de si les position x , y sont dans les limites de la grilles
     */
    public boolean clickedIn(int x,int y){
        if(x<0 || x>=grille.getLignes() || y<0 || y>=grille.getColonnes()){
            return false;
        }
        return true;

    }        

    /** 
      GESTION DU CLIQUE DE SOURIS 
     * Met à jour et affiche les vues individuelles de cellules (grilleVue)
       en appelant modeleMisAJour(this) pour chaque cellule.
     * Affiche les cellules vivantes en noir
     * @param e l'instance de MouseEvent utilisée détecter la souris
     */
    @Override
    public void mouseClicked(MouseEvent e){
        int mousePositionX = e.getY()/tailleCaseY;
        int mousePositionY = e.getX()/ tailleCaseX;
        if( clickedIn(mousePositionX,mousePositionY)){
            grille.getGrille()[mousePositionX][mousePositionY]. changeEtat();
        }
        repaint();
    }    

    /** 
      GESTION DU CLIQUE DE LA SOURIS 
     * @param e l'instance de MouseEvent utilisée détecter la souris
     */
    @Override
    public void mouseEntered(MouseEvent e){}

    /** 
      GESTION DE SORTIE DE LA SOURIS 
     * @param e l'instance de MouseEvent utilisée détecter la souris
     */
    @Override
    public void mouseExited(MouseEvent e){}

    /** 
      GESTION DE LA PRESSION DE SOURIS 
     * @param e l'instance de MouseEvent utilisée détecter la souris
     */
    @Override
    public void mousePressed(MouseEvent e){}
    
    /** 
      GESTION DU RELACHEMENT DE SOURIS 
     * @param e l'instance de MouseEvent utilisée détecter la souris
     */
    @Override
    public void mouseReleased(MouseEvent e){}


    private void setTailleCaseY(){
        tailleCaseY =  (int) gui.getHeight() / grille.getLignes() ;
        if(tailleCaseY <3){
            tailleCaseY = 3;
        }   
    }

    private void setTailleCaseX(){

        tailleCaseX = (int) gui.getWidth()/ grille.getColonnes();
        if(tailleCaseX <3){
            tailleCaseX =3;
        }
    }

     /***
     * Méthode qui peint la vue de la grille 
     * Met à jour et affiche les vues individuelles de cellules (grilleVue)
       en appelant modeleMisAJour(this) pour chaque cellule.
     * Affiche les cellules vivantes en noir
     * @param g l'instance de Graphics utilisée pour peindre la vue
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        setLayout(new GridLayout(grille.getLignes(),grille.getColonnes()));


        setTailleCaseX();
        setTailleCaseY();
        // taille du Jframe
        gui.setPreferredSize(new Dimension(tailleCaseX*grille.getColonnes()+10, gui.getHeight() ));
        
        // taille du Jpanel
        setPreferredSize(new Dimension(tailleCaseX*grille.getColonnes()+10, gui.getHeight() *17 / 20));

        //Contour de la grille
        g.drawRect(0,0,tailleCaseX * grille.getColonnes(),tailleCaseY *grille.getLignes());


        if(!grille.isRandom()){
        // Affichage des cases de la grille
            if(grille.getColonnes() == grille.getLignes()){
                for(int i=0;i<grille.getColonnes();i++){
                    g.drawLine( 0 , tailleCaseY*i , tailleCaseX * grille.getColonnes() , tailleCaseY * i  );
                    g.drawLine( tailleCaseX *i,0,tailleCaseX*i,tailleCaseY * grille.getColonnes());
                }
            }
            else{
                for(int i=0;i<grille.getLignes();i++){
                    for(int j=0;j<grille.getColonnes();j++){
                        g.drawLine(tailleCaseX*j ,tailleCaseY*i, tailleCaseX * (j+1),tailleCaseY*i);
                        g.drawLine(tailleCaseX*j,tailleCaseY*i ,tailleCaseX*j,tailleCaseY * (i+1));
                    }
                }
            }
        }
        // Colorie en Noir les cellule vivantes
        ArrayList<Cellule> listCel = new ArrayList<Cellule>(grille.getCelluleVivante());
        if(listCel != null){
            for(Cellule cel : listCel){
                g.setColor(Color.BLACK);
                g.fillRect(cel.getY()*tailleCaseX+2,cel.getX()*tailleCaseY+2,tailleCaseX-2,tailleCaseY-2);
            }
        }
        g.setColor(Color.RED);
        g.drawString("Nombre de tour : "+grille.getnbTemps() ,5, 10 );      
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