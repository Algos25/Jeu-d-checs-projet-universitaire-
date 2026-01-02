public class Tour extends Piece {
    public Tour(){
        super();
    }

    public Tour(int lig, int col, String color){
        super(lig, col, color);
    }

    public Tour(Tour tour){
        super(tour);
    }

    public String afficherNom(){
            if(this.couleur == "Blanc"){
                return "TB";
            }
            return "TN";
        }

    public boolean deplacement(Case destination) {
        return this.ligne == destination.getLigne() || this.col == destination.getColonne();
    }
    
    public void deplacer(int x, int y){
        if(this.coupOk(x, y)){
            this.setLigne(x);
            this.setColonne(y);
        }else{
            System.out.println("Veuillez choisir un déplacement valide");
        }
    }

    public boolean coupOk(int lig, int col){
        return (lig == this.ligne && col != this.col) || (col == this.col && lig != this.ligne);
    }
    public String toString(){
        return "Tour " + this.couleur + ": [Position : (" + this.ligne + " ; " + this.col + ") " + "; Est en Vie : " + this.isAlive + ";];";
    }
}
