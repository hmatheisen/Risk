package risk.panes;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import risk.Risk;

import java.io.File;
import java.util.ArrayList;

public class MenuPane extends StackPane {

    private Risk risk;
    // CONST
    private static final int NBSELECTIONS = 3;
    // STATE GRAPHIC
    private int itemSelected = 1;
    private int nbHumain = 2, nbIA = 0, idCarte = 0;
    // ELEMENTS COULD BE REFRESH
    private ArrayList<Text> labelsSelections = new ArrayList<>();
    private ArrayList<Text> valuesSelections = new ArrayList<>();

    public MenuPane(Risk risk) {
        this.getChildren().addAll(this.backgroundPane());
        this.risk = risk;
        this.configKeys();
        this.afficher();
    }

    private void configKeys() {
        Platform.runLater(() -> this.risk.getScene().setOnKeyPressed(ke -> {
            if (ke.getCode().equals(KeyCode.UP)) {
                if (this.itemSelected == 1) {
                    this.itemSelected = NBSELECTIONS;
                } else {
                    this.itemSelected--;
                }
                this.refreshLabelsSelections();
            } else if (ke.getCode().equals(KeyCode.DOWN)) {
                if (this.itemSelected == NBSELECTIONS) {
                    this.itemSelected = 1;
                } else {
                    this.itemSelected++;
                }
                this.refreshLabelsSelections();
            } else if (ke.getCode().equals(KeyCode.RIGHT)) {
                if (this.itemSelected == 1 && this.nbHumain < 6) {
                    this.nbHumain++;
                    this.refreshValuesSelections();
                } else if (this.itemSelected == 2 && this.nbIA < 5) {
                    this.nbIA++;
                    this.refreshValuesSelections();
                } else if (this.itemSelected == 3) {
                    if (this.idCarte + 1 == Risk.CARTES.size()) {
                        this.idCarte = 0;
                    } else {
                        this.idCarte++;
                    }
                    this.refreshValuesSelections();
                }
            } else if (ke.getCode().equals(KeyCode.LEFT)) {
                if (this.itemSelected == 1 && this.nbHumain > 2) {
                    this.nbHumain--;
                    this.refreshValuesSelections();
                } else if (this.itemSelected == 2 && this.nbIA > 0) {
                    this.nbIA--;
                    this.refreshValuesSelections();
                } else if (this.itemSelected == 3) {
                    if (this.idCarte == 0) {
                        this.idCarte = Risk.CARTES.size() - 1;
                    } else {
                        this.idCarte--;
                    }
                    this.refreshValuesSelections();
                }
            } else if (ke.getCode().equals(KeyCode.ENTER)) {
                this.risk.jouer(this.nbHumain, this.nbIA, Risk.CARTES.get(this.idCarte));
            }
        }));
    }

    private void afficher() {
        VBox menuBox = new VBox();
        menuBox.getChildren().addAll(this.titreBox(), this.selectionsBox(), this.btnsBox());
        menuBox.getStyleClass().add("menuBox");
        menuBox.setBorder(new Border(new BorderStroke(Color.BLACK, null, null, null)));
        this.getChildren().addAll(menuBox);
    }

    // ---------------------------------------------------------------------------------------------------------
    // BackgoundPane
    // ---------------------------------------------------------------------------------------------------------
    private Pane backgroundPane() {
        Pane pane = new Pane();
        pane.getStyleClass().add("backround");
        ImageView background = new ImageView(new Image("file:///" + new File("img/Accueil.png").getAbsolutePath().replace("\\", "/")));
        background.setX(0);
        background.setY(0);
        background.setPreserveRatio(true);
        pane.getChildren().addAll(background);
        // return
        return pane;
    }

    // ---------------------------------------------------------------------------------------------------------
    // TitreBox
    // ---------------------------------------------------------------------------------------------------------
    private HBox titreBox() {
        HBox box = new HBox();
        Text titre = new Text("Menu");
        titre.getStyleClass().add("titreBox-text");
        box.getChildren().addAll(titre);
        box.getStyleClass().add("titreBox");
        return box;
    }

