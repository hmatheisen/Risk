package risk.logique.units;

public class UnitType {
    private String name, linkLogo;
    private int cost;
    private int nbMovePerTurn;
    private int minPower;
    private int maxPower;
    private int attrPriority;
    private int defPriority;

    public UnitType(String name, int cost, int nbMovePerTurn, int minPower, int maxPower, int attrPriority, int defPriority, String linkLogo) {
        this.name = name;
        this.cost = cost;
        this.nbMovePerTurn = nbMovePerTurn;
        this.minPower = minPower;
        this.maxPower = maxPower;
        this.attrPriority = attrPriority;
        this.defPriority = defPriority;
        this.linkLogo = linkLogo;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public int getNbMovePerTurn() {
        return nbMovePerTurn;
    }

    public int getMinPower() {
        return minPower;
    }

    public int getMaxPower() {
        return maxPower;
    }

    public int getAttrPriority() {
        return attrPriority;
    }

    public int getDefPriority() {
        return defPriority;
    }


    public String getLinkLogo() {
        return linkLogo;
    }
}
