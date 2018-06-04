package risk;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import risk.components.CarteUI;
import risk.components.CarteUIMonde;
import risk.logique.joueurs.Joueur;
import risk.logique.joueurs.JoueurHumain;
import risk.panes.AccueilPane;
import risk.panes.GamePane;
import risk.panes.MenuPane;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class Risk extends Application {

    public static Random r = new Random();
    // CONST
    public static final int WIDTH = 1220, HEIGHT = 900;
    public static final ArrayList<CarteUI> CARTES = new ArrayList<>();
    // STATE
    private Stage stage;
    private Scene scene;
    private ArrayList<Joueur> joueurs = new ArrayList<>();
    private CarteUI carteUI;

    // ---------------------------------------------------------------------------------------------------------
    // JAVAFX
    // ---------------------------------------------------------------------------------------------------------
    @Override
    public void start(Stage stage) {
        stage.setTitle("Risk");
        stage.setResizable(false);
        CARTES.add(new CarteUIMonde());
        this.stage = stage;
        this.gotoPane(new AccueilPane(this),"accueilStyle.css");
        this.gotoPane(new MenuPane(this), "menuStyle.css");
        this.jouer(6, 0, CARTES.get(0));
    }

    public static void main(String[] args) {
        launch(args);
    }
    // ---------------------------------------------------------------------------------------------------------

    private void initialisationJoueurs(int nbHumain, int nbIA) {
        for (int i = 0; i < nbHumain; i++) {
            this.joueurs.add(new JoueurHumain("J" + (i + 1), "Joueur " + (i + 1)));
        }
        // TODO addIA
    }

    public void jouer(int nbHumain, int nbIA, CarteUI carteUI) {
        this.carteUI = carteUI;
        this.initialisationJoueurs(nbHumain, nbIA);
        this.gotoPane(new GamePane(this), "gameStyle.css");
    }

    public void gotoPane(Pane pane, String cssFile) {
        Scene scene = new Scene(pane, WIDTH, HEIGHT);
        scene.getStylesheets().add("file:///" + new File("style/" + cssFile).getAbsolutePath().replace("\\", "/"));
        this.scene = scene;
        this.stage.setScene(scene);
        this.stage.show();
    }

    // ---------------------------------------------------------------------------------------------------------
    // GETTERS & SETTERS
    // ---------------------------------------------------------------------------------------------------------
    public Scene getScene() {
        return scene;
    }

    public int getNbJoueurs() {
        return this.joueurs.size();
    }

    public CarteUI getCarteUI() {
        return carteUI;
    }

    public ArrayList<Joueur> getJoueurs() {
        return joueurs;
    }
}
