package risk.logique.joueurs;

abstract public class Joueur {

    private String nom;
    private String nomLong;
    private boolean isHuman;
    private int renforts, renfortsTmp;

    public Joueur(Boolean isHuman, String nom, String nomLong) {
        this.isHuman = isHuman;
        this.nom = nom;
        this.nomLong = nomLong;
    }

    public void resetRenforts() { // reset les renforts quand on ne les a pas ajoutés à un territoire
        this.renfortsTmp = this.renforts;
    }

    public void removeTmpRenforts(int cout) {
        this.renfortsTmp = this.renfortsTmp - cout;
    }

    public void addTmpRenforts(int cout) {
        this.renfortsTmp = this.renfortsTmp + cout;
    }

    public int getRenfortsTmp() {
        return renfortsTmp;
    }

    public void applyRenforts() {
        this.renforts = this.renfortsTmp;
    }

    public int getRenforts() {
        return renforts;
    }

    public void setRenforts(int renforts) {
        this.renforts = renforts;
    }

    public String getNomLong() {
        return nomLong;
    }

    public String getNom() {
        return nom;
    }
}
