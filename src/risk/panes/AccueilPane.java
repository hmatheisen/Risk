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

public class AccueilPane extends StackPane {

    private Risk risk;
    // CONST
//    private static final int NBSELECTIONS = 3;
    // STATE GRAPHIC
//    private int itemSelected = 1;
//    private int nbHumain = 2, nbIA = 0, idCarte = 0;
    // ELEMENTS COULD BE REFRESH
//    private ArrayList<Text> labelsSelections = new ArrayList<>();
//    private ArrayList<Text> valuesSelections = new ArrayList<>();

    public AccueilPane(Risk risk) {
        this.getChildren().addAll(this.backgroundPane());
        this.risk = risk;
//        this.configKeys();
        this.afficher();
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
        return pane;
    }

    // ---------------------------------------------------------------------------------------------------------
    // Affichage
    // ---------------------------------------------------------------------------------------------------------
    private void afficher() {
        VBox AcceuilBox = new VBox(150);
        AcceuilBox.getChildren().addAll(this.btnBox(), this.footerBox());
        AcceuilBox.getStyleClass().add("accueilBox");

//        AcceuilBox.setBorder(new Border(new BorderStroke(Color.BLACK, null, null, null)));
        this.getChildren().addAll(AcceuilBox);
    }
    // ---------------------------------------------------------------------------------------------------------
    // BtnsBox
    // ---------------------------------------------------------------------------------------------------------
    private HBox btnBox() {
        HBox hBox = new HBox();
        hBox.getChildren().add(this.btnLaunch());
        hBox.getStyleClass().add("btnBox");
        hBox.getStyleClass().add("btnsBox2");

        return hBox;
    }

    // ---------------------------------------------------------------------------------------------------------
    // BtnLaunch
    // ---------------------------------------------------------------------------------------------------------
    private VBox btnLaunch() {
        VBox hBox = new VBox();
        hBox.getStyleClass().add("btnLaunch");
        Text label = new Text("[ CLICK to play ]");
        label.getStyleClass().add("btnLaunch-text");
        hBox.getChildren().add(label);
        hBox.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            risk.gotoPane(new MenuPane(risk),"menuStyle.css");
            event.consume();
        });
        hBox.setOnMouseEntered((EventHandler<javafx.event.Event>) me -> this.setCursor(Cursor.HAND));
        hBox.setOnMouseExited((EventHandler<javafx.event.Event>) me -> this.setCursor(Cursor.DEFAULT));
        return hBox;
    }
    // ---------------------------------------------------------------------------------------------------------
    // FooterBox
    // ---------------------------------------------------------------------------------------------------------
    private HBox footerBox() {
        HBox box = new HBox();
        Text titre = new Text("Henry MATHEISEN - William TROJANOWSKI - Cyprien LAMBERT");
        titre.getStyleClass().add("footerBox-text");
        box.getChildren().addAll(titre);
        box.getStyleClass().add("footerBox");
        return box;
    }

}

