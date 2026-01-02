import java.util.Scanner;

public class Partie {
    private Echiquier echiquier;
    private String joueurActuel;
    private boolean fin;

    public Partie() {
        this.echiquier = new Echiquier();
        this.joueurActuel = "Blanc";
        this.fin = false;
    }

    public void lancer() {
        Scanner scanner = new Scanner(System.in);

        while (!fin) {
            echiquier.afficher();
            System.out.println("Tour de: " + joueurActuel);
            System.out.print("Entrez la position de la piece a deplacer (ex: 2 B): ");
            int ligneDepart = scanner.nextInt();
            char colonneDepartChar = scanner.next().toUpperCase().charAt(0);
            int colonneDepart = colonneDepartChar - 'A' + 1;

            Piece piece = this.echiquier.getCase(ligneDepart, colonneDepart).getOccupe();

            if (piece == null || !piece.getCouleur().equals(this.joueurActuel)) {
                System.out.println("Aucune piece valide a cet endroit.");
                continue;
            }

            System.out.print("Entrez la position d'arrivee (ex: 3 B): ");
            int ligneArrivee = scanner.nextInt();
            char colonneArriveeChar = scanner.next().toUpperCase().charAt(0);
            int colonneArrivee = colonneArriveeChar - 'A' + 1;
            Case caseArrivee = this.echiquier.getCase(ligneArrivee, colonneArrivee);

            if (piece.coupOk(ligneArrivee, colonneArrivee)) {
                Piece cible = this.echiquier.getCase(ligneArrivee, colonneArrivee).getOccupe();
                if (cible == null || !cible.getCouleur().equals(this.joueurActuel)) {
                    this.echiquier.deplacerPiece(piece, caseArrivee);

                    if (estEchecEtMat(adversaire())) {
                        this.echiquier.afficher();
                        System.out.println("Echec et mat! " + joueurActuel + " gagne.");
                        fin = true;
                    } else if (estPat(adversaire())) {
                        this.echiquier.afficher();
                        System.out.println("Pat! Match nul.");
                        fin = true;
                    } else {
                        changerJoueur();
                    }
                } else {
                    System.out.println("Vous ne pouvez pas capturer votre propre piece.");
                    continue;
                }
            } else {
                System.out.println("Coup non valide pour cette piece.");
                continue;
            }
        }
        scanner.close();
    }

    private void changerJoueur() {
        joueurActuel = joueurActuel.equals("Blanc") ? "Noir" : "Blanc";
    }

    private String adversaire() {
        return joueurActuel.equals("Blanc") ? "Noir" : "Blanc";
    }

    private boolean estEchecEtMat(String couleur) {
        Piece roi = trouverRoi(couleur);
        if (roi == null || !estEnEchec(roi)) return false;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece p = echiquier.getCase(i, j).getOccupe();
                if (p != null && p.getCouleur().equals(couleur)) {
                    for (int x = 0; x < 8; x++) {
                        for (int y = 0; y < 8; y++) {
                            if (p.coupOk(x + 1, y + 1)) {
                                Piece cible = echiquier.getCase(x + 1, y + 1).getOccupe();
                                if (cible == null || !cible.getCouleur().equals(couleur)) {
                                    Piece sauvegarde = cible;
                                    int ligOld = p.getLigne();
                                    int colOld = p.getColonne();
                                    echiquier.deplacerPiece(p, this.echiquier.getCase(x + 1, y + 1));
                                    if (!estEnEchec(trouverRoi(couleur))) {
                                        echiquier.deplacerPiece(p, this.echiquier.getCase(ligOld, colOld));
                                        echiquier.getCase(x + 1, y + 1).setOccupee(sauvegarde);
                                        return false;
                                    }
                                    echiquier.deplacerPiece(p, this.echiquier.getCase(ligOld, colOld));
                                    echiquier.getCase(x + 1, y + 1).setOccupee(sauvegarde);
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    private boolean estPat(String couleur) {
        Piece roi = trouverRoi(couleur);
        if (roi == null || estEnEchec(roi)) return false;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece p = echiquier.getCase(i + 1, j + 1).getOccupe();
                if (p != null && p.getCouleur().equals(couleur)) {
                    for (int x = 0; x < 8; x++) {
                        for (int y = 0; y < 8; y++) {
                            if (p.coupOk(x + 1, y + 1)) {
                                Piece cible = echiquier.getCase(x + 1, y + 1).getOccupe();
                                if (cible == null || !cible.getCouleur().equals(couleur)) {
                                    Piece sauvegarde = cible;
                                    int ligOld = p.getLigne();
                                    int colOld = p.getColonne();
                                    echiquier.deplacerPiece(p, this.echiquier.getCase(x + 1, y + 1));
                                    if (!estEnEchec(trouverRoi(couleur))) {
                                        echiquier.deplacerPiece(p, this.echiquier.getCase(ligOld, colOld));
                                        echiquier.getCase(x + 1, y + 1).setOccupee(sauvegarde);
                                        return false;
                                    }
                                    echiquier.deplacerPiece(p, this.echiquier.getCase(ligOld, colOld));
                                    echiquier.getCase(x + 1, y + 1).setOccupee(sauvegarde);
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    private boolean estEnEchec(Piece roi) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece p = echiquier.getCase(i + 1, j + 1).getOccupe();
                if (p != null && !p.getCouleur().equals(roi.getCouleur())) {
                    if (p.coupOk(roi.getLigne(), roi.getColonne())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private Piece trouverRoi(String couleur) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece p = this.echiquier.getCase(i + 1, j + 1).getOccupe();
                if (p != null && p.getCouleur().equals(couleur) && p instanceof Roi) {
                    return p;
                }
            }
        }
        return null;
    }
}
