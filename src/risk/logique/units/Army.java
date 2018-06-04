package risk.logique.units;

import java.util.ArrayList;

public class Army {

    private ArrayList<Unit> units = new ArrayList<>();

    public ArrayList<Unit> getUnits() {
        return units;
    }

    public void sortByCostDecreasing() {
        units.sort((unit1, unit2) -> unit2.getType().getCost() - unit1.getType().getCost());
    }

    public int getNbUnitsByType(String type) {
        int tmp = 0;
        for (Unit unit : this.units) {
            if (unit.getType().getName().equals(type)) {
                tmp++;
            }
        }
        return tmp;
    }

    public String getLogoLinkByType(String type) {
        for (Unit unit : this.units) {
            if (unit.getType().getName().equals(type)) {
                return unit.getLogoLink();
            }
        }
        return "img/icons/source.png";
    }

    public void addNewInstanceByType(UnitType unitType) {
        switch (unitType.getName()) {
            case "Soldat":
                this.units.add(new Soldat());
                break;
            case "Cavalier":
                this.units.add(new Cavalier());
                break;
            case "Canon":
                this.units.add(new Canon());
                break;
        }
    }
}
