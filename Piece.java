public abstract class Piece {
    protected int col;
    protected int ligne;
    protected String couleur;
    protected boolean isAlive;

    public Piece(int lig, int c, String color) {
        this.ligne = lig;
        this.col = c;
        this.isAlive = true;
        this.couleur = color;
    }

    public Piece() {
        this.ligne = 0;
        this.col = 0;
        this.isAlive = true;
        this.couleur = "Blanc";
    }

    public Piece(Piece piece) {
        this.ligne = piece.getLigne();
        this.col = piece.getColonne();
        this.couleur = piece.getCouleur();
        this.isAlive = piece.getIsAlive();
    }

    // === Méthodes abstraites ===

    public abstract String afficherNom();

    public abstract boolean coupOk(int lig, int col);

    public abstract boolean deplacement(Case destination); // ✅ ajoutée pour le polymorphisme

    // === Getters / Setters ===

    public int getColonne() {
        return this.col;
    }

    public int getLigne() {
        return this.ligne;
    }

    public String getCouleur() {
        return this.couleur;
    }

    public boolean getIsAlive() {
        return this.isAlive;
    }

    public void setColonne(int colonne) {
        this.col = colonne;
    }

    public void setLigne(int lig) {
        this.ligne = lig;
    }

    public void setCouleur(String color) {
        this.couleur = color;
    }

    public void setIsAlive(boolean state) {
        this.isAlive = state;
    }

    public String toString() {
        return "Position : (" + this.ligne + " ; " + this.col + "); Couleur : " + this.couleur + "; Est en Vie : " + this.isAlive + ";";
    }
}