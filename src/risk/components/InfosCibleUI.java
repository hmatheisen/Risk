package risk.components;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import risk.logique.units.Unit;
import risk.panes.GamePane;

import java.io.File;

public class InfosCibleUI {

    private GamePane game;
    // ELEMENTS COULD BE REFRESH
    private VBox infosBox = new VBox(0);
    private Text infosMainTerritoire = new Text(), infosSubPlayer = new Text();
    private HBox infosSubUnits = new HBox(3);

    public InfosCibleUI(GamePane game) {
        this.game = game;
        this.infosBox.getStyleClass().add("infosCible");
        this.infosMainTerritoire.getStyleClass().addAll("infosCible-territoire");
        HBox infosMain = new HBox(3);
        ImageView logo = new ImageView(new Image("file:///" + new File("img/icons/cible.png").getAbsolutePath().replace("\\", "/")));
        logo.getStyleClass().addAll("infosCible-logo");
        logo.setFitWidth(23);
        logo.setPreserveRatio(true);
        infosMain.setAlignment(Pos.CENTER_LEFT);
        infosMain.getChildren().addAll(logo, this.infosMainTerritoire);
        HBox infosSub = new HBox(8);
        infosSub.getStyleClass().add("infosCible-sub");
        HBox infosSubPlayer = new HBox(8);
        infosSubPlayer.getStyleClass().add("infosCible-sub-p");
        this.infosSubUnits.getStyleClass().add("infosCible-sub-units");
        HBox infosSubPlayerBox = new HBox();
        infosSubPlayerBox.getChildren().add(this.infosSubPlayer);
        this.infosSubPlayer.getStyleClass().add("infosCible-sub-player-text");
        infosSubPlayerBox.getStyleClass().add("infosCible-sub-player");
        infosSubPlayer.getChildren().addAll(infosSubPlayerBox, this.infosSubUnits);
        infosSub.getChildren().add(infosSubPlayer);
        this.infosBox.getChildren().addAll(infosMain, infosSub);
        this.refreshInfosCible();
    }

    public void refreshInfosCible() {
        this.infosBox.setOpacity(1);
        if (this.game.getRisk().getJoueurs().get(this.game.getActualPlayer()).getRenforts() != 0) {
            this.infosMainTerritoire.setText("Placez vos unités avant toute action");
            this.infosSubPlayer.setText("");
            this.infosBox.setOpacity(0.8);
            this.refreshInfosCibleUnits(null);
        } else {
            if (this.game.getTerritoireUIClickedFirst() == null) {
                this.infosBox.setOpacity(0.8);
            }
            if (this.game.getTerritoireUIActuel() == null) {
                if (this.game.getTerritoireUIClickedSecond() == null) {
                    this.infosMainTerritoire.setText("Sélectionnez un territoire cible");
                    this.infosSubPlayer.setText("");
                    this.refreshInfosCibleUnits(null);
                } else {
                    this.infosMainTerritoire.setText(this.game.getTerritoireUIClickedSecond().getNom() + " (" + this.game.getTerritoireUIClickedSecond().getRegion() + ")");
                    this.infosSubPlayer.setText(this.game.getTerritoireUIClickedSecond().getJoueur().getNom());
                    this.refreshInfosCibleUnits(this.game.getTerritoireUIClickedSecond());
                    this.infosBox.setOpacity(0.8);
                }
            } else if (this.game.getTerritoireUIClickedFirst() != null) {
                this.infosMainTerritoire.setText(this.game.getTerritoireUIActuel().getNom() + " (" + this.game.getTerritoireUIActuel().getRegion() + ")");
                this.infosSubPlayer.setText(this.game.getTerritoireUIActuel().getJoueur().getNom());
                this.refreshInfosCibleUnits(this.game.getTerritoireUIActuel());
                if (this.game.getTerritoireUIActuel() == this.game.getTerritoireUIClickedSecond()) {
                    this.infosBox.setOpacity(0.8);
                }
            }
        }
    }

    private void refreshInfosCibleUnits(TerritoireUI territoireUI) {
        this.infosSubUnits.getChildren().clear();
        GamePane.getUnitTypes().forEach(type -> {
            HBox typeBox = new HBox();
            if (territoireUI != null) {
//                ImageView logo = new ImageView(new Image("file:///" + new File(territoireUI.getArmy().getLogoLinkByType(type)).getAbsolutePath().replace("\\", "/")));
//                logo.setFitWidth(23);
//                logo.setPreserveRatio(true);
                Text nb = new Text(String.valueOf(territoireUI.getArmy().getNbUnitsByType(type.getName())));
                nb.getStyleClass().add("infosCible-sub-unit-text");
                Text typeText = new Text(type.getName() + "(s): ");
                typeText.getStyleClass().add("infosCible-sub-unit");
                typeBox.getChildren().addAll(typeText, nb);
            }
            typeBox.getStyleClass().add("infosCible-sub-unit");
            this.infosSubUnits.getChildren().add(typeBox);
        });
    }

    // ---------------------------------------------------------------------------------------------------------
    // Getters & Setters
    // ---------------------------------------------------------------------------------------------------------
    public VBox getInfosBox() {
        return infosBox;
    }
}
