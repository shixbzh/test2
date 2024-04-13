    public Cellule(int x ,int y,Grilles grille){
        this.x = x;
        this.y = y;
        Cellule.grille = grille;

        if(grille.isRandom()){
            Random rdm = new Random();
            if(rdm.nextInt(2)==1){
                this.etat = Etat.Vivant;
            }
            else{
                this.etat = Etat.Mort;
            }
        }
        
        this.etatArchive = this.etat;

    }