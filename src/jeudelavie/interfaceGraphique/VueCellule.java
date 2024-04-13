package jeudelavie.interfacegraphique ;

import jeudelavie.cellule.*;
import jeudelavie.grille.*;
import jeudelavie.regles.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class qui permet d'afficher chaque cellule en forme de Jbutton
 */
public class VueCellule extends JButton implements EcouteurModele, ActionListener  {

    private Cellule cellule;

    /**
     * Méthode qui initialise la vue de la cellule
      et ajoute 'this' en tant qu'écouteur à la cellule 
     */
    public VueCellule(Cellule cellule){
        super();
        this.cellule = cellule;
        cellule.ajoutEcouteur(this);
        addActionListener(this);
    }

    /**
     * Remplace la cellule associée à la vue de la 
       cellule par la nouvelle cellule spécifiée (nCel).
     */
    public void setCellule(Cellule nCel){
        this.cellule = nCel;
    }

    /**
     * Méthode qui Peint la vue de la cellule 
       (Etat.Mort en blanc, Etat.Vivant en noir)
     * @param g l'instance de Graphics utilisée pour peindre la vue
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        if(cellule.getEtat() == Etat.Mort){
            setBackground(Color.WHITE);
        }else{
            setBackground(Color.BLACK);
        }
    }

    /**
     * Appelle repaint() pour mettre à jour l'apparence de la vue
     * @param source l'objet source pour la mise à jour 
     */
    @Override
    public void modeleMisAJour(Object source){
        repaint();
    }

    /**
     * Invoque changeEtat() sur la cellule pour changer son état.
     * Appelle modeleMisAJour(this) pour mettre à jour la vue de la cellule 
     * @param e l'objet ActionEvent pour l'action déclanchée
     */
    @Override
    public void actionPerformed(ActionEvent e){
        cellule.changeEtat();
        modeleMisAJour(this);
    }

}
