public class Case{
    private int ligne;
    private int colonne;
    private Piece occupe;

    public Case(){
        this.ligne = 0;
        this.colonne = 0;
        this.occupe = null;
    }
    public Case(int lig, int col, Piece piece){
        this.ligne = lig;
        this.colonne = col;
        this.occupe = piece;
    }

    public Case(Case c){
        this.ligne = c.getLigne();
        this.colonne = c.getColonne();
        this.occupe = c.getOccupe();
    }

    public Piece getOccupe(){
        return this.occupe;
    }
    
    public String afficherContenu(){
        if(this.occupe != null){
            return this.occupe.afficherNom();
        }
        return "  ";
        
    }
    public void setOccupee(Piece piece){
        this.occupe = piece;
    }

    public int getColonne(){
        return this.colonne;
    }

    public void setColonne(int col){
        this.colonne = col;
    }

    public int getLigne(){
        return this.ligne;
    }

    public void setLigne(int lig){
        this.ligne = lig;
    }

    public String toString(){
        return "Coordonnées : (" + this.ligne + ";" + this.colonne + "); " + "Occupé: " + this.occupe + ";";
    }
}