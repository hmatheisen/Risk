package risk.components;

import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import risk.panes.GamePane;

public class InfosActionAttaqueUI extends HBox {

    private GamePane game;
    private Text txt = new Text();

    public InfosActionAttaqueUI(GamePane game) {
        this.game = game;
        this.getChildren().add(txt);
    }

    public void refresh() {
        if (this.game.getTerritoireUIActuel() != null && this.game.getTerritoireUIActuel() != this.game.getTerritoireUIClickedFirst()) {
            this.txt.setText(this.game.getTerritoireUIClickedFirst().getNom() + " -> " + this.game.getTerritoireUIActuel().getNom());
        } else if (this.game.getTerritoireUIClickedSecond() != null) {
            this.txt.setText(this.game.getTerritoireUIClickedFirst().getNom() + " -> " + this.game.getTerritoireUIClickedSecond().getNom());
        }
    }
}
