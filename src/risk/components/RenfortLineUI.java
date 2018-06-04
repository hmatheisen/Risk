package risk.components;

import javafx.application.Platform;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import risk.logique.units.UnitType;
import risk.panes.GamePane;

import java.io.File;

public class RenfortLineUI extends HBox {

    private GamePane game;
    private Text textNb = new Text();
    private UnitType type;
    private int actualNb;
    private TerritoireUI territoireUI;

    public RenfortLineUI(GamePane game, TerritoireUI territoireUI, UnitType type) {
        this.game = game;
        this.territoireUI = territoireUI;
        this.type = type;
        this.actualNb = territoireUI.getArmy().getNbUnitsByType(type.getName());
        Text name = new Text(type.getName().substring(0, 1).toUpperCase() + type.getName().substring(1));
        Text cost = new Text("(coût " + String.valueOf(type.getCost()) + ")");
        HBox input = new HBox();
        HBox leftArrowBox = new HBox();
        leftArrowBox.setCursor(Cursor.HAND);
        HBox rightArrowBox = new HBox();
        rightArrowBox.setCursor(Cursor.HAND);
        ImageView leftArrow = new ImageView(new Image("file:///" + new File("img/arrows/arrow_left.png").getAbsolutePath().replace("\\", "/")));
        ImageView rightArrow = new ImageView(new Image("file:///" + new File("img/arrows/arrow_right.png").getAbsolutePath().replace("\\", "/")));
        leftArrow.setFitWidth(23);
        leftArrow.setPreserveRatio(true);
        rightArrow.setFitWidth(23);
        rightArrow.setPreserveRatio(true);
        leftArrowBox.getChildren().add(leftArrow);
        rightArrowBox.getChildren().add(rightArrow);
        input.getChildren().addAll(leftArrowBox, this.textNb, rightArrowBox);
        this.getChildren().addAll(name, cost, input);
        leftArrowBox.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (this.actualNb > territoireUI.getArmy().getNbUnitsByType(type.getName())) {
                this.actualNb--;
                this.territoireUI.getJoueur().addTmpRenforts(this.type.getCost());
                refresh();
                this.game.getGameBarMiddle().refreshTitle();
            }
        });
        rightArrowBox.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            if (this.type.getCost() <= territoireUI.getJoueur().getRenfortsTmp()) {
                this.actualNb++;
                this.territoireUI.getJoueur().removeTmpRenforts(this.type.getCost());
                refresh();
                this.game.getGameBarMiddle().refreshTitle();
            }
        });
        refresh();
    }

    public void refresh() {
        this.textNb.setText(String.valueOf(actualNb));
    }

    public void apply() {
        if (hasUnitToAdd()) {
            Platform.runLater(() -> {
                int toAdd = this.actualNb - this.territoireUI.getArmy().getNbUnitsByType(this.type.getName());
                int nbTerritoiresVides = 0;
                for (TerritoireUI territoireUITmp : this.game.getRisk().getCarteUI().getTerritoiresUI()) {
                    if (territoireUITmp.getJoueur() == this.game.getRisk().getJoueurs().get(this.game.getActualPlayer()) && territoireUITmp.isEmpty() && territoireUITmp != this.territoireUI) {
                        nbTerritoiresVides++;
                    }
                }
                if (this.territoireUI.getJoueur().getRenfortsTmp() < this.game.getMinCostUnits() * nbTerritoiresVides) {
                    this.game.addTerminalInfo("     Ajout des " + this.type.getName() + "(s) impossible");
                } else {
                    for (int i = 0; i < toAdd; i++) {
                        this.territoireUI.getArmy().addNewInstanceByType(type);
                        this.territoireUI.getJoueur().applyRenforts();
                    }
                    this.game.addTerminalInfo("     Ajout de " + toAdd + " " + this.type.getName() + "(s)");
                    this.game.getBtn().refreshBtn();
                }
            });
        }
    }

    public boolean hasUnitToAdd() {
        return this.actualNb != this.territoireUI.getArmy().getNbUnitsByType(this.type.getName());
    }
}

