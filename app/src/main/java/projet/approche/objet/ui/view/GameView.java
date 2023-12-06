package projet.approche.objet.ui.view;

import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import projet.approche.objet.application.App;
import projet.approche.objet.ui.view.infoBar.InfoBar;

public class GameView extends BorderPane {
	private final PickerView pickerView;
	private final GridView gridView;
	private final InfoBar infoBar;

	public GameView(Stage stage, App app) {
		// Tile picker
		this.pickerView = new PickerView(app);
		this.setTop(pickerView);
		// Info bar
		this.infoBar = new InfoBar(app);
		this.infoBar.update();
		this.setRight(infoBar);
		// Grid
		this.gridView = new GridView(app, pickerView);
		this.setCenter(gridView);
	}

	public int getSize() {
		return this.getSize();
	}
}
