package projet.approche.objet.ui.view;

import javafx.geometry.Insets;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import projet.approche.objet.application.App;

public class PickerView extends HBox {
	private final ToggleGroup group = new ToggleGroup();

	public PickerView(App app) {
		this.setSpacing(15);
		this.setPadding(new Insets(15));

		for (String type : app.getBuildingsTypes()) {
			ToggleButton btn = new ToggleButton();
			btn.setToggleGroup(group);
			btn.setUserData(type);
			Image image = BuildingImageResource.get(type);
			ImageView imageView = new ImageView(image);
			btn.setGraphic(imageView);
			getChildren().add(btn);
		}
	}

	public String getSelected() {
		Toggle toggle = group.getSelectedToggle();
		if (toggle == null)
			return null;
		return (String) toggle.getUserData();
	}
}
