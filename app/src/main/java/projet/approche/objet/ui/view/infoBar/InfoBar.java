package projet.approche.objet.ui.view.infoBar;

import javafx.scene.layout.BorderPane;
import projet.approche.objet.application.App;

public class InfoBar extends BorderPane {

	private final Inventory inventory;
	private final Button button;

	public InfoBar(App app) {
		this.inventory = new Inventory(app);
		this.button = new Button(app);
		this.setRight(inventory);
		this.setLeft(button);
	}

	public void update() {
		this.inventory.update();
		this.button.update();
	}
}
