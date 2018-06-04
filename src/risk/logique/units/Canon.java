package risk.logique.units;

import risk.panes.GamePane;

public class Canon extends Unit {

    public static final UnitType TYPE = GamePane.getUnitTypeByName("Canon");

    public Canon() {
        super(TYPE);
    }
}
