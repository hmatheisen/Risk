package risk.logique.units;

import risk.panes.GamePane;

public class Cavalier extends Unit {

    public static final UnitType TYPE = GamePane.getUnitTypeByName("Cavalier");

    public Cavalier() {
        super(TYPE);
    }
}
