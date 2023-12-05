package projet.approche.objet.ui.view;

import javafx.scene.effect.ColorAdjust;
import javafx.scene.layout.BorderPane;
import projet.approche.objet.domain.valueObject.grid.Coordinate;
import projet.approche.objet.domain.valueObject.grid.Grid;
import projet.approche.objet.domain.valueObject.grid.exceptions.NoBuildingHereException;
import projet.approche.objet.domain.valueObject.grid.exceptions.NotFreeException;
import projet.approche.objet.domain.valueObject.grid.exceptions.NotInGridException;

public class GridView extends BorderPane {
	private final Grid grid;
	private final PickerView pickerView;

	public GridView(Grid grid, PickerView pickerView) throws NoBuildingHereException, NotInGridException {
		this.grid = grid;
		this.pickerView = pickerView;
		setPrefSize(grid.getSize() * ImageResource.size,
				grid.getSize() * ImageResource.size);
		for (int i = 0; i < this.grid.getSize(); i++) {
			for (int j = 0; j < this.grid.getSize(); j++) {
				createTile(i, j);
			}
		}
	}

	private void createTile(int i, int j) throws NoBuildingHereException, NotInGridException {
		int layoutX = i * ImageResource.size;
		int layoutY = j * ImageResource.size;
		Tile tile = new Tile(ImageResource.get(this.grid.getBuilding(new Coordinate(i, j)).type), layoutX, layoutY);
		getChildren().add(tile);
		tile.setOnMouseClicked(e -> {
			try {
				update(tile, i, j);
			} catch (NoBuildingHereException | NotInGridException | NotFreeException e1) {
				e1.printStackTrace();
			}
		});
		tile.setOnMouseEntered(e -> {
			if (e.isShiftDown()) {
				try {
					update(tile, i, j);
				} catch (NoBuildingHereException | NotInGridException | NotFreeException e1) {
					e1.printStackTrace();
				}
			}
		});
		ColorAdjust colorAdjust = new ColorAdjust();
		colorAdjust.setBrightness(-0.2);
		tile.setOnMouseEntered(e -> {
			if (e.isShiftDown()) {
				try {
					update(tile, i, j);
				} catch (NoBuildingHereException | NotInGridException | NotFreeException e1) {
					e1.printStackTrace();
				}
			}
			tile.setEffect(colorAdjust);
		});
		tile.setOnMouseExited(e -> {
			tile.setEffect(null);
		});
	}

	private void update(Tile tile, int i, int j) throws NoBuildingHereException, NotInGridException, NotFreeException {
		if (pickerView.getSelected() != null && pickerView.getSelected() != grid.getBuilding(new Coordinate(i, j))) {
			getChildren().remove(tile);
			grid.setBuilding(pickerView.getSelected(), new Coordinate(i, j));
			createTile(i, j);
		}
	}
}