    // ---------------------------------------------------------------------------------------------------------
    // SelectionsBox
    // ---------------------------------------------------------------------------------------------------------
    private VBox selectionsBox() {
        VBox box = new VBox(30);
        box.getChildren().addAll(itemSelectionHumansBox(), itemSelectionAIBox(), itemSelectionCarteBox());
        box.getStyleClass().add("selectionsBox");
        return box;
    }

    private String getTextSelection(int id) {
        String txt;
        if (this.itemSelected == id) {
            txt = "▪  ";
        } else {
            txt = "   ";
        }
        switch (id) {
            case 1:
                txt += "Nombre d'humains (2-6)";
                break;
            case 2:
                txt += "Nombre d'IA (0-5)";
                break;
            case 3:
                txt += "CarteUI";
        }
        return txt;
    }

    private void refreshLabelsSelections() {
        for (int i = 0; i < NBSELECTIONS; i++) {
            this.labelsSelections.get(i).setText(getTextSelection(i + 1));
        }
    }

    private String getTextSelectionValue(int id) {
        switch (id) {
            case 1:
                return String.valueOf(this.nbHumain);
            case 2:
                return String.valueOf(this.nbIA);
            case 3:
                return Risk.CARTES.get(this.idCarte).getLabel();
        }
        return "";
    }

    private void refreshValuesSelections() {
        for (int i = 0; i < NBSELECTIONS; i++) {
            this.valuesSelections.get(i).setText(getTextSelectionValue(i + 1));
        }
    }

