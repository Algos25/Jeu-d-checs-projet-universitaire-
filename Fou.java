public class Fou extends Piece {
    public Fou(){
        super();
    }

    public Fou(int lig, int col, String color){
        super(lig, col, color);
    }

    public Fou(Fou fou){
        super(fou);
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
            return "FB";
        }
        return "FN";
    }

    public boolean deplacement(Case destination) {
        return Math.abs(this.ligne - destination.getLigne()) == Math.abs(this.col - destination.getColonne());
    }

    public boolean coupOk(int lig, int col){
        return Math.abs(lig - this.ligne) == Math.abs(col - this.col);
    }

    public String toString(){
        return "Fou " + this.couleur + ": [Position : (" + this.ligne + " ; " + this.col + ") " + "; Est en Vie : " + this.isAlive + ";];";
    }
}
