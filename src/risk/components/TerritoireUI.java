package risk.components;

import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import risk.logique.carte.Territoire;
import risk.logique.joueurs.Joueur;
import risk.logique.units.Army;
import risk.panes.GamePane;

import java.io.File;
import java.util.ArrayList;

public class TerritoireUI {

    private String id, nom;
    private Territoire territoire;
    private int x, y;
    private ImageView imageView;
    private GamePane game;
    private ArrayList<TerritoireUI> territoiresAdjacents = new ArrayList<>();

    public TerritoireUI(GamePane game, String id, String nom, int x, int y) {
        this.game = game;
        this.nom = nom;
        this.id = id;
        this.territoire = new Territoire(id);
        this.x = x;
        this.y = y;
        this.imageView = new ImageView(new Image("file:///" + new File("img/territoires/" + id + ".png").getAbsolutePath().replace("\\", "/")));
        this.imageView.setX(x);
        this.imageView.setY(y);
        this.imageView.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
            this.imageView.setCursor(Cursor.HAND);
            this.game.setTerritoireUIActuel(this);
            this.imageView.setOpacity(0.5);
            this.game.getSource().refreshInfosSource();
            this.game.getCible().refreshInfosCible();
            this.game.getGameBarMiddle().refreshGameBarMiddle();
            event.consume();
        });
        this.imageView.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
            this.imageView.setCursor(Cursor.DEFAULT);
            this.game.setTerritoireUIActuel(null);
            this.imageView.setOpacity(1);
            this.game.getSource().refreshInfosSource();
            this.game.getCible().refreshInfosCible();
            this.game.getGameBarMiddle().refreshGameBarMiddle();
            event.consume();
        });
        this.imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            this.game.setTerritoireUIClicked(this);
            this.game.getSource().refreshInfosSource();
            this.game.getCible().refreshInfosCible();
            this.game.getGameBarMiddle().refreshGameBarMiddle();
            event.consume();
        });
    }

    public void addTerritoireAdjacent(TerritoireUI territoireUI) {
        this.territoiresAdjacents.add(territoireUI);
    }


    // ---------------------------------------------------------------------------------------------------------
    // Getters & Setters
    // ---------------------------------------------------------------------------------------------------------
    public ImageView getImageView() {
        return imageView;
    }

    public String getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getRegion() {
        return this.territoire.getRegion().getNom();
    }

    public void setJoueur(Joueur joueur) {
        this.territoire.setJoueur(joueur);
    }

    public Joueur getJoueur() {
        return this.territoire.getJoueur();
    }

    public Army getArmy() {
        return this.territoire.getArmy();
    }

    public boolean isEmpty() {
        return this.getArmy().getUnits().size() == 0;
    }

    public Territoire getTerritoire() {
        return territoire;
    }
}
