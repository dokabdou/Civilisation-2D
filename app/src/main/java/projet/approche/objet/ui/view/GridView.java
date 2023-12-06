package projet.approche.objet.ui.view;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.layout.BorderPane;
import projet.approche.objet.application.App;
import projet.approche.objet.domain.valueObject.game.exceptions.GameEnded;
import projet.approche.objet.domain.valueObject.game.exceptions.GameNotStarted;
import projet.approche.objet.domain.valueObject.grid.exceptions.NoBuildingHereException;
import projet.approche.objet.domain.valueObject.grid.exceptions.NotFreeException;
import projet.approche.objet.domain.valueObject.grid.exceptions.NotInGridException;
import projet.approche.objet.ui.view.imageResource.BuildingImageResource;

public class GridView extends BorderPane {
	private final PickerView pickerView;
	private final int gridSize;
	private final App app;

	public GridView(App app, PickerView pickerView) {
		this.pickerView = pickerView;
		this.app = app;
		this.gridSize = app.getGridSize();
		setPrefSize(gridSize * BuildingImageResource.size,
				gridSize * BuildingImageResource.size);
		for (int i = 0; i < this.gridSize; i++) {
			for (int j = 0; j < this.gridSize; j++) {
				createTile(i, j);
			}
		}
	}

	private void createTile(int i, int j) {
		int layoutX = i * BuildingImageResource.size;
		int layoutY = j * BuildingImageResource.size;
		String kind;
		try {
			kind = app.getBuildingType(i, j);
		} catch (NoBuildingHereException e) {
			kind = "Empty";
		} catch (NotInGridException e) {
			throw new RuntimeException(e); // should not happen
		}
		Tile tile = new Tile(BuildingImageResource.get(kind), layoutX, layoutY);
		getChildren().add(tile);
		tile.setOnMouseClicked(e -> {
			update(tile, i, j);
		});
		tile.setOnMouseEntered(e -> {
			if (e.isShiftDown()) {
				update(tile, i, j);
			}
		});
		ColorAdjust colorAdjust = new ColorAdjust();
		colorAdjust.setBrightness(-0.2);
		tile.setOnMouseEntered(e -> {
			if (e.isShiftDown()) {
				update(tile, i, j);
			}
			tile.setEffect(colorAdjust);
		});
		tile.setOnMouseExited(e -> {
			tile.setEffect(null);
		});
	}

	private void update(Tile tile, int i, int j) {
		String kind;
		try {
			kind = app.getBuildingType(i, j);
		} catch (NoBuildingHereException e) {
			kind = null;
		} catch (NotInGridException e) {
			throw new RuntimeException(e); // should not happen
		}
		if (pickerView.getSelected() != null && pickerView.getSelected() != kind) {
			getChildren().remove(tile);
			try {
				app.buildBuilding(pickerView.getSelected(), i, j);
			} catch (GameNotStarted e) {
				// Open a window saying that the game is not started
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Game not started");
				alert.setHeaderText("Game not started...");
				alert.setContentText("You must start the game before building.");
				alert.showAndWait().ifPresent(rs -> {
				});
			} catch (GameEnded e) {
				// Open a window saying that the game is ended
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Game ended");
				alert.setHeaderText("Game ended...");
				alert.setContentText("You must start a new game.");
				alert.showAndWait().ifPresent(rs -> {
				});
			} catch (NotInGridException e) {
				throw new RuntimeException(e); // should not happen
			} catch (NotFreeException e) {
				// Open a window saying that the tile is not free
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Tile not free");
				alert.setHeaderText("Tile not free...");
				alert.setContentText("You must select a free tile.");
				alert.showAndWait().ifPresent(rs -> {
				});
			}
			createTile(i, j);
		}
	}

	public int getSize() {
		return this.getSize();
	}
}