public class Cavalier extends Piece {
    public Cavalier(){
        super();
    }

    public Cavalier(int lig, int col, String color){
        super(lig, col, color);
    }

    public Cavalier(Cavalier cavalier){
        super(cavalier);
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
            return "CB";
        }
        return "CN";
    }

    public boolean deplacement(Case destination) {
        int dx = Math.abs(this.ligne - destination.getLigne());
        int dy = Math.abs(this.col - destination.getColonne());
        return (dx == 2 && dy == 1) || (dx == 1 && dy == 2);
    }

    public boolean coupOk(int lig, int col){
        int dLig = Math.abs(lig - this.ligne);
        int dCol = Math.abs(col - this.col);
        return (dLig == 2 && dCol == 1) || (dLig == 1 && dCol == 2);
    }

    public String toString(){
        return "Cavalier " + this.couleur + ": [Position : (" + this.ligne + " ; " + this.col + ") " + "; Est en Vie : " + this.isAlive + ";];";
    }
}
