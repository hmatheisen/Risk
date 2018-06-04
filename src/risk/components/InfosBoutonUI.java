package risk.components;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import risk.panes.GamePane;

public class InfosBoutonUI {

    private GamePane game;
    // CONST
    private static final String APPLY_REINFORCEMENTS = "Placez vos renforts";
    private static final String END_TURN = "Fin de tour";
    // ELEMENTS COULD BE REFRESH
    private Button btn = new Button(END_TURN);

    public InfosBoutonUI(GamePane game) {
        this.game = game;
        this.btn.getStyleClass().add("infosBouton");
        this.btn.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            this.game.nextPlayer();
            event.consume();
        });
        this.btn.setOnMouseEntered((EventHandler<Event>) me -> this.btn.setCursor(javafx.scene.Cursor.HAND));
        this.btn.setOnMouseExited((EventHandler<javafx.event.Event>) me -> this.btn.setCursor(Cursor.DEFAULT));
        this.refreshBtn();
    }

    public void refreshBtn() {
        if (this.game.getRisk().getJoueurs().get(this.game.getActualPlayer()).getRenforts() == 0) {
            this.btn.setOpacity(1);
            this.btn.setText(END_TURN);
        } else {
            this.btn.setOpacity(0.6);
            this.btn.setText(APPLY_REINFORCEMENTS);
        }
    }

    // ---------------------------------------------------------------------------------------------------------
    // Getters & Setters
    // ---------------------------------------------------------------------------------------------------------
    public Button getBtn() {
        return btn;
    }
}
