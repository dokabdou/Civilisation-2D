package projet.approche.objet.ui.view;

import java.io.File;
import java.io.IOException;

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
import projet.approche.objet.domain.aggregates.Manager;
import projet.approche.objet.domain.valueObject.game.GameStarter;
import projet.approche.objet.domain.valueObject.grid.Coordinate;
import projet.approche.objet.domain.valueObject.grid.Grid;
import projet.approche.objet.domain.valueObject.grid.exceptions.NoBuildingHereException;
import projet.approche.objet.domain.valueObject.grid.exceptions.NotInGridException;

public class EditorView extends BorderPane {
	private final Stage stage;
	private Manager manager;
	private final PickerView pickerView;
	private final GridView gridView;

	public EditorView(Stage stage) throws NoBuildingHereException, NotInGridException {
		this.stage = stage;
		// Tile picker
		this.pickerView = new PickerView();
		this.setRight(pickerView);
		this.gridView = new GridView(manager.getGrid(), this.pickerView);

		MenuBar menuBar = new MenuBar();
		Menu fileMenu = new Menu("File");

		MenuItem easyGame = new MenuItem("Load EASY game");
		MenuItem mediumGame = new MenuItem("Load MEDIUM game");
		MenuItem hardGame = new MenuItem("Load HARD game");

		fileMenu.getItems().addAll(
				easyGame, mediumGame, hardGame);
		menuBar.getMenus().addAll(fileMenu);
		this.setTop(menuBar);

		easyGame.setOnAction(e -> {
			this.manager = new Manager(GameStarter.EASY, 5);
			try {
				updateGrid(manager.getGrid());
			} catch (NoBuildingHereException | NotInGridException e1) {
				e1.printStackTrace();
			}
			// GameEngine gameEngine = new GameEngine(manager, stage);
			// gameEngine.start();
		});

	}

	private void updateGrid(Grid grid) throws NoBuildingHereException, NotInGridException {
		if (grid != null) {
			Pane gridView = new GridView(grid, pickerView);
			this.setCenter(gridView);
			stage.sizeToScene();
		}
	}

}
