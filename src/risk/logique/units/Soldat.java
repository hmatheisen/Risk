package risk.logique.units;

import risk.panes.GamePane;

public class Soldat extends Unit {

    public static final UnitType TYPE = GamePane.getUnitTypeByName("Soldat");

    public Soldat() {
        super(TYPE);
    }
}