    // ---------------------------------------------------------------------------------------------------------
    // HumanSelectionsBox
    // ---------------------------------------------------------------------------------------------------------
    private HBox itemSelectionHumansBox() {
        HBox box = new HBox();
        // label
        HBox boxLabel = new HBox();
        Text label = new Text(this.getTextSelection(1));
        this.labelsSelections.add(label);
        label.getStyleClass().add("selectionsBox-label-text");
        boxLabel.getChildren().addAll(label);
        boxLabel.getStyleClass().add("selectionsBox-label");
        // selection
        HBox selectionBox = new HBox();
        selectionBox.getStyleClass().add("selectionsBox-selection");
        ImageView leftArrow = new ImageView(new Image("file:///" + new File("img/arrows/arrow_left.png").getAbsolutePath().replace("\\", "/")));
        Text valeur = new Text(this.getTextSelectionValue(1));
        this.valuesSelections.add(valeur);
        ImageView rightArrow = new ImageView(new Image("file:///" + new File("img/arrows/arrow_right.png").getAbsolutePath().replace("\\", "/")));
        // events
        leftArrow.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (this.nbHumain > 2) {
                this.nbHumain--;
                valeur.setText(this.getTextSelectionValue(1)); // important pour rafraîchir
            }
            event.consume();
        });
        rightArrow.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (this.nbHumain < 6) {
                this.nbHumain++;
                valeur.setText(this.getTextSelectionValue(1));
            }
            event.consume();
        });
        selectionBox.getChildren().addAll(leftArrow, valeur, rightArrow);
        box.getChildren().addAll(boxLabel, selectionBox);
        return box;
    }

    // ---------------------------------------------------------------------------------------------------------
    // AISelectionsBox
    // ---------------------------------------------------------------------------------------------------------
    private HBox itemSelectionAIBox() {
        HBox box = new HBox();
        // label
        HBox boxLabel = new HBox();
        Text label = new Text(this.getTextSelection(2));
        this.labelsSelections.add(label);
        label.getStyleClass().add("selectionsBox-label-text");
        boxLabel.getChildren().addAll(label);
        boxLabel.getStyleClass().add("selectionsBox-label");
        // selection
        HBox selectionBox = new HBox();
        selectionBox.getStyleClass().add("selectionsBox-selection");
        ImageView leftArrow = new ImageView(new Image("file:///" + new File("img/arrows/arrow_left.png").getAbsolutePath().replace("\\", "/")));
        Text valeur = new Text(this.getTextSelectionValue(2));
        this.valuesSelections.add(valeur);
        ImageView rightArrow = new ImageView(new Image("file:///" + new File("img/arrows/arrow_right.png").getAbsolutePath().replace("\\", "/")));
        // events
        leftArrow.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (this.nbIA > 0) {
                this.nbIA--;
                valeur.setText(this.getTextSelectionValue(2)); // important pour rafraîchir
            }
            event.consume();
        });
        rightArrow.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (this.nbIA < 5) {
                this.nbIA++;
                valeur.setText(this.getTextSelectionValue(2));
            }
            event.consume();
        });
        selectionBox.getChildren().addAll(leftArrow, valeur, rightArrow);
        box.getChildren().addAll(boxLabel, selectionBox);
        return box;
    }

    // ---------------------------------------------------------------------------------------------------------
    // Risk.CARTESelectionsBox
    // ---------------------------------------------------------------------------------------------------------
    private HBox itemSelectionCarteBox() {
        HBox box = new HBox();
        // label
        HBox boxLabel = new HBox();
        Text label = new Text(this.getTextSelection(3));
        this.labelsSelections.add(label);
        label.getStyleClass().add("selectionsBox-label-text");
        boxLabel.getChildren().addAll(label);
        boxLabel.getStyleClass().add("selectionsBox-label");
        // selection
        HBox selectionBox = new HBox();
        selectionBox.getStyleClass().add("selectionsBox-selection");
        ImageView leftArrow = new ImageView(new Image("file:///" + new File("img/arrows/arrow_left.png").getAbsolutePath().replace("\\", "/")));
        Text valeur = new Text(this.getTextSelectionValue(3));
        this.valuesSelections.add(valeur);
        ImageView rightArrow = new ImageView(new Image("file:///" + new File("img/arrows/arrow_right.png").getAbsolutePath().replace("\\", "/")));
        // events
        leftArrow.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (this.idCarte == 0) {
                this.idCarte = Risk.CARTES.size() - 1;
            } else {
                this.idCarte--;
            }
            valeur.setText(this.getTextSelectionValue(3)); // important pour rafraîchir
            event.consume();
        });
        rightArrow.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (this.idCarte + 1 == Risk.CARTES.size()) {
                this.idCarte = 0;
            } else {
                this.idCarte++;
            }
            valeur.setText(this.getTextSelectionValue(3)); // important pour rafraîchir
            event.consume();
        });
        selectionBox.getChildren().addAll(leftArrow, valeur, rightArrow);
        box.getChildren().addAll(boxLabel, selectionBox);
        return box;
    }

    // ---------------------------------------------------------------------------------------------------------
    // BtnsBox
    // ---------------------------------------------------------------------------------------------------------
    private HBox btnsBox() {
        HBox hBox = new HBox();
        hBox.getChildren().add(this.btnLaunch());
        hBox.getStyleClass().add("btnsBox");
        return hBox;
    }

    // ---------------------------------------------------------------------------------------------------------
    // BtnLaunch
    // ---------------------------------------------------------------------------------------------------------
    private HBox btnLaunch() {
        HBox hBox = new HBox();
        hBox.getStyleClass().add("btnLaunch");
        Text label = new Text("Jouer");
        label.getStyleClass().add("btnLaunch-text");
        hBox.getChildren().add(label);
        hBox.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            this.risk.jouer(this.nbHumain, this.nbIA, Risk.CARTES.get(this.idCarte));
            event.consume();
        });
        hBox.setOnMouseEntered((EventHandler<javafx.event.Event>) me -> this.setCursor(Cursor.HAND));
        hBox.setOnMouseExited((EventHandler<javafx.event.Event>) me -> this.setCursor(Cursor.DEFAULT));
        return hBox;
    }
}
