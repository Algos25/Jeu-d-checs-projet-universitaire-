public class Roi extends Piece {
    public Roi(){
        super();
    }

    public Roi(int lig, int col, String color){
        super(lig, col, color);
    }

    public Roi(Roi roi){
        super(roi);
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
            return "RB";
        }
        return "RN";
    }

    public boolean deplacement(Case destination) {
        int dx = Math.abs(this.ligne - destination.getLigne());
        int dy = Math.abs(this.col - destination.getColonne());
        return dx <= 1 && dy <= 1;
    }

    public boolean coupOk(int lig, int col){
        int dLig = Math.abs(lig - this.ligne);
        int dCol = Math.abs(col - this.col);

        return (dLig <= 1 && dCol <= 1) && !(dLig == 0 && dCol == 0);
    }
    public String toString(){
        return "Roi " + this.couleur + ": [Position : (" + this.ligne + " ; " + this.col + ") " + "; Est en Vie : " + this.isAlive + ";];";
    }
}
