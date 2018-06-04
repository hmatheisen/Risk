package risk.logique.carte;

import java.util.ArrayList;

public class Region {

    private static ArrayList<Region> regions = new ArrayList<>();
    private String beginId, nom;

    public Region(String beginId, String nom) {
        this.beginId = beginId;
        this.nom = nom;
    }

    public static void addRegion(Region region) {
        Region.regions.add(region);
    }

    public static Region getRegionById(String id) {
        for (Region region : regions) {
            if (region.beginId.equals(id.substring(0, 1))) {
                return region;
            }
        }
        return null;
    }

    public String getNom() {
        return nom;
    }
}
