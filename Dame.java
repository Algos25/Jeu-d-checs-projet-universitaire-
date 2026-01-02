public class Dame extends Piece {
    public Dame(){
        super();
    }

    public Dame(int lig, int col, String color){
        super(lig, col, color);
    }

    public Dame(Dame dame){
        super(dame);
    }

    public void deplacer(int x, int y){
        if(this.coupOk(x, y)){
            this.setLigne(x);
            this.setColonne(y);
        }else{
            System.out.println("Veuillez choisir un déplacement valide");
        }
    }

    public String afficherNom(){
        if(this.couleur == "Blanc"){
            return "DB";
        }
        return "DN";
    }

    public boolean deplacement(Case destination) {
        int dx = Math.abs(this.ligne - destination.getLigne());
        int dy = Math.abs(this.col - destination.getColonne());
        return (this.ligne == destination.getLigne() || this.col == destination.getColonne()) || (dx == dy);
    }

    public boolean coupOk(int lig, int col){
        int dLig = Math.abs(lig - this.ligne);
        int dCol = Math.abs(col - this.col);

        boolean mouvementTour = (lig == this.ligne && col != this.col) || (col == this.col && lig != this.ligne);
        boolean mouvementFou = dLig == dCol && dLig != 0;
        return mouvementTour || mouvementFou;
    }
    public String toString(){
        return "Dame " + this.couleur + ": [Position : (" + this.ligne + " ; " + this.col + ") " + "; Est en Vie : " + this.isAlive + ";];";
    }
}
