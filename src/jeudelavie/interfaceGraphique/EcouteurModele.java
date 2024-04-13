package jeudelavie.interfacegraphique;

public interface EcouteurModele{

    /**
    * Cette méthode est appelée pour mettre à jour la vue de 
    * la cellule en réponse à un changement dans le modèle 
    * @param source l'objet
    */
    public void modeleMisAJour(Object source);

}