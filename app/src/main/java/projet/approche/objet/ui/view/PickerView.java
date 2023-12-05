package projet.approche.objet.ui.view;

import javafx.geometry.Insets;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import projet.approche.objet.domain.entities.building.Building;
import projet.approche.objet.domain.valueObject.building.BuildingType;

public class PickerView extends VBox {
	private final ToggleGroup group = new ToggleGroup();

	public PickerView() {
		this.setSpacing(15);
		this.setPadding(new Insets(15));

		for (BuildingType type : BuildingType.values()) {
			ToggleButton btn = new ToggleButton();
			btn.setToggleGroup(group);
			btn.setUserData(type);
			btn.setGraphic(new ImageView(ImageResource.get(type)));
			getChildren().add(btn);
		}
	}

	public Building getSelected() {
		Toggle toggle = group.getSelectedToggle();
		if (toggle == null)
			return null;
		return (Building) toggle.getUserData();
	}
}
