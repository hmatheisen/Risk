package risk.components;

import javafx.application.Platform;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import risk.logique.units.UnitType;
import risk.panes.GamePane;

import java.util.ArrayList;

public class InfosActionRenfortUI extends HBox {

    private GamePane game;
    private VBox units = new VBox(2);
    private ArrayList<RenfortLineUI> lines = new ArrayList<>();

    public InfosActionRenfortUI(GamePane game) {
        this.game = game;
        Button valid = new Button("✓");
        valid.setCursor(Cursor.HAND);
        valid.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            boolean infoDisplayed = false;
            for (RenfortLineUI line : this.lines) {
                if (line.hasUnitToAdd()) {
                    if (!infoDisplayed) {
                        String territoireName = "unknown";
                        if (this.game.getTerritoireUIActuel() != null) {
                            territoireName = this.game.getTerritoireUIActuel().getNom();
                        } else if (this.game.getTerritoireUIClickedFirst() != null) {
                            territoireName = this.game.getTerritoireUIClickedFirst().getNom();
                        }
                        this.game.addTerminalInfo("Demande d'ajout d'unité(s) à - " + territoireName + " -");
                        infoDisplayed = true;
                    }
                    line.apply();
                }
            }
            Platform.runLater(() -> {
                if (this.game.getRisk().getJoueurs().get(this.game.getActualPlayer()).getRenforts() == 0 && this.game.getNbTour() == 1) {
                    this.game.nextPlayer();
                }
            });
        });
        this.getChildren().addAll(this.units, valid);
        refresh();
    }

    public void refresh() {
        this.game.getRisk().getJoueurs().get(this.game.getActualPlayer()).resetRenforts();
        this.units.getChildren().clear();
        this.lines.clear();
        for (UnitType type : GamePane.getUnitTypes()) {
            if (this.game.getTerritoireUIActuel() != null) {
                RenfortLineUI line = new RenfortLineUI(this.game, this.game.getTerritoireUIActuel(), type);
                this.lines.add(line);
                this.units.getChildren().add(line);
            } else if (this.game.getTerritoireUIClickedFirst() != null) {
                RenfortLineUI line = new RenfortLineUI(this.game, this.game.getTerritoireUIClickedFirst(), type);
                this.lines.add(line);
                this.units.getChildren().add(line);
            }
        }
    }
}
