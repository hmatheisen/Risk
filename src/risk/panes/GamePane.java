package risk.panes;

import javafx.application.Platform;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import risk.Risk;
import risk.components.*;
import risk.logique.units.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class GamePane extends StackPane {

    private Risk risk;
    private int minCostUnits = 100;
    private static ArrayList<UnitType> unitTypes = new ArrayList<>();
    private TerritoireUI territoireUIActuel = null;
    private TerritoireUI territoireUIClickedFirst = null;
    private TerritoireUI territoireUIClickedSecond = null;
    // STATE GRAPHIC
    private int nbTour = 1;
    private int actualPlayer = 0;
    // ELEMENTS COULD BE REFRESH
    private Text label = new Text();
    private Text nbTourText = new Text();
    private TextArea terminal = new TextArea();
    private InfosSourceUI source;
    private InfosCibleUI cible;
    private InfosJoueursUI joueurs;
    private InfosBoutonUI btn;
    private GameBarMiddleUI gameBarMiddle;

    public GamePane(Risk risk) {
        this.risk = risk;
        // initilisation jeu
        unitTypes.add(new UnitType("Soldat", 1, 1, 4, 9, 3, 2, "img/icons/soldat.png"));
        unitTypes.add(new UnitType("Cavalier", 3, 3, 2, 7, 1, 3, "img/icons/cavalier.png"));
        unitTypes.add(new UnitType("Canon", 7, 2, 1, 6, 2, 1, "img/icons/canon.png"));
        initialisationTypes();
        // initialisation UI
        this.configKeys();
        this.risk.getCarteUI().initialisationCarte(this);
        this.source = new InfosSourceUI(this);
        this.cible = new InfosCibleUI(this);
        this.joueurs = new InfosJoueursUI(this);
        this.btn = new InfosBoutonUI(this);
        this.gameBarMiddle = new GameBarMiddleUI(this);
        // initialisation partie
        this.terminal.setText("[*]   Initialisation de la partie");
        this.initialisationJeu();
        this.joueurs.refreshJoueurs();
        this.btn.refreshBtn();
        this.source.refreshInfosSource();
        this.cible.refreshInfosCible();
        this.addTerminalInfoGame("Début de partie");
        this.addTerminalPlayerBegin(this.actualPlayer);
        this.afficher();
    }

    private void configKeys() {
        Platform.runLater(() -> this.risk.getScene().setOnKeyPressed(ke -> {
        }));
    }

    private void initialisationTypes() {
        for (UnitType unitType : unitTypes) {
            if (unitType.getCost() < this.minCostUnits) {
                this.minCostUnits = unitType.getCost();
            }
        }
    }

    private void initialisationJeu() {
        Collections.shuffle(this.risk.getCarteUI().getTerritoiresUI());
        Collections.shuffle(this.risk.getJoueurs()); // si chacun garde le même numéro d'une partie sur l'autre, cela permet de garantir une équité
        // initialisation logique de chaque territoire
        int i = 0;
        for (TerritoireUI territoireUI : this.risk.getCarteUI().getTerritoiresUI()) {
            territoireUI.setJoueur(this.risk.getJoueurs().get(i));
            territoireUI.getArmy().sortByCostDecreasing();
            if (i == this.risk.getNbJoueurs() - 1) {
                i = 0;
            } else {
                i++;
            }
//            for (int j = 0; j < 20; j++) {
//                territoireUI.getArmy().addUnit(new Soldat());
//            }
//            for (int j = 0; j < 15; j++) {
//                territoireUI.getArmy().addUnit(new Cavalier());
//            }
//            for (int j = 0; j < 10; j++) {
//                territoireUI.getArmy().addUnit(new Canon());
//            }
        }
        // renforts de début de partie
        switch (this.risk.getNbJoueurs()) {
            case 2:
                this.risk.getJoueurs().forEach(joueur -> joueur.setRenforts(40));
                break;
            case 3:
                this.risk.getJoueurs().forEach(joueur -> joueur.setRenforts(35));
                break;
            case 4:
                this.risk.getJoueurs().forEach(joueur -> joueur.setRenforts(30));
                break;
            case 5:
                this.risk.getJoueurs().forEach(joueur -> joueur.setRenforts(25)); // TODO
                break;
            case 6:
                this.risk.getJoueurs().forEach(joueur -> joueur.setRenforts(20));
                break;
        }
        this.risk.getJoueurs().forEach(joueur -> {
            int nb = 0;
            for (TerritoireUI territoireUI : this.risk.getCarteUI().getTerritoiresUI()) {
                if (territoireUI.getJoueur() == joueur) {
                    nb++;
                }
            }
            addTerminalInfo(joueur.getNom() + " a reçu " + joueur.getRenforts() + " renforts et " + nb + " territoires");
        });
    }

    private void afficher() {
        VBox gameBox = new VBox();
        gameBox.getChildren().addAll(this.worldBox(), this.gameBarBox());
        gameBox.getStyleClass().add("gameBox");
        this.getChildren().addAll(gameBox);
        this.terminal.setFocusTraversable(false);
    }

    // ---------------------------------------------------------------------------------------------------------
    // Game events
    // ---------------------------------------------------------------------------------------------------------
    private void nextTurn() {
        this.nbTour++;
        this.refreshNbTourText();
    }

    public void nextPlayer() {
        if (this.risk.getNbJoueurs() - 1 == this.actualPlayer) {
            this.actualPlayer = 0;
            this.nextTurn();
        } else {
            this.actualPlayer++;
        }
        this.btn.refreshBtn();
        this.joueurs.refreshJoueurs();
        this.addTerminalPlayerBegin(this.actualPlayer);
        this.territoireUIClickedFirst = null;
        this.territoireUIClickedSecond = null;
        this.source.refreshInfosSource();
        this.cible.refreshInfosCible();
        this.getGameBarMiddle().refreshGameBarMiddle();
    }

    // ---------------------------------------------------------------------------------------------------------
    // WorldBox
    // ---------------------------------------------------------------------------------------------------------
    private Pane worldBox() {
        Pane worldBox = new Pane();
        worldBox.getStyleClass().add("worldBox");
        worldBox.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> { // reset click
            if (this.territoireUIClickedSecond != null) {
                this.territoireUIClickedSecond = null;
            } else if (this.territoireUIClickedFirst != null) {
                this.territoireUIClickedFirst = null;
            }
            this.source.refreshInfosSource();
            this.cible.refreshInfosCible();
            this.getGameBarMiddle().refreshGameBarMiddle();
            event.consume();
        });
        // add territoires
        this.risk.getCarteUI().getTerritoiresUI().forEach(territoireUI -> worldBox.getChildren().add(territoireUI.getImageView()));
        // add lines
        this.risk.getCarteUI().getLines().forEach(line -> worldBox.getChildren().add(line));
        // add text
        this.risk.getCarteUI().getText().forEach(text -> worldBox.getChildren().add(text));
        // nbTour
        this.nbTourText.setX(48);
        this.nbTourText.setY(31);
        this.nbTourText.getStyleClass().add("worldBox-nbTour");
        ImageView logo = new ImageView(new Image("file:///" + new File("img/icons/tour.png").getAbsolutePath().replace("\\", "/")));
        logo.setX(20);
        logo.setY(13);
        logo.setFitWidth(23);
        logo.setPreserveRatio(true);
        refreshNbTourText();
        worldBox.getChildren().addAll(this.nbTourText, logo);
        // return
        return worldBox;
    }

    // ---------------------------------------------------------------------------------------------------------
    // NbTourText
    // ---------------------------------------------------------------------------------------------------------
    private void refreshNbTourText() {
        this.nbTourText.setText("Tour " + String.valueOf(this.nbTour));
    }

    // ---------------------------------------------------------------------------------------------------------
    // GameBar
    // ---------------------------------------------------------------------------------------------------------
    private HBox gameBarBox() {
        HBox gameBarBox = new HBox();
        gameBarBox.getStyleClass().add("gameBarBox");
        this.terminal.setEditable(false);
        this.terminal.setMaxWidth(470);
        this.terminal.getStyleClass().add("gameBarBox-terminal");
        TextArea terminal = this.terminal;
        this.terminal.textProperty().addListener((observableValue, s, t1) -> terminal.setScrollTop(Double.MAX_VALUE)); // automatic scroll
        // gameBarVBox
        VBox gameBarVBox = new VBox();
        gameBarVBox.getStyleClass().add("gameBarVBox");
        VBox gameBarVBoxTop = new VBox(5);
        HBox gameBarHBoxTop = new HBox(5);
        gameBarHBoxTop.getChildren().addAll(this.getSource().getInfosBox(), this.getCible().getInfosBox());
        gameBarVBoxTop.getStyleClass().add("gameBarVBoxTop");
        HBox gameBarVBoxBottom = new HBox();
        gameBarVBoxBottom.getStyleClass().add("gameBarVBoxBottom");
        // subcomponents
        gameBarVBoxTop.getChildren().addAll(gameBarHBoxTop, this.gameBarMiddle.getBox());
        gameBarVBoxBottom.getChildren().addAll(this.joueurs.getJoueursBox(), this.btn.getBtn());
        gameBarVBox.getChildren().addAll(gameBarVBoxTop, gameBarVBoxBottom);
        gameBarBox.getChildren().addAll(this.terminal, gameBarVBox);
        return gameBarBox;
    }

    private void addTerminal(String toAdd) {
        this.terminal.appendText("\n" + toAdd);
    }

    public void addTerminalInfo(String toAdd) {
        addTerminal("       " + toAdd);
    }

    public void addTerminalPlayerBegin(int id) {
        addTerminal("[" + Integer.valueOf(this.risk.getJoueurs().get(id).getNom().substring(1)) + "]  À vous de jouer ! (tour " + this.nbTour + ")");
    }

    public void addTerminalInfoGame(String titre) {
        addTerminal("[*]  " + titre);
    }
    // ---------------------------------------------------------------------------------------------------------
    // Getters & Setters
    // ---------------------------------------------------------------------------------------------------------

    public void setTerritoireUIActuel(TerritoireUI territoireUIActuel) {
        this.territoireUIActuel = territoireUIActuel;
    }

    public Risk getRisk() {
        return risk;
    }

    public int getActualPlayer() {
        return actualPlayer;
    }

    public void setTerritoireUIClicked(TerritoireUI territoireUI) {
        if (this.territoireUIClickedFirst == null) {
            if (this.risk.getJoueurs().get(this.actualPlayer) == territoireUI.getJoueur()) {
                this.territoireUIClickedFirst = territoireUI;
            }
        } else if (this.territoireUIClickedFirst != territoireUI) {
            if (this.risk.getJoueurs().get(this.actualPlayer).getRenforts() == 0) {
                this.territoireUIClickedSecond = territoireUI;
            } else {
                if (this.risk.getJoueurs().get(this.actualPlayer) == territoireUI.getJoueur()) {
                    this.territoireUIClickedFirst = territoireUI;
                }
            }
        }
    }

    public TerritoireUI getTerritoireUIActuel() {
        return territoireUIActuel;
    }

    public TerritoireUI getTerritoireUIClickedFirst() {
        return territoireUIClickedFirst;
    }

    public TerritoireUI getTerritoireUIClickedSecond() {
        return territoireUIClickedSecond;
    }

    public InfosSourceUI getSource() {
        return source;
    }

    public InfosCibleUI getCible() {
        return cible;
    }

    public GameBarMiddleUI getGameBarMiddle() {
        return gameBarMiddle;
    }

    public static UnitType getUnitTypeByName(String name) {
        for (UnitType unitType : unitTypes) {
            if (unitType.getName().equals(name)) {
                return unitType;
            }
        }
        return null;
    }

    public static ArrayList<UnitType> getUnitTypes() {
        return unitTypes;
    }

    public InfosBoutonUI getBtn() {
        return btn;
    }

    public int getMinCostUnits() {
        return minCostUnits;
    }

    public int getNbTour() {
        return nbTour;
    }
}
