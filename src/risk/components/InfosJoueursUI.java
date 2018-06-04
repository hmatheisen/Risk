package risk.components;

import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import risk.panes.GamePane;

import java.util.ArrayList;

public class InfosJoueursUI {

    private GamePane game;
    // ELEMENTS COULD BE REFRESH
    private HBox joueursBox = new HBox(10);
    private ArrayList<Text> texts = new ArrayList<>();
    private ArrayList<HBox> textBoxes = new ArrayList<>();

    public InfosJoueursUI(GamePane game) {
        this.game = game;
        this.game.getRisk().getJoueurs().forEach(joueur -> {
            Text txt = new Text(joueur.getNom());
            txt.getStyleClass().add("infosJoueur-txt");
            HBox hBox = new HBox();
            hBox.getChildren().add(txt);
            hBox.getStyleClass().add("infosJoueur");
            this.texts.add(txt);
            this.textBoxes.add(hBox);
            this.joueursBox.getChildren().add(hBox);
        });
        this.joueursBox.getStyleClass().add("infosJoueurs");
        this.refreshJoueurs();
    }

    // ---------------------------------------------------------------------------------------------------------
    // Getters & Setters
    // ---------------------------------------------------------------------------------------------------------
    public HBox getJoueursBox() {
        return joueursBox;
    }

    public void refreshJoueurs() {
        this.textBoxes.forEach(textBoxes -> textBoxes.getStyleClass().remove("actuel"));
        this.textBoxes.get(this.game.getActualPlayer()).getStyleClass().add("actuel");
        for (int i = 0; i < this.texts.size(); i++) {
            this.texts.get(i).setText(this.game.getRisk().getJoueurs().get(i).getNom());
            this.texts.get(i).getStyleClass().remove("actuel");
        }
        this.texts.get(this.game.getActualPlayer()).getStyleClass().add("actuel");
        this.texts.get(this.game.getActualPlayer()).setText(this.game.getRisk().getJoueurs().get(this.game.getActualPlayer()).getNomLong());
    }
}
