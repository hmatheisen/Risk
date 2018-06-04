package risk.logique.carte;

import risk.logique.joueurs.Joueur;
import risk.logique.units.Army;

public class Territoire {

    private Region region;
    private Joueur joueur;
    private Army army = new Army();

    public Territoire(String id) {
        this.region = Region.getRegionById(id);
        this.joueur = null;
    }

    public Region getRegion() {
        return region;
    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public Army getArmy() {
        return army;
    }

    public boolean gotUnits() {
        return this.army.getUnits().size() != 0;
    }
}
