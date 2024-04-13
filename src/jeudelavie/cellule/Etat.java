package jeudelavie.cellule;

/**
 * Enumération de l' 'Etat' qui permet de gérer plusieurs état
 * @param couleur la couleur associé à l'état
 * etat Mort = Noir
 * etat Vivant = Blanc
 */
public enum Etat{
        Mort("Noir") 
        , Vivant("Blanc");

        private String couleur;

        private Etat(String couleur) {
            this.couleur = couleur;
        }
}