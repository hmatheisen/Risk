package risk.components;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import risk.panes.GamePane;

import java.io.File;

public class GameBarMiddleUI {

    private GamePane game;
    // CONST
    private static final String LINK_RENFORTS = "img/icons/renforts.png";
    private static final String LINK_MOUVEMENT = "img/icons/deplacement.png";
    private static final String LINK_ATTAQUE = "img/icons/attaque.png";
    // STATE GRAPHIC
    private boolean isDisable = true;
    // ELEMENTS COULD BE REFRESH
    private VBox box = new VBox();
    private HBox subBox = new HBox();
    private ImageView logo = new ImageView();
    private Text titleText = new Text();
    private InfosActionRenfortUI renfortUI;
    private InfosActionMouvementUI mouvementUI;
    private InfosActionAttaqueUI attaqueUI;

    public GameBarMiddleUI(GamePane game) {
        this.game = game;
        this.renfortUI = new InfosActionRenfortUI(game);
        this.mouvementUI = new InfosActionMouvementUI(game);
        this.attaqueUI = new InfosActionAttaqueUI(game);
        this.box.getStyleClass().add("gameBarMiddle");
        HBox title = new HBox(6);
        title.getChildren().addAll(logo, this.titleText);
        this.box.getChildren().addAll(title, this.subBox);
        this.refreshGameBarMiddle();
    }

    public void refreshGameBarMiddle() {
        this.subBox.getChildren().clear();
        this.titleText.setText("");
        if (this.game.getRisk().getJoueurs().get(this.game.getActualPlayer()).getRenforts() != 0) {
            //this.logo.setImage();
            //this.imageView = new ImageView(new Image("file:///" + new File("img/territoires/" + id + ".png").getAbsolutePath().replace("\\", "/")));
            this.renfortUI.refresh();
            if (this.game.getTerritoireUIActuel() != null && this.game.getTerritoireUIActuel().getJoueur() == this.game.getRisk().getJoueurs().get(this.game.getActualPlayer())) {
                this.subBox.getChildren().add(this.renfortUI);
            } else if (this.game.getTerritoireUIActuel() == null && this.game.getTerritoireUIClickedFirst() != null && this.game.getTerritoireUIClickedFirst().getJoueur() == this.game.getRisk().getJoueurs().get(this.game.getActualPlayer())) {
                this.subBox.getChildren().add(this.renfortUI);
            }
        } else {
            if (this.game.getTerritoireUIClickedFirst() != null) {
                if (this.game.getTerritoireUIActuel() != null && this.game.getTerritoireUIActuel() != this.game.getTerritoireUIClickedFirst()) {
                    if (this.game.getTerritoireUIActuel().getJoueur() == this.game.getTerritoireUIClickedFirst().getJoueur()) {
                        this.mouvementUI.refresh();
                        this.subBox.getChildren().add(this.mouvementUI);
                    } else {
                        this.attaqueUI.refresh();
                        this.subBox.getChildren().add(this.attaqueUI);
                    }
                } else if (this.game.getTerritoireUIClickedSecond() != null) {
                    if (this.game.getTerritoireUIClickedSecond().getJoueur() == this.game.getTerritoireUIClickedFirst().getJoueur()) {
                        this.mouvementUI.refresh();
                        this.subBox.getChildren().add(this.mouvementUI);
                    } else {
                        this.attaqueUI.refresh();
                        this.subBox.getChildren().add(this.attaqueUI);
                    }
                }
            }
        }
        this.refreshTitle();
    }

    public void refreshTitle() {
        this.titleText.setText("");
        if (this.game.getRisk().getJoueurs().get(this.game.getActualPlayer()).getRenforts() != 0) {
            this.titleText.setText("Renforts (restants: " + this.game.getRisk().getJoueurs().get(this.game.getActualPlayer()).getRenfortsTmp() + ")");
        } else {
            if (this.game.getTerritoireUIClickedFirst() != null) {
                if (this.game.getTerritoireUIActuel() != null && this.game.getTerritoireUIActuel() != this.game.getTerritoireUIClickedFirst()) {
                    if (this.game.getTerritoireUIActuel().getJoueur() == this.game.getTerritoireUIClickedFirst().getJoueur()) {
                        this.titleText.setText("Déplacement d'unités");
                    } else {
                        this.titleText.setText("Attaque de territoire");
                    }
                } else if (this.game.getTerritoireUIClickedSecond() != null) {
                    if (this.game.getTerritoireUIClickedSecond().getJoueur() == this.game.getTerritoireUIClickedFirst().getJoueur()) {
                        this.titleText.setText("Déplacement d'unités");
                    } else {
                        this.titleText.setText("Attaque de territoire");
                    }
                }
            }
        }
    }

    public VBox getBox() {
        return box;
    }
}
