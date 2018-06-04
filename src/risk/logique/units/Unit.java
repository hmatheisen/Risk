package risk.logique.units;

public abstract class Unit {

    private String name, logoLink;
    private UnitType type;

    public Unit(UnitType type) {
        this.type = type;
        this.name = type.getName();
        this.logoLink = type.getLinkLogo();
    }

    public String getLogoLink() {
        return logoLink;
    }

    public UnitType getType() {
        return type;
    }
}
