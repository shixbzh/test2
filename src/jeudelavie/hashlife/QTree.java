/*package jeudelavie.hashlife;

import java.util.*;
import jeudelavie.grille.*;

public class Qtree{

    private Qtree nw;
    private Qtree ne;
    private Qtree sw;
    private Qtree se;
    private Cellule celLeaf;
    private int profondeur;

    public Qtree(Cellule cel,Qtree nw,Qtree ne,Qtree sw,Qtree se){
        this.celLeaf = cel;
        this.nw = nw;
        this.ne = ne;
        this.sw = sw;
        this.se = se;
        this.profondeur = 0;        
    }

    // Feuille
    public Qtree(Cellule cel){
        this(cel,null,null,null,null);
    }

    // Noeud
    public Qtree(Qtree nw,Qtree ne,Qtree sw,Qtree se){
        this(null,nw,ne,sw,se);
    }

    // true si on est dans une feuille false sinon
    public boolean isLeaf(){
        if(celLeaf == null){
            false;
        }
        return true;
    }




}
*/
