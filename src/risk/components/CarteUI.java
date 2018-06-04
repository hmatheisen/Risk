package risk.components;

import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import risk.logique.carte.Region;
import risk.panes.GamePane;

import java.util.ArrayList;

abstract public class CarteUI {

    protected String label;
    protected GamePane game;
    protected ArrayList<TerritoireUI> territoiresUI = new ArrayList<>();
    protected ArrayList<Line> lines = new ArrayList<>();
    protected ArrayList<Text> texts = new ArrayList<>();


    protected CarteUI(String label) {
        this.label = label;
    }

    public abstract void initialisationCarte(GamePane game);

    protected Line newLine(double startX, double startY, double endX, double endY) {
        Line line = new Line(startX, startY, endX, endY);
        line.getStrokeDashArray().addAll(2d);
        return line;
    }

    protected Text newText(double X, double Y, String name) {
        Text nameRegion = new Text(X, Y, name);
        nameRegion.getStrokeDashArray().addAll(2d);
        return nameRegion;
    }

    // ---------------------------------------------------------------------------------------------------------
    // Getters & Setters
    // ---------------------------------------------------------------------------------------------------------
    public String getLabel() {
        return label;
    }

    public void setGame(GamePane game) {
        this.game = game;
    }

    public ArrayList<TerritoireUI> getTerritoiresUI() {
        return territoiresUI;
    }

    public ArrayList<Line> getLines() {
        return lines;
    }

    public ArrayList<Text> getText() {
        return texts;
    }

}
