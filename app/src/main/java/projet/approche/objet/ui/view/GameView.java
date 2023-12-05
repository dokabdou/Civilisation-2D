package projet.approche.objet.ui.view;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import projet.approche.objet.application.App;

public class GameView extends BorderPane {
	private final PickerView pickerView;
	private final GridView gridView;

	public GameView(Stage stage, App app) {
		// Tile picker
		this.pickerView = new PickerView(app);
		this.setRight(pickerView);
		// Grid
		this.gridView = new GridView(app, pickerView);
		this.setCenter(gridView);
	}
}
