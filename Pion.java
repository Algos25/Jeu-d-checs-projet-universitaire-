public class Pion extends Piece {
    public Pion(){
        super();
    }

    public Pion(int lig, int col, String color){
        super(lig, col, color);
    }

    public Pion(Pion pion){
        super(pion);
    }

    public boolean coupOk(int lig, int col) {
        int direction = this.couleur.equalsIgnoreCase("blanc") ? 1 : -1;
        int startRow = this.couleur.equalsIgnoreCase("blanc") ? 1 : 6;

        if (col == this.col && lig == this.ligne + direction) {
            return true;
        }
        if (col == this.col && this.ligne == startRow && lig == this.ligne + 2 * direction) {
            return true;
        }
        if (Math.abs(col - this.col) == 1 && lig == this.ligne + direction) {
            return true;
        }
        return false;
    }
    
    public boolean deplacement(Case destination) {
        int direction = this.couleur.equals("Blanc") ? 1 : -1;
        int dx = destination.getLigne() - this.ligne;
        int dy = Math.abs(destination.getColonne() - this.col);
        
        if (dy == 0) {
            if (dx == direction) return true;
            if ((this.ligne == 2 && direction == 1 || this.ligne == 7 && direction == -1) && dx == 2 * direction)
                return true;
        }
        if (dy == 1 && dx == direction) return true;

        return false;
    }

    public String afficherNom(){
        if(this.couleur == "Blanc"){
            return "PB";
        }
        return "PN";
    }
    
    public String toString(){
        return "Pion " + this.couleur + ": [Position : (" + this.ligne + " ; " + this.col + ") " + "; Est en Vie : " + this.isAlive + ";];";
    }
}
