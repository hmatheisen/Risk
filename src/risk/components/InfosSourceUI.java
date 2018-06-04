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

public class InfosSourceUI {

    private GamePane game;
    // ELEMENTS COULD BE REFRESH
    private VBox infosBox = new VBox();
    private Text infosMainTerritoire = new Text(), infosSubPlayer = new Text();
    private HBox infosSubUnits = new HBox(3);

    public InfosSourceUI(GamePane game) {
        this.game = game;
        this.infosBox.getStyleClass().add("infosSource");
        this.infosMainTerritoire.getStyleClass().addAll("infosSource-territoire");
        HBox infosMain = new HBox(3);
        // Icone
        ImageView logo = new ImageView(new Image("file:///" + new File("img/icons/source.png").getAbsolutePath().replace("\\", "/")));
        logo.getStyleClass().addAll("infosSource-logo");
        logo.setFitWidth(23);
        logo.setPreserveRatio(true);
        infosMain.setAlignment(Pos.CENTER_LEFT);
        infosMain.getChildren().addAll(logo, this.infosMainTerritoire);

        HBox infosSub = new HBox(8);
        infosSub.getStyleClass().add("infosSource-sub");
        HBox infosSubPlayer = new HBox(8);
        infosSubPlayer.getStyleClass().add("infosSource-sub-p");
        this.infosSubUnits.getStyleClass().add("infosSource-sub-units");
        HBox infosSubPlayerBox = new HBox();
        infosSubPlayerBox.getChildren().add(this.infosSubPlayer);
        this.infosSubPlayer.getStyleClass().add("infosSource-sub-player-text");
        infosSubPlayerBox.getStyleClass().add("infosSource-sub-player");
        infosSubPlayer.getChildren().addAll(infosSubPlayerBox, this.infosSubUnits);
        infosSub.getChildren().add(infosSubPlayer);
        this.infosBox.getChildren().addAll(infosMain, infosSub);
        this.refreshInfosSource();
    }

    public void refreshInfosSource() {
        this.infosMainTerritoire.setText("");
        this.infosSubPlayer.setText("");
        this.refreshInfosSourceUnits(null);
        this.infosBox.setOpacity(1);
        if (this.game.getTerritoireUIClickedFirst() == null) {
            if (this.game.getTerritoireUIActuel() == null) {
                this.infosMainTerritoire.setText("Sélectionnez un territoire source");
                this.infosSubPlayer.setText("");
                this.refreshInfosSourceUnits(null);
            } else {
                this.infosMainTerritoire.setText(this.game.getTerritoireUIActuel().getNom() + " (" + this.game.getTerritoireUIActuel().getRegion() + ")");
                this.infosSubPlayer.setText(this.game.getTerritoireUIActuel().getJoueur().getNom());
                this.refreshInfosSourceUnits(this.game.getTerritoireUIActuel());
            }
        } else {
            if (this.game.getTerritoireUIActuel() != null && this.game.getRisk().getJoueurs().get(this.game.getActualPlayer()).getRenforts() != 0) {
                this.infosMainTerritoire.setText(this.game.getTerritoireUIActuel().getNom() + " (" + this.game.getTerritoireUIActuel().getRegion() + ")");
                this.infosSubPlayer.setText(this.game.getTerritoireUIActuel().getJoueur().getNom());
                this.refreshInfosSourceUnits(this.game.getTerritoireUIActuel());
            } else {
                this.infosMainTerritoire.setText(this.game.getTerritoireUIClickedFirst().getNom() + " (" + this.game.getTerritoireUIClickedFirst().getRegion() + ")");
                this.infosSubPlayer.setText(this.game.getTerritoireUIClickedFirst().getJoueur().getNom());
                this.refreshInfosSourceUnits(this.game.getTerritoireUIClickedFirst());
                if (this.game.getRisk().getJoueurs().get(this.game.getActualPlayer()).getRenforts() == 0) {
                    this.infosBox.setOpacity(0.8);
                }
            }
        }
    }

    private void refreshInfosSourceUnits(TerritoireUI territoireUI) {
        this.infosSubUnits.getChildren().clear();
        GamePane.getUnitTypes().forEach(type -> {
            HBox typeBox = new HBox();
            if (territoireUI != null) {
//                ImageView logo = new ImageView(new Image("file:///" + new File(territoireUI.getArmy().getLogoLinkByType(type)).getAbsolutePath().replace("\\", "/")));
//                logo.setFitWidth(23);
//                logo.setPreserveRatio(true);
                Text nb = new Text(String.valueOf(territoireUI.getArmy().getNbUnitsByType(type.getName())));
                nb.getStyleClass().add("infosSource-sub-unit-text");
                Text typeText = new Text(type.getName() + "(s): ");
                typeText.getStyleClass().add("infosSource-sub-unit");
                typeBox.getChildren().addAll(typeText, nb);
            }
            typeBox.getStyleClass().add("infosSource-sub-unit");
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
