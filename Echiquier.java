import java.util.ArrayList;

public class Echiquier {
    private Piece[] pieces_blanches;
    private Piece[] pieces_noires;
    private Case[][] plateau;
    private ArrayList<String> historique;

    public Echiquier(){
        this.pieces_blanches = new Piece[16];
        this.pieces_noires = new Piece[16];
        this.plateau = new Case[8][8];
        this.historique = new ArrayList<String>();

        this.pieces_blanches[0] = new Tour(1,1, "Blanc");
        this.pieces_blanches[7] = new Tour(1,8, "Blanc");
        this.pieces_blanches[1] = new Cavalier(1, 2, "Blanc");
        this.pieces_blanches[6] = new Cavalier(1, 7, "Blanc");
        this.pieces_blanches[2] = new Fou(1,3,"Blanc");
        this.pieces_blanches[5] = new Fou(1, 6, "Blanc");
        this.pieces_blanches[3] = new Dame(1, 4, "Blanc");
        this.pieces_blanches[4] = new Roi(1, 5, "Blanc");
        
        this.pieces_noires[0] = new Tour(8,1, "Noir");
        this.pieces_noires[7] = new Tour(8,8, "Noir");
        this.pieces_noires[1] = new Cavalier(8, 2, "Noir");
        this.pieces_noires[6] = new Cavalier(8, 7, "Noir");
        this.pieces_noires[2] = new Fou(8,3,"Noir");
        this.pieces_noires[5] = new Fou(8, 6, "Noir");
        this.pieces_noires[3] = new Dame(8, 4,"Noir");
        this.pieces_noires[4] = new Roi(8, 5, "Noir");

        for(int i = 0; i<8; i++){
            this.pieces_blanches[i + 8] = new Pion(2, i + 1, "Blanc");
            this.pieces_noires[i + 8] = new Pion(7, i + 1, "Noir");
        }

        for(int j = 0; j < 8; j++){
            Case[] ligne = new Case[8];
            for(int i = 0; i < 8; i++){
                ligne[i] = new Case(j+1, i+1, null);
            }
            this.plateau[j] = ligne;
        }
        
        for(int i = 0; i<8; i++){
            this.plateau[0][i].setOccupee(this.pieces_blanches[i]);
            this.plateau[1][i].setOccupee(this.pieces_blanches[i+8]);

            this.plateau[7][i].setOccupee(this.pieces_noires[i]);
            this.plateau[6][i].setOccupee(this.pieces_noires[i+8]);
        }
    }

    public void afficher() {
    System.out.println("  +---+---+---+---+---+---+---+---+");
    for (int i = 7; i >= 0; i--) {
        System.out.print((i + 1) + " ");
        for (int j = 0; j < plateau[i].length; j++) {
            String contenu = plateau[i][j].afficherContenu();
            // On s'assure que le contenu est sur 1 caractère
            System.out.print("| " + (contenu.length() == 1 ? contenu + " " : contenu));
        }
        System.out.println("|");
        System.out.println("  +---+---+---+---+---+---+---+---+");
    }
    System.out.println("    A   B   C   D   E   F   G   H");
}

    public void deplacerPiece(Piece piece, Case newCase) {
    if (piece.deplacement(newCase)) {
        boolean caseVide = (newCase.getOccupe() == null);
        boolean couleurDifferente = (!caseVide && !newCase.getOccupe().getCouleur().equals(piece.getCouleur()));

        if (caseVide || couleurDifferente) {
            Case ancienneCase = this.getCase(piece.getLigne(), piece.getColonne());
            newCase.setOccupee(piece);

            // On met à jour les coordonnées de la pièce
            piece.setLigne(newCase.getLigne());
            piece.setColonne(newCase.getColonne());

            ancienneCase.setOccupee(null);
        } else {
            System.out.println("Impossible : case occupée par une pièce de la même couleur.");
        }
    } else {
        System.out.println("Veuillez choisir un déplacement valide.");
    }
}

    public void addToHistorique(String lastMove){
        historique.add(lastMove);
    }

    public Case getCase(int x, int y){
        return this.plateau[x - 1][y - 1];
    }

    public Piece getPieceNoire(int index){
        return this.pieces_noires[index];
    }

    public Piece getPieceBlanche(int index){
        return this.pieces_blanches[index];
    }

    public ArrayList<String> getHistorique(){
        return historique;
    }

    public void setCase(int index, int x, int y, boolean state){
    }

    public void setPieceNoire(int index, int x, int y){
        this.pieces_noires[index].setLigne(x);
        this.pieces_noires[index].setColonne(y);
    }

    public void setPieceBlanche(int index, int x, int y){
        this.pieces_blanches[index].setLigne(x);
        this.pieces_blanches[index].setColonne(y);
    }

    public void setHistorique(ArrayList<String> Historique){
        this.historique = Historique;
    }
}